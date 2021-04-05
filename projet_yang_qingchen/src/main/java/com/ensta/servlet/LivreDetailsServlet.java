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

public class LivreDetailsServlet extends HttpServlet {	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		LivreService livreService = LivreServiceImpl.getInstance();
		String stringid = null;
		stringid= request.getParameter("id");

		String stringauteur = null;
		String stringtitre = null;
		String stringisbn = null;
		stringauteur = request.getParameter("auteur");
		stringtitre = request.getParameter("titre");
		stringisbn = request.getParameter("isbn");
		
		int id = 0;
		try {
			id = Integer.parseInt(stringid);
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		
	
		Livre livre = new Livre();
		
		try {
			
			livre = livreService.getById(id);
		
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		if (stringauteur!=null && stringtitre!=null && stringisbn!=null ) {
			livre.setAuteur(stringauteur);
			livre.setTitre(stringtitre);
			livre.setIsbn(stringisbn);
			try {
				livreService.update(livre);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		request.setAttribute("livre", livre);
		
		
		request.setAttribute("id", id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_details.jsp");
		dispatcher.forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}