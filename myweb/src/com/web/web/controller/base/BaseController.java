package com.web.web.controller.base;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.web.service.base.BaseService;

@Controller
public abstract class BaseController<T> extends JSONOutputController{
	Logger log = Logger.getLogger("BaseController");

	public BaseController()
	{
		setBaseService();
	}

	/***常用 输入异常 返回*/
	public static String INPUT = "input";
	/***常用 成功 返回*/
	public static String SUCCESS = "success";
	/***常用 错误 返回*/
	public static String ERROR = "error";
	/***用户登录信息 session*/
	public static final String userSession_key = "IMAGECAPCHA";
	public static final String sysuserSession_key = "SYSERIMAGECAPCHA";
	protected BaseService<T> baseService;
	@PostConstruct
	protected abstract void setBaseService();

	/***************************************************************************************************
	/***************************************************************************************************
	/***************************************************************************************************
	/***************************************************************************************************
	/****************************只提供如下接口,其他接口自己实现             *************************************
	/***************************************************************************************************
	/***************************************************************************************************
	/***************************************************************************************************
	/***************************************************************************************************
	/***************************************************************************************************/


	@ResponseBody
	@RequestMapping(value="/list.do",produces="text/plain;charset=UTF-8")
	public String getList(){
		return json(baseService.getList());
	}

	@ResponseBody
	@RequestMapping(value="/get.do",produces="text/plain;charset=UTF-8")
	public String getList(Integer id) {
		return json(baseService.get(id));
	}
}
