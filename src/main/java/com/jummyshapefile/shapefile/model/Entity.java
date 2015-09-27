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

import java.util.ArrayList;
import java.util.List;

import com.jummyshapefile.dbf.model.DBFField;

/**
 * Class representing a entity of the Shapefile.
 */
public class Entity {
	private int recordNum;
	private int shapeType;
	private Geometry geometry;
	private List<DBFField> data = new ArrayList<DBFField>();

	/**
	 * Returns the number of the record in the shapefile.
	 *
	 * @return the number of the record in the shapefile
	 */
	public int getRecordNum() {
		return recordNum;
	}

	/**
	 * Sets the number of the record in the shapefile.
	 *
	 * @param recordNum
	 *            the number of the record in the shapefile
	 */
	public void setRecordNum(final int recordNum) {
		this.recordNum = recordNum;
	}

	/**
	 * Returns the shape type.
	 * <p>
	 * Takes into account only the following shape types (defined by the ESRI
	 * Shapefile format):
	 * <ul>
	 * <li>0 Null shape
	 * <li>1 Point
	 * <li>3 Polyline
	 * <li>5 Polygon
	 * <li>8 MultiPoint
	 *
	 * @return the shape type
	 */
	public int getShapeType() {
		return shapeType;
	}

	/**
	 * Sets the shape type.
	 * <p>
	 * Takes into account only the following shape types (defined by the ESRI
	 * Shapefile format):
	 * <ul>
	 * <li>0 Null shape
	 * <li>1 Point
	 * <li>3 Polyline
	 * <li>5 Polygon
	 * <li>8 MultiPoint
	 *
	 * @param shapeType
	 *            the shape type
	 */
	public void setShapeType(final int shapeType) {
		this.shapeType = shapeType;
	}

	/**
	 * Returns the geometry of the entity.
	 *
	 * @return the geometry of the entity
	 */
	public Geometry getGeometry() {
		return geometry;
	}

	/**
	 * Sets the geometry of the entity.
	 *
	 * @param geometry
	 *            the geometry of the entity
	 */
	public void setGeometry(final Geometry geometry) {
		this.geometry = geometry;
	}

	/**
	 * Returns the data of the entity.
	 *
	 * @return the data of the entity
	 */
	public List<DBFField> getData() {
		return data;
	}

	/**
	 * Sets the data of the entity.
	 *
	 * @param data
	 *            the data of the entity
	 */
	public void setData(final List<DBFField> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ENTITY: " + recordNum + " | " + geometry + " | " + data;
	}
}
