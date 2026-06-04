package com.kenichikamoi.taskapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kenichikamoi.taskapp.entity.Task;
import com.kenichikamoi.taskapp.repository.TaskRepository;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    public String showTaskList(Model model) {
        List<Task> taskList = taskRepository.findAll();
        model.addAttribute("tasks", taskList);
        return "tasks";
    }

    @PostMapping("/tasks/add")
    public String addTask(@RequestParam String title) {

        Task newTask = new Task();

        newTask.setTitle(title);

        taskRepository.save(newTask);

        return "redirect:/tasks";
    }

    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {

        taskRepository.deleteById(id);

        return "redirect:/tasks";
    }

    @GetMapping("/tasks/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        Task task = taskRepository.findById(id).orElse(new Task());

        model.addAttribute("task", task);

        return "edit";
    }

    @PostMapping("/tasks/update")
    public String updateTask(@RequestParam Long id, @RequestParam String title) {

        Task updateTask = new Task();

        updateTask.setId(id);
        updateTask.setTitle(title);

        taskRepository.save(updateTask);

        return "redirect:/tasks";
    }
}