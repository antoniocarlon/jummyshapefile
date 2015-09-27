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
 * Class representing a point (Shapefile shape type = 1).
 */
public class Point extends Geometry {
	private double x;
	private double y;

	/**
	 * Returns the X coordinate of the point.
	 *
	 * @return the X coordinate of the point
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the X coordinate of the point.
	 *
	 * @param x
	 *            the X coordinate of the point
	 */
	public void setX(final double x) {
		this.x = x;
	}

	/**
	 * Returns the Y coordinate of the point.
	 *
	 * @return the Y coordinate of the point
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the Y coordinate of the point.
	 *
	 * @param x
	 *            the Y coordinate of the point
	 */
	public void setY(final double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "POINT: " + x + ", " + y;
	}
}
