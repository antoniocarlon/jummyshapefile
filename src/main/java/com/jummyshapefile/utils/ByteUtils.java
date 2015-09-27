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

import java.nio.ByteBuffer;

/**
 * Utility class to work with bytes.
 */
public final class ByteUtils {
	/**
	 * Returns an int representing the bytes.
	 *
	 * @param bytes
	 *            the byte array
	 * @param length
	 *            the length of the chunk
	 * @param offset
	 *            the offset of the chunk
	 * @return an int representing the bytes
	 */
	public static final int readInt(final byte[] bytes, final int length,
			final int offset) {
		int output = -1;

		if (bytes == null) {
			return output;
		}

		final byte[] intBytes = new byte[length];
		for (int i = 0; i < intBytes.length; i++) {
			intBytes[i] = bytes[offset + i];
		}
		output = ByteUtils.toInt(intBytes, 0);

		return output;
	}

	/**
	 * Returns an int representing the bytes (least significant first).
	 *
	 * @param bytes
	 *            the byte array
	 * @param length
	 *            the length of the chunk
	 * @param offset
	 *            the offset of the chunk
	 * @return an int representing the bytes (least significant first)
	 */
	public static final int readIntLeastSignificantFirst(final byte[] bytes,
			final int length, final int offset) {
		int output = -1;

		if (bytes == null) {
			return output;
		}

		final byte[] intBytes = new byte[length];
		for (int i = 0; i < intBytes.length; i++) {
			intBytes[i] = bytes[offset + length - i - 1];
		}
		output = ByteUtils.toInt(intBytes, 0);

		return output;
	}

	/**
	 * Returns an String representing the bytes.
	 *
	 * @param bytes
	 *            the byte array
	 * @param length
	 *            the length of the chunk
	 * @param offset
	 *            the offset of the chunk
	 * @return an String representing the bytes
	 */
	public static final String readString(final byte[] bytes, final int length,
			final int offset) {
		String output = null;

		if (bytes == null) {
			return output;
		}

		final byte[] rowCountBytes = new byte[length];
		for (int i = 0; i < rowCountBytes.length; i++) {
			rowCountBytes[i] = bytes[offset + i];
		}
		output = new String(rowCountBytes);

		return output;
	}

	/**
	 * Returns a double representing the bytes.
	 *
	 * @param bytes
	 *            the byte array
	 * @param length
	 *            the length of the chunk
	 * @param offset
	 *            the offset of the chunk
	 * @return a double representing the bytes
	 */
	public static final double readDouble(final byte[] bytes, final int length,
			final int offset) {
		double output = -1d;

		if (bytes == null) {
			return output;
		}

		final byte[] doubleBytes = new byte[length];
		for (int i = 0; i < doubleBytes.length; i++) {
			doubleBytes[i] = bytes[offset + i];
		}
		output = ByteBuffer.wrap(doubleBytes).getDouble();

		return output;
	}

	/**
	 * Returns a double representing the bytes (least significant first).
	 *
	 * @param bytes
	 *            the byte array
	 * @param length
	 *            the length of the chunk
	 * @param offset
	 *            the offset of the chunk
	 * @return a double representing the bytes (least significant first)
	 */
	public static final double readDoubleLeastSignificantFirst(
			final byte[] bytes, final int length, final int offset) {
		double output = -1d;

		if (bytes == null) {
			return output;
		}

		final byte[] doubleBytes = new byte[length];
		for (int i = 0; i < doubleBytes.length; i++) {
			doubleBytes[i] = bytes[offset + length - i - 1];
		}
		output = ByteBuffer.wrap(doubleBytes).getDouble();

		return output;
	}

	/**
	 * Returns the integer representation of an array of bytes from a given
	 * offset.
	 *
	 * @param bytes
	 *            an array of bytes
	 * @param offset
	 *            the offset of the chunk
	 * @return the integer representation bytes
	 */
	private static final int toInt(final byte[] bytes, final int offset) {
		int output = 0;

		if (bytes == null) {
			return output;
		}

		for (int i = 0; i < 4 && i + offset < bytes.length; i++) {
			output <<= 8;
			output |= bytes[i] & 0xFF;
		}
		return output;
	}
}
