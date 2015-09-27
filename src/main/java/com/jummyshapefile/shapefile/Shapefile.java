/*
 * Copyright 2015 ANTONIO CARLON
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jummyshapefile.shapefile;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jummyshapefile.dbf.DBFFile;
import com.jummyshapefile.dbf.model.DBFField;
import com.jummyshapefile.dbf.model.DBFFieldDescriptor;
import com.jummyshapefile.dbf.model.DBFHeader;
import com.jummyshapefile.dbf.model.DBFRecord;
import com.jummyshapefile.prj.PRJFile;
import com.jummyshapefile.shapefile.model.Entity;
import com.jummyshapefile.shapefile.model.Geometry;
import com.jummyshapefile.shp.SHPFile;
import com.jummyshapefile.shp.model.SHPRecord;
import com.jummyshapefile.shx.SHXFile;
import com.jummyshapefile.shx.model.SHXRecord;
import com.jummyshapefile.utils.GeometryUtils;

/**
 * Facade class representing a shapefile.
 *
 * @see SHPFile
 * @see SHXFile
 * @see DBFFile
 * @see PRJFile
 */
public class Shapefile implements Closeable {
	private final SHPFile shp;
	private final SHXFile shx;
	private final DBFFile dbf;
	private final PRJFile prj;

	/**
	 * Constructor for the shapefile object. Expects several InputStream
	 * objects, each one for a given file resource.
	 *
	 * @param shpIS
	 *            InputStream object for the SHP file (mandatory)
	 * @param shxIS
	 *            InputStream object for the SHX file (mandatory)
	 * @param dbfIS
	 *            InputStream object for the DBF file (mandatory)
	 * @param prjIS
	 *            InputStream object for the PRJ file (optional, may be null)
	 * @throws IOException
	 */
	public Shapefile(final FileInputStream shpIS, final FileInputStream shxIS,
			final FileInputStream dbfIS, final FileInputStream prjIS)
					throws IOException {
		if (shpIS == null) {
			throw new IllegalArgumentException("The SHP file is mandatory");
		}
		if (shxIS == null) {
			throw new IllegalArgumentException("The SHX file is mandatory");
		}
		if (dbfIS == null) {
			throw new IllegalArgumentException("The DBF file is mandatory");
		}

		shp = new SHPFile();
		shp.open(shpIS);

		shx = new SHXFile();
		shx.open(shxIS);

		dbf = new DBFFile();
		dbf.open(dbfIS);

		if (prjIS != null) {
			prj = new PRJFile();
			prj.open(prjIS);
		} else {
			prj = null;
		}
	}

	/**
	 * Closes the shapefile releasing all the resources.
	 */
	public void close() throws IOException {
		if (shp != null) {
			shp.close();
		}
		if (shx != null) {
			shx.close();
		}
		if (dbf != null) {
			dbf.close();
		}
		if (prj != null) {
			prj.close();
		}
	}

	/**
	 * Returns the projection of the shapefile.
	 *
	 * @return the projection of the shapefile
	 */
	public String getProjection() {
		String output = null;

		if (prj != null) {
			output = prj.getProjectionWKT();
		}

		return output;
	}

	/**
	 * Returns the number of entities stored in the shapefile.
	 *
	 * @return the number of entities stored in the shapefile
	 */
	public int getNumEntities() {
		int output = -1;

		final DBFHeader dbfHeader = dbf.getHeader();
		output = dbfHeader.getRecordCount();

		return output;
	}

	/**
	 * Returns the field descriptors of the entity data.
	 *
	 * @return the field descriptors of the entity data
	 *
	 * @see DBFFieldDescriptor
	 */
	public List<DBFFieldDescriptor> getDataFieldDescriptors() {
		List<DBFFieldDescriptor> output;

		final DBFHeader dbfHeader = dbf.getHeader();
		output = dbfHeader.getFields();

		return output;
	}

	/**
	 * Returns the entity that matches a given record number.
	 *
	 * @param recordNum
	 *            the number of the record (1-based)
	 * @param data
	 *            whether the record should (slower) or shouldn't (faster)
	 *            contain alphanumeric data.
	 *
	 * @return the entity that matches a given record number
	 *
	 * @throws IOException
	 *             if there is a problem querying the shapefile
	 *
	 * @see Entity
	 */
	public Entity getEntityByRecordNumber(final int recordNum,
			final boolean data) throws IOException {
		final Entity output = new Entity();
		output.setRecordNum(recordNum);

		final SHXRecord shxRecord = shx.getRecordAt(recordNum);
		if (shxRecord != null) {
			final int offset = shxRecord.getOffset();
			final int length = shxRecord.getLength();

			final SHPRecord shpRecord = shp.getRecord(offset, length);
			if (shpRecord != null) {
				output.setShapeType(shpRecord.getShapeType());
				output.setGeometry(shpRecord.getGeometry());
			}
		}

		if (data) {
			output.setData(getEntityDataByRecordNumber(recordNum));
		}

		return output;
	}

	/**
	 * Returns the List of entities whose MBR (Minimum Bounding Rectangle)
	 * intersects the given rectangle.
	 * <p>
	 * 'intersects' is defined as in the Dimensionally Extended
	 * nine-Intersection Model (DE-9IM).
	 * <p>
	 * Note: The rectangle must be in the same projection as the Shapefile.
	 *
	 * @param minX
	 *            the minX of the rectangle
	 * @param minY
	 *            the minY of the rectangle
	 * @param maxX
	 *            the maxX of the rectangle
	 * @param maxY
	 *            the maxY of the rectangle
	 * @param data
	 *            whether the record should (slower) or shouldn't (faster)
	 *            contain alphanumeric data.
	 *
	 * @return the List of entities whose MBR (Minimum Bounding Rectangle)
	 *         intersects the given rectangle
	 *
	 * @throws IOException
	 *             if there is a problem querying the shapefile
	 *
	 * @see Entity
	 */
	public List<Entity> getEntitiesInRectangle(final double minX,
			final double minY, final double maxX, final double maxY,
			final boolean data) throws IOException {
		final List<Entity> output = new ArrayList<Entity>();

		final int numEntities = getNumEntities();
		for (int i = 1; i <= numEntities; i++) {
			final Entity entity = getEntityByRecordNumber(i, data);
			if (entity != null && entity.getGeometry() != null) {
				final Geometry geometry = entity.getGeometry();
				if (GeometryUtils.intersectsRectangle(geometry.getMinX(),
						geometry.getMinY(), geometry.getMaxX(),
						geometry.getMaxY(), minX, minY, maxX, maxY)) {
					output.add(entity);
				}
			}
		}

		return output;
	}

	/**
	 * Returns a List of DBField representing the entity data that matches the
	 * given record number.
	 *
	 * @param recordNum
	 *            the number of the record (1-based)
	 *
	 * @return a List of DBField representing the entity data that matches the
	 *         given record number
	 *
	 * @throws IOException
	 *             if there is a problem querying the data
	 *
	 * @see DBFField
	 */
	public List<DBFField> getEntityDataByRecordNumber(final int recordNum)
			throws IOException {
		List<DBFField> output = new ArrayList<DBFField>();

		final DBFRecord dbfRecord = dbf.getRecordAt(recordNum);
		if (dbfRecord != null) {
			output = dbfRecord.getFields();
		}
		return output;
	}
}
