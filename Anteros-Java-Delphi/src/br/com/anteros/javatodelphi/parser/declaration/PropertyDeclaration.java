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

import br.com.anteros.javatodelphi.parser.Node;

public class PropertyDeclaration extends Node {

	protected AbstractVariableDeclaration variable;
	protected String readMethod;
	protected String writeMethod;

	public PropertyDeclaration(String name) {
		super(name);
	}

	public String getReadMethod() {
		return readMethod;
	}

	public PropertyDeclaration readMethod(String readMethod) {
		this.readMethod = readMethod;
		return this;
	}

	public String getWriteMethod() {
		return writeMethod;
	}

	public PropertyDeclaration writeMethod(String writeMethod) {
		this.writeMethod = writeMethod;
		return this;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("property ").append(name).append(" : ").append(variable.getType()).append(" read ")
				.append((readMethod==null ? variable.getName() : readMethod)).append(" write ")
				.append((writeMethod==null ? variable.getName() : writeMethod)).append(";\n");
		return sb.toString();
	}

	public AbstractVariableDeclaration getVariable() {
		return variable;
	}

	public PropertyDeclaration variable(AbstractVariableDeclaration variable) {
		this.variable = variable;
		return this;
	}

}
