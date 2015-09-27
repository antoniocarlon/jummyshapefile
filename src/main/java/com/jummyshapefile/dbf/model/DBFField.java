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

package com.jummyshapefile.dbf.model;

/**
 * Represents a DBF Field, including both it's properties (descriptor) and
 * value.
 *
 * @param <T>
 *            The Object type stored in the DBFField.
 *
 * @see DBFFieldDescriptor
 */
public class DBFField<T> {
	private DBFFieldDescriptor descriptor;
	private T value;

	/**
	 * Returns a DBFFieldDescriptor object representing the properties of the
	 * field.
	 *
	 * @return the descriptor of the field
	 *
	 * @see DBFFieldDescriptor
	 */
	public DBFFieldDescriptor getDescriptor() {
		return descriptor;
	}

	/**
	 * Sets the properties of the DBF field in the form of a DBFFieldDescriptor
	 * object.
	 *
	 * @param descriptor
	 *            the properties of the DBF field
	 *
	 * @see DBFFieldDescriptor
	 */
	public void setDescriptor(final DBFFieldDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	/**
	 * Returns an Object representing the value od the field.
	 *
	 * @return the value of the field
	 */
	public T getValue() {
		return value;
	}

	/**
	 * Sets the value of the DBF field.
	 *
	 * @param value
	 */
	public void setValue(final T value) {
		this.value = value;
	}

	@Override
	public String toString() {
		String output = super.toString();

		if (descriptor != null) {
			output = descriptor.getName() + " (" + descriptor.getType()
					+ ") : " + value;
		}

		return output;
	}
}
