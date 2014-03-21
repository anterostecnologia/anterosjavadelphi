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
import br.com.anteros.javatodelphi.parser.body.section.Section;

public class DeclarationSection extends Node {

	protected List<Section> sections = new ArrayList<Section>();
	
	public DeclarationSection(String name) {
		super(name);
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	
	public DeclarationSection addSection(Section section){
		sections.add(section);
		return this;
	}
	
	public void removeSection(Section section){
		sections.remove(section);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Section section : sections)
			sb.append(section);
		return sb.toString();
	}

}
