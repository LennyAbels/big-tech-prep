package com.lenny.backend_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final Map<Long, Task> tasks = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // 1. CREATE (POST /api/tasks)
    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task request) {
        Long id = idGenerator.getAndIncrement();
        Task newTask = new Task(id, request.title(), request.completed());
        tasks.put(id, newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    // 2. READ ALL (GET /api/tasks)
    @GetMapping
    public List<Task> getAll() {
        return new ArrayList<>(tasks.values());
    }

    // 2.1 READ ONE (GET /api/tasks/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        Task task = tasks.get(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    // 3. UPDATE (PUT /api/tasks/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task request) {
        if (!tasks.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        Task updatedTask = new Task(id, request.title(), request.completed());
        tasks.put(id, updatedTask);
        return ResponseEntity.ok(updatedTask);
    }

    // 4. DELETE (DELETE /api/tasks/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (tasks.remove(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}