package com.ithe.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ithe.reggie.entity.Category;
import com.ithe.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
