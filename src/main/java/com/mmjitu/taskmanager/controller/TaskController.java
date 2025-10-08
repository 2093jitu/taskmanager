package com.mmjitu.taskmanager.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmjitu.taskmanager.request.TaskRequestVm;
import com.mmjitu.taskmanager.service.TaskService;
import com.mmjitu.taskmanager.utility.CommonControllerUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	private CommonControllerUtil controllerUtil;
	
	@Autowired
	private TaskService taskService;
	
	
	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> create(HttpServletRequest request, @Valid @RequestBody TaskRequestVm taskRequest) {

	   
	    String requestId = "12345678";

	    // Controller log
	    log.info("TaskController.create:: [RequestID: {}] [URI: {}] [Payload: {}]",
	            requestId,
	            request.getRequestURI(),
	            taskRequest);

	   
	    Map<String, Object> responseMap = taskService.createTask(request, requestId, taskRequest);

	    // Response handling
	    return controllerUtil.getResponseEntity(responseMap, request);
	}
	
	  // ---------------- Get All Tasks ----------------
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllTasks(HttpServletRequest request) {
    	
        String requestId = "12345678";

        Map<String, Object> responseMap = taskService.getAllTasks(request, requestId);

        return controllerUtil.getResponseEntity(responseMap, request);
    }

    // ---------------- Get Task By ID ----------------
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getTaskById(HttpServletRequest request, @PathVariable("id") Long taskId) {

        String requestId = "12345678";

        Map<String, Object> responseMap = taskService.getTaskById(request, requestId, taskId);

        return controllerUtil.getResponseEntity(responseMap, request);
    }

    // ---------------- Update Task ----------------
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateTask(HttpServletRequest request, @PathVariable("id") Long taskId, @Valid @RequestBody TaskRequestVm taskRequest) {

        String requestId = "12345678";

        Map<String, Object> responseMap = taskService.updateTask(request, requestId, taskId, taskRequest);

        return controllerUtil.getResponseEntity(responseMap, request);
    }

    // ---------------- Delete Task ----------------
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteTask(HttpServletRequest request, @PathVariable("id") Long taskId) {

        String requestId = "12345678";

        Map<String, Object> responseMap = taskService.deleteTask(request, requestId, taskId);

        return controllerUtil.getResponseEntity(responseMap, request);
    }


}
