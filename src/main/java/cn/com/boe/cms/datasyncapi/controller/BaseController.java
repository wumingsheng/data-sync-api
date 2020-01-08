package cn.com.boe.cms.datasyncapi.controller;


import cn.com.boe.cms.datasyncapi.common.entity.ResponseEnum;
import cn.com.boe.cms.datasyncapi.common.entity.ResponseResult;

public class BaseController {
	
	
	
	public Object ok(Object data) {
		
		return ResponseResult.success(ResponseEnum.SUCCESS, data);
		
	}

}
