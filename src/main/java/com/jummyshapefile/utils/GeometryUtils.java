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

package com.jummyshapefile.utils;

/**
 * Utility class to work with geometries.
 */
public final class GeometryUtils {
	/**
	 * Checks whether a rectangle intersects another rectangle.
	 *
	 * @param checkMinX
	 *            minX of the rectangle to check
	 * @param checkMinY
	 *            minY of the rectangle to check
	 * @param checkMaxX
	 *            maxX of the rectangle to check
	 * @param checkMaxY
	 *            maxY of the rectangle to check
	 * @param againstMinX
	 *            minX of the reference rectangle
	 * @param againstMinY
	 *            minY of the reference rectangle
	 * @param againstMaxX
	 *            maxX of the reference rectangle
	 * @param againstMaxY
	 *            maxY of the reference rectangle
	 *
	 * @return whether a rectangle intersects another rectangle
	 */
	public static boolean intersectsRectangle(final double checkMinX,
			final double checkMinY, final double checkMaxX,
			final double checkMaxY, final double againstMinX,
			final double againstMinY, final double againstMaxX,
			final double againstMaxY) {
		boolean output = false;

		if (againstMaxX > checkMinX && againstMinX < checkMaxX
				&& againstMaxY > checkMinY && againstMinY < checkMaxY) {
			output = true;
		}
		return output;
	}
}
