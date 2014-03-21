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

public class RepeatStatement extends Statement {

	protected String expression;

	public RepeatStatement(String name) {
		super(name);
	}

	public String getExpression() {
		return expression;
	}

	public RepeatStatement expression(String expression) {
		this.expression = expression;
		return this;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("repeat\n");
		if (codeOfStatement != null)
			sb.append(codeOfStatement);
		else
			sb.append(block);
		sb.append("until (").append(expression).append("); \n");
		return sb.toString();
	}

}
