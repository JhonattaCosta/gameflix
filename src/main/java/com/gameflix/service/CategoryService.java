package com.gameflix.service;

import com.gameflix.dto.CategoryDTO;
import com.gameflix.mapper.CategoryMapper;
import com.gameflix.model.Category;
import com.gameflix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Category> findAllCategory(){
        return repository.findAll();
    }

    public Optional<Category> findCategoryById(Long id){
        return repository.findById(id);
    }

    public Optional<Category> updateCategory(Long categoryId, Category updateCategory){
        Optional<Category> optCategory = repository.findById(categoryId);
        if (optCategory.isPresent()){
            Category category = optCategory.get();
            category.setName(updateCategory.getName());

            repository.save(category);
            return Optional.of(category);
        }

        return Optional.empty();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

}
