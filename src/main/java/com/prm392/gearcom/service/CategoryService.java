package com.prm392.gearcom.service;

import com.prm392.gearcom.model.Category;
import com.prm392.gearcom.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository _categoryRepository) {
        this.categoryRepository = _categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
