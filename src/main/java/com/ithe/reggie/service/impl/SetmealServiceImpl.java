package com.ithe.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ithe.reggie.entity.Dish;
import com.ithe.reggie.entity.Setmeal;
import com.ithe.reggie.mapper.DishMapper;
import com.ithe.reggie.mapper.SetmealMapper;
import com.ithe.reggie.service.DishService;
import com.ithe.reggie.service.SetmealService;
import org.springframework.stereotype.Service;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
}
