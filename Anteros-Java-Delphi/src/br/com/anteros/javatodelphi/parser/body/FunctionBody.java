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
import java.util.List;

import br.com.anteros.javatodelphi.parser.comparator.VariableComparator;
import br.com.anteros.javatodelphi.parser.declaration.AbstractVariableDeclaration;
import br.com.anteros.javatodelphi.parser.declaration.FunctionHeaderDeclaration;


public class FunctionBody extends AbstractMethodBody{

	public FunctionBody(String name) {
		super(name);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (methodHeaderDeclaration.getComment()!=null)
			sb.append(methodHeaderDeclaration.getComment());
		if (methodHeaderDeclaration.isStaticMethod())
			sb.append("class ");
		sb.append("function ");
		if (typeOwner!=null)
			sb.append(typeOwner.getName()).append(".");
		sb.append(methodHeaderDeclaration.getName());
		if (methodHeaderDeclaration.getGenericsTypeOfMethod() != null)
			sb.append("<").append(methodHeaderDeclaration.getGenericsTypeOfMethod()).append(">");
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
		sb.append(": ").append(((FunctionHeaderDeclaration)methodHeaderDeclaration).getReturnType());
		if (methodHeaderDeclaration.isOverride())
			sb.append("; override");
		sb.append(";");
		if (methodHeaderDeclaration.isOverload())
			sb.append(" overload;");
		sb.append("\n");
		if (getVarSection().getVariables().size()>0){
			sb.append("var\n");
			List<AbstractVariableDeclaration> vars = getVarSection().getVariables();
			Collections.sort(vars,new VariableComparator());
			for (AbstractVariableDeclaration variable : vars){
				sb.append(variable);
			}
		}
		sb.append(block);
		sb.append("\n");

		return sb.toString();
	}
	
}
