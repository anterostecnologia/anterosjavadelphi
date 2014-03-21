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
package br.com.anteros.javatodelphi.parser;

public abstract class Node {

	protected String name;

	public Node(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String convertTypeFromDelphiToJava(String type) {
		if (type.contains("<? extends Serializable>"))
			type = replaceAll(type, "<? extends Serializable>", "");
		if (type.contains("<? extends Object>"))
			type = replaceAll(type, "<? extends Object>", "");	
		if ((type.startsWith("Set")) && (!type.startsWith("ISet")))
			type = replaceAll(type, "Set", "ISet");
		if (type.equals("Object"))
			return "TObject";
		if (type.equals("Class<?>"))
			return "TClass";
		if (type.equals("TClass<?>"))
			return "TClass";
		if (type.equals("Class"))
			return "TClass";
		if (type.equals("List"))
			return "IList";
		if (type.equals("Set"))
			return "ISet";
		if (type.equals("Map"))
			return "IDictionary";
		if (type.equals("int"))
			return "Integer";
		if (type.equals("long"))
			return "Integer";
		if (type.equals("float"))
			return "Real";
		if (type.equals("Collection"))
			return "ICollection";
		if (type.contains("[]"))
			return "array of " + convertTypeFromDelphiToJava(replaceAll(type, "[]", ""));
		if (type.startsWith("List"))
			type = replaceAll(type, "List", "IList");
		if (type.startsWith("Collection"))
			type = replaceAll(type, "Collection", "ICollection");
		if (type.startsWith("Map"))
			type = replaceAll(type, "Map", "IDictionary");
		if (type.contains("<?>"))
			type = replaceAll(type, "<?>", "");
		if (type.contains("<Object"))
			type = replaceAll(type, "Object", "TObject");
		return type;
	}
	
	public static String convertConstantJavaToDelphi(String constant){
		if (constant.equals("null")){
			return "nil";
		}
		return constant;
	}

	public static String capitalize(String s) {
		if (s == null)
			return s;
		if (s.length() == 0)
			return s;
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	public static String replaceAll(String input, String forReplace, String replaceWith) {
		if (input == null)
			return null;
		StringBuffer result = new StringBuffer();
		boolean hasMore = true;
		while (hasMore) {
			int start = input.toUpperCase().indexOf(forReplace.toUpperCase());
			int end = start + forReplace.length();
			if (start != -1) {
				result.append(input.substring(0, start) + replaceWith);
				input = input.substring(end);
			} else {
				hasMore = false;
				result.append(input);
			}
		}
		if (result.toString().equals(""))
			return input;

		return result.toString();
	}

}
