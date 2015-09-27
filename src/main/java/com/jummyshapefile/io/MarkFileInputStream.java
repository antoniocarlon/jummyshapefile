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

package com.jummyshapefile.io;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;

/**
 * Class that wraps a FileInputStream to provide mark/reset functionality.
 * <p>
 * Used to improve performance and memory usage over BufferedInputStream.
 */
public class MarkFileInputStream extends FilterInputStream {
	protected final FileInputStream fis;
	protected int marklimit = -1;
	protected long markpos = -1;

	public MarkFileInputStream(final FileInputStream fis) {
		super(fis);
		this.fis = fis;
	}

	@Override
	public synchronized void mark(final int readlimit) {
		marklimit = readlimit;
		markpos = -1;
		try {
			markpos = fis.getChannel().position();
		} catch (final IOException ex) {
			// Do nothing
		}
	}

	@Override
	public synchronized void reset() throws IOException {
		if (markpos == -1) {
			throw new IOException("Resetting to invalid mark");
		}
		fis.getChannel().position(markpos);
	}

	@Override
	public boolean markSupported() {
		return true;
	}
}
