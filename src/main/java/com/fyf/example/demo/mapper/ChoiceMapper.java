package com.fyf.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.fyf.example.demo.pojo.Choice;

@Mapper
public interface ChoiceMapper {

	/**
	 * 查询全部用户
	 * @return
	 */
	@Select("select * from tb_choice")
	List<Choice> getAllChoice();
	
	/**
	 * 保存数据
	 * @return
	 */
	@InsertProvider(type=com.fyf.example.demo.mapper.sql.ChoiceSql.class,method="insertChoiceSql")
	int insertChoice(Choice choice);
	
	/**
	 * 修改数据
	 */
	@UpdateProvider(type=com.fyf.example.demo.mapper.sql.ChoiceSql.class,method="updateChoiceSql")
	void updateChoice(Choice choice);
	
	/**
	 * 删除数据
	 */
	@DeleteProvider(type=com.fyf.example.demo.mapper.sql.ChoiceSql.class,method="deleteChoiceSql")
	void deleteChoice(Choice choice);
}
