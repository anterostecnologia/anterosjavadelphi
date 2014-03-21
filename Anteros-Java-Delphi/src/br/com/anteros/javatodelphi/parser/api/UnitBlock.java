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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.anteros.javatodelphi.parser.Node;
import br.com.anteros.javatodelphi.parser.body.AbstractMethodBody;
import br.com.anteros.javatodelphi.parser.body.InterfaceDeclaration;
import br.com.anteros.javatodelphi.parser.comparator.MethodBodyComparator;
import br.com.anteros.javatodelphi.parser.declaration.AbstractTypeDeclaration;

public class UnitBlock extends Node {

	protected List<AbstractMethodBody> methods = new ArrayList<AbstractMethodBody>();
	protected UnitInitialization unitInitialization;
	protected InterfaceDeclaration interfaceDeclaration;

	public UnitBlock(String name) {
		super(name);
	}

	public UnitInitialization getUnitInitialization() {
		return unitInitialization;
	}

	public void setUnitInitialization(UnitInitialization unitInitialization) {
		this.unitInitialization = unitInitialization;
	}

	public List<AbstractMethodBody> getMethods() {
		return methods;
	}

	public UnitBlock addMethod(AbstractMethodBody method) {
		methods.add(method);
		return this;
	}

	public void removeMethod(AbstractMethodBody method) {
		methods.remove(method);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (AbstractTypeDeclaration typeDeclaration : interfaceDeclaration.getTypeSection().getTypes()) {
			sb.append("{").append(typeDeclaration.getName()).append("}\n");
			List<AbstractMethodBody> methodsByType = getMethodsByTypeDeclaration(typeDeclaration);

			for (AbstractMethodBody method : methodsByType) {
				sb.append(method);
			}
		}

		return sb.toString();
	}

	public List<AbstractMethodBody> getMethodsByTypeDeclaration(AbstractTypeDeclaration typeDeclaration) {
		List<AbstractMethodBody> result = new ArrayList<AbstractMethodBody>();
		for (AbstractMethodBody method : methods) {
			if (method.getTypeOwner().equals(typeDeclaration)) {
				result.add(method);
			}
		}
		Collections.sort(result, new MethodBodyComparator());
		return result;
	}


	public InterfaceDeclaration getInterfaceDeclaration() {
		return interfaceDeclaration;
	}

	public void setInterfaceDeclaration(InterfaceDeclaration interfaceDeclaration) {
		this.interfaceDeclaration = interfaceDeclaration;
	}

}
