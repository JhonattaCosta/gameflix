package com.gameflix.service;

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

    public Category saveCategory(Category category){
        return repository.save(category);
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
