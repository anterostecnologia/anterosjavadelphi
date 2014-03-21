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

import java.util.ArrayList;
import java.util.List;

import br.com.anteros.javatodelphi.parser.Node;
import br.com.anteros.javatodelphi.parser.statement.Statement;
import br.com.anteros.javatodelphi.parser.statement.StatementOwner;

public class Block extends Node implements StatementOwner {

	protected List<Statement> statements = new ArrayList<Statement>();
	protected boolean appendEndOfLineDelimiter = true;

	public Block(String name) {
		super(name);
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public Block addStatement(Statement statement) {
		statements.add(statement);
		return this;
	}

	public void removeStatement(Statement statement) {
		statements.remove(statement);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("begin\n");
		for (Statement stmt : statements) {
			sb.append(stmt);
		}
		if (appendEndOfLineDelimiter)
			sb.append("end;\n");
		else
			sb.append("end\n");
		return sb.toString();
	}

	public boolean isAppendEndOfLineDelimiter() {
		return appendEndOfLineDelimiter;
	}

	public Block appendEndOfLineDelimiter(boolean appendEndOfLineDelimiter) {
		this.appendEndOfLineDelimiter = appendEndOfLineDelimiter;
		return this;
	}

}
