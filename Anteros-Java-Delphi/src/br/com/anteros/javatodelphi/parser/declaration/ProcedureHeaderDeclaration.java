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

public class ProcedureHeaderDeclaration extends AbstractMethodHeaderDeclaration {

	public ProcedureHeaderDeclaration(String name) {
		super(name);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		if (isConstructor)
			sb.append("constructor Create");
		else {
			if (isStaticMethod)
				sb.append("class ");

			sb.append("procedure ").append(name);
			if (genericsTypeOfMethod != null)
				sb.append("<").append(genericsTypeOfMethod).append(">");
		}
		if (parameters.size() > 0) {
			sb.append("(");
			boolean appendDelimiter = false;
			for (String paramName : parameters.keySet()) {
				if (appendDelimiter)
					sb.append("; ");
				sb.append(paramName).append(":").append(parameters.get(paramName));
				appendDelimiter = true;
			}
			sb.append(")");
		}
		if (isOverride())
			sb.append("; override");

		sb.append(";");
		if (isOverload)
			sb.append(" overload;");
		
		if (isVirtualMethod)
			sb.append(" virtual;");
		sb.append("\n");

		return sb.toString();
	}

	@Override
	public String getTypeOfMethod() {
		if (isConstructor)
			return "constructor";
		else
			return "procedure";
	}

}
