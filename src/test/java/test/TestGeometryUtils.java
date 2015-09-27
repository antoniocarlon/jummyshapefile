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

import java.io.IOException;

import junit.framework.TestCase;

import com.jummyshapefile.utils.GeometryUtils;

public class TestGeometryUtils extends TestCase {
	public void testMBR1() throws IOException {
		final boolean check = GeometryUtils.intersectsRectangle(1, 1, 3, 3, 0,
				0, 2, 2);

		assertTrue(check);
	}

	public void testMBR2() throws IOException {
		final boolean check = GeometryUtils.intersectsRectangle(0, 1, 2, 3, 1,
				0, 3, 2);

		assertTrue(check);
	}

	public void testMBR3() throws IOException {
		final boolean check = GeometryUtils.intersectsRectangle(0, 0, 2, 2, 1,
				1, 3, 3);

		assertTrue(check);
	}

	public void testMBR4() throws IOException {
		final boolean check = GeometryUtils.intersectsRectangle(1, 0, 3, 2, 0,
				1, 2, 3);

		assertTrue(check);
	}

	public void testMBR5() throws IOException {
		final boolean check = GeometryUtils.intersectsRectangle(1, 1, 3, 3, 0,
				0, 4, 4);

		assertTrue(check);
	}

	public void testMBR6() throws IOException {
		final boolean check = GeometryUtils.intersectsRectangle(0, 0, 4, 4, 1,
				1, 3, 3);

		assertTrue(check);
	}

	public void testMBR7() throws IOException {
		final boolean check = GeometryUtils.intersectsRectangle(0, 1, 4, 3, 1,
				0, 3, 4);

		assertTrue(check);
	}

	public void testMBR8() throws IOException {
		final boolean check = GeometryUtils.intersectsRectangle(1, 0, 3, 4, 0,
				1, 4, 3);

		assertTrue(check);
	}

	public void testMBR9() throws IOException {
		final boolean check = GeometryUtils.intersectsRectangle(1, 3, 2, 5, 3,
				0, 5, 2);

		assertFalse(check);
	}

	public void testMBR10() throws IOException {
		final boolean check = GeometryUtils.intersectsRectangle(3, 3, 5, 5, 0,
				0, 2, 2);

		assertFalse(check);
	}

	public void testMBR11() throws IOException {
		final boolean check = GeometryUtils.intersectsRectangle(0, 0, 2, 2, 3,
				3, 5, 5);

		assertFalse(check);
	}

	public void testMBR12() throws IOException {
		final boolean check = GeometryUtils.intersectsRectangle(3, 0, 5, 2, 0,
				3, 2, 5);

		assertFalse(check);
	}
}
