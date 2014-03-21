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

public class ForStatement extends Statement {

	protected String initialValue;
	protected String toValue;
	
	public ForStatement(String name) {
		super(name);
	}

	public String getInitialValue() {
		return initialValue;
	}

	public ForStatement initialValue(String initialValue) {
		this.initialValue = initialValue;
		return this;
	}

	public String getToValue() {
		return toValue;
	}

	public ForStatement toValue(String toValue) {
		this.toValue = toValue;
		return this;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("for ").append(initialValue).append(" to ").append(toValue).append(" do\n");
		sb.append(block);
		
		return sb.toString();
	}


}
