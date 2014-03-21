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
import br.com.anteros.javatodelphi.parser.body.DeclarationSection;
import br.com.anteros.javatodelphi.parser.body.UsesClause;

public class UnitImplementation extends Node {

	protected UsesClause usesClause = new UsesClause("");
	protected DeclarationSection declarationSection = new DeclarationSection("");

	public UnitImplementation(String name) {
		super(name);
	}

	public UsesClause getUsesClause() {
		return usesClause;
	}

	public void setUsesClause(UsesClause usesClause) {
		this.usesClause = usesClause;
	}

	public DeclarationSection getDeclarationSection() {
		return declarationSection;
	}

	public void setDeclarationSection(DeclarationSection declarationSection) {
		this.declarationSection = declarationSection;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("implementation").append("\n");
		sb.append(usesClause);
		sb.append(declarationSection);
		return sb.toString();
	}

}
