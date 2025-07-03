package com.example.blog_app_apis.services;

import com.example.blog_app_apis.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {


    //Create
    public CategoryDto createCategory(CategoryDto categoryDto);



    //Update
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    //delete
    public void deleteCategory( Integer categoryId);



    //get
    public CategoryDto getCategory(  Integer categoryId);



    //get All
    public List<CategoryDto> getCategories();
}
