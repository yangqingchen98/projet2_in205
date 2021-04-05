package com.ensta.servlet;


import com.ensta.service.impl.*;
import com.ensta.service.*;
import com.ensta.model.*;
import com.ensta.exception.*;
import java.io.IOException;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MembreDeleteServlet extends HttpServlet {	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		MembreService membreService = MembreServiceImpl.getInstance();
		String stringid = null;
		stringid= request.getParameter("id");
		int id = 0;
		
		try {
			id = Integer.parseInt(stringid);
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		
	
		Membre membre = new Membre();
		
		try {
			
			membre = membreService.getById(id);
		
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		request.setAttribute("membre", membre);
		try {
			membreService.delete(id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("id", id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_delete.jsp");
		dispatcher.forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}