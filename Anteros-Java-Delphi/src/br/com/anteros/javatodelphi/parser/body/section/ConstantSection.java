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
package br.com.anteros.javatodelphi.parser.body.section;

import java.util.ArrayList;
import java.util.List;

import br.com.anteros.javatodelphi.parser.declaration.AbstractVariableDeclaration;

public class ConstantSection extends Section {

	protected ConstantKeyType constKey;
	protected List<AbstractVariableDeclaration> variables = new ArrayList<AbstractVariableDeclaration>();
	
	public ConstantSection(String name) {
		super(name);
	}

	public ConstantKeyType getConstKey() {
		return constKey;
	}

	public ConstantSection constKey(ConstantKeyType constKey) {
		this.constKey = constKey;
		return this;
	}

	public List<AbstractVariableDeclaration> getVariables() {
		return variables;
	}

	public ConstantSection variables(List<AbstractVariableDeclaration> variables) {
		this.variables = variables;
		return this;
	}
	
	public ConstantSection addVariable(AbstractVariableDeclaration variable) {
		for (AbstractVariableDeclaration var : variables){
			if (var.getName().equals(variable.getName()))
				return this;
		}
		variables.add(variable);
		return this;
	}

	public void removeVariable(AbstractVariableDeclaration variable) {
		variables.remove(variable);
	}
	
	@Override
	public String toString() {
		if (getVariables().size()==0)
			return "";
		StringBuffer sb = new StringBuffer();
		sb.append("const\n");
		for (AbstractVariableDeclaration variable : getVariables()) {
			sb.append(variable.toString());
		}
		sb.append("\n");
		return sb.toString();
	}


}
