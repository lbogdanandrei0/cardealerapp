package com.lbogdanandrei.cardealerapp.service;

import com.lbogdanandrei.cardealerapp.model.task.TaskModel;
import com.lbogdanandrei.cardealerapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    EntityManager entityManager;

    public List<TaskModel> getAllTasks(){
        return taskRepository.findAll();
    }

    public boolean deleteTask(long id){
        try {
            taskRepository.deleteById(id);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public TaskModel saveTask(TaskModel task){
        return taskRepository.save(task);
    }

    public ResponseEntity<?> updateTask(TaskModel task){
        if(taskRepository.updateTaskById(task.getId(), task.getTitle(), task.getDescription(), task.getPriority(), task.getCreatedBy()) != 0)
            return ResponseEntity.ok(task);
        //else throw custom exception
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
