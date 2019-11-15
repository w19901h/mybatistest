// package org.mybatis.generator.run.main;
//
// import java.io.File;
// import java.net.URL;
// import java.util.ArrayList;
// import java.util.List;
//
// import org.mybatis.generator.config.Configuration;
// import org.mybatis.generator.config.xml.ConfigurationParser;
//
// public class GeneratorMain1
// {
//
// private static String CONFIG_FILE_NAME = "generatorConfig.xml";
//
//
// public static void main(String[] args) throws Exception
// {
//
// //获取配置文件
// URL configFileUrl =
// GeneratorMain.class.getClassLoader().getResource(CONFIG_FILE_NAME);
// if(configFileUrl==null)
// {
// throw new Exception("configFilePath:generatorConfig.xml not find in
// Resource");
// }
// String configFilePath = configFileUrl.getPath();
//
// File configFile = new File(configFilePath);
// if(!configFile.exists())
// {
// throw new Exception("configFilePath:generatorConfig.xml not find in
// Resource");
// }
//
// try
// {
// List<String> warnings = new ArrayList<String>();
// ConfigurationParser cp = new ConfigurationParser(warnings);
// Configuration config = cp.parseConfiguration(configFile);
// config.toString();
// }
// catch (Exception e)
// {
//
// }
//
//
// }
// }
