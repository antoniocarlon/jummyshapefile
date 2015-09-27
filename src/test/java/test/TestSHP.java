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

import com.jummyshapefile.shapefile.model.MultiPoint;
import com.jummyshapefile.shapefile.model.Point;
import com.jummyshapefile.shapefile.model.Polygon;
import com.jummyshapefile.shapefile.model.Polyline;
import com.jummyshapefile.shp.SHPFile;
import com.jummyshapefile.shp.model.SHPHeader;
import com.jummyshapefile.shp.model.SHPRecord;
import com.jummyshapefile.utils.FileUtils;

public class TestSHP extends TestCase {
	public void testReadHeader() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shp");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHPFile shp = null;
		try {
			shp = new SHPFile();
			shp.open(is);

			final SHPHeader header = shp.getHeader();

			assertNotNull(header);
		} finally {
			shp.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testShapeType() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shp");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHPFile shp = null;
		try {
			shp = new SHPFile();
			shp.open(is);

			final SHPHeader header = shp.getHeader();
			final int shapeType = header.getShapeType();

			assertEquals(5, shapeType);
		} finally {
			shp.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testMinX() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shp");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHPFile shp = null;
		try {
			shp = new SHPFile();
			shp.open(is);

			final SHPHeader header = shp.getHeader();
			final int minX = (int) header.getMinX();

			assertEquals(437717, minX);
		} finally {
			shp.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testMinY() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shp");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHPFile shp = null;
		try {
			shp = new SHPFile();
			shp.open(is);

			final SHPHeader header = shp.getHeader();
			final int minY = (int) header.getMinY();

			assertEquals(4470909, minY);
		} finally {
			shp.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testMaxX() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shp");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHPFile shp = null;
		try {
			shp = new SHPFile();
			shp.open(is);

			final SHPHeader header = shp.getHeader();
			final int maxX = (int) header.getMaxX();

			assertEquals(447951, maxX);
		} finally {
			shp.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testMaxY() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shp");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHPFile shp = null;
		try {
			shp = new SHPFile();
			shp.open(is);

			final SHPHeader header = shp.getHeader();
			final int maxY = (int) header.getMaxY();

			assertEquals(4479694, maxY);
		} finally {
			shp.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testMinZ() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shp");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHPFile shp = null;
		try {
			shp = new SHPFile();
			shp.open(is);

			final SHPHeader header = shp.getHeader();
			final int minZ = (int) header.getMinZ();

			assertEquals(0, minZ);
		} finally {
			shp.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testMaxZ() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shp");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHPFile shp = null;
		try {
			shp = new SHPFile();
			shp.open(is);

			final SHPHeader header = shp.getHeader();
			final int maxZ = (int) header.getMaxZ();

			assertEquals(0, maxZ);
		} finally {
			shp.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testMinM() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shp");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHPFile shp = null;
		try {
			shp = new SHPFile();
			shp.open(is);

			final SHPHeader header = shp.getHeader();
			final int minM = (int) header.getMinM();

			assertEquals(0, minM);
		} finally {
			shp.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testMaxM() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shp");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHPFile shp = null;
		try {
			shp = new SHPFile();
			shp.open(is);

			final SHPHeader header = shp.getHeader();
			final int maxM = (int) header.getMaxM();

			assertEquals(0, maxM);
		} finally {
			shp.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testPoint() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Point.shp");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHPFile shp = null;
		try {
			shp = new SHPFile();
			shp.open(is);

			final SHPRecord record = shp.getRecord(156, 20);
			assertEquals(1, record.getShapeType());

			final Point point = (Point) record.getGeometry();
			assertEquals(447199, (int) point.getX());
		} finally {
			shp.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testMultiPoint() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"MultiPoint.shp");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHPFile shp = null;
		try {
			shp = new SHPFile();
			shp.open(is);

			final SHPRecord record = shp.getRecord(260, 88);
			assertEquals(8, record.getShapeType());

			final MultiPoint multipoint = (MultiPoint) record.getGeometry();
			assertEquals(3, multipoint.getPoints().size());

			final Point point = multipoint.getPoints().get(1);
			assertEquals(447307, (int) point.getX());
		} finally {
			shp.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testMultiPointSingle() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"MultiPoint.shp");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHPFile shp = null;
		try {
			shp = new SHPFile();
			shp.open(is);

			final SHPRecord record = shp.getRecord(196, 56);
			assertEquals(8, record.getShapeType());

			final MultiPoint multipoint = (MultiPoint) record.getGeometry();
			assertEquals(1, multipoint.getPoints().size());

			final Point point = multipoint.getPoints().get(0);
			assertEquals(444301, (int) point.getX());
		} finally {
			shp.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testPolyline() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polyline.shp");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHPFile shp = null;
		try {
			shp = new SHPFile();
			shp.open(is);

			final SHPRecord record = shp.getRecord(100, 180);
			assertEquals(3, record.getShapeType());

			final Polyline polyline = (Polyline) record.getGeometry();
			final List<List<Point>> parts = polyline.getParts();
			assertEquals(2, parts.size());

			final List<Point> points = parts.get(0);
			assertEquals(5, points.size());

			final Point point = points.get(1);
			assertEquals(438200, (int) point.getX());
		} finally {
			shp.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testPolylineSingle() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polyline.shp");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHPFile shp = null;
		try {
			shp = new SHPFile();
			shp.open(is);

			final SHPRecord record = shp.getRecord(288, 128);
			assertEquals(3, record.getShapeType());

			final Polyline polyline = (Polyline) record.getGeometry();
			final List<List<Point>> parts = polyline.getParts();
			assertEquals(1, parts.size());

			final List<Point> points = parts.get(0);
			assertEquals(5, points.size());

			final Point point = points.get(1);
			assertEquals(444247, (int) point.getX());
		} finally {
			shp.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testPolygon() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shp");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHPFile shp = null;
		try {
			shp = new SHPFile();
			shp.open(is);

			final SHPRecord record = shp.getRecord(236, 296);
			assertEquals(5, record.getShapeType());

			final Polygon polygon = (Polygon) record.getGeometry();
			final List<List<Point>> parts = polygon.getParts();
			assertEquals(3, parts.size());

			final List<Point> points = parts.get(0);
			assertEquals(5, points.size());

			final Point point = points.get(1);
			assertEquals(437878, (int) point.getX());
		} finally {
			shp.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testPolygonSingle() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shp");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHPFile shp = null;
		try {
			shp = new SHPFile();
			shp.open(is);

			final SHPRecord record = shp.getRecord(100, 128);
			assertEquals(5, record.getShapeType());

			final Polygon polygon = (Polygon) record.getGeometry();
			final List<List<Point>> parts = polygon.getParts();
			assertEquals(1, parts.size());

			final List<Point> points = parts.get(0);
			assertEquals(5, points.size());

			final Point point = points.get(1);
			assertEquals(444498, (int) point.getX());
		} finally {
			shp.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testPolygonNoHoles() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shp");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHPFile shp = null;
		try {
			shp = new SHPFile();
			shp.open(is);

			final SHPRecord record = shp.getRecord(1148, 212);
			assertEquals(5, record.getShapeType());

			final Polygon polygon = (Polygon) record.getGeometry();
			final List<List<Point>> parts = polygon.getParts();
			assertEquals(2, parts.size());

			final List<Point> points = parts.get(0);
			assertEquals(5, points.size());

			final Point point = points.get(1);
			assertEquals(444569, (int) point.getX());
		} finally {
			shp.close();
			FileUtils.closeInputStream(is);
		}
	}
}
