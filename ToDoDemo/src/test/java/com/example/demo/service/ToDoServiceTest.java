package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.entity.Todo;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ToDoRepository;



public class ToDoServiceTest {
	

	@Autowired
	private ToDoService toDoService;

	@MockBean
	private ToDoRepository toDoRepository;

	@Test
	public void getTodoTest() {
		when(toDoRepository.findAll()).thenReturn(Stream
				.of(new Todo((long) 4, "GGG"), new Todo((long) 5, "Harish")).collect(Collectors.toList()));
		assertEquals(2, toDoService.getAllToDo().size());
	}

	@Test
	public void getTodoByNameTest() throws ResourceNotFoundException {
		String name = "Harish";
		when(toDoRepository.findByName(name))
				.thenReturn(Stream.of(new Todo((long) 5, "Harish")).collect(Collectors.toList()));
		assertEquals(1, toDoService.getToDoByName("Harish").size());
	}
	
	@Test(expected=NullPointerException.class)
	public void getTodoByNameNullTest() throws ResourceNotFoundException {
		String name="";
		int expectedCount = 0;
		List<Todo> list=toDoService.getToDoByName(name);
		Assert.assertEquals(expectedCount, list.size());
	}

	@Test
	public void createTodoTest() {
		Todo todo = new Todo((long) 6, "Sample");
		when(toDoRepository.save(todo)).thenReturn(todo);
		assertEquals(todo, toDoService.createToDo(todo));
	}

	@Test
	public void deleteTodoTest() throws ResourceNotFoundException {
		Todo todo = new Todo((long) 6, "Sample");
		toDoService.deleteToDo(todo.getId());
		verify(toDoRepository, times(1)).delete(todo);
	}
	
	@Test(expected=NullPointerException.class)
	public void deleteTodowithoutIdTest() throws ResourceNotFoundException {
		Long id = null;
		toDoService.deleteToDo(id);
		verify(toDoRepository, times(0)).delete(new Todo((long) 6, "Sample"));
	}
	
	@Test(expected=ResourceNotFoundException.class)
	public void deleteTodowithInValidIdTest() throws ResourceNotFoundException {
		Long id = (long) 10;
		toDoService.deleteToDo(id);
		verify(toDoRepository, times(0)).delete(new Todo((long) 6, "Sample"));
	}
	
	
}
