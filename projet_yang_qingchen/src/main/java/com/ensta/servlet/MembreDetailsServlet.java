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

public class MembreDetailsServlet extends HttpServlet {	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		MembreService membreService =MembreServiceImpl.getInstance();
		String stringid = null;
		String stringnom = null;
		String stringprenom = null;
		String stringtel = null;
		String stringadr = null;
		String stringemail = null;
		stringid= request.getParameter("id");
		stringnom = request.getParameter("nom");
		stringprenom = request.getParameter("prenom");
		stringtel = request.getParameter("telephone");
		stringadr = request.getParameter("adresse");
		stringemail = request.getParameter("email");
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
		
		if (stringadr!=null && stringemail!=null && stringnom!=null && stringprenom!=null && stringtel!=null) {
			membre.setAdresse(stringadr);
			membre.setEmail(stringemail);
			membre.setNom(stringnom);
			membre.setPrenom(stringprenom);
			membre.setTelephone(stringtel);
			try {
				membreService.update(membre);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		request.setAttribute("membre", membre);
		
		
		request.setAttribute("id", id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
		dispatcher.forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}