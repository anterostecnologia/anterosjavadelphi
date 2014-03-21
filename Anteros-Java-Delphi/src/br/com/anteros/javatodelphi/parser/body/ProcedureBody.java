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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.anteros.javatodelphi.parser.declaration.AbstractVariableDeclaration;

public class ProcedureBody extends AbstractMethodBody {

	public ProcedureBody(String name) {
		super(name);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (methodHeaderDeclaration.isConstructor()) {
			sb.append("constructor ");
			if (typeOwner != null)
				sb.append(typeOwner.getName()).append(".");
			sb.append("Create");
		} else {
			if (methodHeaderDeclaration.isStaticMethod())
				sb.append("class ");

			sb.append("procedure ");
			if (typeOwner != null)
				sb.append(typeOwner.getName()).append(".");
			sb.append(methodHeaderDeclaration.getName());
			if (methodHeaderDeclaration.getGenericsTypeOfMethod() != null)
				sb.append("<").append(methodHeaderDeclaration.getGenericsTypeOfMethod()).append(">");
		}
		if (methodHeaderDeclaration.getParameters().size() > 0) {
			sb.append("(");
			boolean appendDelimiter = false;
			for (String paramName : methodHeaderDeclaration.getParameters().keySet()) {
				if (appendDelimiter)
					sb.append("; ");
				sb.append(paramName).append(":").append(methodHeaderDeclaration.getParameters().get(paramName));
				appendDelimiter = true;
			}
			sb.append(")");
		}
		if (methodHeaderDeclaration.isOverride())
			sb.append("; override");

		sb.append(";");
		if (methodHeaderDeclaration.isOverload())
			sb.append(" overload;");
		sb.append("\n");
		if (getVarSection().getVariables().size() > 0) {
			sb.append("var\n");
			List<AbstractVariableDeclaration> vars = getVarSection().getVariables();
			Collections.sort(vars, new VariableComparator());
			for (AbstractVariableDeclaration variable : vars) {
				sb.append(variable);
			}
		}

		sb.append(block);
		sb.append("\n");

		return sb.toString();
	}

	class VariableComparator implements Comparator<AbstractVariableDeclaration> {

		@Override
		public int compare(AbstractVariableDeclaration o1, AbstractVariableDeclaration o2) {
			return o1.getName().compareTo(o2.getName());
		}

	}
}
