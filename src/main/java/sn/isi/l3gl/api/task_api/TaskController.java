package sn.isi.l3gl.api.task_api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.isi.l3gl.core.task_core.Task;
import sn.isi.l3gl.core.task_core.TaskService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // POST /api/tasks → créer une tâche
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Map<String, String> body) {
        Task task = taskService.createTask(body.get("title"), body.get("description"));
        return ResponseEntity.ok(task);
    }

    // GET /api/tasks → lister toutes les tâches
    @GetMapping
    public ResponseEntity<List<Task>> listTasks() {
        return ResponseEntity.ok(taskService.listTasks());
    }

    // PUT /api/tasks/{id}/status → modifier le statut
    @PutMapping("/{id}/status")
    public ResponseEntity<Task> updateStatus(@PathVariable Long id,
                                              @RequestBody Map<String, String> body) {
        Task.Status status = Task.Status.valueOf(body.get("status"));
        Task task = taskService.updateStatus(id, status);
        return ResponseEntity.ok(task);
    }

    // GET /api/tasks/done/count → nombre de tâches DONE
    @GetMapping("/done/count")
    public ResponseEntity<Long> countCompletedTasks() {
        return ResponseEntity.ok(taskService.countCompletedTasks());
    }
}