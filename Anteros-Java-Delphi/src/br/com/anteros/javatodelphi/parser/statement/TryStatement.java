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

import java.util.ArrayList;
import java.util.List;

import br.com.anteros.javatodelphi.parser.body.Block;

public class TryStatement extends Statement {

	protected FinallyClause finallyClause = new FinallyClause("");
	protected List<CatchClause> catchs = new ArrayList<CatchClause>();

	public TryStatement(String name) {
		super(name);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (finallyClause.getFinallyBlock().getStatements().size() > 0)
			sb.append("try\n");
		if (catchs.size() > 0)
			sb.append("try\n");
		for (Statement stmt : block.getStatements()) {
			sb.append(stmt);
		}
		if (catchs.size() > 0) {
			sb.append("except\n");
			for (CatchClause cl : catchs) {
				sb.append(cl);
			}
		}
		if (catchs.size() > 0)
			sb.append("end;\n");
		sb.append(finallyClause);
		if (finallyClause.getFinallyBlock().getStatements().size() > 0)
			sb.append("end;\n");
		return sb.toString();
	}

	public TryStatement addCatchClause(CatchClause catchClause) {
		catchs.add(catchClause);
		return this;
	}

	public FinallyClause getFinallyClause() {
		return finallyClause;
	}

	public TryStatement finallyClause(FinallyClause finallyClause) {
		this.finallyClause = finallyClause;
		return this;
	}

}
