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

import java.util.LinkedHashMap;
import java.util.Map;

import br.com.anteros.javatodelphi.parser.Node;

public abstract class AbstractMethodHeaderDeclaration extends Node {

	protected Map<String, String> parameters = new LinkedHashMap<String, String>();
	protected boolean isConstructor = false;
	protected boolean isVirtualMethod = false;
	protected boolean isPrivateMethod = false;
	protected boolean isProtectedMethod = false;
	protected boolean isPublicMethod = false;
	protected boolean isStaticMethod = false;
	protected boolean isOverride = false;
	protected boolean isOverload = false;
	protected String genericsTypeOfMethod;
	protected AbstractTypeDeclaration typeDeclaration;
	protected String comment;

	public AbstractMethodHeaderDeclaration(String name) {
		super(Character.toUpperCase(name.charAt(0)) + name.substring(1));
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public AbstractMethodHeaderDeclaration addParameter(String parameterName, String parameterType) {
		parameters.put(parameterName, convertTypeFromDelphiToJava(parameterType));
		return this;
	}

	public boolean isConstructor() {
		return isConstructor;
	}

	public AbstractMethodHeaderDeclaration setConstructor(boolean isConstructor) {
		this.isConstructor = isConstructor;
		return this;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public boolean isVirtualMethod() {
		return isVirtualMethod;
	}

	public AbstractMethodHeaderDeclaration virtualMethod(boolean isVirtualMethod) {
		this.isVirtualMethod = isVirtualMethod;
		return this;
	}

	public boolean isPrivateMethod() {
		return isPrivateMethod;
	}

	public AbstractMethodHeaderDeclaration privateMethod(boolean isPrivateMethod) {
		this.isPrivateMethod = isPrivateMethod;
		return this;
	}

	public boolean isProtectedMethod() {
		return isProtectedMethod;
	}

	public AbstractMethodHeaderDeclaration protectedMethod(boolean isProtectedMethod) {
		this.isProtectedMethod = isProtectedMethod;
		return this;
	}

	public boolean isPublicMethod() {
		return isPublicMethod;
	}

	public AbstractMethodHeaderDeclaration publicMethod(boolean isPublicMethod) {
		this.isPublicMethod = isPublicMethod;
		return this;
	}

	public boolean isStaticMethod() {
		return isStaticMethod;
	}

	public AbstractMethodHeaderDeclaration staticMethod(boolean isStaticMethod) {
		this.isStaticMethod = isStaticMethod;
		return this;
	}

	public boolean isOverride() {
		return isOverride;
	}

	public AbstractMethodHeaderDeclaration override(boolean isOverride) {
		this.isOverride = isOverride;
		return this;
	}

	public String getGenericsTypeOfMethod() {
		return genericsTypeOfMethod;
	}

	public AbstractMethodHeaderDeclaration genericsTypeOfMethod(String genericsTypeOfMethod) {
		this.genericsTypeOfMethod = genericsTypeOfMethod;
		return this;
	}

	public boolean isOverload() {
		return isOverload;
	}

	public AbstractMethodHeaderDeclaration overload(boolean isOverload) {
		this.isOverload = isOverload;
		return this;
	}

	public AbstractTypeDeclaration getTypeDeclaration() {
		return typeDeclaration;
	}

	public AbstractMethodHeaderDeclaration typeDeclaration(AbstractTypeDeclaration typeDeclaration) {
		this.typeDeclaration = typeDeclaration;
		return this;
	}
	
	public abstract String getTypeOfMethod();

	public String getComment() {
		return comment;
	}

	public AbstractMethodHeaderDeclaration comment(String comment) {
		this.comment = comment;
		return this;
	}

}
