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

package com.jummyshapefile.shp.model;

/**
 * Represents the header of a record in a SHP file.
 * <p>
 * https://en.m.wikipedia.org/wiki/Shapefile
 */
public class SHPRecordHeader {
	private int recordNum;
	private int recordLength;

	/**
	 * Returns the number of the record in the SHP file.
	 *
	 * @return the number of the record in the SHP file
	 */
	public int getRecordNum() {
		return recordNum;
	}

	/**
	 * Sets the number of the record in the SHP file.
	 *
	 * @param recordNum
	 *            the number of the record in the SHP file
	 */
	public void setRecordNum(final int recordNum) {
		this.recordNum = recordNum;
	}

	/**
	 * Returns the length of the record in the SHP file.
	 *
	 * @return the length of the record in the SHP file
	 */
	public int getRecordLength() {
		return recordLength;
	}

	/**
	 * Sets the length of the record in the SHP file.
	 *
	 * @param recordLength
	 *            the length of the record in the SHP file
	 */
	public void setRecordLength(final int recordLength) {
		this.recordLength = recordLength;
	}

	@Override
	public String toString() {
		return recordNum + ": " + recordLength + " bytes";
	}
}
