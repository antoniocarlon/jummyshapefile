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

package com.jummyshapefile.shp.model;

import com.jummyshapefile.binaryfile.model.BinaryFileHeader;
import com.jummyshapefile.shp.SHPParameters;

/**
 * Represents the header of a SHP file.
 * <p>
 * https://en.m.wikipedia.org/wiki/Shapefile
 */
public class SHPHeader extends BinaryFileHeader {
	private final int headerSize = SHPParameters.SHP_HEADER_LENGTH;
	private int shapeType;
	private double minX;
	private double minY;
	private double maxX;
	private double maxY;
	private double minZ;
	private double maxZ;
	private double minM;
	private double maxM;

	/**
	 * Returns the size of the header of the file in bytes.
	 *
	 * @return the size of the header of the file in bytes
	 */
	public int getHeaderSize() {
		return headerSize;
	}

	/**
	 * Returns the type of the shapes stored.
	 *
	 * @return the type of the shapes stored
	 */
	public int getShapeType() {
		return shapeType;
	}

	/**
	 * Sets the type of the shapes stored.
	 *
	 * @param shapeType
	 *            the type of the shapes stored
	 */
	public void setShapeType(final int shapeType) {
		this.shapeType = shapeType;
	}

	/**
	 * Returns the min X of the MBR (Minimum bounding rectangle) of all shapes
	 * contained.
	 *
	 * @return the min X of the MBR (Minimum bounding rectangle) of all shapes
	 *         contained
	 */
	public double getMinX() {
		return minX;
	}

	/**
	 * Sets the min X of the MBR (Minimum bounding rectangle) of all shapes
	 * contained.
	 *
	 * @param minX
	 *            the min X of the MBR (Minimum bounding rectangle) of all
	 *            shapes contained
	 */
	public void setMinX(final double minX) {
		this.minX = minX;
	}

	/**
	 * Returns the min Y of the MBR (Minimum bounding rectangle) of all shapes
	 * contained.
	 *
	 * @return the min Y of the MBR (Minimum bounding rectangle) of all shapes
	 *         contained
	 */
	public double getMinY() {
		return minY;
	}

	/**
	 * Sets the min Y of the MBR (Minimum bounding rectangle) of all shapes
	 * contained.
	 *
	 * @param minY
	 *            the min Y of the MBR (Minimum bounding rectangle) of all
	 *            shapes contained
	 */
	public void setMinY(final double minY) {
		this.minY = minY;
	}

	/**
	 * Returns the max X of the MBR (Minimum bounding rectangle) of all shapes
	 * contained.
	 *
	 * @return the max X of the MBR (Minimum bounding rectangle) of all shapes
	 *         contained
	 */
	public double getMaxX() {
		return maxX;
	}

	/**
	 * Sets the max X of the MBR (Minimum bounding rectangle) of all shapes
	 * contained.
	 *
	 * @param maxX
	 *            the max X of the MBR (Minimum bounding rectangle) of all
	 *            shapes contained
	 */
	public void setMaxX(final double maxX) {
		this.maxX = maxX;
	}

	/**
	 * Returns the max Y of the MBR (Minimum bounding rectangle) of all shapes
	 * contained.
	 *
	 * @return the max Y of the MBR (Minimum bounding rectangle) of all shapes
	 *         contained
	 */
	public double getMaxY() {
		return maxY;
	}

	/**
	 * Sets the max Y of the MBR (Minimum bounding rectangle) of all shapes
	 * contained.
	 *
	 * @param maxY
	 *            the max Y of the MBR (Minimum bounding rectangle) of all
	 *            shapes contained
	 */
	public void setMaxY(final double maxY) {
		this.maxY = maxY;
	}

	/**
	 * Returns the min Z of the ranges of Z of all shapes contained.
	 *
	 * @return the min Z of the ranges of Z of all shapes contained
	 */
	public double getMinZ() {
		return minZ;
	}

	/**
	 * Sets the min Z of the ranges of Z of all shapes contained.
	 *
	 * @param minZ
	 *            the min Z of the ranges of Z of all shapes contained
	 */
	public void setMinZ(final double minZ) {
		this.minZ = minZ;
	}

	/**
	 * Returns the max Z of the ranges of Z of all shapes contained.
	 *
	 * @return the max Z of the ranges of Z of all shapes contained
	 */
	public double getMaxZ() {
		return maxZ;
	}

	/**
	 * Sets the max Z of the ranges of Z of all shapes contained.
	 *
	 * @param maxZ
	 *            the max Z of the ranges of Z of all shapes contained
	 */
	public void setMaxZ(final double maxZ) {
		this.maxZ = maxZ;
	}

	/**
	 * Returns the min M of the ranges of M of all shapes contained.
	 *
	 * @return the min M of the ranges of M of all shapes contained
	 */
	public double getMinM() {
		return minM;
	}

	/**
	 * Sets the min M of the ranges of M of all shapes contained.
	 *
	 * @param minM
	 *            the min M of the ranges of M of all shapes contained
	 */
	public void setMinM(final double minM) {
		this.minM = minM;
	}

	/**
	 * Returns the max M of the ranges of M of all shapes contained.
	 *
	 * @return the max M of the ranges of M of all shapes contained
	 */
	public double getMaxM() {
		return maxM;
	}

	/**
	 * Sets the max M of the ranges of M of all shapes contained.
	 *
	 * @param maxM
	 *            the max M of the ranges of M of all shapes contained
	 */
	public void setMaxM(final double maxM) {
		this.maxM = maxM;
	}

	@Override
	public String toString() {
		String output = " --- SHP HEADER ---";
		output += "\n\t- Header Size: " + headerSize;
		output += "\n\t- Shape type: " + shapeType;
		output += "\n\t- Min X: " + minX;
		output += "\n\t- Min Y: " + minY;
		output += "\n\t- Max X: " + maxX;
		output += "\n\t- Max Y: " + maxY;
		output += "\n\t- Min Z: " + minZ;
		output += "\n\t- Max Z: " + maxZ;
		output += "\n\t- Min M: " + minM;
		output += "\n\t- Max M: " + maxM;

		return output;
	}
}
