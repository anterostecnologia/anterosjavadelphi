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

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.InfixExpression.Operator;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.SuperFieldAccess;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.TypeLiteral;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import br.com.anteros.javatodelphi.parser.api.Unit;
import br.com.anteros.javatodelphi.parser.api.UnitBlock;
import br.com.anteros.javatodelphi.parser.body.AbstractMethodBody;
import br.com.anteros.javatodelphi.parser.body.FunctionBody;
import br.com.anteros.javatodelphi.parser.body.ProcedureBody;
import br.com.anteros.javatodelphi.parser.body.section.ConstantSection;
import br.com.anteros.javatodelphi.parser.body.section.TypeSection;
import br.com.anteros.javatodelphi.parser.declaration.AbstractMethodHeaderDeclaration;
import br.com.anteros.javatodelphi.parser.declaration.AbstractVariableDeclaration;
import br.com.anteros.javatodelphi.parser.declaration.ClassTypeDeclaration;
import br.com.anteros.javatodelphi.parser.declaration.FunctionHeaderDeclaration;
import br.com.anteros.javatodelphi.parser.declaration.InterfaceTypeDeclaration;
import br.com.anteros.javatodelphi.parser.declaration.ProcedureHeaderDeclaration;
import br.com.anteros.javatodelphi.parser.declaration.PropertyDeclaration;
import br.com.anteros.javatodelphi.parser.declaration.SimpleVariableDeclaration;
import br.com.anteros.javatodelphi.parser.declaration.VarWithGenericsDeclaration;
import br.com.anteros.javatodelphi.parser.statement.BreakStatement;
import br.com.anteros.javatodelphi.parser.statement.CaseConditionStatement;
import br.com.anteros.javatodelphi.parser.statement.CaseStatement;
import br.com.anteros.javatodelphi.parser.statement.CatchClause;
import br.com.anteros.javatodelphi.parser.statement.ConstructorInvocationStatement;
import br.com.anteros.javatodelphi.parser.statement.ElseStatement;
import br.com.anteros.javatodelphi.parser.statement.EnhancedForStatement;
import br.com.anteros.javatodelphi.parser.statement.ExpressionStatement;
import br.com.anteros.javatodelphi.parser.statement.FinallyClause;
import br.com.anteros.javatodelphi.parser.statement.ForStatement;
import br.com.anteros.javatodelphi.parser.statement.IfStatement;
import br.com.anteros.javatodelphi.parser.statement.InheritedStatement;
import br.com.anteros.javatodelphi.parser.statement.RepeatStatement;
import br.com.anteros.javatodelphi.parser.statement.ReturnStatement;
import br.com.anteros.javatodelphi.parser.statement.StatementOwner;
import br.com.anteros.javatodelphi.parser.statement.SynchronizedStatement;
import br.com.anteros.javatodelphi.parser.statement.ThrowStatement;
import br.com.anteros.javatodelphi.parser.statement.TryStatement;
import br.com.anteros.javatodelphi.parser.statement.UnknownStatement;
import br.com.anteros.javatodelphi.parser.statement.VariableAssignStatement;
import br.com.anteros.javatodelphi.parser.statement.WhileStatement;

public class ASTJavaVisitor extends ASTVisitor {

	private Unit unit;
	private AbstractMethodBody methodBody;
	private AbstractMethodHeaderDeclaration methodHeaderDeclaration;

	public ASTJavaVisitor(String fileName) {
		unit = new Unit(fileName);
	}

	@Override
	public void preVisit(ASTNode node) {
	}

	@Override
	public void postVisit(ASTNode node) {
	}

	@Override
	public boolean visit(CompilationUnit node) {
		unit.getUnitHeader().setName(node.getPackage().getName() + "." + unit.getName());
		return super.visit(node);
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		return super.visit(node);
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		ConstantSection constantSection = unit.getUnitInterface().getInterfaceDeclaration().getConstantSection();
		TypeSection typeSection = unit.getUnitInterface().getInterfaceDeclaration().getTypeSection();
		if (node.isInterface()) {
			InterfaceTypeDeclaration interfaceTypeDeclaration = new InterfaceTypeDeclaration(node.getName()
					.getFullyQualifiedName());
			Type superclassType = node.getSuperclassType();
			if (superclassType != null)
				interfaceTypeDeclaration.interfaceType(superclassType.toString());
			typeSection.addType(interfaceTypeDeclaration);
		} else {
			ClassTypeDeclaration classTypeDeclaration = new ClassTypeDeclaration(node.getName().getFullyQualifiedName());
			Type superclassType = node.getSuperclassType();
			String superClassNames = "";
			if ((superclassType == null) && (node.superInterfaceTypes().size() > 0))
				superClassNames = "TInterfacedObject";
			else if (superclassType != null)
				superClassNames = superclassType.toString();

			for (Object s : node.superInterfaceTypes()) {
				if (s instanceof SimpleType) {
					if (!"".equals(superClassNames))
						superClassNames = superClassNames + ",";
					superClassNames = superClassNames + ((SimpleType) s).getName();
				}
			}
			classTypeDeclaration.classType(superClassNames);
			typeSection.addType(classTypeDeclaration);
			addMethodsAndParameters(node, classTypeDeclaration);
			addFieldsAndProperties(node, classTypeDeclaration, constantSection);
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(EnumConstantDeclaration node) {
		System.out.println("EnumConstantDeclaration " + node);
		return super.visit(node);
	}

	@Override
	public boolean visit(EnumDeclaration node) {
		System.out.println("EnumDeclaration " + node);
		return super.visit(node);
	}

	protected void addFieldsAndProperties(TypeDeclaration node, ClassTypeDeclaration classTypeDeclaration,
			ConstantSection constantSection) {
		boolean appendDelimiter;
		for (FieldDeclaration fieldDeclaration : node.getFields()) {
			AbstractVariableDeclaration variable = null;
			if (fieldDeclaration.getType().isParameterizedType()) {
				variable = new VarWithGenericsDeclaration(
						Node.capitalize(((VariableDeclarationFragment) fieldDeclaration.fragments().get(0)).getName()
								.toString()));
				appendDelimiter = false;
				String typeName = Node.convertTypeFromDelphiToJava(((ParameterizedType) fieldDeclaration.getType())
						.getType().toString()) + "<";
				for (Object pt : ((ParameterizedType) fieldDeclaration.getType()).typeArguments()) {
					if (appendDelimiter)
						typeName = typeName + ',';
					typeName = typeName + Node.convertTypeFromDelphiToJava(pt.toString());
					appendDelimiter = true;
				}
				typeName = typeName + ">";
				typeName = Node.convertTypeFromDelphiToJava(typeName);
				variable.type(typeName);
			} else {
				variable = new SimpleVariableDeclaration(
						Node.capitalize(((VariableDeclarationFragment) fieldDeclaration.fragments().get(0)).getName()
								.toString()));
				variable.type(Node.convertTypeFromDelphiToJava(fieldDeclaration.getType().toString()));
			}
			if (fieldDeclaration.modifiers().size() == 0)
				variable.privateVariable(true);
			else {
				for (Object modifier : fieldDeclaration.modifiers()) {
					if (modifier instanceof Modifier) {
						if (((Modifier) modifier).isPrivate()) {
							variable.privateVariable(true);
						} else if (((Modifier) modifier).isProtected()) {
							variable.protectedVariable(true);
						} else if (((Modifier) modifier).isPublic()) {
							variable.publicVariable(true);
						} else if (((Modifier) modifier).isStatic()) {
							variable.staticVariable(true);
						}
					}
				}
			}
			if (variable.isStaticVariable()) {
				VariableDeclarationFragment dc = (VariableDeclarationFragment) fieldDeclaration.fragments().get(0);
				variable.value(Node.convertTypeFromDelphiToJava(processExpression(dc.getInitializer())));
				constantSection.addVariable(variable);
			} else {
				if ((variable.isProtectedVariable() || variable.isPrivateVariable())
						&& ((hasGetMethodForField(variable.getName(), node.getMethods())) || (hasSetMethodForField(
								variable.getName(), node.getMethods())))) {
					PropertyDeclaration property = new PropertyDeclaration(variable.getName());
					property.readMethod(Node.capitalize(getGetMethodForField(variable.getName(), node.getMethods())));
					property.writeMethod(Node.capitalize(getSetMethodForField(variable.getName(), node.getMethods())));
					property.variable(variable);
					variable.setName("F" + variable.getName());
					classTypeDeclaration.addProperty(property);
				}
				classTypeDeclaration.addVariable(variable);
			}
		}
	}

	protected void addMethodsAndParameters(TypeDeclaration node, ClassTypeDeclaration classTypeDeclaration) {
		boolean appendDelimiter;
		UnitBlock unitBlock = unit.getUnitBlock();
		for (MethodDeclaration method : node.getMethods()) {
			/*
			 * Cria o método
			 */
			methodHeaderDeclaration = null;
			if ((method.getReturnType2() != null) && (!"void".equalsIgnoreCase(method.getReturnType2().toString()))) {
				methodHeaderDeclaration = new FunctionHeaderDeclaration(method.getName().getFullyQualifiedName());
				if (method.getReturnType2().isParameterizedType()) {
					((FunctionHeaderDeclaration) methodHeaderDeclaration).returnType(ConvertTypeToString(method
							.getReturnType2()));
				} else
					((FunctionHeaderDeclaration) methodHeaderDeclaration).returnType(Node
							.convertTypeFromDelphiToJava(method.getReturnType2().toString()));
			} else {
				methodHeaderDeclaration = new ProcedureHeaderDeclaration(method.getName().getFullyQualifiedName());
			}
			methodHeaderDeclaration.typeDeclaration(classTypeDeclaration);
			methodHeaderDeclaration.setConstructor(method.isConstructor());
			if (countNumberOfMethodsByName(methodHeaderDeclaration.getName(), node.getMethods()) > 1)
				methodHeaderDeclaration.overload(true);
			if (method.getJavadoc() != null) {
				if (method.getJavadoc().isBlockComment() || method.getJavadoc().isDocComment()) {
					String result = "{\n";
					for (Object tag : method.getJavadoc().tags()) {
						result = result + tag.toString();
					}
					result = result + "}\n";
					methodHeaderDeclaration.comment(result);
				} else if (method.getJavadoc().isLineComment()) {
					String result = "";
					for (Object tag : method.getJavadoc().tags()) {
						result = result + "\\" + tag.toString();
					}
					methodHeaderDeclaration.comment(result);
				}
			}
			/*
			 * Seta os modificadores
			 */
			for (Object modifier : method.modifiers()) {
				if (modifier instanceof MarkerAnnotation) {
					if ("Override".equals(((MarkerAnnotation) modifier).getTypeName())) {
						methodHeaderDeclaration.override(true);
					}
				} else if (modifier instanceof Modifier) {
					if (((Modifier) modifier).isAbstract())
						methodHeaderDeclaration.virtualMethod(true);
					if (((Modifier) modifier).isPrivate()) {
						methodHeaderDeclaration.privateMethod(true);
					} else if (((Modifier) modifier).isProtected()) {
						methodHeaderDeclaration.protectedMethod(true);
					} else if (((Modifier) modifier).isStatic()) {
						methodHeaderDeclaration.staticMethod(true);
					} else if (((Modifier) modifier).isPublic()) {
						methodHeaderDeclaration.publicMethod(true);
					}
				}
			}

			/*
			 * Adiciona os parâmetros do método
			 */
			appendDelimiter = false;
			String typeName = "";
			for (Object pt : method.typeParameters()) {
				if (appendDelimiter)
					typeName = typeName + ',';
				typeName = typeName + Node.convertTypeFromDelphiToJava(pt.toString());
				appendDelimiter = true;
			}
			if (!"".equals(typeName))
				methodHeaderDeclaration.genericsTypeOfMethod(typeName);

			for (Object p : method.parameters()) {
				if (p instanceof SingleVariableDeclaration) {
					typeName = getTypeSingleVariable(p);
					methodHeaderDeclaration.addParameter(
							'A' + Node.capitalize(((SingleVariableDeclaration) p).getName().getFullyQualifiedName()),
							typeName);
				}
			}
			classTypeDeclaration.addMethod(methodHeaderDeclaration);

			/*
			 * Cria o corpo do método
			 */
			if (methodHeaderDeclaration instanceof ProcedureHeaderDeclaration) {
				methodBody = new ProcedureBody("");
			} else {
				methodBody = new FunctionBody("");
			}
			methodBody.typeOwner(classTypeDeclaration);
			methodBody.methodHeaderDeclaration(methodHeaderDeclaration);

			Block block = method.getBody();
			processBlock(block, methodBody);

			/*
			 * Adiciona o método na lista de métodos da unit
			 */
			unitBlock.addMethod(methodBody);
		}
	}

	protected String getTypeSingleVariable(Object p) {
		boolean appendDelimiter;
		String typeName;
		typeName = "";
		Type type = ((SingleVariableDeclaration) p).getType();
		if (type instanceof ParameterizedType) {
			appendDelimiter = false;
			typeName = Node.convertTypeFromDelphiToJava(((ParameterizedType) type).getType().toString()) + "<";
			for (Object pt : ((ParameterizedType) type).typeArguments()) {
				if (appendDelimiter)
					typeName = typeName + ',';
				typeName = typeName + Node.convertTypeFromDelphiToJava(pt.toString());
				appendDelimiter = true;
			}
			typeName = typeName + ">";
			typeName = Node.convertTypeFromDelphiToJava(typeName);
		} else {
			typeName = Node.convertTypeFromDelphiToJava(((SingleVariableDeclaration) p).getType().toString());
			if (((SingleVariableDeclaration) p).isVarargs()) {
				typeName = "array of " + typeName;
			}
		}
		return typeName;
	}

	protected void processBlock(Block block, StatementOwner owner) {
		for (Object o : block.statements()) {
			if (o instanceof org.eclipse.jdt.core.dom.AssertStatement) {
				System.out.println("AssertStatement)");
			} else if (o instanceof org.eclipse.jdt.core.dom.BreakStatement) {
				processBreakStatement(owner);
			} else if (o instanceof org.eclipse.jdt.core.dom.ConstructorInvocation) {
				processConstructorInvocation(owner, o);
			} else if (o instanceof org.eclipse.jdt.core.dom.ContinueStatement) {
				processContinueStatement(owner);
			} else if (o instanceof org.eclipse.jdt.core.dom.DoStatement) {
				processDoStatement(owner, o);
			} else if (o instanceof org.eclipse.jdt.core.dom.EmptyStatement) {
				processEmptyStatement(owner, o);
			} else if (o instanceof org.eclipse.jdt.core.dom.EnhancedForStatement) {
				processEnhancedForStatement(owner, o);
			} else if (o instanceof org.eclipse.jdt.core.dom.ExpressionStatement) {
				processExpressionStatement(owner, o);
			} else if (o instanceof org.eclipse.jdt.core.dom.ForStatement) {
				processForStatement(owner, o);
			} else if (o instanceof org.eclipse.jdt.core.dom.IfStatement) {
				processIfStatement(owner, (org.eclipse.jdt.core.dom.IfStatement) o);
			} else if (o instanceof org.eclipse.jdt.core.dom.LabeledStatement) {
				System.out.println("LabeledStatement) ");
			} else if (o instanceof org.eclipse.jdt.core.dom.ReturnStatement) {
				processReturnStatement(owner, o);
			} else if (o instanceof org.eclipse.jdt.core.dom.SuperConstructorInvocation) {
				processSuperConstructorInvocation(owner, o);
			} else if (o instanceof org.eclipse.jdt.core.dom.SwitchStatement) {
				processSwitchStatement(owner, o);
			} else if (o instanceof org.eclipse.jdt.core.dom.SynchronizedStatement) {
				processSynchronizedStatement(owner, o);
			} else if (o instanceof org.eclipse.jdt.core.dom.ThrowStatement) {
				processThrowStatement(owner, o);
			} else if (o instanceof org.eclipse.jdt.core.dom.TryStatement) {
				processTryStatement(owner, o);
			} else if (o instanceof org.eclipse.jdt.core.dom.VariableDeclarationStatement) {
				processVariableDeclarationStatement(owner, o);
			} else if (o instanceof org.eclipse.jdt.core.dom.WhileStatement) {
				processWhileStatement(owner, o);
			} else {
				owner.addStatement(new UnknownStatement(o.toString()));
			}
		}
	}

	private void processConstructorInvocation(StatementOwner owner, Object o) {
		org.eclipse.jdt.core.dom.ConstructorInvocation ci = (org.eclipse.jdt.core.dom.ConstructorInvocation) o;
		ConstructorInvocationStatement newConstructorInvocationStatement = new ConstructorInvocationStatement("");
		String result = "self.Create";
		if (ci.arguments().size() > 0) {
			result = result + "(";
			boolean appendDelimiter = false;
			for (Object arg : ci.arguments()) {
				if (appendDelimiter)
					result = result + ",";
				result = result + processExpression((Expression) arg);
				appendDelimiter = true;
			}
			result = result + ")";
		}
		newConstructorInvocationStatement.codeOfStatement(result);
		owner.addStatement(newConstructorInvocationStatement);
	}

	private void processSuperConstructorInvocation(StatementOwner owner, Object o) {
		org.eclipse.jdt.core.dom.SuperConstructorInvocation sc = (org.eclipse.jdt.core.dom.SuperConstructorInvocation) o;
		InheritedStatement newInheritedStatement = new InheritedStatement("");
		if (sc.getExpression() != null) {
			newInheritedStatement.codeOfStatement("inherited Create(" + processExpression(sc.getExpression()) + ")");
		} else {
			String result = "inherited Create";
			if (sc.arguments().size() > 0) {
				result = result + "(";
				boolean appendDelimiter = false;
				for (Object arg : sc.arguments()) {
					if (appendDelimiter)
						result = result + ",";
					result = result + processExpression((Expression) arg);
					appendDelimiter = true;
				}
				result = result + ")";
			}
			newInheritedStatement.codeOfStatement(result);
		}
		owner.addStatement(newInheritedStatement);
	}

	private void processEmptyStatement(StatementOwner owner, Object o) {
		ExpressionStatement newExpressionStatement = new ExpressionStatement("");
		newExpressionStatement.codeOfStatement("");
		owner.addStatement(newExpressionStatement);
	}

	protected String processExpression(Expression expression) {
		if (expression instanceof ArrayAccess) {
			return processArrayAccessExpression(expression);
		} else if (expression instanceof ArrayCreation) {
			return processArrayCreationExpression(expression);
		} else if (expression instanceof ArrayInitializer) {
			return processArrayInitializerExpression(expression);
		} else if (expression instanceof Assignment) {
			return processAssignmentExpression(expression);
		} else if (expression instanceof BooleanLiteral) {
			return processArrayAccessExpression(expression);
		} else if (expression instanceof CastExpression) {
			return processCastExpression(expression);
		} else if (expression instanceof CharacterLiteral) {
			return processArrayAccessExpression(expression);
		} else if (expression instanceof ClassInstanceCreation) {
			return processClassInstanceCreationExpression(expression);
		} else if (expression instanceof ConditionalExpression) {
			return processConditionalExpression(expression);
		} else if (expression instanceof FieldAccess) {
			return processFieldAccessExpression(expression);
		} else if (expression instanceof InfixExpression) {
			return processInfixExpression(expression);
		} else if (expression instanceof org.eclipse.jdt.core.dom.InstanceofExpression) {
			return processInstanceofExpression(expression);
		} else if (expression instanceof MethodInvocation) {
			return processMethodInvocationExpression(expression);
		} else if (expression instanceof QualifiedName) {
			return processQualifiedNameExpression(expression);
		} else if (expression instanceof Name) {
			return processNameExpression(expression);
		} else if (expression instanceof NullLiteral) {
			return processNullLiteralExpression();
		} else if (expression instanceof NumberLiteral) {
			return processArrayAccessExpression(expression);
		} else if (expression instanceof ParenthesizedExpression) {
			return processParenthesizedExpression(expression);
		} else if (expression instanceof PostfixExpression) {
			return processPostfixExpression(expression);
		} else if (expression instanceof PrefixExpression) {
			return processPrefixExpression(expression);
		} else if (expression instanceof StringLiteral) {
			return processStringLiteralExpression(expression);
		} else if (expression instanceof SuperFieldAccess) {
			System.out.println("SuperFieldAccess " + expression);
		} else if (expression instanceof SuperMethodInvocation) {
			System.out.println("SuperMethodInvocation " + expression);
		} else if (expression instanceof ThisExpression) {
			return processThisExpression();
		} else if (expression instanceof TypeLiteral) {
			return processArrayAccessExpression(expression);
		} else if (expression instanceof VariableDeclarationExpression) {
			return processVariableDeclarationExpression(expression);
		}
		return "";
	}

	protected String processThisExpression() {
		return "self";
	}

	protected void processBreakStatement(StatementOwner owner) {
		BreakStatement newBreakStatement = new BreakStatement("");
		owner.addStatement(newBreakStatement);
	}

	protected void processContinueStatement(StatementOwner owner) {
		ExpressionStatement newExpressionStatement = new ExpressionStatement("");
		newExpressionStatement.codeOfStatement("continue");
		owner.addStatement(newExpressionStatement);
	}

	protected void processThrowStatement(StatementOwner owner, Object o) {
		org.eclipse.jdt.core.dom.ThrowStatement ts = (org.eclipse.jdt.core.dom.ThrowStatement) o;
		ThrowStatement newThrowStatement = new ThrowStatement("");
		newThrowStatement.codeOfStatement(processExpression(ts.getExpression()));
		owner.addStatement(newThrowStatement);
	}

	protected void processSynchronizedStatement(StatementOwner owner, Object o) {
		org.eclipse.jdt.core.dom.SynchronizedStatement st = (org.eclipse.jdt.core.dom.SynchronizedStatement) o;
		SynchronizedStatement newSynchronizedStatement = new SynchronizedStatement("");
		processBlock(st.getBody(), newSynchronizedStatement);
		owner.addStatement(newSynchronizedStatement);
	}

	protected void processExpressionStatement(StatementOwner owner, Object o) {
		org.eclipse.jdt.core.dom.ExpressionStatement ex = (org.eclipse.jdt.core.dom.ExpressionStatement) o;
		ExpressionStatement newExpressionStatement = new ExpressionStatement("");
		newExpressionStatement.codeOfStatement(processExpression(ex.getExpression()));
		owner.addStatement(newExpressionStatement);
	}

	protected void processDoStatement(StatementOwner owner, Object o) {
		org.eclipse.jdt.core.dom.DoStatement ds = (org.eclipse.jdt.core.dom.DoStatement) o;
		RepeatStatement newRepeatStatement = new RepeatStatement("");
		newRepeatStatement.expression(processExpression(ds.getExpression()));
		if (ds.getBody() instanceof Block)
			processBlock((Block) ds.getBody(), newRepeatStatement);
		else if (ds.getBody() instanceof org.eclipse.jdt.core.dom.ExpressionStatement) {
			org.eclipse.jdt.core.dom.ExpressionStatement ex = (org.eclipse.jdt.core.dom.ExpressionStatement) ds
					.getBody();
			ExpressionStatement newExpressionStatement = new ExpressionStatement("");
			newExpressionStatement.codeOfStatement(processExpression(ex.getExpression()));
			newRepeatStatement.addStatement(newExpressionStatement);
		}
		owner.addStatement(newRepeatStatement);
	}

	protected void processEnhancedForStatement(StatementOwner owner, Object o) {
		org.eclipse.jdt.core.dom.EnhancedForStatement forStmt = (org.eclipse.jdt.core.dom.EnhancedForStatement) o;
		SingleVariableDeclaration parameter = forStmt.getParameter();
		SimpleVariableDeclaration variable = new SimpleVariableDeclaration(parameter.getName().toString());
		variable.type(Node.convertTypeFromDelphiToJava(parameter.getType().toString()));
		methodBody.getVarSection().addVariable(variable);
		EnhancedForStatement newEnhancedForStatement = new EnhancedForStatement("");
		newEnhancedForStatement.paramName(processExpression(forStmt.getParameter().getName()));
		newEnhancedForStatement.expression(processExpression(forStmt.getExpression()));
		if (forStmt.getBody() instanceof org.eclipse.jdt.core.dom.ExpressionStatement) {
			org.eclipse.jdt.core.dom.ExpressionStatement ex = (org.eclipse.jdt.core.dom.ExpressionStatement) forStmt
					.getBody();
			ExpressionStatement newExpressionStatement = new ExpressionStatement("");
			newExpressionStatement.codeOfStatement(processExpression(ex.getExpression()));
			newEnhancedForStatement.addStatement(newExpressionStatement);
		} else if (forStmt.getBody() instanceof Block)
			processBlock((Block) forStmt.getBody(), newEnhancedForStatement);
		owner.addStatement(newEnhancedForStatement);
	}

	protected void processForStatement(StatementOwner owner, Object o) {
		org.eclipse.jdt.core.dom.ForStatement fstmt = (org.eclipse.jdt.core.dom.ForStatement) o;
		ForStatement newForStatement = new ForStatement("");
		String result = "";
		for (Object init : fstmt.initializers()) {
			result = result + processExpression((Expression) init);
		}
		newForStatement.initialValue(result);
		newForStatement.toValue(processExpression(fstmt.getExpression()));
		if (fstmt.getBody() instanceof org.eclipse.jdt.core.dom.ExpressionStatement) {
			org.eclipse.jdt.core.dom.ExpressionStatement ex = (org.eclipse.jdt.core.dom.ExpressionStatement) fstmt
					.getBody();
			ExpressionStatement newExpressionStatement = new ExpressionStatement("");
			newExpressionStatement.codeOfStatement(processExpression(ex.getExpression()));
			newForStatement.addStatement(newExpressionStatement);
		} else if (fstmt.getBody() instanceof Block)
			processBlock((Block) fstmt.getBody(), newForStatement);
		owner.addStatement(newForStatement);
	}

	protected void processWhileStatement(StatementOwner owner, Object o) {
		org.eclipse.jdt.core.dom.WhileStatement wt = (org.eclipse.jdt.core.dom.WhileStatement) o;
		WhileStatement newWhileStatement = new WhileStatement("");
		newWhileStatement.expression(processExpression(wt.getExpression()));
		if (wt.getBody() instanceof Block)
			processBlock((Block) wt.getBody(), newWhileStatement);
		else if (wt.getBody() instanceof org.eclipse.jdt.core.dom.ExpressionStatement) {
			org.eclipse.jdt.core.dom.ExpressionStatement ex = (org.eclipse.jdt.core.dom.ExpressionStatement) wt
					.getBody();
			ExpressionStatement newExpressionStatement = new ExpressionStatement("");
			newExpressionStatement.codeOfStatement(processExpression(ex.getExpression()));
			newWhileStatement.addStatement(newExpressionStatement);
		}
		owner.addStatement(newWhileStatement);
	}

	protected void processVariableDeclarationStatement(StatementOwner owner, Object o) {
		VariableDeclarationStatement vd = (VariableDeclarationStatement) o;
		processVariable(vd, methodBody);
		for (Object fragment : ((VariableDeclarationStatement) o).fragments()) {
			VariableDeclarationFragment vf = ((VariableDeclarationFragment) fragment);
			if (vf.getInitializer() != null) {
				String result = vf.getName().toString();
				if (result.equalsIgnoreCase("result"))
					result = "_Result";
				owner.addStatement(new VariableAssignStatement(result, Node
						.convertConstantJavaToDelphi(processExpression(vf.getInitializer()))));
			}
		}
	}

	protected void processSwitchStatement(StatementOwner owner, Object o) {
		org.eclipse.jdt.core.dom.SwitchStatement st = (org.eclipse.jdt.core.dom.SwitchStatement) o;
		CaseStatement newCaseStatement = new CaseStatement("");
		newCaseStatement.expression(processExpression(st.getExpression()));
		for (Object stmt : st.statements()) {
			if (stmt instanceof SwitchCase) {
				SwitchCase sc = (SwitchCase) stmt;
				CaseConditionStatement newCaseConditionStatement = new CaseConditionStatement("");
				if (sc.getExpression() != null)
					newCaseConditionStatement.codeOfStatement(processExpression(sc.getExpression()));
				else
					newCaseConditionStatement.setDefault(true);
				newCaseStatement.addStatement(newCaseConditionStatement);
			} else if (stmt instanceof org.eclipse.jdt.core.dom.BreakStatement) {
				BreakStatement newBreakStatement = new BreakStatement("");
				newCaseStatement.addStatement(newBreakStatement);
			} else if (stmt instanceof org.eclipse.jdt.core.dom.ExpressionStatement) {
				org.eclipse.jdt.core.dom.ExpressionStatement ex = (org.eclipse.jdt.core.dom.ExpressionStatement) stmt;
				ExpressionStatement newExpressionStatement = new ExpressionStatement("");
				newExpressionStatement.codeOfStatement(processExpression(ex.getExpression()));
				newCaseStatement.addStatement(newExpressionStatement);
			} else if (stmt instanceof org.eclipse.jdt.core.dom.ReturnStatement) {
				processReturnStatement(newCaseStatement, stmt);
			}
		}
		owner.addStatement(newCaseStatement);
	}

	protected void processTryStatement(StatementOwner owner, Object o) {
		org.eclipse.jdt.core.dom.TryStatement tr = (org.eclipse.jdt.core.dom.TryStatement) o;
		TryStatement newTryStatement = new TryStatement("");
		processBlock(tr.getBody(), newTryStatement);
		for (Object occ : tr.catchClauses()) {
			org.eclipse.jdt.core.dom.CatchClause cc = (org.eclipse.jdt.core.dom.CatchClause) occ;
			String typeName = getTypeSingleVariable(cc.getException());
			CatchClause newCatchClause = new CatchClause("");
			SimpleVariableDeclaration simpleVariableDeclaration = new SimpleVariableDeclaration(cc.getException()
					.getName().getFullyQualifiedName());
			simpleVariableDeclaration.type(typeName);
			newCatchClause.variable(simpleVariableDeclaration);
			processBlock(cc.getBody(), newCatchClause);
			newTryStatement.addCatchClause(newCatchClause);
		}
		if (tr.getFinally() != null) {
			FinallyClause newFinallyClause = new FinallyClause("");
			processBlock(tr.getFinally(), newFinallyClause);
			newTryStatement.finallyClause(newFinallyClause);
		}
		owner.addStatement(newTryStatement);
	}

	protected void processReturnStatement(StatementOwner owner, Object o) {
		org.eclipse.jdt.core.dom.ReturnStatement rs = (org.eclipse.jdt.core.dom.ReturnStatement) o;
		String result = "result := " + processExpression(rs.getExpression());
		if ((rs.getParent() instanceof Block)) {
			if (!(rs.getParent().getParent() instanceof MethodDeclaration))
				result = result + ";\n" + "exit";
		} else if (rs.getParent() instanceof org.eclipse.jdt.core.dom.IfStatement)
			result = result + ";\n" + "exit";
		ReturnStatement newReturnStatement = new ReturnStatement("");
		newReturnStatement.codeOfStatement(result);
		owner.addStatement(newReturnStatement);
	}

	protected void processIfStatement(StatementOwner owner, org.eclipse.jdt.core.dom.IfStatement ifStmt) {
		IfStatement newIfStatement = new IfStatement("");
		owner.addStatement(newIfStatement);
		newIfStatement.expression(processExpression(ifStmt.getExpression()));
		if (ifStmt.getThenStatement() instanceof Block)
			processBlock((Block) ifStmt.getThenStatement(), newIfStatement);
		else if (ifStmt.getThenStatement() instanceof org.eclipse.jdt.core.dom.ExpressionStatement) {
			org.eclipse.jdt.core.dom.ExpressionStatement ex = (org.eclipse.jdt.core.dom.ExpressionStatement) ifStmt
					.getThenStatement();
			ExpressionStatement newExpressionStatement = new ExpressionStatement("");
			newExpressionStatement.codeOfStatement(processExpression(ex.getExpression()));
			newIfStatement.addStatement(newExpressionStatement);
		} else if (ifStmt.getThenStatement() instanceof org.eclipse.jdt.core.dom.ReturnStatement)
			processReturnStatement(newIfStatement, ifStmt.getThenStatement());
		if (ifStmt.getElseStatement() != null) {
			if (ifStmt.getElseStatement() instanceof Block) {
				ElseStatement newElseStatement = new ElseStatement("");
				processBlock((Block) ifStmt.getElseStatement(), newElseStatement);
				newIfStatement.elseStatement(newElseStatement);
			} else if (ifStmt.getElseStatement() instanceof org.eclipse.jdt.core.dom.ExpressionStatement) {
				org.eclipse.jdt.core.dom.ExpressionStatement ex = (org.eclipse.jdt.core.dom.ExpressionStatement) ifStmt
						.getElseStatement();
				ExpressionStatement newExpressionStatement = new ExpressionStatement("");
				newExpressionStatement.codeOfStatement(processExpression(ex.getExpression()));
				newIfStatement.addStatement(newExpressionStatement);
			} else if (ifStmt.getElseStatement() instanceof org.eclipse.jdt.core.dom.IfStatement) {
				ElseStatement newElseStatement = new ElseStatement("");
				processIfStatement(newElseStatement, (org.eclipse.jdt.core.dom.IfStatement) ifStmt.getElseStatement());
				newIfStatement.elseStatement(newElseStatement);
			}
		}
	}

	protected String processArrayAccessExpression(Expression expression) {
		return expression.toString();
	}

	protected String processFieldAccessExpression(Expression expression) {
		FieldAccess fd = (FieldAccess) expression;
		return processExpression(fd.getExpression()) + "." + processExpression(fd.getName());
	}

	protected String processVariableDeclarationExpression(Expression expression) {
		VariableDeclarationExpression ve = (VariableDeclarationExpression) expression;
		AbstractVariableDeclaration variable = new SimpleVariableDeclaration(
				Node.capitalize(((VariableDeclarationFragment) ve.fragments().get(0)).getName().toString()));
		variable.type(Node.convertTypeFromDelphiToJava(ve.getType().toString()));
		if (variable.getName().equalsIgnoreCase("result"))
			variable.setName("_Result");
		methodBody.getVarSection().addVariable(variable);
		for (Object fragment : ve.fragments()) {
			VariableDeclarationFragment vf = ((VariableDeclarationFragment) fragment);
			if (vf.getInitializer() != null) {
				return variable.getName() + " := "
						+ Node.convertConstantJavaToDelphi(processExpression(vf.getInitializer()));
			}
		}
		return "";
	}

	protected String processStringLiteralExpression(Expression expression) {
		StringLiteral sl = (StringLiteral) expression;
		String result = Node.replaceAll(sl.getEscapedValue().toString(), String.valueOf('"'), "");
		result = result.replaceAll("(\\\\r\\\\n|\\\\n)", "'" + "+#13+" + "'");
		return "'" + result + "'";
	}

	protected String processPrefixExpression(Expression expression) {
		PrefixExpression pf = (PrefixExpression) expression;
		String result = "";
		if (pf.getOperator().equals(Operator.CONDITIONAL_AND))
			result = "and";
		else if (pf.getOperator().equals(Operator.EQUALS))
			result = "=";
		else if (pf.getOperator().equals(Operator.NOT_EQUALS))
			result = "<>";
		else if (pf.getOperator().equals(Operator.CONDITIONAL_OR))
			result = "or";
		else if (pf.getOperator().toString().equals("!"))
			result = "not";
		else
			result = pf.getOperator() + "";
		result = result + " " + processExpression(pf.getOperand());
		return result;
	}

	protected String processPostfixExpression(Expression expression) {
		PostfixExpression pe = (PostfixExpression) expression;
		if (pe.getOperator().toString().equals("++"))
			return processExpression(pe.getOperand()) + " := " + processExpression(pe.getOperand()) + " + 1";
		else if (pe.getOperator().toString().equals("--"))
			return processExpression(pe.getOperand()) + " := " + processExpression(pe.getOperand()) + " - 1";
		else
			return processArrayAccessExpression(expression);
	}

	protected String processNullLiteralExpression() {
		return "nil";
	}

	protected String processParenthesizedExpression(Expression expression) {
		ParenthesizedExpression pe = (ParenthesizedExpression) expression;
		return "(" + processExpression(pe.getExpression()) + ")";
	}

	protected String processNameExpression(Expression expression) {
		if (methodBody.existsParameter("A" + Node.capitalize(expression.toString())))
			return "A" + Node.capitalize(expression.toString());
		else if (methodBody.existsVariable("_" + Node.capitalize(expression.toString())))
			return "_" + Node.capitalize(expression.toString());
		else
			return processArrayAccessExpression(expression);
	}

	protected String processQualifiedNameExpression(Expression expression) {
		QualifiedName qn = (QualifiedName) expression;
		String leftName = Node.capitalize(processExpression(qn.getQualifier()));
		return leftName + "." + processExpression(qn.getName());
	}

	protected String processMethodInvocationExpression(Expression expression) {
		MethodInvocation mi = (MethodInvocation) expression;
		String mn = processExpression(mi.getName());
		if (mn.equalsIgnoreCase("equals"))
			mn = "=";
		String result = "";
		if (mi.getExpression() != null) {
			result = processExpression(mi.getExpression());
			if (!mn.equals("="))
				result = result + ".";
		}
		result = result + mn;
		if (mi.arguments().size() > 0)
			result = result + "(";
		boolean appendDelimiter = false;
		for (Object arg : mi.arguments()) {
			if (appendDelimiter)
				result = result + ",";
			if (arg instanceof Expression) {
				result = result + processExpression((Expression) arg);
			} else
				result = result + arg.toString();
			appendDelimiter = true;
		}
		if (mi.arguments().size() > 0)
			result = result + ")";

		return result;
	}

	protected String processInstanceofExpression(Expression expression) {
		org.eclipse.jdt.core.dom.InstanceofExpression io = (org.eclipse.jdt.core.dom.InstanceofExpression) expression;
		return processExpression(io.getLeftOperand()) + " is " + io.getRightOperand().toString();
	}

	protected String processConditionalExpression(Expression expression) {
		ConditionalExpression ce = (ConditionalExpression) expression;
		return "Iif(" + processExpression(ce.getExpression()) + "," + processExpression(ce.getThenExpression()) + ","
				+ processExpression(ce.getElseExpression()) + ")";
	}

	protected String processCastExpression(Expression expression) {
		CastExpression ce = (CastExpression) expression;
		return ConvertTypeToString(ce.getType()) + "(" + processExpression(ce.getExpression()) + ")";
	}

	protected String processArrayCreationExpression(Expression expression) {
		ArrayCreation ac = (ArrayCreation) expression;
		if (expression.getParent() instanceof Assignment) {
			return processExpression((Expression) ac.dimensions().get(0));
		} else if ((expression.getParent() instanceof MethodInvocation)
				|| (expression.getParent() instanceof ConstructorInvocation)) {
			if (ac.getInitializer() != null) {
				return "VarArrayOf([" + processExpression(ac.getInitializer()) + "])";
			} else {
				SimpleVariableDeclaration variable = new SimpleVariableDeclaration("emptyArray");
				variable.type(Node.convertTypeFromDelphiToJava(ConvertTypeToString(ac.getType())));
				methodBody.getVarSection().addVariable(variable);
				return "emptyArray";
			}
		} else
			return processArrayAccessExpression(expression);
	}

	protected String processArrayInitializerExpression(Expression expression) {
		ArrayInitializer ai = (ArrayInitializer) expression;
		boolean appendDelimiter = false;
		String result = "";
		for (Object exp : ai.expressions()) {
			if (appendDelimiter)
				result = result + ",";
			result = result + processExpression((Expression) exp);
			appendDelimiter = true;
		}
		return result;
	}

	protected String processAssignmentExpression(Expression expression) {
		Assignment ass = (Assignment) expression;
		if (ass.getOperator().toString().equals("=")) {
			if (ass.getRightHandSide() instanceof ArrayCreation) {
				return "SetLength(" + processExpression(ass.getLeftHandSide()) + ","
						+ Node.convertConstantJavaToDelphi(processExpression(ass.getRightHandSide())) + ")";
			} else
				return processExpression(ass.getLeftHandSide()) + " := "
						+ Node.convertConstantJavaToDelphi(processExpression(ass.getRightHandSide()));
		} else
			return processArrayAccessExpression(expression);
	}

	protected String processClassInstanceCreationExpression(Expression expression) {
		ClassInstanceCreation ci = (ClassInstanceCreation) expression;
		String result = "";
		if (ci.getExpression() != null)
			result = processExpression(ci.getExpression()) + ".";
		result = result + ci.getType() + ".Create";
		if (ci.arguments().size() > 0) {
			result = result + "(";
			boolean appendDelimiter = false;
			for (Object arg : ci.arguments()) {
				if (appendDelimiter)
					result = result + ",";
				if (arg instanceof Expression) {
					result = result + processExpression((Expression) arg);
				} else
					result = result + arg.toString();
				appendDelimiter = true;
			}
			result = result + ")";
		}
		return result;
	}

	protected String processInfixExpression(Expression expression) {
		InfixExpression ifx = (InfixExpression) expression;
		String result = processExpression(ifx.getLeftOperand());
		String operator = "";
		boolean appendParentheses = false;
		if (ifx.getOperator().equals(Operator.CONDITIONAL_AND)) {
			operator = "and";
			appendParentheses = true;
		} else if (ifx.getOperator().equals(Operator.EQUALS))
			operator = "=";
		else if (ifx.getOperator().equals(Operator.NOT_EQUALS))
			operator = "<>";
		else if (ifx.getOperator().equals(Operator.CONDITIONAL_OR)) {
			operator = "or";
			appendParentheses = true;
		} else if (ifx.getOperator().toString().equals("!")) {
			operator = "not";
			appendParentheses = true;
		} else
			operator = ifx.getOperator() + "";

		result = (appendParentheses ? "(" : "") + result + (appendParentheses ? ") " : " ") + operator
				+ (appendParentheses ? " (" : " ") + processExpression(ifx.getRightOperand())
				+ (appendParentheses ? ")" : "");
		if (ifx.hasExtendedOperands()) {
			for (Object eo : ifx.extendedOperands()) {
				result = result + " " + operator + (appendParentheses ? " (" : " ")
						+ processExpression((Expression) eo) + (appendParentheses ? ")" : "");
			}
		}
		return result;
	}

	private String ConvertTypeToString(Type type) {
		String result = "";
		if (type.isParameterizedType()) {
			ParameterizedType p = (ParameterizedType) type;
			result = Node.convertTypeFromDelphiToJava(p.getType().toString()) + "<";
			boolean appendDelimiter = false;
			for (Object arg : p.typeArguments()) {
				if (appendDelimiter)
					result = result + ",";
				result = result + ConvertTypeToString((Type) arg);
				appendDelimiter = true;
			}
			result = result + ">";
		} else
			result = Node.convertTypeFromDelphiToJava(type.toString());
		return result;
	}

	public void processVariable(VariableDeclarationStatement node, AbstractMethodBody method) {
		/*
		 * Declara a varíavel
		 */
		AbstractVariableDeclaration variable = null;
		if (node.getType().isParameterizedType()) {
			String varName = Node.capitalize(((VariableDeclarationFragment) node.fragments().get(0)).getName()
					.toString());
			if (varName.equalsIgnoreCase("result"))
				varName = "_Result";

			variable = new VarWithGenericsDeclaration(varName);
			boolean appendDelimiter = false;
			String typeName = Node.convertTypeFromDelphiToJava(((ParameterizedType) node.getType()).getType()
					.toString()) + "<";
			for (Object pt : ((ParameterizedType) node.getType()).typeArguments()) {
				if (appendDelimiter)
					typeName = typeName + ',';
				typeName = typeName + Node.convertTypeFromDelphiToJava(pt.toString());
				appendDelimiter = true;
			}
			typeName = typeName + ">";
			typeName = Node.convertTypeFromDelphiToJava(typeName);
			variable.type(typeName);
		} else {
			String varName = Node.capitalize(((VariableDeclarationFragment) node.fragments().get(0)).getName()
					.toString());
			if (varName.equalsIgnoreCase("result"))
				varName = "_Result";
			variable = new SimpleVariableDeclaration(varName);
			variable.type(Node.convertTypeFromDelphiToJava(node.getType().toString()));
		}
		method.getVarSection().addVariable(variable);
	}

	protected int countNumberOfMethodsByName(String name, MethodDeclaration[] methods) {
		int result = 0;
		for (MethodDeclaration method : methods) {
			if (method.getName().getFullyQualifiedName().equalsIgnoreCase(name)) {
				result++;
			}
		}
		return result;
	}

	protected boolean hasGetAndSetMethodForField(String fieldName, MethodDeclaration[] methods) {
		return (hasGetMethodForField(fieldName, methods) && hasSetMethodForField(fieldName, methods));
	}

	protected boolean hasGetMethodForField(String fieldName, MethodDeclaration[] methods) {
		for (MethodDeclaration method : methods) {
			if ((method.getName().getFullyQualifiedName().equalsIgnoreCase("get" + fieldName))
					|| (method.getName().getFullyQualifiedName().equalsIgnoreCase(fieldName)))
				return true;
		}
		return false;
	}

	protected boolean hasSetMethodForField(String fieldName, MethodDeclaration[] methods) {
		for (MethodDeclaration method : methods) {
			if ((method.getName().getFullyQualifiedName().equalsIgnoreCase("set" + fieldName))
					|| (method.getName().getFullyQualifiedName().equalsIgnoreCase(fieldName)))
				return true;
		}
		return false;
	}

	protected String getGetMethodForField(String fieldName, MethodDeclaration[] methods) {
		for (MethodDeclaration method : methods) {
			if ((method.getName().getFullyQualifiedName().equalsIgnoreCase("get" + fieldName)))
				return method.getName().getFullyQualifiedName();
		}
		return null;
	}

	protected String getSetMethodForField(String fieldName, MethodDeclaration[] methods) {
		for (MethodDeclaration method : methods) {
			if ((method.getName().getFullyQualifiedName().equalsIgnoreCase("set" + fieldName))
					|| (method.getName().getFullyQualifiedName().equalsIgnoreCase(fieldName)))
				return method.getName().getFullyQualifiedName();
		}
		return null;
	}

	public Unit getUnit() {
		return unit;
	}
}
