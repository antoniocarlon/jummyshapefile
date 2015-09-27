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

import com.jummyshapefile.binaryfile.AbstractBinaryFile;
import com.jummyshapefile.shx.model.SHXHeader;
import com.jummyshapefile.shx.model.SHXRecord;

/**
 * Class representing a SHX file resource.
 * <p>
 * https://en.m.wikipedia.org/wiki/Shapefile
 */
public class SHXFile extends AbstractBinaryFile<SHXHeader> {
	@Override
	public void init() {
		headerReader = new SHXHeaderReader();
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
	 * @see SHXRecord
	 */
	public SHXRecord getRecordAt(final int recordNum)
			throws IndexOutOfBoundsException, IOException {
		SHXRecord output = null;

		if (recordNum <= 0) {
			throw new IndexOutOfBoundsException("Requested: " + recordNum);
		}

		mfis.reset();
		mfis.skip(header.getHeaderSize() + (recordNum - 1)
				* header.getRecordSize());

		final byte[] dataRead = new byte[header.getRecordSize()];
		final int bytesRead = mfis.read(dataRead);

		if (bytesRead != header.getRecordSize()) {
			throw new IOException("Unexpected data length read");
		}

		final SHXRecordReader recordReader = new SHXRecordReader();
		output = recordReader.readRecord(recordNum, dataRead);

		return output;
	}
}
