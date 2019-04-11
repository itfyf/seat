package com.fyf.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fyf.example.demo.common.JSONUtil;
import com.fyf.example.demo.common.JsonResponseData;
import com.fyf.example.demo.pojo.Type;
import com.fyf.example.demo.service.SeatService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("TbTypeController相关的api")
public class SeatController {

	@Autowired
	private SeatService seatService;
	
	
	@RequestMapping("/svaeRedis/{planeType}")
	@ApiOperation(value = "根据planeType飞机型号查询信息", notes = "查询数据库中某个的飞机型号的座位信息")
    @ApiImplicitParam(name = "planeType", value = "飞机型号", paramType = "path", required = true, dataType = "String")
	public JsonResponseData svaeRedis(@PathVariable("planeType")String planeType){
		JsonResponseData select = seatService.select(planeType);
		return select;
	}
	
	
	/**
	 * 返回前端需要的格式
	 * @param planeType  飞机的机型
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getData/{planeType}")
	public Type getData(@PathVariable("planeType")String planeType) throws Exception{
		return seatService.getData(planeType);
	}
	

}
