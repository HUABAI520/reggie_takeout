package com.ithe.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ithe.reggie.dto.DishDto;
import com.ithe.reggie.entity.Category;
import com.ithe.reggie.entity.Dish;
import com.ithe.reggie.entity.DishFlavor;
import com.ithe.reggie.mapper.CategoryMapper;
import com.ithe.reggie.mapper.DishMapper;
import com.ithe.reggie.service.CategoryService;
import com.ithe.reggie.service.DishFlavorService;
import com.ithe.reggie.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;
    /*
    新增菜品，同时保存口味数据
    * */
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        //保存菜品的基本信息
        this.save(dishDto);
        Long dishid = dishDto.getId();
        //保存菜品口味数据
        List<DishFlavor> flavors = dishDto.getFlavors();
       flavors = flavors.stream().map((item) -> {
            item.setDishId(dishid);
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
    }
}
