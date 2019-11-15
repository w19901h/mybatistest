
package org.mybatis.generator.codegen.mybatis3.xmlmapper;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.XmlConstants;

public class XmlDaoMapperGenrator extends AbstractXmlGenerator
{
	
	public XmlDaoMapperGenrator()
	{
		
	};
	public XmlDaoMapperGenrator(IntrospectedTable introspectedTable)
	{
		this.introspectedTable = introspectedTable;
	}
	
	@Override
	public Document getDocument()
	{
		Document document = new Document(XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID, XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);
		document.setRootElement(getSqlMapElement());
		
		// if (!this.getContext().getPlugins().sqlMapDocumentGenerated(document,
		// introspectedTable))
		// {
		// document = null;
		// }
		return document;
	}
	protected XmlElement getSqlMapElement()
	{
		XmlElement answer = new XmlElement("mapper");
		String namespace = introspectedTable.getMyBatis3SqlMapNamespace();
		answer.addAttribute(new Attribute("namespace", namespace));
		introspectedTable.getContext().getCommentGenerator().addRootComment(answer);
		return answer;
	}
}
