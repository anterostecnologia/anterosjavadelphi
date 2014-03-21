/*******************************************************************************
 * Copyright 2012 Anteros Tecnologia
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package br.com.anteros.javatodelphi.parser.declaration;

public class ArrayDeclaration extends AbstractVariableDeclaration {

	
	protected Integer startElement;
	protected Integer endElement;
	
	public ArrayDeclaration(String name) {
		super(name);
	}

	public String getType() {
		return type;
	}

	public ArrayDeclaration type(String type) {
		this.type = type;
		return this;
	}

	public Integer getStartElement() {
		return startElement;
	}

	public ArrayDeclaration startElement(Integer startElement) {
		this.startElement = startElement;
		return this;
	}

	public Integer getEndElement() {
		return endElement;
	}

	public ArrayDeclaration endElement(Integer endElement) {
		this.endElement = endElement;
		return this;
	}

}
