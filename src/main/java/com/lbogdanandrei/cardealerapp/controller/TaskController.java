package com.lbogdanandrei.cardealerapp.controller;

import com.lbogdanandrei.cardealerapp.model.task.TaskMapper;
import com.lbogdanandrei.cardealerapp.model.task.TaskModel;
import com.lbogdanandrei.cardealerapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskModel>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTask(@Valid @RequestBody TaskModel task){
        return taskService.updateTask(task);
    }

    @PostMapping("/add")
    public ResponseEntity<TaskModel> addTask(@Valid @RequestBody TaskModel task){
        return ResponseEntity.ok(taskService.saveTask(task));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteTask(@Valid @RequestBody TaskModel task){
        if(taskService.deleteTask(task.getId())) {
            return ResponseEntity.ok(task);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
