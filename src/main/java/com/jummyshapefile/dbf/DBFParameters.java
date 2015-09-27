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

package com.jummyshapefile.dbf;

public final class DBFParameters {
	public static final int DATEOFLASTUPDATEYEAR_OFFSET = 1900;

	public static final int DBF_HEADER_LENGTH_WITHOUT_FIELDS = 32;
	public static final int DBF_HEADER_FIELD_LENGTH = 32;

	public static final int DBF_HEADER_DATEOFLASTUPDATEYEAR_OFFSET = 1;
	public static final int DBF_HEADER_DATEOFLASTUPDATEYEAR_SIZE = 1;
	public static final int DBF_HEADER_DATEOFLASTUPDATEMONTH_OFFSET = 2;
	public static final int DBF_HEADER_DATEOFLASTUPDATEMONTH_SIZE = 1;
	public static final int DBF_HEADER_DATEOFLASTUPDATEDAY_OFFSET = 3;
	public static final int DBF_HEADER_DATEOFLASTUPDATEDAY_SIZE = 1;

	public static final int DBF_HEADER_NUMBEROFRECORDS_OFFSET = 4;
	public static final int DBF_HEADER_NUMBEROFRECORDS_SIZE = 4;

	public static final int DBF_HEADER_NUMBEROFBYTESINHEADER_OFFSET = 8;
	public static final int DBF_HEADER_NUMBEROFBYTESINHEADER_SIZE = 2;

	public static final int DBF_HEADER_NUMBEROFBYTESINRECORD_OFFSET = 10;
	public static final int DBF_HEADER_NUMBEROFBYTESINRECORD_SIZE = 2;

	public static final int DBF_HEADERFIELD_NAME_OFFSET = 0;
	public static final int DBF_HEADERFIELD_NAME_SIZE = 11;

	public static final int DBF_HEADERFIELD_TYPE_OFFSET = 11;
	public static final int DBF_HEADERFIELD_TYPE_SIZE = 1;

	public static final int DBF_HEADERFIELD_LENGTH_OFFSET = 16;
	public static final int DBF_HEADERFIELD_LENGTH_SIZE = 1;

	public static final int DBF_HEADERFIELD_DECIMALCOUNT_OFFSET = 17;
	public static final int DBF_HEADERFIELD_DECIMALCOUNT_SIZE = 1;

	public static final byte DBF_RECORD_DELETED = 0x2A;
	public static final byte DBF_RECORD_DATA_OFFSET = 1;
}
