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

import br.com.anteros.javatodelphi.parser.Node;
import br.com.anteros.javatodelphi.parser.comparator.MethodHeaderComparator;

public abstract class AbstractTypeDeclaration extends Node {

	protected List<AbstractMethodHeaderDeclaration> methods = new ArrayList<AbstractMethodHeaderDeclaration>();

	public AbstractTypeDeclaration(String name) {
		super(name);
	}

	public List<AbstractMethodHeaderDeclaration> getMethods() {
		return methods;
	}

	public void setMethods(List<AbstractMethodHeaderDeclaration> methods) {
		this.methods = methods;
	}

	public AbstractTypeDeclaration addMethod(AbstractMethodHeaderDeclaration method) {
		this.methods.add(method);
		return this;
	}

	public void removeMethod(AbstractMethodHeaderDeclaration method) {
		this.methods.remove(method);
	}

	public List<AbstractMethodHeaderDeclaration> getPublicMethods() {
		List<AbstractMethodHeaderDeclaration> result = new ArrayList<AbstractMethodHeaderDeclaration>();
		for (AbstractMethodHeaderDeclaration method : methods) {
			if (method.isPublicMethod())
				result.add(method);
		}
		Collections.sort(result,new MethodHeaderComparator());
		return result;
	}

	public List<AbstractMethodHeaderDeclaration> getPrivateMethods() {
		List<AbstractMethodHeaderDeclaration> result = new ArrayList<AbstractMethodHeaderDeclaration>();
		for (AbstractMethodHeaderDeclaration method : methods) {
			if (method.isPrivateMethod())
				result.add(method);
		}
		Collections.sort(result,new MethodHeaderComparator());
		return result;
	}

	public List<AbstractMethodHeaderDeclaration> getProtectedMethods() {
		List<AbstractMethodHeaderDeclaration> result = new ArrayList<AbstractMethodHeaderDeclaration>();
		for (AbstractMethodHeaderDeclaration method : methods) {
			if (method.isProtectedMethod())
				result.add(method);
		}
		Collections.sort(result,new MethodHeaderComparator());
		return result;
	}
	
	public abstract String getStringToPreviousDeclarationOfType();
	
	
}
