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

public class Statement extends Node implements StatementOwner {

	protected Block block = new Block("");
	protected String codeOfStatement;
	
	public Statement(String name) {
		super(name);
	}

	public String getCodeOfStatement() {
		return codeOfStatement;
	}

	public Statement codeOfStatement(String codeOfStatement) {
		this.codeOfStatement = codeOfStatement;
		return this;
	}

	public Block getBlock() {
		return block;
	}

	public Statement block(Block block) {
		this.block = block;
		return this;
	}
	
	public Statement addStatement(Statement statement) {
		block.getStatements().add(statement);
		return this;
	}
	
}
