package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Todo;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ToDoRepository;

@Service
public class ToDoService {
	
	@Autowired
	ToDoRepository toDoRepository;
	
	public List<Todo> getAllToDo() {
		return toDoRepository.findAll();
	}
	
	public List<Todo> getToDoByName(String name) throws ResourceNotFoundException {
		
		if(name == null || name.equals("")) {
			throw new ResourceNotFoundException("Can not get Todo data as the input name is null/empty");
		}
		return toDoRepository.findByName(name);
	}
	
	
	public Todo createToDo(Todo todo) {
		return toDoRepository.save(todo);
	}
	
	public <U> void updateTodo(Long id, Todo newtodo) throws ResourceNotFoundException {
		if(id == null) {
			throw new NullPointerException();
		}
		if(!toDoRepository.existsById(id)) {
			throw new ResourceNotFoundException("Request resource with id:" + id+ " not available");
		}
		Todo todo = toDoRepository.getById(id);		
		todo.setName(newtodo.getName());
	    toDoRepository.save(todo);
	}
	
	public void updateName(Long id, String name) throws ResourceNotFoundException {
		if(id == null) {
			throw new NullPointerException();
		}
		
		if(!toDoRepository.existsById(id)) {
			throw new ResourceNotFoundException("Request resource with id:" + id+ " not available");
		}
		Todo todo = toDoRepository.getById(id);
		
		todo.setName(name);
	    toDoRepository.save(todo);
	}
	
	public void deleteToDo(Long id) throws ResourceNotFoundException {
		if(id == null) {
			throw new NullPointerException();
		}
		if(!toDoRepository.existsById(id)) {
			throw new ResourceNotFoundException("Request resource with id:" + id+ "not available");
		}
		toDoRepository.deleteById(id);
	}
}
