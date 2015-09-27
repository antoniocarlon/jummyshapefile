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

package com.jummyshapefile.shapefile.model;

/**
 * Class representing a geometry.
 */
public class Geometry {
	protected double minX;
	protected double minY;
	protected double maxX;
	protected double maxY;

	/**
	 * Returns the min X of the MBR (Minimum bounding rectangle) of the
	 * geometry.
	 *
	 * @return the min X of the MBR (Minimum bounding rectangle) of the geometry
	 */
	public double getMinX() {
		return minX;
	}

	/**
	 * Sets the min X of the MBR (Minimum bounding rectangle) of the geometry.
	 *
	 * @param minX
	 *            the min X of the MBR (Minimum bounding rectangle) of the
	 *            geometry
	 */
	public void setMinX(final double minX) {
		this.minX = minX;
	}

	/**
	 * Returns the min Y of the MBR (Minimum bounding rectangle) of the
	 * geometry.
	 *
	 * @return the min Y of the MBR (Minimum bounding rectangle) of the geometry
	 */
	public double getMinY() {
		return minY;
	}

	/**
	 * Sets the min Y of the MBR (Minimum bounding rectangle) of the geometry.
	 *
	 * @param minY
	 *            the min Y of the MBR (Minimum bounding rectangle) of the
	 *            geometry
	 */
	public void setMinY(final double minY) {
		this.minY = minY;
	}

	/**
	 * Returns the max X of the MBR (Minimum bounding rectangle) of the
	 * geometry.
	 *
	 * @return the max X of the MBR (Minimum bounding rectangle) of the geometry
	 */
	public double getMaxX() {
		return maxX;
	}

	/**
	 * Sets the max X of the MBR (Minimum bounding rectangle) of the geometry.
	 *
	 * @param maxX
	 *            the max X of the MBR (Minimum bounding rectangle) of the
	 *            geometry
	 */
	public void setMaxX(final double maxX) {
		this.maxX = maxX;
	}

	/**
	 * Returns the max Y of the MBR (Minimum bounding rectangle) of the
	 * geometry.
	 *
	 * @return the max Y of the MBR (Minimum bounding rectangle) of the geometry
	 */
	public double getMaxY() {
		return maxY;
	}

	/**
	 * Sets the max Y of the MBR (Minimum bounding rectangle) of the geometry.
	 *
	 * @param maxY
	 *            the max Y of the MBR (Minimum bounding rectangle) of the
	 *            geometry
	 */
	public void setMaxY(final double maxY) {
		this.maxY = maxY;
	}
}
