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
import java.io.InputStream;

import com.jummyshapefile.binaryfile.BinaryFileHeaderReader;
import com.jummyshapefile.shp.model.SHPHeader;
import com.jummyshapefile.utils.ByteUtils;

/**
 * Class that reads the header of a SHP file.
 * <p>
 * https://en.m.wikipedia.org/wiki/Shapefile
 *
 * @see SHPHeader
 */
public class SHPHeaderReader implements BinaryFileHeaderReader<SHPHeader> {
	/**
	 * Returns a SHXHeader object representing the header of a SHP file.
	 *
	 * @param is
	 *            the InputStream for the file
	 * @return SHPHeader object representing the header of a SHP file
	 * @throws IOException
	 *             if there is any problem reading the header
	 *
	 * @see SHPHeader
	 */
	public SHPHeader readHeader(final InputStream is) throws IOException {
		SHPHeader header = null;

		if (is == null) {
			throw new IllegalArgumentException("The InputStream is null");
		}

		final byte[] headerBytes = new byte[SHPParameters.SHP_HEADER_LENGTH];
		final int read = is.read(headerBytes);
		if (read == SHPParameters.SHP_HEADER_LENGTH) {
			header = new SHPHeader();
			header.setShapeType(ByteUtils.readIntLeastSignificantFirst(
					headerBytes, SHPParameters.SHP_HEADER_SHAPETYPE_SIZE,
					SHPParameters.SHP_HEADER_SHAPETYPE_OFFSET));
			header.setMinX(ByteUtils.readDoubleLeastSignificantFirst(
					headerBytes, SHPParameters.SHP_HEADER_MBRMINX_SIZE,
					SHPParameters.SHP_HEADER_MBRMINX_OFFSET));
			header.setMinY(ByteUtils.readDoubleLeastSignificantFirst(
					headerBytes, SHPParameters.SHP_HEADER_MBRMINY_SIZE,
					SHPParameters.SHP_HEADER_MBRMINY_OFFSET));
			header.setMaxX(ByteUtils.readDoubleLeastSignificantFirst(
					headerBytes, SHPParameters.SHP_HEADER_MBRMAXX_SIZE,
					SHPParameters.SHP_HEADER_MBRMAXX_OFFSET));
			header.setMaxY(ByteUtils.readDoubleLeastSignificantFirst(
					headerBytes, SHPParameters.SHP_HEADER_MBRMAXY_SIZE,
					SHPParameters.SHP_HEADER_MBRMAXY_OFFSET));
			header.setMinZ(ByteUtils.readDoubleLeastSignificantFirst(
					headerBytes, SHPParameters.SHP_HEADER_MBRMINZ_SIZE,
					SHPParameters.SHP_HEADER_MBRMINZ_OFFSET));
			header.setMaxZ(ByteUtils.readDoubleLeastSignificantFirst(
					headerBytes, SHPParameters.SHP_HEADER_MBRMAXZ_SIZE,
					SHPParameters.SHP_HEADER_MBRMAXZ_OFFSET));
			header.setMinM(ByteUtils.readDoubleLeastSignificantFirst(
					headerBytes, SHPParameters.SHP_HEADER_MBRMINM_SIZE,
					SHPParameters.SHP_HEADER_MBRMINM_OFFSET));
			header.setMaxM(ByteUtils.readDoubleLeastSignificantFirst(
					headerBytes, SHPParameters.SHP_HEADER_MBRMAXM_SIZE,
					SHPParameters.SHP_HEADER_MBRMAXM_OFFSET));
		}

		return header;
	}
}
