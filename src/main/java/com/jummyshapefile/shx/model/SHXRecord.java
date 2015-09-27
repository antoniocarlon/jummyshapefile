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

package com.jummyshapefile.shx.model;

/**
 * Represents a record in a SHX file.
 * <p>
 * https://en.m.wikipedia.org/wiki/Shapefile
 */
public class SHXRecord {
	private int recordNum;
	private int offset;
	private int length;

	/**
	 * Returns the number of the record in the SHX file.
	 *
	 * @return the number of the record in the SHX file
	 */
	public int getRecordNum() {
		return recordNum;
	}

	/**
	 * Sets the number of the record in the SHX file.
	 *
	 * @param recordNum
	 *            the number of the record in the SHX file
	 */
	public void setRecordNum(final int recordNum) {
		this.recordNum = recordNum;
	}

	/**
	 * Returns the record offset in the SHP file.
	 *
	 * @return the record offset in the SHP file
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * Sets the record offset in the SHP file.
	 *
	 * @param offset
	 *            the record offset in the SHP file
	 */
	public void setOffset(final int offset) {
		this.offset = offset;
	}

	/**
	 * Returns the record length in the SHP file.
	 *
	 * @return the record length in the SHP file
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Sets the record length in the SHP file.
	 *
	 * @param offset
	 *            the record length in the SHP file
	 */
	public void setLength(final int length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return recordNum + ": " + length + " bytes @ " + offset;
	}
}
