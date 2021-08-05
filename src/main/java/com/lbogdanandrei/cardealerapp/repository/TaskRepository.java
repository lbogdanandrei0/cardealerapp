package com.lbogdanandrei.cardealerapp.repository;

import com.lbogdanandrei.cardealerapp.model.task.TaskModel;
import com.lbogdanandrei.cardealerapp.model.task.TaskPriority;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long> {

    Optional<TaskModel> findTaskById(long id);

    List<TaskModel> findTaskByCreatedBy(String createdBy);

    List<TaskModel> findTaskByPriority(String priority);

    @Transactional
    @Modifying
    @Query("update task set title = :title, description = :description, priority = :priority, created_by = :createdBy where id = :id")
    Integer updateTaskById(@Param("id") long id,
                             @Param("title") String title,
                             @Param("description") String description,
                             @Param("priority") TaskPriority priority,
                             @Param("createdBy") String createdBy);

}
