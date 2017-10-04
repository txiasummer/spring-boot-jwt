package txia.bootjwt.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.util.Assert
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import txia.bootjwt.domain.Task
import txia.bootjwt.repository.TaskRepository

import javax.annotation.Resource

@RestController
@RequestMapping('/tasks')
@Api(value = 'Tasks', description = 'You can do all the CRUD operations on tasks here')
class TaskController {

	@Resource
	TaskRepository taskRepository

	@PostMapping
	@ApiOperation(value = "Create a new Task")
	@ApiResponses(value = [@ApiResponse(code=200, message = 'Successfully created Task')])
	void addTask(@RequestBody Task task) {
		taskRepository.save(task)
	}

	@GetMapping
	@ApiOperation(value = "Gets all Tasks")
	@ApiResponses(value = [@ApiResponse(code=200, message = 'Gets all the Tasks')])
	List<Task> getTasks() {
		return taskRepository.findAll()
	}

	@PutMapping('/{id}')
	@ApiOperation(value = "Updates Task")
	@ApiResponses(value = [@ApiResponse(code=200, message = 'Successfully updated Task description')])
	void editTask(@PathVariable Long id, @RequestBody Task task) {
		Task existingTask = taskRepository.findOne(id)
		Assert.notNull(existingTask, 'Task not found')
		existingTask.description = task.description
		taskRepository.save(existingTask)
	}

	@DeleteMapping('/{id}')
	@ApiOperation(value = "Deletes Task")
	@ApiResponses(value = [@ApiResponse(code=200, message = 'Successfully deleted a Task')])
	void deleteTask(@PathVariable Long id) {
		taskRepository.delete(id)
	}
}
