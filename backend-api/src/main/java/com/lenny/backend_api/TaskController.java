package com.lenny.backend_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    // Spring inyecta automáticamente el repositorio aquí
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 1. CREATE (POST /api/tasks)
    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task request) {
        Task newTask = new Task(request.getTitle(), request.isCompleted());
        Task savedTask = taskRepository.save(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    // 2. READ ALL (GET /api/tasks)
    @GetMapping
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    // 2.1 READ ONE (GET /api/tasks/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. UPDATE (PUT /api/tasks/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task request) {
        return taskRepository.findById(id)
                .map(existingTask -> {
                    existingTask.setTitle(request.getTitle());
                    existingTask.setCompleted(request.isCompleted());
                    return ResponseEntity.ok(taskRepository.save(existingTask));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. DELETE (DELETE /api/tasks/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!taskRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}