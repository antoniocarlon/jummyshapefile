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

package com.jummyshapefile.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * Utility class to work with files.
 */
public final class FileUtils {
	/**
	 * Closes an InputStream.
	 *
	 * @param is
	 *            InputStream to close
	 */
	public static void closeInputStream(InputStream is) {
		if (is != null) {
			try {
				is.close();
				is = null;
			} catch (final IOException ioe) {
				// Ignore
			}
		}
	}

	/**
	 * Reads a file.
	 *
	 * @param is
	 *            InputStream for the file
	 * @param encoding
	 *            encoding for the file
	 * @return the String retpresentation of the file
	 * @throws IOException
	 */
	public static String readFile(final InputStream is, final Charset encoding)
			throws IOException {
		if (is == null) {
			throw new IllegalArgumentException("The InputStream is null");
		}
		if (encoding == null) {
			throw new IllegalArgumentException("The Charset is null");
		}

		final Reader reader = new BufferedReader(new InputStreamReader(is,
				encoding));
		final StringBuilder builder = new StringBuilder();
		final char[] buffer = new char[8192];
		int read;
		while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
			builder.append(buffer, 0, read);
		}
		return builder.toString();
	}
}
