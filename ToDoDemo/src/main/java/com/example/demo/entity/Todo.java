package com.example.demo.entity;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long Id;
	
	@Column(name="name")
	String name;
	
	public Todo(Long id, String name) {
		super();
		Id = id;
		this.name = name;
	}
	public Todo() {
		super();
	}
	@Override
	public String toString() {
		return "Todo [Id=" + Id + ", name=" + name + "]";
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
