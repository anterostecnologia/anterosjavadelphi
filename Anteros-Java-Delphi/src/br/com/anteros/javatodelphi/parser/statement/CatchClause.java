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

import br.com.anteros.javatodelphi.parser.Node;
import br.com.anteros.javatodelphi.parser.body.Block;
import br.com.anteros.javatodelphi.parser.declaration.SimpleVariableDeclaration;

public class CatchClause extends Node implements StatementOwner {

	protected SimpleVariableDeclaration variable;
	protected Block exceptionBlock = new Block("");

	public CatchClause(String name) {
		super(name);
	}

	public SimpleVariableDeclaration getVariable() {
		return variable;
	}

	public CatchClause variable(SimpleVariableDeclaration variable) {
		this.variable = variable;
		return this;
	}

	public Block getExceptionBlock() {
		return exceptionBlock;
	}

	public CatchClause exceptionBlock(Block exceptionBlock) {
		this.exceptionBlock = exceptionBlock;
		return this;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("on ").append(variable.getName() + " : " + variable.getType()).append(" do\n");
		sb.append(exceptionBlock);
		return sb.toString();
	}

	@Override
	public StatementOwner addStatement(Statement statement) {
		exceptionBlock.addStatement(statement);
		return this;
	}

}
