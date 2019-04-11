package com.fyf.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fyf.example.demo.mapper.ChoiceMapper;
import com.fyf.example.demo.pojo.Choice;
import com.fyf.example.demo.service.ChoiceService;


@Service
public class ChoiceServiceImpl implements ChoiceService{

	@Autowired
	private ChoiceMapper choiceMapper;
	
	@Override
	public int save(Choice choice) {
		int insertChoice = choiceMapper.insertChoice(choice);
		return insertChoice;
	}

	@Override
	public void updete(Choice choice) {
		
		
	}

}
