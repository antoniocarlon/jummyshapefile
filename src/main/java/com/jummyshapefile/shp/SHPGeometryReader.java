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

package com.jummyshapefile.shp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jummyshapefile.shapefile.model.Geometry;
import com.jummyshapefile.shapefile.model.MultiPoint;
import com.jummyshapefile.shapefile.model.NullShape;
import com.jummyshapefile.shapefile.model.Point;
import com.jummyshapefile.shapefile.model.Polygon;
import com.jummyshapefile.shapefile.model.Polyline;
import com.jummyshapefile.utils.ByteUtils;

/**
 * Class that reads a geometry in a record of a SHP file.
 * <p>
 * Takes into account only the following shape types (defined by the ESRI
 * Shapefile format):
 * <ul>
 * <li>0 Null shape
 * <li>1 Point
 * <li>3 Polyline
 * <li>5 Polygon
 * <li>8 MultiPoint
 * <p>
 * For more field types, see the documentation:
 * <p>
 * https://en.m.wikipedia.org/wiki/Shapefile
 *
 * @see Geometry
 */
public class SHPGeometryReader {
	/**
	 * Returns a Geometry object representing the byte array.
	 *
	 * @param data
	 *            the byte array representing the geometry
	 *
	 * @return a Geometry object representing the byte array
	 * @throws IOException
	 *             if there is a problem reading the geometry
	 *
	 * @see Geometry
	 */
	public Geometry readGeometry(final byte[] data, final int geometryType)
			throws IOException {
		Geometry output = null;

		if (data == null) {
			throw new IllegalArgumentException("The byte array is null");
		}

		switch (geometryType) {
		case 0:
			output = readNullShape(data,
					SHPParameters.SHP_RECORDHEADER_SHAPETYPE_SIZE);
			break;
		case 1:
			output = readPoint(data,
					SHPParameters.SHP_RECORDHEADER_SHAPETYPE_SIZE);
			break;
		case 3:
			output = readPolyline(data,
					SHPParameters.SHP_RECORDHEADER_SHAPETYPE_SIZE);
			break;
		case 5:
			output = readPolygon(data,
					SHPParameters.SHP_RECORDHEADER_SHAPETYPE_SIZE);
			break;
		case 8:
			output = readMultiPoint(data,
					SHPParameters.SHP_RECORDHEADER_SHAPETYPE_SIZE);
			break;
		default:
			throw new UnsupportedOperationException(
					"Geometry type not allowed (not implemented): "
							+ geometryType);
		}

		return output;
	}

	/**
	 * Reads a null shape from a byte array.
	 *
	 * @param data
	 *            the byte array
	 * @param offset
	 *            the initial offset in the byte array
	 *
	 * @return a NullShape object representing the byte array
	 * @throws IOException
	 *             if there is a problem reading the null shape
	 *
	 * @see Geometry
	 * @see NullShape
	 */
	private Geometry readNullShape(final byte[] data, final int offset)
			throws IOException {
		final Geometry output = new NullShape();

		if (data == null) {
			throw new IllegalArgumentException("The byte array is null");
		}

		return output;
	}

	/**
	 * Reads a point from a byte array.
	 *
	 * @param data
	 *            the byte array
	 * @param offset
	 *            the initial offset in the byte array
	 *
	 * @return a Point object representing the byte array
	 * @throws IOException
	 *             if there is a problem reading the point
	 *
	 * @see Geometry
	 * @see Point
	 */
	private Geometry readPoint(final byte[] data, final int offset)
			throws IOException {
		final Point output = new Point();

		if (data == null) {
			throw new IllegalArgumentException("The byte array is null");
		}

		int off = offset;

		output.setX(ByteUtils.readDoubleLeastSignificantFirst(data,
				SHPParameters.SHP_DOUBLE_LENGTH, off));
		off += SHPParameters.SHP_DOUBLE_LENGTH;

		output.setY(ByteUtils.readDoubleLeastSignificantFirst(data,
				SHPParameters.SHP_DOUBLE_LENGTH, off));

		output.setMinX(output.getX());
		output.setMinY(output.getY());
		output.setMaxX(output.getX());
		output.setMaxY(output.getY());

		return output;
	}

	/**
	 * Reads a polyline from a byte array.
	 *
	 * @param data
	 *            the byte array
	 * @param offset
	 *            the initial offset in the byte array
	 *
	 * @return a Polyline object representing the byte array
	 * @throws IOException
	 *             if there is a problem reading the polyline
	 *
	 * @see Geometry
	 * @see Polyline
	 */
	private Geometry readPolyline(final byte[] data, final int offset)
			throws IOException {
		final Polyline output = new Polyline();

		if (data == null) {
			throw new IllegalArgumentException("The byte array is null");
		}

		int off = offset;

		output.setMinX(ByteUtils.readDoubleLeastSignificantFirst(data,
				SHPParameters.SHP_DOUBLE_LENGTH, off));
		off += SHPParameters.SHP_DOUBLE_LENGTH;

		output.setMinY(ByteUtils.readDoubleLeastSignificantFirst(data,
				SHPParameters.SHP_DOUBLE_LENGTH, off));
		off += SHPParameters.SHP_DOUBLE_LENGTH;

		output.setMaxX(ByteUtils.readDoubleLeastSignificantFirst(data,
				SHPParameters.SHP_DOUBLE_LENGTH, off));
		off += SHPParameters.SHP_DOUBLE_LENGTH;

		output.setMaxY(ByteUtils.readDoubleLeastSignificantFirst(data,
				SHPParameters.SHP_DOUBLE_LENGTH, off));
		off += SHPParameters.SHP_DOUBLE_LENGTH;

		final int numberOfParts = ByteUtils.readIntLeastSignificantFirst(data,
				SHPParameters.SHP_INT_LENGTH, off);
		off += SHPParameters.SHP_INT_LENGTH;

		final int numberOfPoints = ByteUtils.readIntLeastSignificantFirst(data,
				SHPParameters.SHP_INT_LENGTH, off);
		off += SHPParameters.SHP_INT_LENGTH;

		final int[] partsArray = new int[numberOfParts];
		for (int i = 0; i < numberOfParts; i++) {
			final int numPointsPart = ByteUtils.readIntLeastSignificantFirst(
					data, SHPParameters.SHP_INT_LENGTH, off);
			off += SHPParameters.SHP_INT_LENGTH;
			partsArray[i] = numPointsPart;
		}

		final List<List<Point>> parts = new ArrayList<List<Point>>();
		int numberOfPointsRead = 0;

		for (int i = 0; i < partsArray.length; i++) {
			final List<Point> points = new ArrayList<Point>();

			int numberOfPointsInPart = numberOfPoints - numberOfPointsRead;
			if (i < partsArray.length - 1) {
				numberOfPointsInPart = partsArray[i + 1] - partsArray[i];
			}

			for (int j = 0; j < numberOfPointsInPart; j++) {
				points.add((Point) readPoint(data, off));
				numberOfPointsRead++;
				off += 2 * SHPParameters.SHP_DOUBLE_LENGTH;
			}

			parts.add(points);
		}

		output.setParts(parts);

		return output;
	}

	/**
	 * Reads a polygon from a byte array.
	 *
	 * @param data
	 *            the byte array
	 * @param offset
	 *            the initial offset in the byte array
	 *
	 * @return a Polygon object representing the byte array
	 * @throws IOException
	 *             if there is a problem reading the polygon
	 *
	 * @see Geometry
	 * @see Polygon
	 */
	private Geometry readPolygon(final byte[] data, final int offset)
			throws IOException {
		final Polygon output = new Polygon();

		if (data == null) {
			throw new IllegalArgumentException("The byte array is null");
		}

		final Polyline polyline = (Polyline) readPolyline(data, offset);
		output.setMinX(polyline.getMinX());
		output.setMinY(polyline.getMinY());
		output.setMaxX(polyline.getMaxX());
		output.setMaxY(polyline.getMaxY());
		output.setParts(polyline.getParts());

		return output;
	}

	/**
	 * Reads a multipoint from a byte array.
	 *
	 * @param data
	 *            the byte array
	 * @param offset
	 *            the initial offset in the byte array
	 *
	 * @return a MultiPoint object representing the byte array
	 * @throws IOException
	 *             if there is a problem reading the multipoint
	 *
	 * @see Geometry
	 * @see MultiPoint
	 */
	private Geometry readMultiPoint(final byte[] data, final int offset)
			throws IOException {
		final MultiPoint output = new MultiPoint();

		if (data == null) {
			throw new IllegalArgumentException("The byte array is null");
		}

		int off = offset;

		output.setMinX(ByteUtils.readDoubleLeastSignificantFirst(data,
				SHPParameters.SHP_DOUBLE_LENGTH, off));
		off += SHPParameters.SHP_DOUBLE_LENGTH;

		output.setMinY(ByteUtils.readDoubleLeastSignificantFirst(data,
				SHPParameters.SHP_DOUBLE_LENGTH, off));
		off += SHPParameters.SHP_DOUBLE_LENGTH;

		output.setMaxX(ByteUtils.readDoubleLeastSignificantFirst(data,
				SHPParameters.SHP_DOUBLE_LENGTH, off));
		off += SHPParameters.SHP_DOUBLE_LENGTH;

		output.setMaxY(ByteUtils.readDoubleLeastSignificantFirst(data,
				SHPParameters.SHP_DOUBLE_LENGTH, off));
		off += SHPParameters.SHP_DOUBLE_LENGTH;

		final int numberOfPoints = ByteUtils.readIntLeastSignificantFirst(data,
				SHPParameters.SHP_INT_LENGTH, off);
		off += SHPParameters.SHP_INT_LENGTH;

		final List<Point> points = new ArrayList<Point>();

		for (int i = 0; i < numberOfPoints; i++) {
			points.add((Point) readPoint(data, off));
			off += 2 * SHPParameters.SHP_DOUBLE_LENGTH;
		}

		output.setPoints(points);

		return output;
	}
}
