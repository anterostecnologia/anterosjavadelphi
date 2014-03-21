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

public class AbstractVariableDeclaration extends Node {
	
	protected String type;
	protected boolean isPrivateVariable = false;
	protected boolean isProtecteVariable = false;
	protected boolean isPublicVariable = false;
	protected boolean isStaticVariable = false;
	protected String value;

	public AbstractVariableDeclaration(String name) {
		super(name);
	}

	public String getType() {
		return type;
	}

	public AbstractVariableDeclaration type(String type) {
		this.type = type;
		return this;
	}

	public boolean isPrivateVariable() {
		return isPrivateVariable;
	}

	public AbstractVariableDeclaration privateVariable(boolean isPrivateVariable) {
		this.isPrivateVariable = isPrivateVariable;
		return this;
	}

	public boolean isProtectedVariable() {
		return isProtecteVariable;
	}

	public AbstractVariableDeclaration protectedVariable(boolean isProtectedVariable) {
		this.isProtecteVariable = isProtectedVariable;
		return this;
	}

	public boolean isPublicVariable() {
		return isPublicVariable;
	}

	public AbstractVariableDeclaration publicVariable(boolean isPublicVariable) {
		this.isPublicVariable = isPublicVariable;
		return this;
	}

	public boolean isStaticVariable() {
		return isStaticVariable;
	}

	public AbstractVariableDeclaration staticVariable(boolean isStaticVariable) {
		this.isStaticVariable = isStaticVariable;
		return this;
	}

	public String getValue() {
		return value;
	}

	public AbstractVariableDeclaration value(String value) {
		this.value = value;
		return this;
	}

}
