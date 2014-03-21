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

public class IfStatement extends Statement {

	protected String expression;
	protected ElseStatement elseStatement;

	public IfStatement(String name) {
		super(name);
	}

	public String getExpression() {
		return expression;
	}

	public IfStatement expression(String expression) {
		this.expression = expression;
		return this;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("if (").append(expression).append(") then\n");
		if (elseStatement!=null)
			block.appendEndOfLineDelimiter(false);
		sb.append(block);
		if (elseStatement!=null)
			sb.append(elseStatement);

		return sb.toString();
	}

	public ElseStatement getElseStatement() {
		return elseStatement;
	}

	public IfStatement elseStatement(ElseStatement elseStatement) {
		this.elseStatement = elseStatement;
		return this;
	}

}
