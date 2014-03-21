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

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class DelphiCodeGenerator {

	/*public static void converClassToDelphi(String className, String classFileName, String targetFolder)
			throws Exception {
		File file = new File(classFileName);
		BufferedReader in = new BufferedReader(new FileReader(file));
		StringBuffer buffer = new StringBuffer();
		String line = null;
		while (null != (line = in.readLine())) {
			buffer.append("\t" + line);
			buffer.append("\n");		}
    	convertClassToDelphi(className, buffer.toString());
	}*/

	public static void convertClassToDelphi(String className, String sourceClass, String targetFolder) {
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setSource(sourceClass.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		CompilationUnit compilationUnit = (CompilationUnit) parser.createAST(null);
		ASTJavaVisitor visitor = new ASTJavaVisitor(className);
		compilationUnit.accept(visitor);

		System.out.println(visitor.getUnit());
	}
}
