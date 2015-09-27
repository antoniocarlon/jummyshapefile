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

import junit.framework.TestCase;

import com.jummyshapefile.shx.SHXFile;
import com.jummyshapefile.shx.model.SHXHeader;
import com.jummyshapefile.shx.model.SHXRecord;
import com.jummyshapefile.utils.FileUtils;

public class TestSHX extends TestCase {
	public void testReadHeader() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shx");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHXFile shx = null;
		try {
			shx = new SHXFile();
			shx.open(is);

			final SHXHeader header = shx.getHeader();

			assertNotNull(header);
		} finally {
			shx.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testShapeType() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shx");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHXFile shx = null;
		try {
			shx = new SHXFile();
			shx.open(is);

			final SHXHeader header = shx.getHeader();
			final int shapeType = header.getShapeType();

			assertEquals(5, shapeType);
		} finally {
			shx.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testMinX() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shx");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHXFile shx = null;
		try {
			shx = new SHXFile();
			shx.open(is);

			final SHXHeader header = shx.getHeader();
			final int minX = (int) header.getMinX();

			assertEquals(437717, minX);
		} finally {
			shx.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testMinY() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shx");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHXFile shx = null;
		try {
			shx = new SHXFile();
			shx.open(is);

			final SHXHeader header = shx.getHeader();
			final int minY = (int) header.getMinY();

			assertEquals(4470909, minY);
		} finally {
			shx.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testMaxX() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shx");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHXFile shx = null;
		try {
			shx = new SHXFile();
			shx.open(is);

			final SHXHeader header = shx.getHeader();
			final int maxX = (int) header.getMaxX();

			assertEquals(447951, maxX);
		} finally {
			shx.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testMaxY() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shx");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHXFile shx = null;
		try {
			shx = new SHXFile();
			shx.open(is);

			final SHXHeader header = shx.getHeader();
			final int maxY = (int) header.getMaxY();

			assertEquals(4479694, maxY);
		} finally {
			shx.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testMinZ() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shx");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHXFile shx = null;
		try {
			shx = new SHXFile();
			shx.open(is);

			final SHXHeader header = shx.getHeader();
			final int minZ = (int) header.getMinZ();

			assertEquals(0, minZ);
		} finally {
			shx.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testMaxZ() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shx");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHXFile shx = null;
		try {
			shx = new SHXFile();
			shx.open(is);

			final SHXHeader header = shx.getHeader();
			final int maxZ = (int) header.getMaxZ();

			assertEquals(0, maxZ);
		} finally {
			shx.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testMinM() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shx");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHXFile shx = null;
		try {
			shx = new SHXFile();
			shx.open(is);

			final SHXHeader header = shx.getHeader();
			final int minM = (int) header.getMinM();

			assertEquals(0, minM);
		} finally {
			shx.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testMaxM() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shx");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHXFile shx = null;
		try {
			shx = new SHXFile();
			shx.open(is);

			final SHXHeader header = shx.getHeader();
			final int maxM = (int) header.getMaxM();

			assertEquals(0, maxM);
		} finally {
			shx.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testRecord() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shx");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHXFile shx = null;
		try {
			shx = new SHXFile();
			shx.open(is);

			final SHXRecord record = shx.getRecordAt(3);

			assertNotNull(record);
		} finally {
			shx.close();
			FileUtils.closeInputStream(is);
		}
	}

	public void testRecordValue() throws IOException, URISyntaxException {
		final URL resource = getClass().getClassLoader().getResource(
				"Polygon.shx");
		final File file = new File(resource.toURI());
		final FileInputStream is = new FileInputStream(file);

		SHXFile shx = null;
		try {
			shx = new SHXFile();
			shx.open(is);

			final SHXRecord record = shx.getRecordAt(3);
			final int recordLength = record.getLength();
			final int recordOffset = record.getOffset();

			assertEquals(296, recordLength);
			assertEquals(540, recordOffset);
		} finally {
			shx.close();
			FileUtils.closeInputStream(is);
		}
	}
}
