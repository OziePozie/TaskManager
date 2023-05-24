package com.example.controllers;

import com.example.TaskStatus;
import com.example.dto.TaskDto;
import com.example.models.Person;
import com.example.models.Task;
import com.example.service.TaskService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final UserService personService;

    public TaskController(@Autowired TaskService taskService, @Autowired UserService personService) {
        this.taskService = taskService;
        this.personService = personService;
    }

    @GetMapping("/tasks")
    public String getTasks(Model model, Principal principal) {
        String name = principal.getName();
        Person person = personService.getUserByUsername(name);
        List<Task> tasks = taskService.getAllTasksByUser(person);

        model.addAttribute("tasks",tasks);
        return "tasks";
    }

    @PostMapping("/tasks")
    public String addTask(@ModelAttribute("task") TaskDto taskDto, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "tasks";
        }
        String name = principal.getName();
        Person person = personService.getUserByUsername(name);
        taskService.saveTask(new Task(taskDto.getTitle(), taskDto.getDesc(), TaskStatus.IN_PROGRESS, person));
        return "redirect:/tasks";
    }
    @PostMapping("/tasks/update-status")
    public String updateTaskStatus(@RequestParam("taskId") Long taskId, @RequestParam("status") TaskStatus status) {
        Task task = taskService.getTaskById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task Id: " + taskId));

        task.setStatus(status);
        taskService.saveTask(task);

        return "redirect:/tasks";
    }
}
