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

import com.jummyshapefile.binaryfile.AbstractBinaryFile;
import com.jummyshapefile.shp.model.SHPHeader;
import com.jummyshapefile.shp.model.SHPRecord;
import com.jummyshapefile.shp.model.SHPRecordHeader;
import com.jummyshapefile.shx.SHXFile;

/**
 * Class representing a SHP file resource.
 * <p>
 * Takes into account only the following shape types (defined by the ESRI
 * Shapefile format):
 * <ul>
 * <li>0 Null shape
 * <li>1 Point
 * <li>3 Polyline
 * <li>5 Polygon
 * <li>8 MultiPoint
 * <p>
 * For more field types, see the documentation:
 * <p>
 * <p>
 * https://en.m.wikipedia.org/wiki/Shapefile
 *
 * This class only takes into a
 */
public class SHPFile extends AbstractBinaryFile<SHPHeader> {
	private final SHPRecordReader recordReader = new SHPRecordReader();

	@Override
	public void init() {
		headerReader = new SHPHeaderReader();
	}

	/**
	 * Returns a SHPRecord object representing a record of the SHP file (defined
	 * by offset and length as the records in the SHP file has variable
	 * lengths).
	 *
	 * @param offset
	 *            the offset in bytes of the record to query (can be obtained
	 *            using the SHX file)
	 * @param length
	 *            the length in bytes of the record to query (can be obtained
	 *            using the SHX file)
	 *
	 * @return a SHPRecord object representing a record of the SHP file (defined
	 *         by offset and length as the records in the SHP file has variable
	 *         lengths)
	 * @throws IndexOutOfBoundsException
	 *             if there is no record that matches the given offset and
	 *             length
	 * @throws IOException
	 *             if there is a problem reading the record
	 *
	 * @see SHPRecord
	 * @see SHXFile
	 */
	public SHPRecord getRecord(final int offset, final int length)
			throws IndexOutOfBoundsException, IOException {
		SHPRecord output = null;

		if (offset < 0 || length <= 0) {
			throw new IndexOutOfBoundsException("Requested: " + length
					+ " bytes @ " + offset);
		}

		mfis.reset();
		mfis.skip(offset);

		final SHPRecordHeader recordHeader = getRecordHeader(offset);

		final byte[] dataRead = new byte[length];
		final int bytesRead = mfis.read(dataRead);

		if (bytesRead != length) {
			throw new IOException("Unexpected data length read");
		}

		output = recordReader.readRecord(dataRead);

		output.setRecordHeader(recordHeader);

		return output;
	}

	/**
	 * Returns a SHPRecordHeader object representing a record of the SHP file
	 * (defined by offset as the records in the SHP file has variable lengths).
	 *
	 * @param offset
	 *            the offset in bytes of the record to query (can be obtained
	 *            using the SHX file)
	 *
	 * @return a SHPRecordHeader object representing a record of the SHP file
	 *         (defined by offset as the records in the SHP file has variable
	 *         lengths)
	 * @throws IndexOutOfBoundsException
	 *             if there is no record that matches the given offset
	 * @throws IOException
	 *             if there is a problem reading the record
	 *
	 * @see SHPRecordHeader
	 * @see SHXFile
	 */
	private SHPRecordHeader getRecordHeader(final int offset)
			throws IndexOutOfBoundsException, IOException {
		SHPRecordHeader output = null;

		if (offset <= 0) {
			throw new IndexOutOfBoundsException("Requested: " + offset);
		}

		final byte[] dataRead = new byte[SHPParameters.SHP_RECORDHEADER_LENGTH];
		final int bytesRead = mfis.read(dataRead);

		if (bytesRead != SHPParameters.SHP_RECORDHEADER_LENGTH) {
			throw new IOException("Unexpected data length read");
		}

		output = recordReader.readRecordHeader(dataRead);

		return output;
	}
}
