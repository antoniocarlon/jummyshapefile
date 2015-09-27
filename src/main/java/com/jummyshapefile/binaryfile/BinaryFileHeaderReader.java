/*
 * Copyright 2015 ANTONIO CARLON
 *
import java.io.IOException;
import java.io.InputStream;

import com.jummyshapefile.binaryfile.model.BinaryFileHeader;
 the License.
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

package com.jummyshapefile.binaryfile;

import java.io.IOException;
import java.io.InputStream;

import com.jummyshapefile.binaryfile.model.BinaryFileHeader;

/**
 * Interface that stablishes the contract for the reader of the header of a
 * binary file.
 *
 * @see BinaryFileHeader
 */
public interface BinaryFileHeaderReader<T extends BinaryFileHeader> {
	/**
	 * Returns a BinaryFileHeader object representing the header of a binary
	 * file.
	 *
	 * @param is
	 *            the InputStream for the file
	 * @return BinaryFileHeader object representing the header of a binary file
	 * @throws IOException
	 *             if there is any problem reading the header
	 *
	 * @see BinaryFileHeader
	 */
	public T readHeader(InputStream is) throws IOException;
}
