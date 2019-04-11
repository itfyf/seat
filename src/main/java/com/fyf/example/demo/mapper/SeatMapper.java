package com.fyf.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.junit.Test;

import com.fyf.example.demo.pojo.Seat;


@Mapper
public interface SeatMapper {

	@Select("select * from tb_type where plane_type=#{planeType}")
	Seat select(String planeType);
	
	@Insert("insert into tb_type(plane_type,seat_window,seat_aisle,seat_gate,seat_random,seat_num) value(#{plane_type},#{seat_window},#{seat_aisle},#{seat_gate},#{seat_random},#{seat_num})")
	int save(@Param("plane_type")String plane_type,@Param("seat_window")String seat_window,@Param("seat_aisle")String seat_aisle,@Param("seat_gate")String seat_gate,@Param("seat_random")String seat_random,@Param("seat_num")Integer seat_num);
}
