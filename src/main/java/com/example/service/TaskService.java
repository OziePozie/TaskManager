package com.example.service;

import com.example.models.Person;
import com.example.models.Task;
import com.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(@Autowired TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasksByUser(Person person) {
        return person.getTasks();
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public boolean saveTask(Task task) {
        taskRepository.save(task);
        return true;
    }
    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById(id);
    }

    public boolean deleteTaskById(Long id) {
        taskRepository.deleteById(id);
        return true;
    }

}
