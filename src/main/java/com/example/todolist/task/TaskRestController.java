package com.example.todolist.task;

import com.example.todolist.category.CategoryRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/tasks")
@RestController
public class TaskRestController {

    private final TaskRepository taskRepository;
    private final TaskService taskService;
    private final CategoryRepository categoryRepository;

    public TaskRestController(TaskRepository taskRepository, TaskService taskService, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.taskService = taskService;
        this.categoryRepository = categoryRepository;
    }

    //1.Wyswietl liste
    @GetMapping("")
    public List<TaskDto> findFiltered(@RequestParam(required = true) Long categoryId) {

        return taskService.findFiltered(categoryId);

    }


    //2.Wyswietl id

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findById(@PathVariable Long id) {
        Optional<TaskDto> taskOptional = taskService.findById(id);
        return taskOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //3.Dodaj zadanie
    @PostMapping("")
    public ResponseEntity<TaskDto> addTask(@RequestBody TaskInsertDto dto) {
        TaskDto taskDto = taskService.insert(dto);
        return ResponseEntity.ok(taskDto);

    }

    //4.Update

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> addTaskDto(@PathVariable Long id, @RequestBody TaskInsertDto dto) {
        return taskService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        try {
            taskRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ignore) {
            // ignore
        }
    }


}