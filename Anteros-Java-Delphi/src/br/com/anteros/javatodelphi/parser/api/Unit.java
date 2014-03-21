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

public class Unit extends Node {

	protected UnitHeader unitHeader = new UnitHeader("");
	protected UnitInterface unitInterface = new UnitInterface("");
	protected UnitImplementation unitImplementation = new UnitImplementation("");
	protected UnitBlock unitBlock = new UnitBlock("");

	public Unit(String name) {
		super(name);
		unitBlock.setInterfaceDeclaration(unitInterface.getInterfaceDeclaration());
	}

	public UnitHeader getUnitHeader() {
		return unitHeader;
	}

	public void setUnitHeader(UnitHeader unitHeader) {
		this.unitHeader = unitHeader;
	}

	public UnitInterface getUnitInterface() {
		return unitInterface;
	}

	public void setUnitInterface(UnitInterface unitInterface) {
		this.unitInterface = unitInterface;
	}

	public UnitImplementation getUnitImplementation() {
		return unitImplementation;
	}

	public void setUnitImplementation(UnitImplementation unitImplementation) {
		this.unitImplementation = unitImplementation;
	}

	public UnitBlock getUnitBlock() {
		return unitBlock;
	}

	public void setUnitBlock(UnitBlock unitBlock) {
		this.unitBlock = unitBlock;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(unitHeader).append("\n");
		sb.append(unitInterface).append("\n");
		sb.append(unitImplementation).append("\n");
		sb.append(unitBlock).append("\n");
		sb.append("end.");
		return sb.toString();
	}

}
