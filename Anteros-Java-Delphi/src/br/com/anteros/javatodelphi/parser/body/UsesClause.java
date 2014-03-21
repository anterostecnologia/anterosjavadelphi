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

public class UsesClause extends Node {

	protected List<String> uses = new ArrayList<String>();

	public UsesClause(String name) {
		super(name);
	}

	public List<String> getUses() {
		return uses;
	}

	public void setUses(List<String> uses) {
		this.uses = uses;
	}

	public UsesClause addUseClause(String use) {
		uses.add(use);
		return this;
	}

	@Override
	public String toString() {
		if (uses.size()==0)
			return "";
		StringBuffer sb = new StringBuffer();
		
		sb.append("uses").append("\n");
		boolean appendDelimiter = false;
		for (String u : uses) {
			sb.append(u);
			if (appendDelimiter)
				sb.append(",");
		}
		if (uses.size() > 0)
			sb.append(";\n");
		sb.append("\n");
		return sb.toString();
	}

}
