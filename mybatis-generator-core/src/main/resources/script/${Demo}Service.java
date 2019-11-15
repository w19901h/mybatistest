
package com.it.ecshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.it.ecshop.dao.${Demo}Mapper;
import com.it.ecshop.model.${Demo};

@Service
public class ${Demo}Service extends BaseServiceImpl<${Demo}>
{
	
	@Autowired
	private ${Demo}Mapper ${demo}Mapper;
	
	@Transactional(rollbackFor = Exception.class)
	public void biz(${Demo} ${demo}) throws Exception
	{
		/**
		 * 
		 * 
		 * java code 
		 */
	};
	
	
}
