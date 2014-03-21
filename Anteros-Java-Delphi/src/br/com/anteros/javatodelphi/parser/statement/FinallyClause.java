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

public class FinallyClause extends Node implements StatementOwner {

	protected Block finallyBlock = new Block("");

	public FinallyClause(String name) {
		super(name);
	}

	public Block getFinallyBlock() {
		return finallyBlock;
	}

	public FinallyClause finallyBlock(Block finallyBlock) {
		this.finallyBlock = finallyBlock;
		return this;
	}

	@Override
	public String toString() {
		if (finallyBlock.getStatements().size()==0)
			return "";
		
		StringBuffer sb = new StringBuffer();
		sb.append("finally\n");
		sb.append(finallyBlock);
		return sb.toString();
	}

	@Override
	public StatementOwner addStatement(Statement statement) {
		finallyBlock.addStatement(statement);
		return this;
	}

}
