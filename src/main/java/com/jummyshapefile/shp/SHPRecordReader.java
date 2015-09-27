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

package com.jummyshapefile.shp;

import java.io.IOException;

import com.jummyshapefile.shp.model.SHPHeader;
import com.jummyshapefile.shp.model.SHPRecord;
import com.jummyshapefile.shp.model.SHPRecordHeader;
import com.jummyshapefile.utils.ByteUtils;

/**
 * Class that reads a record in a SHP file.
 * <p>
 * https://en.m.wikipedia.org/wiki/Shapefile
 *
 * @see SHPRecord
 * @see SHPRecordHeader
 * @see SHPHeader
 */
public class SHPRecordReader {
	/**
	 * Returns a SHPRecordHeader object representing the byte array.
	 *
	 * @param data
	 *            the byte array representing the record header
	 *
	 * @return a SHPRecordHeader object representing the byte array
	 * @throws IOException
	 *             if there is a problem reading the record header
	 *
	 * @see SHPRecordHeader
	 */
	public SHPRecordHeader readRecordHeader(final byte[] data)
			throws IOException {
		SHPRecordHeader output = null;

		if (data == null) {
			throw new IllegalArgumentException("The byte array is null");
		}

		output = new SHPRecordHeader();
		output.setRecordNum(ByteUtils.readInt(data,
				SHPParameters.SHP_RECORDHEADER_RECORDNUMBER_SIZE,
				SHPParameters.SHP_RECORDHEADER_RECORDNUMBER_OFFSET));
		// NOTE: The value returned is in 16-bit words
		output.setRecordLength(2 * ByteUtils.readInt(data,
				SHPParameters.SHP_RECORDHEADER_RECORDLENGTH_SIZE,
				SHPParameters.SHP_RECORDHEADER_RECORDLENGTH_OFFSET));

		return output;
	}

	/**
	 * Returns a SHPRecord object representing the byte array.
	 *
	 * @param data
	 *            the byte array representing the record
	 *
	 * @return a SHPRecord object representing the byte array
	 * @throws IOException
	 *             if there is a problem reading the record
	 *
	 * @see SHPRecord
	 */
	public SHPRecord readRecord(final byte[] data) throws IOException {
		SHPRecord output = null;

		if (data == null) {
			throw new IllegalArgumentException("The byte array is null");
		}

		output = new SHPRecord();
		output.setShapeType(ByteUtils.readIntLeastSignificantFirst(data,
				SHPParameters.SHP_RECORDHEADER_SHAPETYPE_SIZE,
				SHPParameters.SHP_RECORDHEADER_SHAPETYPE_OFFSET));

		final SHPGeometryReader geometryReader = new SHPGeometryReader();
		output.setGeometry(geometryReader.readGeometry(data,
				output.getShapeType()));

		return output;
	}
}
