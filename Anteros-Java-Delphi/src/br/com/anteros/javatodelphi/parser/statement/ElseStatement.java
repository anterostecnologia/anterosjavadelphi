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

public class ElseStatement extends Statement {

	public ElseStatement(String name) {
		super(name);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("else\n");
		if (block.getStatements().size() > 0) {
			if (block.getStatements().get(0) instanceof IfStatement) {
				IfStatement ifStmt = (IfStatement) block.getStatements().get(0);
				if (ifStmt.elseStatement != null)
					ifStmt.getBlock().appendEndOfLineDelimiter(false);
				sb.append(ifStmt);
			} else
				sb.append(block);
		}

		return sb.toString();
	}

}
