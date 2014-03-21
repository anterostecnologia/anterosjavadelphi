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
package br.com.anteros.javatodelphi.parser.statement;

public class CaseStatement extends Statement {

	protected String expression;

	public CaseStatement(String name) {
		super(name);
	}

	public String getExpression() {
		return expression;
	}

	public CaseStatement expression(String expression) {
		this.expression = expression;
		return this;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("case ").append(expression).append(" of\n");
		boolean appendDelimiter = false;
		for (Statement stmt : block.getStatements()) {
			if (stmt instanceof CaseConditionStatement) {
				if (appendDelimiter)
					sb.append("end;\n");
				sb.append(stmt);
				sb.append("begin\n");
				appendDelimiter = true;
			} else if (stmt instanceof ExpressionStatement) {
				sb.append(stmt);
			} else if (stmt instanceof ReturnStatement) {
				sb.append(stmt);
			}
		}
		if (appendDelimiter)
			sb.append("end;\n");
		sb.append("end;\n");

		return sb.toString();
	}

}
