package com.example.todolist.category;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream().map(this::toDto)
                .collect(Collectors.toList());
    }


    private CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    public CategoryDto insert(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        categoryRepository.save(category);
        return toDto(category);
    }

    public void deleteById(Long id) {
        try {
            categoryRepository.deleteById(id);
        }catch (EmptyResultDataAccessException ignore) {
            //ignore
        }
    }
}
