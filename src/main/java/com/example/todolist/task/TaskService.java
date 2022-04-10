package com.example.todolist.task;


import com.example.todolist.category.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    public TaskService(TaskRepository taskRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<TaskDto> findFiltered(Long categoryId) {
        List<Task> tasks;
        if(categoryId == null) {
            tasks = taskRepository.findAll();
        } else {
            tasks = taskRepository.findByCategoryId(categoryId);
        }
        return tasks
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<TaskDto> findAll() {
        List<Task> tasks = taskRepository.findAll();

        return tasks
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private TaskDto toDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setCategoryName(task.getCategory().getName());
        return dto;
    }

    public Optional<TaskDto> findById(Long id) {
        return taskRepository.findById(id).map(this::toDto);
    }

    public TaskDto insert(TaskInsertDto dto) {
        Task task = new Task();
        setEntityFields(task, dto);
        taskRepository.save(task);
        return toDto(task);
    }


    public Optional<TaskDto> update(Long id, TaskInsertDto dto) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task taskInDb = taskOptional.get();
            setEntityFields(taskInDb, dto);
            taskRepository.save(taskInDb);
            return Optional.of(toDto(taskInDb));
        } else {
            return Optional.empty();
        }
    }

    private void setEntityFields(Task entity, TaskInsertDto dto) {
        entity.setName(dto.getName());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setCategory(categoryRepository.findById(dto.getCategoryId()).orElse(null));


    }
}