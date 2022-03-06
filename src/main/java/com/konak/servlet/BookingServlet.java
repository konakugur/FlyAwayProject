package com.konak.servlet;

import java.io.IOException;
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
 * Servlet implementation class BookingServlet
 */
@WebServlet("/buy")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if (request.getParameter("chosenFlightId") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/");
			request.setAttribute("errorMessage", "You need to be signed in to make this operation.");
			dispatcher.forward(request, response);
		}
		
		int flightId = Integer.parseInt(request.getParameter("chosenFlightId"));
		System.out.println("chosenFlightId : " + flightId );
		HttpSession httpSession = request.getSession();
		SessionFactory sessionFactory = (SessionFactory) httpSession.getAttribute("sessionFactory");
		List<Flight> flightList = new ArrayList<>();
		if (sessionFactory == null) {
			response.sendRedirect("index.jsp");
		} else {
			Session session = sessionFactory.openSession();
			String hql = "FROM Flight F WHERE F.flightId = :flightId  ";
			Query query = session.createQuery(hql);
			query.setParameter("flightId", flightId);
			flightList = query.list();
			session.close();
			for (Flight flight : flightList) {
				System.out.println(flight);
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("payment.jsp");
		request.setAttribute("flight", flightList.get(0));
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
