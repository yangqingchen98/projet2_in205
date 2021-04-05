package com.ensta.servlet;


import com.ensta.service.impl.*;
import com.ensta.service.*;
import com.ensta.model.*;
import com.ensta.exception.*;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmpruntAddServlet extends HttpServlet {	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpruntService empruntService = EmpruntServiceImpl.getInstance();
		List<Emprunt> emprunt = new ArrayList<Emprunt>();
		
		int idMembre = 0;
		int idLivre = 0;
		
		try {
			idMembre = Integer.parseInt(request.getParameter("idMembre"));
			idLivre = Integer.parseInt(request.getParameter("idLivre"));
			System.out.println(idLivre);
			
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		
		try {
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
		List<Livre> livredispo = new ArrayList<Livre>();
		try {
			livredispo = livreService.getListDispo();
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		MembreService membreService = MembreServiceImpl.getInstance();
		List<Membre> membredispo = new ArrayList<Membre>();
		try {
			membredispo = membreService.getListMembreEmpruntPossible();
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			empruntService.create(idMembre, idLivre, null);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
		request.setAttribute("emprunt", emprunt);
		request.setAttribute("livredispo", livredispo);
		request.setAttribute("membredispo", membredispo);
		request.setAttribute("empruntCurrent", empruntCurrent);
		
		request.setAttribute("idLivre", idLivre);
		request.setAttribute("idMembre", idMembre);
		dispatcher.forward(request, response);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}