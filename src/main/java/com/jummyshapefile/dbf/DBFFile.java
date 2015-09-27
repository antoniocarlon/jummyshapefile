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

package com.jummyshapefile.dbf;

import java.io.IOException;
import java.util.List;

import com.jummyshapefile.binaryfile.AbstractBinaryFile;
import com.jummyshapefile.dbf.model.DBFFieldDescriptor;
import com.jummyshapefile.dbf.model.DBFHeader;
import com.jummyshapefile.dbf.model.DBFRecord;

/**
 * Class representing a DBF file resource.
 * <p>
 * http://www.dbase.com/Knowledgebase/INT/db7_file_fmt.htm
 *
 * @see DBFHeader
 * @see DBFFieldDescriptor
 * @see DBFRecord
 */
public class DBFFile extends AbstractBinaryFile<DBFHeader> {
	@Override
	public void init() {
		headerReader = new DBFHeaderReader();
	}

	/**
	 * Returns the number of records stored in the DBF file.
	 *
	 * @return the number of records stored in the DBF file
	 */
	public int getRecordCount() {
		return header.getRecordCount();
	}

	/**
	 * Returns a list of DBFFieldDescriptor objects representing the fields of
	 * the DBF file.
	 *
	 * @return a list of DBFFieldDescriptor objects representing the fields of
	 *         the DBF file
	 *
	 * @see DBFFieldDescriptor
	 */
	public List<DBFFieldDescriptor> getFields() {
		return header.getFields();
	}

	/**
	 * Returns a DBFRecord object representing a record of the DBF file (defined
	 * by recordNum).
	 *
	 * @param recordNum
	 *            the number of the record to query
	 * @return a DBFRecord object representing a record of the DBF file (defined
	 *         by recordNum)
	 * @throws IndexOutOfBoundsException
	 *             if there is no record that matches recordNum
	 * @throws IOException
	 *             if there is a problem reading the record
	 *
	 * @see DBFRecord
	 */
	public DBFRecord getRecordAt(final int recordNum)
			throws IndexOutOfBoundsException, IOException {
		DBFRecord output = null;

		if (recordNum <= 0 || recordNum > header.getRecordCount()) {
			throw new IndexOutOfBoundsException("Number of records: "
					+ header.getRecordCount() + ". Requested: " + recordNum);
		}

		mfis.reset();
		mfis.skip(header.getHeaderSize() + (recordNum - 1)
				* header.getRecordSize());

		final byte[] dataRead = new byte[header.getRecordSize()];
		final int bytesRead = mfis.read(dataRead);

		if (bytesRead != header.getRecordSize()) {
			throw new IOException("Unexpected data length read");
		}

		final DBFRecordReader recordReader = new DBFRecordReader();
		output = recordReader.readRecord(recordNum, dataRead, header);

		return output;
	}
}
