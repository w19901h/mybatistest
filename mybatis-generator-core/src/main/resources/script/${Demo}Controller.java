
package com.it.ecshop.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.ecshop.model.${Demo};
import com.it.ecshop.service.${Demo}Service;

import ch.qos.logback.classic.Logger;

@Controller
@RestController
public class ${Demo}Controller
{
	private static Logger	LOG		= (Logger) LoggerFactory.getLogger(${Demo}Controller.class);
	
	@Autowired 
	public ${Demo}Service		${demo}Service;
	
	private String modelName = "${demo}";
	
	/**
	 * get one by id
	 *
	 * @param id
	 *
	 * @return
	 */
	@GetMapping("/${demo}/get/{id}")
	public String get(@PathVariable("id") String id)
	{
		${Demo} ${demo}  = null;
		try
		{
			${demo} = ${demo}Service.getById(id);
		}
		catch (Exception e)
		{
			LOG.error(e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * add model
	 *
	 * @param demo
	 *
	 * @return
	 */
	@PostMapping("/${demo}/add")
	public String add(${Demo}${demo})
	{
		try
		{
			${demo} = ${demo}Service.save(${demo});
		}
		catch (Exception e)
		{
			LOG.error(e.getMessage());// TODO: handle exception
		}
		
		return null;
	}
	
	/**
	 * del by id
	 *
	 * @param id
	 *
	 * @return
	 */
	@DeleteMapping("/${demo}/del/{id}")
	public String delById(@PathVariable("id") String id)
	{
		try
		{
			if (${demo}Service.del(id))
				return null;
			else
				return null;
		}
		catch (Exception e)
		{
			LOG.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * update demo
	 *
	 * @param demo
	 *
	 * @return
	 */
	@PutMapping("/${demo}/update")
	public String updateById(${Demo}${demo})
	{
		try
		{
			${demo} = ${demo}Service.update(${demo});
		}
		catch (Exception e)
		{
			LOG.error(e.getMessage());
		}
		
		return null;
	}
	
	@PostMapping("/${demo}/list")
	public String getList(${Demo}${demo})
	{
		List<${Demo}> ${demo}list = null;
		try
		{
			${demo}.setName("测试");
			${demo}list = ${demo}Service.getList(${demo});
			
		}
		catch (Exception e)
		{
			LOG.error(e.getMessage());// TODO: handle exception
		}
		
		return  null;
	}
	
}
