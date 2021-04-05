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

public class LibraryServlet extends HttpServlet {	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
		case "/dashboard":
			DashboardServlet dashboardServlet = new DashboardServlet();
			dashboardServlet.doGet(request, response);
			break;
			
		case "/emprunt_add":
			EmpruntAddServlet empruntAddServlet  = new EmpruntAddServlet();
			empruntAddServlet .doGet(request, response);
			break;
			
			
		case "/emprunt_list":
			EmpruntListServlet empruntListServlet = new EmpruntListServlet();
			empruntListServlet.doGet(request, response);
			break;
			
			
		case "/emprunt_return":
			EmpruntReturnServlet empruntReturnServlet = new EmpruntReturnServlet();
			empruntReturnServlet.doGet(request, response);
			break;
			
		case "/livre_add":
			LivreAddServlet livreAddServlet  = new LivreAddServlet();
			livreAddServlet.doGet(request, response);
			break;
			
			
		case "/livre_list":
			LivreListServlet livreListServlet = new LivreListServlet();
			livreListServlet.doGet(request, response);
			break;
			
		case "/livre_delete":
			LivreDeleteServlet livreDeleteServlet = new LivreDeleteServlet();
			livreDeleteServlet.doGet(request, response);
			break;
		case "/livre_details":
			LivreDetailsServlet livreDetailsServlet = new LivreDetailsServlet();
			livreDetailsServlet.doGet(request, response);
			break;
		
		case "/membre_add":
			MembreAddServlet membreAddServlet  = new MembreAddServlet();
			membreAddServlet.doGet(request, response);
			break;
			
			
		case "/membre_list":
			MembreListServlet membreListServlet = new MembreListServlet();
			membreListServlet.doGet(request, response);
			break;
			
			
		case "/membre_details":
			MembreDetailsServlet membreDetailsServlet = new MembreDetailsServlet();
			membreDetailsServlet.doGet(request, response);
			break;
			
		case "/membre_delete":
			MembreDeleteServlet membreDeleteServlet = new MembreDeleteServlet();
			membreDeleteServlet.doGet(request, response);
			break;

		default:
			System.out.println("Default redirecting case from " + action + " !");
			DashboardServlet e = new DashboardServlet();
			e.doGet(request, response);
			
		}
					
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}