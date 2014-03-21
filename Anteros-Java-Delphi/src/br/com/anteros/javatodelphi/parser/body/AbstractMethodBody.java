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
package br.com.anteros.javatodelphi.parser.body;

import java.util.ArrayList;
import java.util.List;

import br.com.anteros.javatodelphi.parser.Node;
import br.com.anteros.javatodelphi.parser.body.section.Section;
import br.com.anteros.javatodelphi.parser.body.section.VarSection;
import br.com.anteros.javatodelphi.parser.declaration.AbstractMethodHeaderDeclaration;
import br.com.anteros.javatodelphi.parser.declaration.AbstractTypeDeclaration;
import br.com.anteros.javatodelphi.parser.declaration.AbstractVariableDeclaration;
import br.com.anteros.javatodelphi.parser.statement.Statement;
import br.com.anteros.javatodelphi.parser.statement.StatementOwner;
import br.com.anteros.javatodelphi.parser.statement.UnknownStatement;

public abstract class AbstractMethodBody extends Node implements StatementOwner {

	protected AbstractMethodHeaderDeclaration methodHeaderDeclaration;
	protected List<Section> sections = new ArrayList<Section>();
	protected Block block = new Block("");
	protected AbstractTypeDeclaration typeOwner;

	public AbstractMethodBody(String name) {
		super(name);
	}

	public AbstractMethodBody addSection(Section section) {
		sections.add(section);
		return this;
	}

	public void removeSection(Section section) {
		sections.remove(section);
	}

	public VarSection getVarSection() {
		for (Section section : sections) {
			if (section instanceof Section)
				return (VarSection) section;
		}
		VarSection result = new VarSection("");
		sections.add(result);
		return result;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Section section : sections)
			sb.append(section);
		sb.append(block).append("\n");
		return sb.toString();
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public AbstractMethodHeaderDeclaration getMethodHeaderDeclaration() {
		return methodHeaderDeclaration;
	}

	public AbstractMethodBody methodHeaderDeclaration(AbstractMethodHeaderDeclaration methodHeaderDeclaration) {
		this.methodHeaderDeclaration = methodHeaderDeclaration;
		return this;
	}

	public AbstractTypeDeclaration getTypeOwner() {
		return typeOwner;
	}

	public AbstractMethodBody typeOwner(AbstractTypeDeclaration typeOwner) {
		this.typeOwner = typeOwner;
		return this;
	}

	public AbstractMethodBody addStatement(Statement statement) {
		block.statements.add(statement);
		return this;
	}

	public boolean existsParameter(String name) {
		for (String parameter : methodHeaderDeclaration.getParameters().keySet()) {
			if (parameter.equalsIgnoreCase(name))
				return true;
		}
		return false;
	}

	public boolean existsVariable(String name) {
		for (AbstractVariableDeclaration variable : getVarSection().getVariables()) {
			if (variable.getName().equalsIgnoreCase(name))
				return true;
		}
		return false;
	}

	@Override
	public String getName() {
		if (methodHeaderDeclaration != null)
			return methodHeaderDeclaration.getName();
		else
			return "";

	}

}
