package com.konak.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.konak.entity.Flight;

/**
 * Servlet implementation class SearchFlightServlet
 */
@WebServlet("/search")
public class SearchFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchFlightServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String cityFrom = request.getParameter("cityFrom");
		System.out.println("cityFrom : " + cityFrom);
		String cityTo = request.getParameter("cityTo");
		System.out.println("cityTo : " + cityTo);
		String numberOfPeople = request.getParameter("numberOfPeople");
		System.out.println("numberOfPeople : " + numberOfPeople);
		
		String flightDate = request.getParameter("flightDate");
		System.out.println("flightDate : " + flightDate);
		
		if (cityFrom == null || "".equals(cityFrom) || cityTo == null || "".equals(cityTo) || numberOfPeople == null || "".equals(numberOfPeople) || flightDate == null || "".equals(flightDate) ) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			request.setAttribute("searchErrorMessage", "Make sure you selected all necessary fields.");
			dispatcher.forward(request, response);
		} else if (cityFrom.equals(cityTo)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			request.setAttribute("searchErrorMessage", "Source and destination can not be the same.");
			dispatcher.forward(request, response);
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
		LocalDate chosenDate = LocalDate.parse(flightDate.substring(0, 10), formatter);
		flightDate = chosenDate.toString();
		System.out.println("flightDate : " + flightDate );
		
		HttpSession httpSession = request.getSession();
		SessionFactory sessionFactory = (SessionFactory) httpSession.getAttribute("sessionFactory");
		List<Flight> flightList = new ArrayList<>();
		if (sessionFactory == null) {
			response.sendRedirect("index.jsp");
		} else {
			Session session = sessionFactory.openSession();
			String hql = "FROM Flight F WHERE F.source = :cityFrom  "
					+ "AND F.destination = :cityTo "
					+ "AND F.date = :flightDate";
			Query query = session.createQuery(hql);
			query.setParameter("cityFrom", cityFrom);
			query.setParameter("cityTo", cityTo);
			query.setParameter("flightDate", chosenDate);
			flightList = query.list();
			session.close();
			for (Flight flight : flightList) {
				System.out.println(flight);
			}
		}
		
		if (flightList.size() <= 0) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			request.setAttribute("searchErrorMessage", "No flights can be found for your search.");
			dispatcher.forward(request, response);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("flights.jsp");
		request.setAttribute("flightList", flightList);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
