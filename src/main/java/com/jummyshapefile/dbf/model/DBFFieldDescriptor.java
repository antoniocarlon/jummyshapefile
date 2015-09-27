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

package com.jummyshapefile.dbf.model;

/**
 * Represents the properties of a DBF Field.
 * <p>
 * http://www.dbase.com/Knowledgebase/INT/db7_file_fmt.htm
 */
public class DBFFieldDescriptor {
	private String name;
	private String type;
	private int length = -1;
	private int decimalCount = -1;

	/**
	 * Returns the name of the DBF field.
	 *
	 * @return the name of the DBF field
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the DBF field.
	 *
	 * @param the
	 *            name of the DBF field
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Returns the type of the field, as defined in the documentation.
	 * <p>
	 * Field type in ASCII (B, C, D, N, L, M, @, I, +, F, 0 or G).
	 *
	 * @return the type of the field, as defined in the documentation
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type of the field, as defined in the documentation.
	 * <p>
	 * Field type in ASCII (B, C, D, N, L, M, @, I, +, F, 0 or G).
	 *
	 * @param type
	 *            the type of the field, as defined in the documentation
	 */
	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * Returns the length of the field in bytes.
	 *
	 * @return the length of the field in bytes
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Sets the length of the field in bytes.
	 *
	 * @param length
	 *            the length of the field in bytes
	 */
	public void setLength(final int length) {
		this.length = length;
	}

	/**
	 * Returns the field decimal count.
	 *
	 * @return the field decimal count
	 */
	public int getDecimalCount() {
		return decimalCount;
	}

	/**
	 * Sets the field decimal count.
	 *
	 * @param decimalCount
	 *            the decimal count
	 */
	public void setDecimalCount(final int decimalCount) {
		this.decimalCount = decimalCount;
	}

	@Override
	public String toString() {
		return "--- FIELD DESCRIPTOR: " + name + " / " + type + " / " + length
				+ " / " + decimalCount;
	}
}
