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

import br.com.anteros.javatodelphi.parser.Node;

public class UnitHeader extends Node {

	protected HintingDirectiveType hintingDirective;
	protected String nameSpace;

	public UnitHeader(String name) {
		super(name);
	}

	public HintingDirectiveType getHintingDirective() {
		return hintingDirective;
	}

	public void setHintingDirective(HintingDirectiveType hintingDirective) {
		this.hintingDirective = hintingDirective;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("unit ");
		if (hintingDirective != null)
			sb.append(hintingDirective.getTypeName());
		sb.append((nameSpace == null ? "" : nameSpace)).append(name);
		sb.append(";\n");
		return sb.toString();
	}

}
