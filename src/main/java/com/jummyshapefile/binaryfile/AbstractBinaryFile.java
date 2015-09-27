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

package com.jummyshapefile.binaryfile;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;

import com.jummyshapefile.binaryfile.model.BinaryFileHeader;
import com.jummyshapefile.io.MarkFileInputStream;
import com.jummyshapefile.utils.FileUtils;

/**
 * Class representing a binary file resource.
 *
 * @see BinaryFileHeader
 * @see BinaryFileHeaderReader
 */
public abstract class AbstractBinaryFile<T extends BinaryFileHeader> implements
Closeable {
	protected MarkFileInputStream mfis;
	protected T header;
	protected BinaryFileHeaderReader headerReader;

	/**
	 * Opens a binary file and reads its header.
	 *
	 * @param is
	 *            the InputStream representing the binary file
	 * @throws IOException
	 *             if there is a problem opening the binary file or reading the
	 *             header
	 */
	public void open(final FileInputStream is) throws IOException {
		if (is == null) {
			throw new IllegalArgumentException("The FileInputStream is null");
		}

		this.mfis = new MarkFileInputStream(is);
		mfis.mark(mfis.available() + 1);
		mfis.reset();

		init();
		readHeader();
	}

	/**
	 * Initializes the binary file.
	 */
	public abstract void init();

	/**
	 * Returns a BinaryFileHeader object representing the header of the binary
	 * file.
	 *
	 * @return a BinaryFileHeader object representing the header of the binary
	 *         file
	 *
	 * @see BinaryFileHeader
	 */
	public T getHeader() {
		return header;
	}

	/**
	 * Closes the binary file releasing all the resources.
	 */
	public void close() {
		FileUtils.closeInputStream(mfis);
	}

	/**
	 * Reads the header of the binary file.
	 *
	 * @throws IOException
	 *             if there is a problem reading the header of the binary file
	 */
	protected void readHeader() throws IOException {
		header = (T) headerReader.readHeader(mfis);

		if (header == null) {
			throw new IOException("The header is null");
		}
	}
}
