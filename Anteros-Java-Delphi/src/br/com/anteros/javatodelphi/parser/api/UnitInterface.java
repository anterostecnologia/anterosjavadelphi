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
package br.com.anteros.javatodelphi.parser.api;

import br.com.anteros.javatodelphi.parser.Node;
import br.com.anteros.javatodelphi.parser.body.InterfaceDeclaration;
import br.com.anteros.javatodelphi.parser.body.UsesClause;

public class UnitInterface extends Node {

	protected UsesClause usesClause = new UsesClause("");
	protected InterfaceDeclaration interfaceDeclaration = new InterfaceDeclaration("");

	public UnitInterface(String name) {
		super(name);
	}

	public UsesClause getUsesClause() {
		return usesClause;
	}

	public UnitInterface usesClause(UsesClause usesClause) {
		this.usesClause = usesClause;
		return this;
	}

	public InterfaceDeclaration getInterfaceDeclaration() {
		return interfaceDeclaration;
	}

	public UnitInterface interfaceDeclaration(InterfaceDeclaration interfaceDeclaration) {
		this.interfaceDeclaration = interfaceDeclaration;
		return this;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("interface ").append("\n").append(usesClause).append(interfaceDeclaration);
		return sb.toString();
	}

}
