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

package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import junit.framework.TestCase;

import com.jummyshapefile.dbf.DBFFile;
import com.jummyshapefile.dbf.model.DBFField;
import com.jummyshapefile.dbf.model.DBFFieldDescriptor;
import com.jummyshapefile.dbf.model.DBFHeader;
import com.jummyshapefile.dbf.model.DBFRecord;
import com.jummyshapefile.utils.FileUtils;

public class TestDBF extends TestCase {
	public void testReadHeader() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.dbf");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		DBFFile dbf = null;
		try {
			dbf = new DBFFile();
			dbf.open(is);

			final DBFHeader header = dbf.getHeader();

			assertNotNull(header);
		} finally {
			dbf.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testRecordCount() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.dbf");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		DBFFile dbf = null;
		try {
			dbf = new DBFFile();
			dbf.open(is);

			final DBFHeader header = dbf.getHeader();
			final int recordCount = header.getRecordCount();

			assertEquals(5, recordCount);
		} finally {
			dbf.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testHeaderSize() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.dbf");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		DBFFile dbf = null;
		try {
			dbf = new DBFFile();
			dbf.open(is);

			final DBFHeader header = dbf.getHeader();
			final int headerSize = header.getHeaderSize();

			assertEquals(289, headerSize);
		} finally {
			dbf.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testRecordSize() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.dbf");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		DBFFile dbf = null;
		try {
			dbf = new DBFFile();
			dbf.open(is);

			final DBFHeader header = dbf.getHeader();
			final int recordSize = header.getRecordSize();

			assertEquals(114, recordSize);
		} finally {
			dbf.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testFieldCount() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.dbf");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		DBFFile dbf = null;
		try {
			dbf = new DBFFile();
			dbf.open(is);

			final DBFHeader header = dbf.getHeader();
			final int fieldCount = header.getFieldCount();

			assertEquals(8, fieldCount);
		} finally {
			dbf.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testFieldListSize() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.dbf");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		DBFFile dbf = null;
		try {
			dbf = new DBFFile();
			dbf.open(is);

			final DBFHeader header = dbf.getHeader();
			final List<DBFFieldDescriptor> fields = header.getFields();

			assertEquals(8, fields.size());
		} finally {
			dbf.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testFieldInHeader() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.dbf");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		DBFFile dbf = null;
		try {
			dbf = new DBFFile();
			dbf.open(is);

			final DBFHeader header = dbf.getHeader();
			final List<DBFFieldDescriptor> fields = header.getFields();

			final DBFFieldDescriptor field = fields.get(2);
			final String fieldName = field.getName();
			final String fieldType = field.getType();
			final int fieldLength = field.getLength();

			assertEquals("Field1", fieldName);
			assertEquals("N", fieldType);
			assertEquals(4, fieldLength);
		} finally {
			dbf.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testRecord() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.dbf");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		DBFFile dbf = null;
		try {
			dbf = new DBFFile();
			dbf.open(is);

			final DBFRecord record = dbf.getRecordAt(3);

			assertNotNull(record);
		} finally {
			dbf.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testRecordValue() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.dbf");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		DBFFile dbf = null;
		try {
			dbf = new DBFFile();
			dbf.open(is);

			final DBFRecord record = dbf.getRecordAt(3);
			final List<DBFField> fields = record.getFields();
			final DBFField field = fields.get(2);
			final DBFFieldDescriptor descriptor = field.getDescriptor();

			final String fieldName = descriptor.getName();
			final String fieldType = descriptor.getType();
			final int fieldLength = descriptor.getLength();

			assertEquals("Field1", fieldName);
			assertEquals("N", fieldType);
			assertEquals(4, fieldLength);

			final Object value = field.getValue();
			final Double doubleValue = (Double) value;

			assertEquals(Double.valueOf(3), doubleValue);
		} finally {
			dbf.close();
			FileUtils.closeInputStream(is);
		}
	}
}
