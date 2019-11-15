
package org.mybatis.generator.codegen.mybatis3.controller;

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
import org.mybatis.generator.config.JavaControllerGeneratorConfiguration;
import org.mybatis.generator.config.JavaServiceGeneratorConfiguration;

public class RestControllerGenerator extends AbstractJavaGenerator
{
	
	@Override
	public List<CompilationUnit> getCompilationUnits()
	{
		JavaServiceGeneratorConfiguration javaServiceGeneratorConfiguration = context.getJavaServiceGeneratorConfiguration();
		JavaControllerGeneratorConfiguration javaControllerGeneratorConfiguration = context.getJavaControllerGeneratorConfiguration();
		
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		progressCallback.startTask(getString("Progress.19", table.toString())); //$NON-NLS-1$
		// Plugin plugins = context.getPlugins();
		// CommentGenerator commentGenerator = context.getCommentGenerator();
		
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		String model = type.getShortName();
		String firsrtName = Character.isUpperCase(model.charAt(0)) ? Character.toLowerCase(model.charAt(0)) + "" + model.subSequence(1, model.length()) : model;
		TopLevelClass topLevelClass = new TopLevelClass(new FullyQualifiedJavaType(javaControllerGeneratorConfiguration.getTargetPackage() + "." + model + "Controller"));
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		topLevelClass.addAnnotation("@Controller");
		topLevelClass.addAnnotation("@RestController");
		
		String serviceClassName = javaServiceGeneratorConfiguration.getTargetPackage() + "." + model + "Service";
		Field serviceField = new Field(firsrtName + "Service", new FullyQualifiedJavaType(serviceClassName));
		serviceField.setVisibility(JavaVisibility.PUBLIC);
		serviceField.addAnnotation("@Autowired");
		topLevelClass.addField(serviceField);
		
		Set<FullyQualifiedJavaType> importedTypes = new HashSet<FullyQualifiedJavaType>();
		importedTypes.add(new FullyQualifiedJavaType("org.springframework.stereotype.Controller"));
		importedTypes.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RestController"));
		importedTypes.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.GetMapping"));
		importedTypes.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.PostMapping"));
		importedTypes.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.DeleteMapping"));
		importedTypes.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.PostMapping"));
		importedTypes.add(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
		importedTypes.add(new FullyQualifiedJavaType(type.getFullyQualifiedName()));
		importedTypes.add(new FullyQualifiedJavaType(serviceClassName));
		topLevelClass.addImportedTypes(importedTypes);
		
		Method getMethod = new Method("get" + model);
		getMethod.addAnnotation("@GetMapping(\"/" + firsrtName + "/get\")");
		getMethod.setVisibility(JavaVisibility.PUBLIC);
		getMethod.setReturnType(FullyQualifiedJavaType.getStringInstance());
		getMethod.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "id"));
		getMethod.addException(new FullyQualifiedJavaType("java.lang.Exception"));
		topLevelClass.addMethod(getMethod);
		
		Method getListMethod = new Method("get" + model + "List");
		getListMethod.addAnnotation("@GetMapping(\"/" + firsrtName + "/get/list\")");
		getListMethod.setVisibility(JavaVisibility.PUBLIC);
		getListMethod.setReturnType(FullyQualifiedJavaType.getStringInstance());
		getListMethod.addException(new FullyQualifiedJavaType("java.lang.Exception"));
		getListMethod = addMethodBody(getListMethod);
		
		topLevelClass.addMethod(getListMethod);
		
		Method postMethod = new Method("add" + model);
		postMethod.addAnnotation("@PostMapping(\"/" + firsrtName + "/add\")");
		postMethod.setVisibility(JavaVisibility.PUBLIC);
		postMethod.setReturnType(FullyQualifiedJavaType.getStringInstance());
		postMethod.addParameter(new Parameter(new FullyQualifiedJavaType(type.getFullyQualifiedName()), firsrtName));
		postMethod.addException(new FullyQualifiedJavaType("java.lang.Exception"));
		postMethod = addMethodBody(postMethod);
		topLevelClass.addMethod(postMethod);
		
		Method delMethod = new Method("del" + model);
		delMethod.addAnnotation("@DeleteMapping(\"/" + firsrtName + "/del\")");
		delMethod.setVisibility(JavaVisibility.PUBLIC);
		delMethod.setReturnType(FullyQualifiedJavaType.getStringInstance());
		delMethod.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "id"));
		delMethod.addException(new FullyQualifiedJavaType("java.lang.Exception"));
		delMethod = addMethodBody(delMethod);
		topLevelClass.addMethod(delMethod);
		
		Method updateMethod = new Method("update" + model);
		updateMethod.addAnnotation("@DeleteMapping(\"/" + firsrtName + "/update\")");
		updateMethod.setVisibility(JavaVisibility.PUBLIC);
		updateMethod.setReturnType(FullyQualifiedJavaType.getStringInstance());
		updateMethod.addParameter(new Parameter(new FullyQualifiedJavaType(type.getFullyQualifiedName()), firsrtName));
		updateMethod.addException(new FullyQualifiedJavaType("java.lang.Exception"));
		updateMethod = addMethodBody(updateMethod);
		topLevelClass.addMethod(delMethod);
		
		List<CompilationUnit> compilationUnits = new ArrayList<CompilationUnit>();
		compilationUnits.add(topLevelClass);
		return compilationUnits;
	}
	
//	private String copyFile(String fileName)
//	{
//		
//		// 获取配置文件
//		URL controllerDemoUrl = GeneratorMain.class.getClassLoader().getResource("script/${Demo}Controller.java");
//		
//		String controllerDemoFilePath = controllerDemoUrl.getPath();
//		
//		File controllerDemoFile = new File(controllerDemoFilePath);
//		try
//		{
//			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(new FileInputStream(controllerDemoFile)));
//			StringBuffer sb = new StringBuffer();
//			String context = "";
//			while ((context = bufferReader.readLine()) != null)
//			{
//				sb.append(context);
//			}
//			String UppName = fileName.substring(0, 1).toUpperCase() + fileName.substring(1);
//			bufferReader.close();
//			return sb.toString().replaceAll("${Demo}", UppName).replaceAll("${demo}", fileName);
//		}
//		catch (FileNotFoundException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		catch (IOException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//		
//	}
	
	// public void testFile(String[] args)
	// {
	// copyFile("");
	// }
	public Method addMethodBody(Method method)
	{
		method.addBodyLine("try");
		method.addBodyLine("{");
		method.addBodyLine("/**");
		method.addBodyLine("* java code ");
		method.addBodyLine("*/");
		method.addBodyLine("} ");
		method.addBodyLine("catch (Exception e)");
		method.addBodyLine("{");
		method.addBodyLine("e.printStackTrace();");
		method.addBodyLine("return null ; ");
		method.addBodyLine("}");
		method.addBodyLine("return null ; ");
		return method;
	}
	
}
