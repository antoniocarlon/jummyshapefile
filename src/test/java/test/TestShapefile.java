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

package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import junit.framework.TestCase;

import com.jummyshapefile.dbf.model.DBFField;
import com.jummyshapefile.dbf.model.DBFFieldDescriptor;
import com.jummyshapefile.shapefile.Shapefile;
import com.jummyshapefile.shapefile.model.Entity;
import com.jummyshapefile.utils.FileUtils;

public class TestShapefile extends TestCase {
	public void testProjection() throws IOException, URISyntaxException {
		final URL shpResource = getClass().getClassLoader().getResource(
				"Polygon.shp");
		final File shpFile = new File(shpResource.toURI());
		final FileInputStream shpIS = new FileInputStream(shpFile);
		final URL shxResource = getClass().getClassLoader().getResource(
				"Polygon.shx");
		final File shxFile = new File(shxResource.toURI());
		final FileInputStream shxIS = new FileInputStream(shxFile);
		final URL dbfResource = getClass().getClassLoader().getResource(
				"Polygon.dbf");
		final File dbfFile = new File(dbfResource.toURI());
		final FileInputStream dbfIS = new FileInputStream(dbfFile);
		final URL prjResource = getClass().getClassLoader().getResource(
				"Polygon.prj");
		final File prjFile = new File(prjResource.toURI());
		final FileInputStream prjIS = new FileInputStream(prjFile);

		Shapefile shapefile = null;
		try {
			shapefile = new Shapefile(shpIS, shxIS, dbfIS, prjIS);

			final String projection = shapefile.getProjection();

			assertNotNull(projection);
		} finally {
			shapefile.close();
			FileUtils.closeInputStream(prjIS);
			FileUtils.closeInputStream(dbfIS);
			FileUtils.closeInputStream(shxIS);
			FileUtils.closeInputStream(shpIS);
		}
	}

	public void testNumEntities() throws IOException, URISyntaxException {
		final URL shpResource = getClass().getClassLoader().getResource(
				"Polygon.shp");
		final File shpFile = new File(shpResource.toURI());
		final FileInputStream shpIS = new FileInputStream(shpFile);
		final URL shxResource = getClass().getClassLoader().getResource(
				"Polygon.shx");
		final File shxFile = new File(shxResource.toURI());
		final FileInputStream shxIS = new FileInputStream(shxFile);
		final URL dbfResource = getClass().getClassLoader().getResource(
				"Polygon.dbf");
		final File dbfFile = new File(dbfResource.toURI());
		final FileInputStream dbfIS = new FileInputStream(dbfFile);
		final URL prjResource = getClass().getClassLoader().getResource(
				"Polygon.prj");
		final File prjFile = new File(prjResource.toURI());
		final FileInputStream prjIS = new FileInputStream(prjFile);

		Shapefile shapefile = null;
		try {
			shapefile = new Shapefile(shpIS, shxIS, dbfIS, prjIS);

			final int numEntities = shapefile.getNumEntities();

			assertEquals(5, numEntities);
		} finally {
			shapefile.close();
			FileUtils.closeInputStream(prjIS);
			FileUtils.closeInputStream(dbfIS);
			FileUtils.closeInputStream(shxIS);
			FileUtils.closeInputStream(shpIS);
		}
	}

	public void testFields() throws IOException, URISyntaxException {
		final URL shpResource = getClass().getClassLoader().getResource(
				"Polygon.shp");
		final File shpFile = new File(shpResource.toURI());
		final FileInputStream shpIS = new FileInputStream(shpFile);
		final URL shxResource = getClass().getClassLoader().getResource(
				"Polygon.shx");
		final File shxFile = new File(shxResource.toURI());
		final FileInputStream shxIS = new FileInputStream(shxFile);
		final URL dbfResource = getClass().getClassLoader().getResource(
				"Polygon.dbf");
		final File dbfFile = new File(dbfResource.toURI());
		final FileInputStream dbfIS = new FileInputStream(dbfFile);
		final URL prjResource = getClass().getClassLoader().getResource(
				"Polygon.prj");
		final File prjFile = new File(prjResource.toURI());
		final FileInputStream prjIS = new FileInputStream(prjFile);

		Shapefile shapefile = null;
		try {
			shapefile = new Shapefile(shpIS, shxIS, dbfIS, prjIS);

			final List<DBFFieldDescriptor> fieldDescriptors = shapefile
					.getDataFieldDescriptors();
			final DBFFieldDescriptor descriptor = fieldDescriptors.get(2);
			final String fieldName = descriptor.getName();
			final String fieldType = descriptor.getType();
			final int fieldLength = descriptor.getLength();

			assertEquals("Field1", fieldName);
			assertEquals("N", fieldType);
			assertEquals(4, fieldLength);
		} finally {
			shapefile.close();
			FileUtils.closeInputStream(prjIS);
			FileUtils.closeInputStream(dbfIS);
			FileUtils.closeInputStream(shxIS);
			FileUtils.closeInputStream(shpIS);
		}
	}

	public void testEntityByRecordNumber() throws IOException,
	URISyntaxException {
		final URL shpResource = getClass().getClassLoader().getResource(
				"Polygon.shp");
		final File shpFile = new File(shpResource.toURI());
		final FileInputStream shpIS = new FileInputStream(shpFile);
		final URL shxResource = getClass().getClassLoader().getResource(
				"Polygon.shx");
		final File shxFile = new File(shxResource.toURI());
		final FileInputStream shxIS = new FileInputStream(shxFile);
		final URL dbfResource = getClass().getClassLoader().getResource(
				"Polygon.dbf");
		final File dbfFile = new File(dbfResource.toURI());
		final FileInputStream dbfIS = new FileInputStream(dbfFile);
		final URL prjResource = getClass().getClassLoader().getResource(
				"Polygon.prj");
		final File prjFile = new File(prjResource.toURI());
		final FileInputStream prjIS = new FileInputStream(prjFile);

		Shapefile shapefile = null;
		try {
			shapefile = new Shapefile(shpIS, shxIS, dbfIS, prjIS);

			Entity entity = shapefile.getEntityByRecordNumber(3, true);

			assertEquals(5, entity.getShapeType());
			assertEquals(3, entity.getRecordNum());
			assertNotNull(entity.getGeometry());
			assertTrue(entity.getData().size() > 0);

			entity = shapefile.getEntityByRecordNumber(3, false);

			assertEquals(5, entity.getShapeType());
			assertEquals(3, entity.getRecordNum());
			assertNotNull(entity.getGeometry());
			assertTrue(entity.getData().size() == 0);
		} finally {
			shapefile.close();
			FileUtils.closeInputStream(prjIS);
			FileUtils.closeInputStream(dbfIS);
			FileUtils.closeInputStream(shxIS);
			FileUtils.closeInputStream(shpIS);
		}
	}

	public void testEntityDataByRecordNumber() throws IOException,
			URISyntaxException {
		final URL shpResource = getClass().getClassLoader().getResource(
				"Polygon.shp");
		final File shpFile = new File(shpResource.toURI());
		final FileInputStream shpIS = new FileInputStream(shpFile);
		final URL shxResource = getClass().getClassLoader().getResource(
				"Polygon.shx");
		final File shxFile = new File(shxResource.toURI());
		final FileInputStream shxIS = new FileInputStream(shxFile);
		final URL dbfResource = getClass().getClassLoader().getResource(
				"Polygon.dbf");
		final File dbfFile = new File(dbfResource.toURI());
		final FileInputStream dbfIS = new FileInputStream(dbfFile);
		final URL prjResource = getClass().getClassLoader().getResource(
				"Polygon.prj");
		final File prjFile = new File(prjResource.toURI());
		final FileInputStream prjIS = new FileInputStream(prjFile);

		Shapefile shapefile = null;
		try {
			shapefile = new Shapefile(shpIS, shxIS, dbfIS, prjIS);

			final List<DBFField> entityData = shapefile
					.getEntityDataByRecordNumber(3);

			assertTrue(entityData.size() > 0);
		} finally {
			shapefile.close();
			FileUtils.closeInputStream(prjIS);
			FileUtils.closeInputStream(dbfIS);
			FileUtils.closeInputStream(shxIS);
			FileUtils.closeInputStream(shpIS);
		}
	}

	public void testEntitiesInRectangle() throws IOException,
			URISyntaxException {
		final URL shpResource = getClass().getClassLoader().getResource(
				"Polygon.shp");
		final File shpFile = new File(shpResource.toURI());
		final FileInputStream shpIS = new FileInputStream(shpFile);
		final URL shxResource = getClass().getClassLoader().getResource(
				"Polygon.shx");
		final File shxFile = new File(shxResource.toURI());
		final FileInputStream shxIS = new FileInputStream(shxFile);
		final URL dbfResource = getClass().getClassLoader().getResource(
				"Polygon.dbf");
		final File dbfFile = new File(dbfResource.toURI());
		final FileInputStream dbfIS = new FileInputStream(dbfFile);
		final URL prjResource = getClass().getClassLoader().getResource(
				"Polygon.prj");
		final File prjFile = new File(prjResource.toURI());
		final FileInputStream prjIS = new FileInputStream(prjFile);

		Shapefile shapefile = null;
		try {
			shapefile = new Shapefile(shpIS, shxIS, dbfIS, prjIS);

			List<Entity> entities = shapefile.getEntitiesInRectangle(440539,
					4471192, 448629, 4476120, true);
			assertTrue(entities.size() == 2);

			Entity entity = entities.get(0);
			assertEquals(5, entity.getShapeType());
			assertEquals(1, entity.getRecordNum());
			assertNotNull(entity.getGeometry());
			assertTrue(entity.getData().size() > 0);

			entities = shapefile.getEntitiesInRectangle(440539, 4471192,
					448629, 4476120, false);
			assertTrue(entities.size() == 2);

			entity = entities.get(0);
			assertEquals(5, entity.getShapeType());
			assertEquals(1, entity.getRecordNum());
			assertNotNull(entity.getGeometry());
			assertTrue(entity.getData().size() == 0);

			entities = shapefile.getEntitiesInRectangle(10, 10, 200, 200, true);
			assertTrue(entities.size() == 0);
		} finally {
			shapefile.close();
			FileUtils.closeInputStream(prjIS);
			FileUtils.closeInputStream(dbfIS);
			FileUtils.closeInputStream(shxIS);
			FileUtils.closeInputStream(shpIS);
		}
	}
}
