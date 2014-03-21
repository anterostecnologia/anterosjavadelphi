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

public class VariableAssignStatement extends Statement{

	protected String variable;
	protected String initializerExpression;
	
	public VariableAssignStatement(String name) {
		super(name);
	}
	
	public VariableAssignStatement(String variable, String initializerExpression){
		super("");
		this.variable = variable;
		this.initializerExpression = initializerExpression;
	}

	public String getVariable() {
		return variable;
	}

	public VariableAssignStatement variable(String variable) {
		this.variable = variable;
		return this;
	}

	public String getInitializerExpression() {
		return initializerExpression;
	}

	public VariableAssignStatement initializerExpression(String initializerExpression) {
		this.initializerExpression = initializerExpression;
		return this;
	}
	
	@Override
	public String toString() {
		return variable+" := "+initializerExpression+";\n";
	}

}
