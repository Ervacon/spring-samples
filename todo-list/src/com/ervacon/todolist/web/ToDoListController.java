package com.ervacon.todolist.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.ervacon.todolist.domain.ToDoList;

public class ToDoListController extends AbstractController {
	
	private ToDoList toDoList;
	
	public void setToDoList(ToDoList toDoList) {
		this.toDoList = toDoList;
	}
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("toDoList", "toDos", toDoList.getToDos());
	}

}
