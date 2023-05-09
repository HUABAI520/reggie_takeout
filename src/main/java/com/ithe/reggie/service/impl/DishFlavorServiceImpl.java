package com.ithe.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ithe.reggie.entity.Dish;
import com.ithe.reggie.entity.DishFlavor;
import com.ithe.reggie.mapper.DishFlavorMapper;
import com.ithe.reggie.mapper.DishMapper;
import com.ithe.reggie.service.DishFlavorService;
import com.ithe.reggie.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
