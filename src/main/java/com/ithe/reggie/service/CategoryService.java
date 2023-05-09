package com.ithe.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ithe.reggie.entity.Category;
import org.apache.ibatis.annotations.Mapper;


public interface CategoryService extends IService<Category> {
    public void remove(Long ids);
}
