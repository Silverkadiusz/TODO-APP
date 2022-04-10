package com.example.todolist.category;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/categories")
@RestController
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public List<CategoryDto> findAll() {
        return categoryService.findAll();

    }

    @PostMapping("")
    public CategoryDto insert(@RequestBody CategoryDto dto) {
        return categoryService.insert(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

}



