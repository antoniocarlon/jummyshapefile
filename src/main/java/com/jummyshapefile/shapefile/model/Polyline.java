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

import java.util.List;

/**
 * Class representing a point (Shapefile shape type = 3).
 */
public class Polyline extends Geometry {
	protected List<List<Point>> parts;

	/**
	 * Returns a List representing the parts of the polyline. Each List in the
	 * List of parts represents the points of the current part of the polyline.
	 *
	 * @return a List representing the parts of the polyline. Each List in the
	 *         List of parts represents the points of the current part of the
	 *         polyline.
	 */
	public List<List<Point>> getParts() {
		return parts;
	}

	/**
	 * Sets the List representing the parts of the polyline. Each List in the
	 * List of parts represents the points of the current part of the polyline.
	 *
	 * @param parts
	 *            a List representing the parts of the polyline. Each List in
	 *            the List of parts represents the points of the current part of
	 *            the polyline.
	 */
	public void setParts(final List<List<Point>> parts) {
		this.parts = parts;
	}

	@Override
	public String toString() {
		return "POLYLINE (MBR " + minX + " " + minY + " " + maxX + " " + maxY
				+ ") " + parts;
	}
}
