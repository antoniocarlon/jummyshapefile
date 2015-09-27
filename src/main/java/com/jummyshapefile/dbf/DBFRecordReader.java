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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jummyshapefile.dbf.model.DBFField;
import com.jummyshapefile.dbf.model.DBFFieldDescriptor;
import com.jummyshapefile.dbf.model.DBFHeader;
import com.jummyshapefile.dbf.model.DBFRecord;
import com.jummyshapefile.utils.ByteUtils;

/**
 * Class that reads a record in a DBF file.
 * <p>
 * Takes into account only the following data types (defined by the ESRI
 * Shapefile format):
 * <ul>
 * <li>Short Integer (N)
 * <li>Long Integer (N)
 * <li>Float (F)
 * <li>Double (F)
 * <li>Text (C)
 * <li>Date (D)
 * <p>
 * For more field types, see the documentation:
 * <p>
 * http://www.dbase.com/Knowledgebase/INT/db7_file_fmt.htm
 *
 * @see DBFRecord
 * @see DBFHeader
 * @see DBFField
 * @see DBFFieldDescriptor
 */
public class DBFRecordReader {
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	/**
	 * Returns a DBFRecord object representing the byte array.
	 * <p>
	 * This class takes into account only the following data types (defined by
	 * the ESRI Shapefile format):
	 * <ul>
	 * <li>Short Integer (N)
	 * <li>Long Integer (N)
	 * <li>Float (F)
	 * <li>Double (F)
	 * <li>Text (C)
	 * <li>Date (D)
	 *
	 * @param data
	 *            the byte array representing the record
	 * @param header
	 *            the DBFHeader object representing the header of the file
	 * @return a DBFRecord object representing the byte array
	 * @throws IOException
	 *             if there is a problem reading the record
	 *
	 * @see DBFRecord
	 * @see DBFHeader
	 */
	public DBFRecord readRecord(final int recordNum, final byte[] data,
			final DBFHeader header) throws IOException {
		DBFRecord output = null;

		if (data == null) {
			throw new IllegalArgumentException("The byte array is null");
		}
		if (header == null) {
			throw new IllegalArgumentException("The header is null");
		}

		final List<DBFField> fields = readFields(data, header.getFields());

		output = new DBFRecord();
		output.setRecordNum(recordNum);
		output.setActive(isActive(data));
		output.setFields(fields);

		return output;
	}

	/**
	 * Returns whether the record is active.
	 *
	 * @param data
	 *            the byte array representing the record
	 * @return whether the record is active
	 * @throws IOException
	 *             if there is a problem reading the record
	 */
	private boolean isActive(final byte[] data) throws IOException {
		if (data == null) {
			throw new IllegalArgumentException("The byte array is null");
		}

		return data[0] != DBFParameters.DBF_RECORD_DELETED;
	}

	/**
	 * Returns a list of the fields read from the byte array.
	 *
	 * @param data
	 *            the byte array representing the record
	 * @param descriptors
	 *            the list of descriptors for each field
	 * @return a list of the fields read from the byte array
	 * @throws IOException
	 *             if there is a problem reading the fields
	 *
	 * @see DBFField
	 * @see DBFFieldDescriptor
	 */
	private List<DBFField> readFields(final byte[] data,
			final List<DBFFieldDescriptor> descriptors) throws IOException {
		List<DBFField> output = null;

		if (data == null) {
			throw new IllegalArgumentException("The byte array is null");
		}
		if (descriptors == null) {
			throw new IllegalArgumentException(
					"The list of field descriptors is null");
		}

		output = new ArrayList<DBFField>();

		int offset = 0;
		for (final DBFFieldDescriptor descriptor : descriptors) {
			final DBFField field = readField(data, offset, descriptor);
			if (field != null) {
				output.add(field);
			}
			offset += descriptor.getLength();
		}

		return output;
	}

	/**
	 * Returns a DBFField object representing the field read from the byte
	 * array.
	 * <p>
	 * This class takes into account only the following data types (defined by
	 * the ESRI Shapefile format):
	 * <ul>
	 * <li>Short Integer (N)
	 * <li>Long Integer (N)
	 * <li>Float (F)
	 * <li>Double (F)
	 * <li>Text (C)
	 * <li>Date (D)
	 *
	 * @param data
	 *            the byte array representing the record
	 * @param offset
	 *            the offset of the field in the byte array
	 * @param descriptor
	 *            the descriptor for the field
	 * @return a DBFField object representing the field read from the byte array
	 * @throws IOException
	 *             if there is a problem reading the field
	 *
	 * @see DBFField
	 * @see DBFFieldDescriptor
	 */
	private DBFField readField(final byte[] data, final int offset,
			final DBFFieldDescriptor descriptor) throws IOException {
		DBFField output = null;

		if (data == null) {
			throw new IllegalArgumentException("The byte array is null");
		}

		final String fieldString = ByteUtils.readString(data,
				descriptor.getLength(),
				DBFParameters.DBF_RECORD_DATA_OFFSET + offset).trim();

		// Integer (N), Long Integer (N)
		if ("N".equalsIgnoreCase(descriptor.getType())) {
			output = readNField(fieldString, descriptor);
		}
		// Float (F), Double (F)
		else if ("F".equalsIgnoreCase(descriptor.getType())) {
			output = readFField(fieldString, descriptor);
		}
		// Text (C)
		else if ("C".equalsIgnoreCase(descriptor.getType())) {
			output = readCField(fieldString, descriptor);
		}
		// Date (D)
		else if ("D".equalsIgnoreCase(descriptor.getType())) {
			output = readDField(fieldString, descriptor);
		}

		if (output != null) {
			output.setDescriptor(descriptor);
		}

		return output;
	}

	/**
	 * Returns a DBField<Long> object representing the field.
	 *
	 * @param fieldValue
	 *            the String value of the field
	 * @param descriptor
	 *            the parameters of the field
	 * @return a DBField<Long> object representing the field
	 *
	 * @see DBFField
	 * @see DBFFieldDescriptor
	 */
	private DBFField readNField(final String fieldValue,
			final DBFFieldDescriptor descriptor) {
		final DBFField output = new DBFField<Long>();
		output.setDescriptor(descriptor);

		if (fieldValue != null) {
			output.setValue(Double.parseDouble(fieldValue));
		}

		return output;
	}

	/**
	 * Returns a DBField<Double> object representing the field.
	 *
	 * @param fieldValue
	 *            the String value of the field
	 * @param descriptor
	 *            the parameters of the field
	 * @return a DBField<Double> object representing the field
	 *
	 * @see DBFField
	 * @see DBFFieldDescriptor
	 */
	private DBFField readFField(final String fieldValue,
			final DBFFieldDescriptor descriptor) {
		final DBFField output = new DBFField<Double>();
		output.setDescriptor(descriptor);

		if (fieldValue != null) {
			output.setValue(Double.parseDouble(fieldValue));
		}

		return output;
	}

	/**
	 * Returns a DBField<String> object representing the field.
	 *
	 * @param fieldValue
	 *            the String value of the field
	 * @param descriptor
	 *            the parameters of the field
	 * @return a DBField<String> object representing the field
	 *
	 * @see DBFField
	 * @see DBFFieldDescriptor
	 */
	private DBFField readCField(final String fieldValue,
			final DBFFieldDescriptor descriptor) {
		final DBFField output = new DBFField<String>();
		output.setDescriptor(descriptor);

		output.setValue(fieldValue);

		return output;
	}

	/**
	 * Returns a DBField<Date> object representing the field.
	 *
	 * @param fieldValue
	 *            the String value of the field
	 * @param descriptor
	 *            the parameters of the field
	 * @return a DBField<Date> object representing the field
	 *
	 * @see DBFField
	 * @see DBFFieldDescriptor
	 */
	private DBFField readDField(final String fieldValue,
			final DBFFieldDescriptor descriptor) {
		final DBFField output = new DBFField<Date>();
		output.setDescriptor(descriptor);

		if (fieldValue != null) {
			try {
				output.setValue(sdf.parseObject(fieldValue));
			} catch (final ParseException pe) {
				// Do nothing
			}
		}

		return output;
	}
}
