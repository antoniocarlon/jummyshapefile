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

import com.jummyshapefile.shapefile.model.Geometry;

/**
 * Represents a record in a SHP file.
 * <p>
 * https://en.m.wikipedia.org/wiki/Shapefile
 *
 * @see SHPRecordHeader
 * @see Geometry
 */
public class SHPRecord {
	private SHPRecordHeader recordHeader;
	private int shapeType = 0;
	private Geometry geometry;

	/**
	 * Returns a SHPRecordHeader object representing the record header.
	 *
	 * @return a SHPRecordHeader object representing the record header
	 *
	 * @see SHPRecord
	 */
	public SHPRecordHeader getRecordHeader() {
		return recordHeader;
	}

	/**
	 * Sets the record header.
	 *
	 * @param recordHeader
	 *            a SHPRecordHeader object representing the record header
	 *
	 * @see SHPRecord
	 */
	public void setRecordHeader(final SHPRecordHeader recordHeader) {
		this.recordHeader = recordHeader;
	}

	/**
	 * Returns the type of the shape.
	 *
	 * @return the type of the shape
	 */
	public int getShapeType() {
		return shapeType;
	}

	/**
	 * Sets the type of the shape.
	 *
	 * @param shapeType
	 *            the type of the shape
	 */
	public void setShapeType(final int shapeType) {
		this.shapeType = shapeType;
	}

	/**
	 * Returns the geometry of the record.
	 *
	 * @return the geometry of the record
	 */
	public Geometry getGeometry() {
		return geometry;
	}

	/**
	 * Sets the geometry of the record.
	 *
	 * @param geometry
	 *            the geometry of the record
	 */
	public void setGeometry(final Geometry geometry) {
		this.geometry = geometry;
	}

	@Override
	public String toString() {
		return recordHeader + " / Shape type: " + shapeType + " // " + geometry;
	}
}
