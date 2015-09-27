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

import java.util.List;

/**
 * Represents a record in a DBF file.
 */
public class DBFRecord {
	private int recordNum;
	private boolean active = true;
	private List<DBFField> fields;

	/**
	 * Returns the number of the record in the DBF file.
	 *
	 * @return the number of the record in the DBF file
	 */
	public int getRecordNum() {
		return recordNum;
	}

	/**
	 * Sets the number of the record in the DBF file.
	 *
	 * @param recordNum
	 *            the number of the record in the DBF file
	 */
	public void setRecordNum(final int recordNum) {
		this.recordNum = recordNum;
	}

	/**
	 * Returns whether the record is active.
	 *
	 * @return record is active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets whether the record is active.
	 *
	 * @param active
	 *            record is active
	 */
	public void setActive(final boolean active) {
		this.active = active;
	}

	/**
	 * Returns a list with the fields of the record.
	 *
	 * @return a list with the fields of the record
	 *
	 * @see DBFField
	 */
	public List<DBFField> getFields() {
		return fields;
	}

	/**
	 * Sets a list with the fields of the record.
	 *
	 * @param fields
	 *            a list with the fields of the record
	 *
	 * @see DBFField
	 */
	public void setFields(final List<DBFField> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		return recordNum + " (" + active + ") -- " + fields;
	}
}
