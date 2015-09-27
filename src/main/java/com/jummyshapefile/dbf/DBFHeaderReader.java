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
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jummyshapefile.binaryfile.BinaryFileHeaderReader;
import com.jummyshapefile.dbf.model.DBFFieldDescriptor;
import com.jummyshapefile.dbf.model.DBFHeader;
import com.jummyshapefile.utils.ByteUtils;

/**
 * Class that reads the header of a DBF file.
 * <p>
 * http://www.dbase.com/Knowledgebase/INT/db7_file_fmt.htm
 *
 * @see DBFHeader
 */
public class DBFHeaderReader implements BinaryFileHeaderReader<DBFHeader> {
	private final SimpleDateFormat sdf = new SimpleDateFormat("d/M/y");

	/**
	 * Returns a DBFHeader object representing the header of a DBF file.
	 *
	 * @param is
	 *            the InputStream for the file
	 * @return DBFHeader object representing the header of a DBF file
	 * @throws IOException
	 *             if there is any problem reading the header
	 *
	 * @see DBFHeader
	 */
	public DBFHeader readHeader(final InputStream is) throws IOException {
		DBFHeader header = null;

		if (is == null) {
			throw new IllegalArgumentException("The InputStream is null");
		}

		final byte[] headerBytes = new byte[DBFParameters.DBF_HEADER_LENGTH_WITHOUT_FIELDS];
		final int read = is.read(headerBytes);
		if (read == DBFParameters.DBF_HEADER_LENGTH_WITHOUT_FIELDS) {
			header = new DBFHeader();
			header.setDateModified(readDateModified(headerBytes));
			header.setRecordCount(ByteUtils.readIntLeastSignificantFirst(
					headerBytes, DBFParameters.DBF_HEADER_NUMBEROFRECORDS_SIZE,
					DBFParameters.DBF_HEADER_NUMBEROFRECORDS_OFFSET));
			header.setHeaderSize(ByteUtils.readIntLeastSignificantFirst(
					headerBytes,
					DBFParameters.DBF_HEADER_NUMBEROFBYTESINHEADER_SIZE,
					DBFParameters.DBF_HEADER_NUMBEROFBYTESINHEADER_OFFSET));
			header.setRecordSize(ByteUtils.readIntLeastSignificantFirst(
					headerBytes,
					DBFParameters.DBF_HEADER_NUMBEROFBYTESINRECORD_SIZE,
					DBFParameters.DBF_HEADER_NUMBEROFBYTESINRECORD_OFFSET));
			header.setFieldCount((header.getHeaderSize() - DBFParameters.DBF_HEADER_LENGTH_WITHOUT_FIELDS)
					/ DBFParameters.DBF_HEADER_FIELD_LENGTH);

			header.setFields(readFields(is, header.getFieldCount()));
		}

		return header;
	}

	/**
	 * Returns the modification date stored in the header of the DBF file.
	 *
	 * @param headerBytes
	 *            the byte array of the header of the DBF file
	 * @return the modification date stored in the header of the DBF file.
	 * @throws IOException
	 *             if there is a problem reading the date
	 */
	private Date readDateModified(final byte[] headerBytes) throws IOException {
		Date output = null;

		if (headerBytes == null) {
			throw new IllegalArgumentException("The byte array is null");
		}

		final int year = ByteUtils.readInt(headerBytes,
				DBFParameters.DBF_HEADER_DATEOFLASTUPDATEYEAR_SIZE,
				DBFParameters.DBF_HEADER_DATEOFLASTUPDATEYEAR_OFFSET)
				+ DBFParameters.DATEOFLASTUPDATEYEAR_OFFSET;
		final int month = ByteUtils.readInt(headerBytes,
				DBFParameters.DBF_HEADER_DATEOFLASTUPDATEMONTH_SIZE,
				DBFParameters.DBF_HEADER_DATEOFLASTUPDATEMONTH_OFFSET);
		final int day = ByteUtils.readInt(headerBytes,
				DBFParameters.DBF_HEADER_DATEOFLASTUPDATEDAY_SIZE,
				DBFParameters.DBF_HEADER_DATEOFLASTUPDATEDAY_OFFSET);

		try {
			output = sdf.parse(day + "/" + month + "/" + year);
		} catch (final ParseException pe) {
		}

		return output;
	}

	/**
	 * Returns a list of DBFFieldDescriptor objects representing the properties
	 * of the fields in a DBF file.
	 *
	 * @param is
	 *            InputStream for the DBF file
	 * @param numFields
	 *            the number of fields in the DBF file
	 * @return a list of DBFFieldDescriptor objects representing the properties
	 *         of the fields in a DBF file
	 * @throws IOException
	 *             if there is a problem reading the fields
	 *
	 * @see DBFFieldDescriptor
	 */
	private List<DBFFieldDescriptor> readFields(final InputStream is,
			final int numFields) throws IOException {
		final List<DBFFieldDescriptor> output = new ArrayList<DBFFieldDescriptor>();

		if (is == null) {
			throw new IllegalArgumentException("The InputStream is null");
		}

		for (int i = 0; i < numFields; i++) {
			final DBFFieldDescriptor field = readField(is);
			if (field != null) {
				output.add(field);
			}
		}

		return output;
	}

	/**
	 * Returns a DBFFieldDescriptor object representing the properties of a
	 * field in a DBF file.
	 *
	 * @param is
	 *            InputStream for the DBF file
	 * @return a DBFFieldDescriptor object representing the properties of a
	 *         field in a DBF file
	 * @throws IOException
	 *             if there is a problem reading the field
	 *
	 * @see DBFFieldDescriptor
	 */
	private DBFFieldDescriptor readField(final InputStream is)
			throws IOException {
		DBFFieldDescriptor output = null;

		if (is == null) {
			throw new IllegalArgumentException("The InputStream is null");
		}

		final byte[] fieldBytes = new byte[DBFParameters.DBF_HEADER_FIELD_LENGTH];
		final int read = is.read(fieldBytes);
		if (read == DBFParameters.DBF_HEADER_FIELD_LENGTH) {
			output = new DBFFieldDescriptor();
			output.setName(ByteUtils.readString(fieldBytes,
					DBFParameters.DBF_HEADERFIELD_NAME_SIZE,
					DBFParameters.DBF_HEADERFIELD_NAME_OFFSET).trim());
			output.setType(ByteUtils.readString(fieldBytes,
					DBFParameters.DBF_HEADERFIELD_TYPE_SIZE,
					DBFParameters.DBF_HEADERFIELD_TYPE_OFFSET).trim());
			output.setLength(ByteUtils.readInt(fieldBytes,
					DBFParameters.DBF_HEADERFIELD_LENGTH_SIZE,
					DBFParameters.DBF_HEADERFIELD_LENGTH_OFFSET));
			output.setDecimalCount(ByteUtils.readInt(fieldBytes,
					DBFParameters.DBF_HEADERFIELD_DECIMALCOUNT_SIZE,
					DBFParameters.DBF_HEADERFIELD_DECIMALCOUNT_OFFSET));
		}

		return output;
	}
}
