package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Todo;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.ToDoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/todo")
@Api(value = "Todo Service", description = "Todo operations")
public class ToDoController {

	@Autowired
	ToDoService toDoService;
	
	@GetMapping("/getTodo")
	@ApiOperation("Get All Todo details")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	List<Todo> getAllTodo() {
		List<Todo> list = toDoService.getAllToDo();
		return list;
	}	
	
	@GetMapping("/getTodo/{name}")
	@ApiOperation("Get Todo records by name")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	List<Todo> getTodoByName(@PathVariable String name) throws ResourceNotFoundException {
		return toDoService.getToDoByName(name);
	}
	
	@PostMapping("/createTodo")
	@ApiOperation("Add/Create a new todo record")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public void createTodo(@RequestBody Todo todo) {
		toDoService.createToDo(todo);
	}
	
	@PutMapping("/updateTodo/{id}")
	@ApiOperation("Update complete todo record")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public void updateTodo(@PathVariable Long id, @RequestBody Todo todo) throws ResourceNotFoundException {
		toDoService.updateTodo(id, todo);
	}
	
	@PatchMapping("/updateName/{id}")
	@ApiOperation("Update specific todo record ")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public void updateName(@PathVariable Long id,
							@RequestParam String name) throws ResourceNotFoundException {
		toDoService.updateName(id, name);
	}
	
	@DeleteMapping("/deleteTodo/{id}")
	@ApiOperation("Delete a todo Record")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public void deleteToDo(@PathVariable Long id) throws ResourceNotFoundException {
		toDoService.deleteToDo(id);
	}
	
	
}
