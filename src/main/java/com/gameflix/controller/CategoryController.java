package com.gameflix.controller;

import com.gameflix.dto.CategoryDTO;
import com.gameflix.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("gamesflix/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public ResponseEntity<String> saveCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO newCategory = service.saveCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Category created successfully !");
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> categories = service.findAllCategory();
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<CategoryDTO> categoryDTO = service.findCategoryById(id);
        return ResponseEntity.ok(categoryDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        Optional<CategoryDTO> optCategoryDTO = service.findCategoryById(id);
        if(optCategoryDTO != null){
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(service.updateCategory(id,categoryDTO));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Category with id: " + id + " not found !");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        Optional<CategoryDTO> optCategoryDTO = service.findCategoryById(id);
        if (optCategoryDTO.isPresent()){
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body("Category with id: " + id + " deleted successfully !");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Category with id: " + id + " not found !");
        }
    }


}
