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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.anteros.javatodelphi.parser.body.AbstractMethodBody;
import br.com.anteros.javatodelphi.parser.comparator.VariableComparator;

public class ClassTypeDeclaration extends AbstractTypeDeclaration {

	protected boolean isAbstractClassType = false;
	protected String classType = "";
	protected List<AbstractVariableDeclaration> variables = new ArrayList<AbstractVariableDeclaration>();
	protected List<PropertyDeclaration> properties = new ArrayList<PropertyDeclaration>();

	public ClassTypeDeclaration(String name) {
		super(name);
	}

	public boolean isAbstractClassType() {
		return isAbstractClassType;
	}

	public ClassTypeDeclaration abstractClassType(boolean isAbstractClassType) {
		this.isAbstractClassType = isAbstractClassType;
		return this;
	}

	public String getClassType() {
		return classType;
	}

	public ClassTypeDeclaration classType(String classType) {
		this.classType = classType;
		return this;
	}
	

	public ClassTypeDeclaration addVariable(AbstractVariableDeclaration variable) {
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
	

	public ClassTypeDeclaration addProperty(PropertyDeclaration variable) {
		properties.add(variable);
		return this;
	}

	public void removeProperty(PropertyDeclaration variable) {
		properties.remove(variable);
	}
	
	public List<AbstractVariableDeclaration> getVariables() {
		return variables;
	}

	public List<PropertyDeclaration> getProperties() {
		return properties;
	}
	
	
	public List<AbstractVariableDeclaration> getPublicVariables() {
		List<AbstractVariableDeclaration> result = new ArrayList<AbstractVariableDeclaration>();
		for (AbstractVariableDeclaration variable : variables) {
			if (variable.isPublicVariable())
				result.add(variable);
		}
		return result;
	}

	public List<AbstractVariableDeclaration> getPrivateVariables() {
		List<AbstractVariableDeclaration> result = new ArrayList<AbstractVariableDeclaration>();
		for (AbstractVariableDeclaration variable : variables) {
			if (variable.isPrivateVariable())
				result.add(variable);
		}
		return result;
	}

	public List<AbstractVariableDeclaration> getProtectedVariables() {
		List<AbstractVariableDeclaration> result = new ArrayList<AbstractVariableDeclaration>();
		for (AbstractVariableDeclaration variable : variables) {
			if (variable.isProtectedVariable())
				result.add(variable);
		}
		return result;
	}


	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(name);
		if (isAbstractClassType)
			sb.append(" = abstract ");
		else
			sb.append(" = ");
		if ("".equals(classType))
			sb.append("class \n");
		else
			sb.append("class (").append(classType).append(")\n");

		sb.append("private\n");
		List<AbstractVariableDeclaration> vars = getPrivateVariables();
		Collections.sort(vars,new VariableComparator());
		for (AbstractVariableDeclaration variable : vars) {
			sb.append(variable.toString());
		}		
		for (AbstractMethodHeaderDeclaration method : getPrivateMethods()) {
			sb.append(method.toString());
		}		
		sb.append("protected\n");
		vars = getProtectedVariables();
		Collections.sort(vars,new VariableComparator());
		for (AbstractVariableDeclaration variable : vars) {
			sb.append(variable.toString());
		}
		for (AbstractMethodHeaderDeclaration method : getProtectedMethods()) {
			sb.append(method.toString());
		}		
		sb.append("public\n");
		vars = getPublicVariables();
		Collections.sort(vars,new VariableComparator());
		for (AbstractVariableDeclaration variable : vars) {
			sb.append(variable.toString());
		}
		for (AbstractMethodHeaderDeclaration method : getPublicMethods()) {
			sb.append(method.toString());
		}
		for (PropertyDeclaration property : getProperties()){
			sb.append(property.toString());
		}
		sb.append("published\n");
		sb.append("end;\n\n");
		return sb.toString();
	}
	
	@Override
	public String getStringToPreviousDeclarationOfType() {
		return name+" = class;\n";
	}

	


}
