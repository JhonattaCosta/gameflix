package com.gameflix.service;

import com.gameflix.dto.CategoryDTO;
import com.gameflix.mapper.CategoryMapper;
import com.gameflix.model.Category;
import com.gameflix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper categoryMapper;

    public CategoryDTO saveCategory(CategoryDTO categoryDTO){
        Category category = categoryMapper.map(categoryDTO);
        category = repository.save(category);
        return categoryMapper.map(category);
    }

    public List<CategoryDTO> findAllCategory(){
        List<Category> categories = repository.findAll();
        return categories.stream()
                .map(CategoryMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<CategoryDTO> findCategoryById(Long id){
        Optional<Category> category = repository.findById(id);
        return category.map(CategoryMapper::map);
    }

    public Optional<CategoryDTO> updateCategory(Long categoryId, CategoryDTO categoryDTO){
        Optional<Category> optCategory = repository.findById(categoryId);
        if (optCategory.isPresent()){
            Category category = optCategory.get();
            if(categoryDTO.getName() != null){
                category.setName(categoryDTO.getName());
            }
            Category categorySave = repository.save(category);
            return Optional.of(categoryMapper.map(categorySave));
        }
        return Optional.empty();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

}
