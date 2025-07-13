package com.gameflix.mapper;

import com.gameflix.dto.CategoryDTO;
import com.gameflix.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public  static CategoryDTO toCategoryDto(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        return categoryDTO;

    }

    public static Category toCategory(CategoryDTO categoryDTO){
        Category category = new Category();

        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());

        return category;
    }



}
