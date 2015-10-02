package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import junit.framework.TestCase;

import com.jummyshapefile.shapefile.Shapefile;
import com.jummyshapefile.shapefile.model.Entity;
import com.jummyshapefile.utils.FileUtils;
import com.jummyshapefile.utils.WKTUtils;

public class TestWKT extends TestCase {
	public void testWKTPoints() throws IOException, URISyntaxException {
		final URL shpResource = getClass().getClassLoader().getResource(
				"Point.shp");
		final File shpFile = new File(shpResource.toURI());
		final FileInputStream shpIS = new FileInputStream(shpFile);
		final URL shxResource = getClass().getClassLoader().getResource(
				"Point.shx");
		final File shxFile = new File(shxResource.toURI());
		final FileInputStream shxIS = new FileInputStream(shxFile);
		final URL dbfResource = getClass().getClassLoader().getResource(
				"Point.dbf");
		final File dbfFile = new File(dbfResource.toURI());
		final FileInputStream dbfIS = new FileInputStream(dbfFile);
		final URL prjResource = getClass().getClassLoader().getResource(
				"Point.prj");
		final File prjFile = new File(prjResource.toURI());
		final FileInputStream prjIS = new FileInputStream(prjFile);

		Shapefile shapefile = null;
		try {
			shapefile = new Shapefile(shpIS, shxIS, dbfIS, prjIS);
			final int numEntities = shapefile.getNumEntities();
			for (int i = 1; i <= numEntities; i++) {
				final Entity entity = shapefile.getEntityByRecordNumber(i,
						false);
				final String wkt = WKTUtils.geometry2WKT(entity.getGeometry());

				assertNotNull(wkt);
			}
		} finally {
			shapefile.close();
			FileUtils.closeInputStream(prjIS);
			FileUtils.closeInputStream(dbfIS);
			FileUtils.closeInputStream(shxIS);
			FileUtils.closeInputStream(shpIS);
		}
	}

	public void testWKTPoint() throws IOException, URISyntaxException {
		final URL shpResource = getClass().getClassLoader().getResource(
				"Point.shp");
		final File shpFile = new File(shpResource.toURI());
		final FileInputStream shpIS = new FileInputStream(shpFile);
		final URL shxResource = getClass().getClassLoader().getResource(
				"Point.shx");
		final File shxFile = new File(shxResource.toURI());
		final FileInputStream shxIS = new FileInputStream(shxFile);
		final URL dbfResource = getClass().getClassLoader().getResource(
				"Point.dbf");
		final File dbfFile = new File(dbfResource.toURI());
		final FileInputStream dbfIS = new FileInputStream(dbfFile);
		final URL prjResource = getClass().getClassLoader().getResource(
				"Point.prj");
		final File prjFile = new File(prjResource.toURI());
		final FileInputStream prjIS = new FileInputStream(prjFile);

		Shapefile shapefile = null;
		try {
			shapefile = new Shapefile(shpIS, shxIS, dbfIS, prjIS);
			final Entity entity = shapefile.getEntityByRecordNumber(2, false);
			final String wkt = WKTUtils.geometry2WKT(entity.getGeometry());

			assertEquals("POINT (443997.4509899819 4479103.865639006)", wkt);
		} finally {
			shapefile.close();
			FileUtils.closeInputStream(prjIS);
			FileUtils.closeInputStream(dbfIS);
			FileUtils.closeInputStream(shxIS);
			FileUtils.closeInputStream(shpIS);
		}
	}

	public void testWKTPolylines() throws IOException, URISyntaxException {
		final URL shpResource = getClass().getClassLoader().getResource(
				"Polyline.shp");
		final File shpFile = new File(shpResource.toURI());
		final FileInputStream shpIS = new FileInputStream(shpFile);
		final URL shxResource = getClass().getClassLoader().getResource(
				"Polyline.shx");
		final File shxFile = new File(shxResource.toURI());
		final FileInputStream shxIS = new FileInputStream(shxFile);
		final URL dbfResource = getClass().getClassLoader().getResource(
				"Polyline.dbf");
		final File dbfFile = new File(dbfResource.toURI());
		final FileInputStream dbfIS = new FileInputStream(dbfFile);
		final URL prjResource = getClass().getClassLoader().getResource(
				"Polyline.prj");
		final File prjFile = new File(prjResource.toURI());
		final FileInputStream prjIS = new FileInputStream(prjFile);

		Shapefile shapefile = null;
		try {
			shapefile = new Shapefile(shpIS, shxIS, dbfIS, prjIS);
			final int numEntities = shapefile.getNumEntities();
			for (int i = 1; i <= numEntities; i++) {
				final Entity entity = shapefile.getEntityByRecordNumber(i,
						false);
				final String wkt = WKTUtils.geometry2WKT(entity.getGeometry());

				assertNotNull(wkt);
			}
		} finally {
			shapefile.close();
			FileUtils.closeInputStream(prjIS);
			FileUtils.closeInputStream(dbfIS);
			FileUtils.closeInputStream(shxIS);
			FileUtils.closeInputStream(shpIS);
		}
	}

	public void testWKTPolyline() throws IOException, URISyntaxException {
		final URL shpResource = getClass().getClassLoader().getResource(
				"Polyline.shp");
		final File shpFile = new File(shpResource.toURI());
		final FileInputStream shpIS = new FileInputStream(shpFile);
		final URL shxResource = getClass().getClassLoader().getResource(
				"Polyline.shx");
		final File shxFile = new File(shxResource.toURI());
		final FileInputStream shxIS = new FileInputStream(shxFile);
		final URL dbfResource = getClass().getClassLoader().getResource(
				"Polyline.dbf");
		final File dbfFile = new File(dbfResource.toURI());
		final FileInputStream dbfIS = new FileInputStream(dbfFile);
		final URL prjResource = getClass().getClassLoader().getResource(
				"Polyline.prj");
		final File prjFile = new File(prjResource.toURI());
		final FileInputStream prjIS = new FileInputStream(prjFile);

		Shapefile shapefile = null;
		try {
			shapefile = new Shapefile(shpIS, shxIS, dbfIS, prjIS);
			final Entity entity = shapefile.getEntityByRecordNumber(2, false);
			final String wkt = WKTUtils.geometry2WKT(entity.getGeometry());

			assertEquals(
					"LINESTRING (443764.86592127185 4479300.668389453,444247.92721782345 4479622.709253821,444194.25374042883 4479103.865639006,444677.3150369805 4479372.233025979,444462.621127402 4478799.71593377)",
					wkt);
		} finally {
			shapefile.close();
			FileUtils.closeInputStream(prjIS);
			FileUtils.closeInputStream(dbfIS);
			FileUtils.closeInputStream(shxIS);
			FileUtils.closeInputStream(shpIS);
		}
	}

	public void testWKTPolygons() throws IOException, URISyntaxException {
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
			for (int i = 1; i <= numEntities; i++) {
				final Entity entity = shapefile.getEntityByRecordNumber(i,
						false);
				final String wkt = WKTUtils.geometry2WKT(entity.getGeometry());

				assertNotNull(wkt);
			}
		} finally {
			shapefile.close();
			FileUtils.closeInputStream(prjIS);
			FileUtils.closeInputStream(dbfIS);
			FileUtils.closeInputStream(shxIS);
			FileUtils.closeInputStream(shpIS);
		}
	}

	public void testWKTPolygon() throws IOException, URISyntaxException {
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
			final Entity entity = shapefile.getEntityByRecordNumber(2, false);
			final String wkt = WKTUtils.geometry2WKT(entity.getGeometry());

			assertEquals(
					"POLYGON ((438826.9060009662 4476885.361906694,437878.6745669963 4476867.470747564,437842.89224873297 4477386.314362381,438862.6883192295 4477350.532044115,438826.9060009662 4476885.361906694),(438862.6883192295 4478262.981159829,438880.57947836164 4477726.246385878,437807.10993046965 4477726.246385878,437807.10993046965 4478245.090000693,438862.6883192295 4478262.981159829),(437926.9795887191 4477851.890691707,438758.8779889196 4477838.942856684,438755.46024257503 4478163.628759531,437918.11238787137 4478153.375520492,437926.9795887191 4477851.890691707))",
					wkt);
		} finally {
			shapefile.close();
			FileUtils.closeInputStream(prjIS);
			FileUtils.closeInputStream(dbfIS);
			FileUtils.closeInputStream(shxIS);
			FileUtils.closeInputStream(shpIS);
		}
	}

	public void testWKTMultiPoints() throws IOException, URISyntaxException {
		final URL shpResource = getClass().getClassLoader().getResource(
				"MultiPoint.shp");
		final File shpFile = new File(shpResource.toURI());
		final FileInputStream shpIS = new FileInputStream(shpFile);
		final URL shxResource = getClass().getClassLoader().getResource(
				"MultiPoint.shx");
		final File shxFile = new File(shxResource.toURI());
		final FileInputStream shxIS = new FileInputStream(shxFile);
		final URL dbfResource = getClass().getClassLoader().getResource(
				"MultiPoint.dbf");
		final File dbfFile = new File(dbfResource.toURI());
		final FileInputStream dbfIS = new FileInputStream(dbfFile);
		final URL prjResource = getClass().getClassLoader().getResource(
				"MultiPoint.prj");
		final File prjFile = new File(prjResource.toURI());
		final FileInputStream prjIS = new FileInputStream(prjFile);

		Shapefile shapefile = null;
		try {
			shapefile = new Shapefile(shpIS, shxIS, dbfIS, prjIS);
			final int numEntities = shapefile.getNumEntities();
			for (int i = 1; i <= numEntities; i++) {
				final Entity entity = shapefile.getEntityByRecordNumber(i,
						false);
				final String wkt = WKTUtils.geometry2WKT(entity.getGeometry());

				assertNotNull(wkt);
			}
		} finally {
			shapefile.close();
			FileUtils.closeInputStream(prjIS);
			FileUtils.closeInputStream(dbfIS);
			FileUtils.closeInputStream(shxIS);
			FileUtils.closeInputStream(shpIS);
		}
	}

	public void testWKTMultiPoint() throws IOException, URISyntaxException {
		final URL shpResource = getClass().getClassLoader().getResource(
				"MultiPoint.shp");
		final File shpFile = new File(shpResource.toURI());
		final FileInputStream shpIS = new FileInputStream(shpFile);
		final URL shxResource = getClass().getClassLoader().getResource(
				"MultiPoint.shx");
		final File shxFile = new File(shxResource.toURI());
		final FileInputStream shxIS = new FileInputStream(shxFile);
		final URL dbfResource = getClass().getClassLoader().getResource(
				"MultiPoint.dbf");
		final File dbfFile = new File(dbfResource.toURI());
		final FileInputStream dbfIS = new FileInputStream(dbfFile);
		final URL prjResource = getClass().getClassLoader().getResource(
				"MultiPoint.prj");
		final File prjFile = new File(prjResource.toURI());
		final FileInputStream prjIS = new FileInputStream(prjFile);

		Shapefile shapefile = null;
		try {
			shapefile = new Shapefile(shpIS, shxIS, dbfIS, prjIS);
			final Entity entity = shapefile.getEntityByRecordNumber(2, false);
			final String wkt = WKTUtils.geometry2WKT(entity.getGeometry());

			assertEquals("MULTIPOINT ((444301.6006952181 4479193.321434664))",
					wkt);
		} finally {
			shapefile.close();
			FileUtils.closeInputStream(prjIS);
			FileUtils.closeInputStream(dbfIS);
			FileUtils.closeInputStream(shxIS);
			FileUtils.closeInputStream(shpIS);
		}
	}
}
