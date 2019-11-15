
package org.mybatis.generator.codegen.mybatis3.service;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.config.JavaServiceGeneratorConfiguration;

public class ServiceGenerator extends AbstractJavaGenerator
{
	
	@Override
	public List<CompilationUnit> getCompilationUnits()
	{
		JavaServiceGeneratorConfiguration javaServiceGeneratorConfiguration = context.getJavaServiceGeneratorConfiguration();
		
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		progressCallback.startTask(getString("Progress.19", table.toString())); //$NON-NLS-1$
		// Plugin plugins = context.getPlugins();
		// CommentGenerator commentGenerator = context.getCommentGenerator();
		
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		String model = type.getShortName();
		String firsrtName = Character.isUpperCase(model.charAt(0)) ? Character.toLowerCase(model.charAt(0)) + "" + model.subSequence(1, model.length()) : model;
		TopLevelClass topLevelClass = new TopLevelClass(new FullyQualifiedJavaType(javaServiceGeneratorConfiguration.getTargetPackage() + "." + model + "Service"));
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		topLevelClass.addAnnotation("@Service");
		
		String mapperQualifiedName = javaServiceGeneratorConfiguration.getTargetPackage() + "." + model + "Mapper";
		Field serviceField = new Field(firsrtName + "Mapper", new FullyQualifiedJavaType(mapperQualifiedName));
		serviceField.setVisibility(JavaVisibility.PRIVATE);
		serviceField.addAnnotation("@Autowired");
		topLevelClass.addField(serviceField);
		
		topLevelClass.setSuperClass(new FullyQualifiedJavaType("com.it.ecshop.service.BaseServiceImpl<"+type+">"));
		
		Set<FullyQualifiedJavaType> importedTypes = new HashSet<FullyQualifiedJavaType>();
		importedTypes.add(new FullyQualifiedJavaType("org.springframework.stereotype.Service"));
		importedTypes.add(new FullyQualifiedJavaType(mapperQualifiedName));
		importedTypes.add(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
		importedTypes.add(new FullyQualifiedJavaType("org.springframework.transaction.annotation.Transactional"));
		importedTypes.add(new FullyQualifiedJavaType(javaServiceGeneratorConfiguration.getTargetPackage()+".BaseServiceImpl" ));
		importedTypes.add(new FullyQualifiedJavaType(type.getFullyQualifiedName()));
		topLevelClass.addImportedTypes(importedTypes);
		
		Method method = new Method("saveTramsaction" + model);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addParameter(new Parameter(new FullyQualifiedJavaType(type.getFullyQualifiedName()), firsrtName));
		method.addException(new FullyQualifiedJavaType("java.lang.Exception"));
		method.addBodyLine("/**");
		method.addBodyLine("* java code ");
		method.addBodyLine("*/");
		topLevelClass.addMethod(method);
		
		List<CompilationUnit> compilationUnits = new ArrayList<CompilationUnit>();
		compilationUnits.add(topLevelClass);
		return compilationUnits;
	}
	
}
