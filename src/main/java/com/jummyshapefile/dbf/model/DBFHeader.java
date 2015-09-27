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

import java.util.Date;
import java.util.List;

import com.jummyshapefile.binaryfile.model.BinaryFileHeader;

/**
 * Represents the header of a DFB file.
 * <p>
 * http://www.dbase.com/Knowledgebase/INT/db7_file_fmt.htm
 */
public class DBFHeader extends BinaryFileHeader {
	private Date dateModified;
	private int headerSize = -1;
	private int recordCount = -1;
	private int recordSize = -1;
	private int fieldCount = -1;
	private List<DBFFieldDescriptor> fields;

	/**
	 * Returns the date of modification of the file.
	 *
	 * @return the date of modification of the file
	 */
	public Date getDateModified() {
		return dateModified;
	}

	/**
	 * Sets the date of modification of the file.
	 *
	 * @param dateModified
	 *            the date of modification of the file
	 */
	public void setDateModified(final Date dateModified) {
		this.dateModified = dateModified;
	}

	/**
	 * Returns the size of the header of the file in bytes.
	 *
	 * @return the size of the header of the file in bytes
	 */
	public int getHeaderSize() {
		return headerSize;
	}

	/**
	 * Sets the size of the header of the file in bytes.
	 *
	 * @param headerSize
	 *            the size of the header of the file in bytes
	 */
	public void setHeaderSize(final int headerSize) {
		this.headerSize = headerSize;
	}

	/**
	 * Returns the number of records stored in the file.
	 *
	 * @return the number of records stored in the file
	 */
	public int getRecordCount() {
		return recordCount;
	}

	/**
	 * Sets the number of records stored in the file.
	 *
	 * @param recordCount
	 *            the number of records stored in the file
	 */
	public void setRecordCount(final int recordCount) {
		this.recordCount = recordCount;
	}

	/**
	 * Returns the size of each record in bytes.
	 *
	 * @return the size of each record in bytes
	 */
	public int getRecordSize() {
		return recordSize;
	}

	/**
	 * Sets the size of each record in bytes.
	 *
	 * @param recordSize
	 *            the size of each record in bytes
	 */
	public void setRecordSize(final int recordSize) {
		this.recordSize = recordSize;
	}

	/**
	 * Returns the number of fields stored in the DBF file.
	 *
	 * @return the number of fields stored in the DBF file
	 */
	public int getFieldCount() {
		return fieldCount;
	}

	/**
	 * Sets the number of fields stored in the DBF file
	 *
	 * @param fieldCount
	 *            the number of fields stored in the DBF file
	 */
	public void setFieldCount(final int fieldCount) {
		this.fieldCount = fieldCount;
	}

	/**
	 * Returns a list with the properties of each field.
	 *
	 * @return a list with the properties of each field
	 *
	 * @see DBFFieldDescriptor
	 */
	public List<DBFFieldDescriptor> getFields() {
		return fields;
	}

	/**
	 * Sets a list with the properties of each field.
	 *
	 * @param fields
	 *            a list with the properties of each field
	 *
	 * @see DBFFieldDescriptor
	 */
	public void setFields(final List<DBFFieldDescriptor> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		String output = " --- DBF HEADER ---";
		output += "\n\t- Date of last update: " + dateModified;
		output += "\n\t- Number of records in the table: " + recordCount;
		output += "\n\t- Number of bytes in the header: " + headerSize;
		output += "\n\t- Number of bytes in the record: " + recordSize;
		output += "\n\t- Number of fields: " + fieldCount;
		if (fields != null) {
			int i = 0;
			for (final DBFFieldDescriptor field : fields) {
				output += "\n\t\t(" + (++i) + ") " + field;
			}
		}
		return output;
	}
}
