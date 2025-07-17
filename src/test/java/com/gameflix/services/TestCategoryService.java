package com.gameflix.services;

import com.gameflix.model.Category;
import com.gameflix.repository.CategoryRepository;
import com.gameflix.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TestCategoryService {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryService service;

    @Test
    public void testSaveCategory(){
        Category category = new Category(1L, "Ação");

        when(repository.save(category)).thenReturn(category);

        Category categorySave = service.saveCategory(category);

        assertEquals(category, categorySave);
    }

    @Test
    public void testListCategory(){
        List<Category> categories = Arrays.asList(
                new Category(1L, "Ação"),
                new Category(2L, "Terceira Pessoa")
        );

        when(repository.findAll()).thenReturn(categories);

        List<Category> categoryList = service.findAllCategory();

        assertEquals(categoryList, categories);


    }

    @Test
    public void testListCategoryById(){

        Optional<Category> categories = Optional.of(
                new Category(1L, "Ação")
        );

        when(repository.findById(1L)).thenReturn(categories);

        Optional<Category> findIdCategory = service.findCategoryById(1L);

        assertTrue(findIdCategory.isPresent());
        assertEquals(findIdCategory.get(),categories.get());

    }

    @Test
    public void testUpdateCategory(){
        Category categoryFake = new Category(1L, "acao");
        Category categoryUpdate = new Category(1L, "Ação");

        when(repository.findById(1L)).thenReturn(Optional.of(categoryFake));
        when(repository.save(categoryFake)).thenReturn(categoryFake);

        Optional<Category> result = service.updateCategory(1L, categoryUpdate);

        assertTrue(result.isPresent());
        assertEquals("Ação", result.get().getName());


    }

    @Test
    public void testDeleteCategory(){
        Category categoryFake = new Category(1L, "Ação");
        service.deleteById(1L);
        verify(repository).deleteById(1L);
    }
}
