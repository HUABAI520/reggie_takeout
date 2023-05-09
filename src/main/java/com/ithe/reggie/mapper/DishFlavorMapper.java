package com.ithe.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ithe.reggie.entity.Dish;
import com.ithe.reggie.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {
}
