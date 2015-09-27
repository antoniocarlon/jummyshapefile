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

package com.jummyshapefile.prj;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import com.jummyshapefile.dbf.model.DBFFieldDescriptor;
import com.jummyshapefile.dbf.model.DBFHeader;
import com.jummyshapefile.dbf.model.DBFRecord;
import com.jummyshapefile.utils.FileUtils;

/**
 * Class representing a PRJ file resource.
 *
 * @see DBFHeader
 * @see DBFFieldDescriptor
 * @see DBFRecord
 */
public class PRJFile implements Closeable {
	private String projectionWKT;

	/**
	 * Opens a PRJ file and reads it.
	 *
	 * @param is
	 *            the InputStream representing the PRJ file
	 * @throws IOException
	 *             if there is a problem opening the PRJ file
	 */
	public void open(final InputStream is) throws IOException {
		if (is == null) {
			throw new IllegalArgumentException("The InputStream is null");
		}

		projectionWKT = FileUtils.readFile(is, Charset.defaultCharset());
	}

	/**
	 * Returns a String object representing the projection in ESRI WKT format.
	 *
	 * @return a String object representing the projection in ESRI WKT format
	 */
	public String getProjectionWKT() {
		return projectionWKT;
	}

	/**
	 * Closes the PRJ file releasing all the resources.
	 */
	public void close() {
		// PRJ file has nothing to close. Kept to maintaint symmetry
	}
}
