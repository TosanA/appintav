package fr.uha.appintav.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.uha.appintav.error.RecordNotFoundException;
import fr.uha.appintav.model.Task;
import fr.uha.appintav.repo.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	public Task findById(Integer id) {
		Optional<Task> task = this.taskRepository.findById(id);
		if (task.equals(Optional.empty()))
			throw new RecordNotFoundException("Task with id '" + id + "' does not exist.");
		
		return task.get();
	}
	
	public Task save(Task task) {
		return this.taskRepository.save(task);
	}
	
}
