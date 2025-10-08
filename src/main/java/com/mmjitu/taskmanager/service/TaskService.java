package com.mmjitu.taskmanager.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.mmjitu.taskmanager.entity.Task;
import com.mmjitu.taskmanager.repository.TaskRepository;
import com.mmjitu.taskmanager.request.TaskRequestVm;
import com.mmjitu.taskmanager.response.DataPack;
import com.mmjitu.taskmanager.response.DataPackSingle;
import com.mmjitu.taskmanager.response.IdResponseVm;
import com.mmjitu.taskmanager.response.TaskResponseVm;
import com.mmjitu.taskmanager.utility.CommonServiceModel;
import com.mmjitu.taskmanager.utility.ServiceUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Service
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;
    
    private final ServiceUtil serviceUtil;

    public TaskService(TaskRepository taskRepository, ServiceUtil serviceUtil) {
        this.taskRepository = taskRepository;
        this.serviceUtil = serviceUtil;
    }

    // Create Task
    public Map<String, Object> createTask(HttpServletRequest request, String requestId, @Valid TaskRequestVm taskRequestVm) {

        DataPackSingle<TaskResponseVm> dataPack;
        CommonServiceModel serviceModel = new CommonServiceModel();

        try {
        	
            Task task = new Task();
            BeanUtils.copyProperties(taskRequestVm, task);
            task.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            Task savedTask = taskRepository.save(task);

            TaskResponseVm responseVm = new TaskResponseVm();
            BeanUtils.copyProperties(savedTask, responseVm);            

            dataPack = new DataPackSingle<>(true, responseVm, List.of("Task saved successfully."));
            serviceUtil.addResponseDataForSuccess(dataPack, "Task created successfully.", serviceModel);

            log.info("Successfully created Task with ID: {}", savedTask.getId());

        } catch (Exception e) {
            log.error("Error while creating Task for request ID: {}, Exception: {}", requestId, e.getMessage(), e);
            serviceUtil.addResponseDataForException(e, serviceModel);
        }

        return serviceUtil.getServiceReturn(serviceModel);
    }

    // Get All Tasks
    public Map<String, Object> getAllTasks(HttpServletRequest request, String requestId) {

        DataPack<TaskResponseVm> dataPack;
        CommonServiceModel serviceModel = new CommonServiceModel();

        try {
            List<TaskResponseVm> taskVmList = getAllTaskList();

            if (!taskVmList.isEmpty()) {
                dataPack = new DataPack<>(true, taskVmList, List.of(taskVmList.size() + " task(s) found."));
                serviceUtil.addResponseDataForSuccess(dataPack, "Successfully fetched tasks.", serviceModel);
                log.info("Fetched {} tasks successfully for request ID: {}", taskVmList.size(), requestId);
            } else {
                dataPack = new DataPack<>(false, new ArrayList<>(), List.of("No tasks found."));
                serviceUtil.addResponseDataForSuccess(dataPack, "No task data found.", serviceModel);
                log.info("No tasks found for request ID: {}", requestId);
            }

        } catch (Exception e) {
            log.error("Error while fetching tasks for request ID: {}, Exception: {}", requestId, e.getMessage(), e);
            serviceUtil.addResponseDataForException(e, serviceModel);
        }

        return serviceUtil.getServiceReturn(serviceModel);
    }

    // Get Task List Helper
    public List<TaskResponseVm> getAllTaskList() {
        List<Task> taskList = taskRepository.findAll();
        List<TaskResponseVm> taskVmList = new ArrayList<>();

        if (taskList != null && !taskList.isEmpty()) {
            for (Task task : taskList) {
                TaskResponseVm vm = new TaskResponseVm();
                BeanUtils.copyProperties(task, vm);
                taskVmList.add(vm);
            }
        }

        return taskVmList;
    }

    // Get Task By ID
    public Map<String, Object> getTaskById(HttpServletRequest request, String requestId, Long taskId) {

        DataPackSingle<TaskResponseVm> dataPack;
        CommonServiceModel serviceModel = new CommonServiceModel();

        try {
            Optional<Task> optTask = taskRepository.findById(taskId);

            if (optTask.isPresent()) {
                TaskResponseVm responseVm = new TaskResponseVm();
                BeanUtils.copyProperties(optTask.get(), responseVm);

                dataPack = new DataPackSingle<>(true, responseVm, List.of("Task found."));
                serviceUtil.addResponseDataForSuccess(dataPack, "Task fetched successfully.", serviceModel);

                log.info("Task fetched successfully with ID: {}", taskId);

            } else {
                dataPack = new DataPackSingle<>(false, null, List.of("Task not found."));
                serviceUtil.addResponseDataForSuccess(dataPack, "Task not found.", serviceModel);

                log.warn("Task not found with ID: {}", taskId);
            }

        } catch (Exception e) {
            log.error("Error while fetching task ID: {}, Exception: {}", taskId, e.getMessage(), e);
            serviceUtil.addResponseDataForException(e, serviceModel);
        }

        return serviceUtil.getServiceReturn(serviceModel);
    }

 // ---------------- Update Task ----------------
    public Map<String, Object> updateTask(HttpServletRequest request, String requestId, Long taskId, @Valid TaskRequestVm taskRequest) {

        DataPackSingle<TaskResponseVm> dataPack;
        CommonServiceModel serviceModel = new CommonServiceModel();

        try {
            Optional<Task> optTask = taskRepository.findById(taskId);

            if (optTask.isPresent()) {
                Task task = optTask.get();
                
                BeanUtils.copyProperties(taskRequest, task);
                task.setModifiedAt(new Timestamp(System.currentTimeMillis()));               

                Task updatedTask = taskRepository.save(task);

                TaskResponseVm responseVm = new TaskResponseVm();
                BeanUtils.copyProperties(updatedTask, responseVm);

                dataPack = new DataPackSingle<>(true, responseVm, List.of("Task updated successfully."));
                serviceUtil.addResponseDataForSuccess(dataPack, "Task updated successfully.", serviceModel);
                log.info("Task updated successfully with ID: {}", taskId);

            } else {
                dataPack = new DataPackSingle<>(false, null, List.of("Task not found."));
                serviceUtil.addResponseDataForSuccess(dataPack, "Task not found.", serviceModel);
                log.warn("Task not found to update with ID: {}", taskId);
            }

        } catch (Exception e) {
            log.error("Error while updating task ID: {}, Exception: {}", taskId, e.getMessage(), e);
            serviceUtil.addResponseDataForException(e, serviceModel);
        }

        return serviceUtil.getServiceReturn(serviceModel);
    }
    
    // Delete Task
    public Map<String, Object> deleteTask(HttpServletRequest request, String requestId, Long taskId) {
    	
        CommonServiceModel serviceModel = new CommonServiceModel();        
        IdResponseVm response = new IdResponseVm();
         

        try {
            if (taskRepository.existsById(taskId)) {
                taskRepository.deleteById(taskId);                
                log.info("Task deleted successfully with ID: {}", taskId);
                response.setId(taskId);
                serviceUtil.addResponseDataForSuccess(response, "Task deleted successfully.", serviceModel);
            } else {               
                log.warn("Task not found to delete with ID: {}", taskId);
                serviceUtil.addResponseDataForSuccess(response, "Task not found.", serviceModel);
            }
        } catch (Exception e) {
            log.error("Error while deleting task ID: {}, Exception: {}", taskId, e.getMessage(), e);
            serviceUtil.addResponseDataForException(e, serviceModel);
        }

        return serviceUtil.getServiceReturn(serviceModel);
    }
}
