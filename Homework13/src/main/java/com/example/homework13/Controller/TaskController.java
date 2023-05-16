package com.example.homework13.Controller;


import com.example.homework13.ApiResponse.ApiResponse;
import com.example.homework13.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/task")
public class TaskController {

    ArrayList<Task> tasks = new ArrayList<>();

    //read
    @GetMapping("/get")
    public ArrayList<Task> getTask() {
        return tasks;
    }
    //create

    @PostMapping("/add")
    public ApiResponse addTask(@RequestBody Task task) {
        tasks.add(task);
        return new ApiResponse("done add");
    }

    // update
    @PutMapping("/update/{index}")
    public ApiResponse upadteTask(@PathVariable int index, @RequestBody Task task) {
        tasks.set(index, task);
        return new ApiResponse("done Update");
    }

    //delet
    @DeleteMapping("/delet/{index}")
    public ApiResponse deletTask(@PathVariable int index) {
        tasks.remove(index);
        return new ApiResponse("done delet");
    }

    @PutMapping("/changeStatus/{index}")
    public ApiResponse changeStatus(@PathVariable int index) {
        if (tasks.get(index).getStatus().equals("not done")) {
            tasks.get(index).setStatus("done");
            return new ApiResponse("done change status");
        }
        return new ApiResponse("already done");

    }

    @GetMapping("/search/{title}")
    public ApiResponse search(@PathVariable String title) {
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTitle().equals(title)) {
                found = true;

            }
        }
        if (found) {
            return new ApiResponse("found");
        } else {
            return new ApiResponse("not found");
        }

    }


}
