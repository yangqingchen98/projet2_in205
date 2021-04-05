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

public class EmpruntReturnServlet  extends HttpServlet{ 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpruntService empruntService = EmpruntServiceImpl.getInstance();
		List<Emprunt> emprunt = new ArrayList<Emprunt>();
		int inputId = 0;
		
		try {
			inputId = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		
		System.out.println(inputId);

		try {
			empruntService.returnBook(inputId);
			emprunt = empruntService.getList();
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		List<Emprunt> empruntCurrent = new ArrayList<Emprunt>();
		try {
			empruntCurrent = empruntService.getListCurrent();
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		LivreService livreService = LivreServiceImpl.getInstance();
		List<Livre> livre = new ArrayList<Livre>();
		try {
			livre = livreService.getList();
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		MembreService membreService = MembreServiceImpl.getInstance();
		List<Membre> membre = new ArrayList<Membre>();
		try {
			membre = membreService.getList();
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp");
		request.setAttribute("emprunt", emprunt);
		request.setAttribute("livre", livre);
		request.setAttribute("membre", membre);
		request.setAttribute("empruntCurrent", empruntCurrent);
		request.setAttribute("id", inputId);
		dispatcher.forward(request, response);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}