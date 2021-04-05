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

public class MembreAddServlet extends HttpServlet {	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		String nom = request.getParameter("nom");
		String  prenom = request.getParameter("prenom");
		String  adresse = request.getParameter("adresse");
		String  email = request.getParameter("email");
		String  telephone = request.getParameter("telephone");
		MembreService membreService = MembreServiceImpl.getInstance();
		
		
		if (nom!=null && prenom!=null &&adresse !=null &&email!=null&&telephone!=null)
		{

			try {
				membreService.create(nom,prenom,adresse,email,telephone,"BASIC");
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_add.jsp");

		dispatcher.forward(request, response);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}