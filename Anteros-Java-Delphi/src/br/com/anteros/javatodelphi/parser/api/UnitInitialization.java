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
package br.com.anteros.javatodelphi.parser.api;

import java.util.ArrayList;
import java.util.List;

import br.com.anteros.javatodelphi.parser.Node;
import br.com.anteros.javatodelphi.parser.statement.Statement;

public class UnitInitialization extends Node {

	protected List<Statement> statements = new ArrayList<Statement>();
	protected UnitFinalization unitFinalization;
	
	public UnitInitialization(String name) {
		super(name);
	}

	public UnitFinalization getUnitFinalization() {
		return unitFinalization;
	}

	public void setUnitFinalization(UnitFinalization unitFinalization) {
		this.unitFinalization = unitFinalization;
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public UnitInitialization addStatement(Statement statement){
		this.statements.add(statement);
		return this;
	}
	
	public void removeStatement(Statement statement){
        this.statements.remove(statement);		
	}

}
