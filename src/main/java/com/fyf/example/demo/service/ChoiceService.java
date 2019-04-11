package com.fyf.example.demo.service;

import com.fyf.example.demo.pojo.Choice;

public interface ChoiceService {

	/**
	 * 保存数据
	 * @param choice
	 * @return
	 */
	int save(Choice choice);
	
	/**
	 * 修改数据
	 */
	void updete(Choice choice);
}
