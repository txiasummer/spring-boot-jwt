package txia.bootjwt.task

import org.springframework.util.Assert
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.annotation.Resource

@RestController
@RequestMapping('/tasks')
class TaskController {

	@Resource
	TaskRepository taskRepository

	@PostMapping
	void addTask(@RequestBody Task task) {
		taskRepository.save(task)
	}

	@GetMapping
	List<Task> getTasks() {
		return taskRepository.findAll()
	}

	@PutMapping('/{id}')
	void editTask(@PathVariable long id, @RequestBody Task task) {
		Task existingTask = taskRepository.findOne(id)
		Assert.notNull(existingTask, 'Task not found')
		existingTask.description = task.description
		taskRepository.save(existingTask)
	}

	@DeleteMapping('/{id}')
	void deleteTask(@PathVariable long id) {
		taskRepository.delete(id)
	}
}
