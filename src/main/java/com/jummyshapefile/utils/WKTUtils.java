package com.jummyshapefile.utils;

import java.util.List;

import com.jummyshapefile.shapefile.model.Geometry;
import com.jummyshapefile.shapefile.model.MultiPoint;
import com.jummyshapefile.shapefile.model.Point;
import com.jummyshapefile.shapefile.model.Polygon;
import com.jummyshapefile.shapefile.model.Polyline;

/**
 * Utility class to work with WKTs and Geometries.
 * 
 * @see Geometry
 */
public final class WKTUtils {
	/**
	 * Returns the WKT representing the given Geometry.
	 * 
	 * @param geometry
	 *            the Geometry to convert to WKT
	 * @return the WKT representing the given Geometry
	 * 
	 * @see Geometry
	 */
	public static String geometry2WKT(final Geometry geometry) {
		String output = null;

		if (geometry == null) {
			return output;
		}

		if (geometry instanceof Point) {
			output = point2WKT((Point) geometry);
		}
		// As Polygon extends Polyline, check Polygon first
		else if (geometry instanceof Polygon) {
			output = polygon2WKT((Polygon) geometry);
		} else if (geometry instanceof Polyline) {
			output = polyline2WKT((Polyline) geometry);
		} else if (geometry instanceof MultiPoint) {
			output = multipoint2WKT((MultiPoint) geometry);
		}

		return output;
	}

	/**
	 * Returns the WKT representing the given Point.
	 * 
	 * @param point
	 *            the Point to convert to WKT
	 * @return the WKT representing the given Point
	 * 
	 * @see Point
	 */
	private static String point2WKT(final Point point) {
		String output = null;

		if (point == null) {
			return output;
		}

		final StringBuilder sb = new StringBuilder();
		sb.append("POINT (");
		sb.append(point.getX());
		sb.append(" ");
		sb.append(point.getY());
		sb.append(")");
		output = sb.toString();

		return output;
	}

	/**
	 * Returns the WKT representing the given Polyline.
	 * 
	 * @param polyline
	 *            the Polyline to convert to WKT
	 * @return the WKT representing the given Polyline
	 * 
	 * @see Polyline
	 */
	private static String polyline2WKT(final Polyline polyline) {
		String output = null;

		if (polyline == null) {
			return output;
		}

		boolean isEmtpy = true;
		final StringBuilder sb = new StringBuilder();

		final List<List<Point>> parts = polyline.getParts();
		if (parts != null) {
			if (parts.size() == 1) {
				final List<Point> points = parts.get(0);
				if (points != null && points.size() > 0) {
					sb.append("LINESTRING (");
					for (int i = 0; i < points.size(); i++) {
						isEmtpy = false;
						final Point point = points.get(i);
						sb.append(point.getX());
						sb.append(" ");
						sb.append(point.getY());

						if (i < points.size() - 1) {
							sb.append(",");
						}
					}
					sb.append(")");
				}
			} else if (parts.size() > 1) {
				sb.append("MULTILINESTRING (");
				for (int i = 0; i < parts.size(); i++) {
					final List<Point> points = parts.get(i);
					if (points != null && points.size() > 0) {
						sb.append("(");

						for (int j = 0; j < points.size(); j++) {
							isEmtpy = false;
							final Point point = points.get(j);
							sb.append(point.getX());
							sb.append(" ");
							sb.append(point.getY());

							if (j < points.size() - 1) {
								sb.append(",");
							}
						}
						sb.append(")");
					}
					if (i < parts.size() - 1) {
						sb.append(",");
					}
				}
				sb.append(")");
			}
		}

		if (isEmtpy) {
			output = "LINESTRING EMPTY";
		} else {
			output = sb.toString();
		}

		return output;
	}

	/**
	 * Returns the WKT representing the given Polygon.
	 * 
	 * @param polygon
	 *            the Polygon to convert to WKT
	 * @return the WKT representing the given Polygon
	 * 
	 * @see Polygon
	 */
	private static String polygon2WKT(final Polygon polygon) {
		String output = null;

		if (polygon == null) {
			return output;
		}

		boolean isEmtpy = true;
		final StringBuilder sb = new StringBuilder();

		final List<List<Point>> parts = polygon.getParts();
		if (parts != null) {
			sb.append("POLYGON (");
			for (int i = 0; i < parts.size(); i++) {
				final List<Point> points = parts.get(i);
				if (points != null && points.size() > 0) {
					sb.append("(");

					for (int j = 0; j < points.size(); j++) {
						isEmtpy = false;
						final Point point = points.get(j);
						sb.append(point.getX());
						sb.append(" ");
						sb.append(point.getY());

						if (j < points.size() - 1) {
							sb.append(",");
						}
					}

					sb.append(")");
				}

				if (i < parts.size() - 1) {
					sb.append(",");
				}
			}
			sb.append(")");
		}

		if (isEmtpy) {
			output = "POLYGON EMPTY";
		} else {
			output = sb.toString();
		}

		return output;
	}

	/**
	 * Returns the WKT representing the given MultiPoint.
	 * 
	 * @param multipoint
	 *            the MultiPoint to convert to WKT
	 * @return the WKT representing the given MultiPoint
	 * 
	 * @see MultiPoint
	 */
	private static String multipoint2WKT(final MultiPoint multipoint) {
		String output = null;

		if (multipoint == null) {
			return output;
		}

		final StringBuilder sb = new StringBuilder();

		final List<Point> points = multipoint.getPoints();
		if (points != null) {
			sb.append("MULTIPOINT (");
			for (int i = 0; i < points.size(); i++) {
				final Point point = points.get(i);
				sb.append("(");
				sb.append(point.getX());
				sb.append(" ");
				sb.append(point.getY());
				sb.append(")");

				if (i < points.size() - 1) {
					sb.append(",");
				}
			}
			sb.append(")");
		} else {
			sb.append("MULTIPOINT EMPTY");
		}

		output = sb.toString();

		return output;
	}
}
