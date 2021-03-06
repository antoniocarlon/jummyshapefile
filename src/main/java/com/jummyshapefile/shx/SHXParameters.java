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

public final class SHXParameters {
	public static final int SHX_HEADER_LENGTH = 100;
	public static final int SHX_RECORD_LENGTH = 8;

	public static final int SHX_HEADER_SHAPETYPE_OFFSET = 32;
	public static final int SHX_HEADER_SHAPETYPE_SIZE = 4;

	public static final int SHX_HEADER_MBRMINX_OFFSET = 36;
	public static final int SHX_HEADER_MBRMINX_SIZE = 8;
	public static final int SHX_HEADER_MBRMINY_OFFSET = 44;
	public static final int SHX_HEADER_MBRMINY_SIZE = 8;
	public static final int SHX_HEADER_MBRMAXX_OFFSET = 52;
	public static final int SHX_HEADER_MBRMAXX_SIZE = 8;
	public static final int SHX_HEADER_MBRMAXY_OFFSET = 60;
	public static final int SHX_HEADER_MBRMAXY_SIZE = 8;

	public static final int SHX_HEADER_MBRMINZ_OFFSET = 68;
	public static final int SHX_HEADER_MBRMINZ_SIZE = 8;
	public static final int SHX_HEADER_MBRMAXZ_OFFSET = 76;
	public static final int SHX_HEADER_MBRMAXZ_SIZE = 8;

	public static final int SHX_HEADER_MBRMINM_OFFSET = 84;
	public static final int SHX_HEADER_MBRMINM_SIZE = 8;
	public static final int SHX_HEADER_MBRMAXM_OFFSET = 92;
	public static final int SHX_HEADER_MBRMAXM_SIZE = 8;

	public static final int SHX_RECORD_RECORDOFFSET_OFFSET = 0;
	public static final int SHX_RECORD_RECORDOFFSET_SIZE = 4;
	public static final int SHX_RECORD_RECORDLENGTH_OFFSET = 4;
	public static final int SHX_RECORD_RECORDLENGTH_SIZE = 4;
}
