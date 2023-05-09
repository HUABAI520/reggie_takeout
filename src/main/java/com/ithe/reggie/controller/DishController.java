package com.ithe.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ithe.reggie.common.R;
import com.ithe.reggie.dto.DishDto;
import com.ithe.reggie.entity.Category;
import com.ithe.reggie.entity.Dish;
import com.ithe.reggie.entity.Employee;
import com.ithe.reggie.service.CategoryService;
import com.ithe.reggie.service.DishFlavorService;
import com.ithe.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private DishFlavorService dishFlavorService;
    @Autowired
    private CategoryService categoryService;

    /*
    * 菜品信息的分页
    * */
    @GetMapping("/page")
    public R<Page> page (int page,int pageSize,String name){
        log.info("page={},pageSize={},name={}",page,pageSize,name);
        Page<Dish> pageInfo = new Page<>(page,pageSize);
        Page<DishDto> dishDtoPage = new Page<>();
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), Dish::getName, name);
        queryWrapper.orderByDesc(Dish::getUpdateTime);
        dishService.page(pageInfo,queryWrapper);
        //对象拷贝
        BeanUtils.copyProperties(pageInfo,dishDtoPage,"records");//将除了Dish数据全部传给dishdto
        List<Dish> records = pageInfo.getRecords();
        List<DishDto> list =records.stream().map((item)->{
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item,dishDto);//将dish也传递给dishdto
            Long categoryId = item.getCategoryId();
            //根据id查询分类对象
            Category category = categoryService.getById(categoryId);
            String categoryName = category.getName();
            dishDto.setCategoryName(categoryName);//通过查询将获得的分类名称传给dishdto
            return  dishDto;//返回dishdto
        }).collect(Collectors.toList());

        dishDtoPage.setRecords(list);//将返回值获得的数据添加到dishdto
        log.info(String.valueOf(dishDtoPage.getTotal()));
        return R.success(dishDtoPage);
    }
    /*
    新增菜品
    * */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        log.info("dish:{}",dishDto.toString());
        dishService.saveWithFlavor(dishDto);
        return R.success("cg");
    }
}
