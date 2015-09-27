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

package com.jummyshapefile.shx;

import java.io.IOException;

import com.jummyshapefile.dbf.model.DBFHeader;
import com.jummyshapefile.shx.model.SHXHeader;
import com.jummyshapefile.shx.model.SHXRecord;
import com.jummyshapefile.utils.ByteUtils;

/**
 * Class that reads a record in a SHX file.
 * <p>
 * https://en.m.wikipedia.org/wiki/Shapefile
 *
 * @see SHXRecord
 * @see SHXHeader
 */
public class SHXRecordReader {
	/**
	 * Returns a SHXRecord object representing the byte array.
	 *
	 * @param data
	 *            the byte array representing the record
	 * @return a SHXRecord object representing the byte array
	 * @throws IOException
	 *             if there is a problem reading the record
	 *
	 * @see SHXRecord
	 * @see DBFHeader
	 */
	public SHXRecord readRecord(final int recordNum, final byte[] data)
			throws IOException {
		SHXRecord output = null;

		if (data == null) {
			throw new IllegalArgumentException("The byte array is null");
		}

		output = new SHXRecord();
		output.setRecordNum(recordNum);
		// NOTE: The value returned is in 16-bit words
		output.setOffset(2 * ByteUtils.readInt(data,
				SHXParameters.SHX_RECORD_RECORDOFFSET_SIZE,
				SHXParameters.SHX_RECORD_RECORDOFFSET_OFFSET));
		// NOTE: The value returned is in 16-bit words
		output.setLength(2 * ByteUtils.readInt(data,
				SHXParameters.SHX_RECORD_RECORDLENGTH_SIZE,
				SHXParameters.SHX_RECORD_RECORDLENGTH_OFFSET));

		return output;
	}
}
