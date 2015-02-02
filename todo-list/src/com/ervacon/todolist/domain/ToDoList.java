package com.ervacon.todolist.domain;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ToDoList {
	
	private long toDoIdCounter = 0;
	private List<ToDo> toDos = new LinkedList<ToDo>();
	
	private synchronized long nextToDoId() {
		return toDoIdCounter++;
	}
	
	public List<ToDo> getToDos() {
		//only return TODOs where the deadline has not yet been passed
		List<ToDo> res = new LinkedList<ToDo>();
		for (ToDo toDo : toDos) {
			if (toDo.isActive()) {
				res.add(toDo);
			}
		}
		return res;
	}

	public void addToDo(ToDo newToDo) throws IOException {
		if (newToDo.getId() == null) {
			newToDo.setId(nextToDoId());
			toDos.add(newToDo);
			//keep list sorted
			Collections.sort(toDos);
		}
	}
	
	// test support

	public void initTestData() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// create some dummy TODOs
		addToDo(new ToDo("Celebrate the new year", dateFormat.parse("1/1/2007"), ""));
		addToDo(new ToDo("Present at SpringOne 2007", dateFormat.parse("20/6/2007"),
				"Tell people about SWF and show them all the nice features."));
		addToDo(new ToDo("Write Spring Web Flow book", dateFormat.parse("30/7/2007"), 
				"Write 'Working with Spring Web Flow' book"));
	}
}
