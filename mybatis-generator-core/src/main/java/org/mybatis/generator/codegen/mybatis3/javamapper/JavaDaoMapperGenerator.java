
package org.mybatis.generator.codegen.mybatis3.javamapper;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XmlDaoMapperGenrator;
import org.mybatis.generator.config.PropertyRegistry;

public class JavaDaoMapperGenerator extends AbstractJavaClientGenerator
{
	
	public JavaDaoMapperGenerator()
	{
		super(true);
	}
	public JavaDaoMapperGenerator(boolean requiresXMLGenerator)
	{
		super(requiresXMLGenerator);
	}
	
	@Override
	public AbstractXmlGenerator getMatchedXMLGenerator()
	{
		return new XmlDaoMapperGenrator();
	}
	
	@Override
	public List<CompilationUnit> getCompilationUnits()
	{
		progressCallback.startTask(getString("Progress.21", //$NON-NLS-1$
				introspectedTable.getFullyQualifiedTable().toString()));
		CommentGenerator commentGenerator = context.getCommentGenerator();
		
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());
		
		FullyQualifiedJavaType model = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		Interface interfaze = new Interface(type);
		interfaze.setVisibility(JavaVisibility.PUBLIC);
		commentGenerator.addJavaFileComment(interfaze);
		interfaze.addSuperInterface(new FullyQualifiedJavaType("BaseMapper<" + model.getShortName() + ">"));
		
		Set<FullyQualifiedJavaType> importedTypes = new HashSet<FullyQualifiedJavaType>();
		importedTypes.add(new FullyQualifiedJavaType(model.getFullyQualifiedName()));
		importedTypes.add(new FullyQualifiedJavaType("com.it.ecshop.dao.base.BaseMapper"));
		
		interfaze.addImportedTypes(importedTypes);
		String rootInterface = introspectedTable.getTableConfigurationProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
		if (!stringHasValue(rootInterface))
		{
			rootInterface = context.getJavaClientGeneratorConfiguration().getProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
		}
		
		if (stringHasValue(rootInterface))
		{
			FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(rootInterface);
			interfaze.addSuperInterface(fqjt);
			interfaze.addImportedType(fqjt);
		}
		
		List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
		if (context.getPlugins().clientGenerated(interfaze, null, introspectedTable))
		{
			answer.add(interfaze);
		}
		
		List<CompilationUnit> extraCompilationUnits = getExtraCompilationUnits();
		if (extraCompilationUnits != null)
		{
			answer.addAll(extraCompilationUnits);
		}
		
		return answer;
	}
	public List<CompilationUnit> getExtraCompilationUnits()
	{
		return null;
	}
	
}
