package com.konak.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
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
import org.hibernate.Transaction;
import org.hibernate.service.ServiceRegistry;

import com.konak.db.HibernateUtil;
import com.konak.entity.AdminUser;
import com.konak.entity.Customer;
import com.konak.entity.Flight;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/login")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = request.getParameter("url");
		System.out.println("url : " + url);
		String driver = request.getParameter("driver");
		System.out.println("driver : " + driver);
		String username = request.getParameter("username");
		System.out.println("username : " + username);
		String password = request.getParameter("password");
		System.out.println("password : " + password);
		String adminCheckbox = request.getParameter("adminCheckbox");
		System.out.println("adminCheckbox : " + adminCheckbox);
		String adminName = request.getParameter("adminName");
		System.out.println("adminName : " + adminName);
		String adminPassword = request.getParameter("adminPassword");
		System.out.println("adminPassword : " + adminPassword);
		
		if (url == null || "".equals(url) || driver == null || "".equals(driver) ) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/");
			request.setAttribute("errorMessage", "You need to be signed in to make this operation.");
			dispatcher.forward(request, response);
		}
		
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory(driver, url, username, password);
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("sessionFactory", sessionFactory);
		createFlightTable(sessionFactory);
		createAdminTable(sessionFactory, (String) request.getAttribute("adminPassword"));
		
		if(adminCheckbox == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		} else {
			if ("admin".equals(adminName)) {
				String dbAdminPassword = getAdminPassword(sessionFactory);
				if (dbAdminPassword.equals(adminPassword)) {
					List<Flight> flights = getAllFlightsFromDb(sessionFactory); 
					request.setAttribute("flights", flights);
					RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("payment.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	private List<Flight> getAllFlightsFromDb(SessionFactory sessionFactory) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "FROM Flight";
		Query<Flight> query = session.createQuery("from Flight", Flight.class);
		tx.commit();
		return query.list();
		
	}
	
	private void createAdminTable(SessionFactory sessionFactory, String adminPassword) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(new AdminUser("admin", "admin"));
		
		tx.commit();
	}
	
	private String getAdminPassword(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		String hql = "FROM AdminUser U WHERE U.username = 'admin'  ";
		Query query = session.createQuery(hql);
		List<AdminUser> adminList = query.list();
		return adminList.get(0).getPassword();
	}
	
	private void addFlight(SessionFactory sessionFactory, Flight flight) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(flight);
		tx.commit();
	}
	
	
	private void createFlightTable(SessionFactory sessionFactory) {
		LocalDate date = LocalDate.now();
		Flight flight = new Flight();
		flight.setSource("Istanbul");
		flight.setDestination("Paris");
		flight.setAirline("Turkish Airlines");
		flight.setPrice("100");
		flight.setDate(date.plusDays(1));
		flight.setTime("12:00 AM");
		
		addFlight(sessionFactory, new Flight("Istanbul", "Paris", "Turkish Airlines", "100", date.plusDays(1), "08:00"));
		addFlight(sessionFactory, new Flight("Istanbul", "Paris", "Turkish Airlines", "100", date.plusDays(1), "13:00"));
		addFlight(sessionFactory, new Flight("Istanbul", "Paris", "Turkish Airlines", "100", date.plusDays(1), "21:00"));
		
		addFlight(sessionFactory, new Flight("Istanbul", "London", "Turkish Airlines", "200", date.plusDays(1), "10:00"));
		addFlight(sessionFactory, new Flight("Istanbul", "London", "Turkish Airlines", "200", date.plusDays(1), "15:00"));
		addFlight(sessionFactory, new Flight("Istanbul", "London", "Turkish Airlines", "200", date.plusDays(1), "23:00"));
		
		addFlight(sessionFactory, new Flight("Paris", "Istanbul", "French Airlines", "100", date.plusDays(1), "06:00"));
		addFlight(sessionFactory, new Flight("Paris", "Istanbul", "French Airlines", "100", date.plusDays(1), "15:00"));
		addFlight(sessionFactory, new Flight("Paris", "Istanbul", "French Airlines", "100", date.plusDays(1), "23:00"));
		
		addFlight(sessionFactory, new Flight("London", "Istanbul", "English Airlines", "200", date.plusDays(1), "02:00"));
		addFlight(sessionFactory, new Flight("London", "Istanbul", "English Airlines", "200", date.plusDays(1), "11:00"));
		addFlight(sessionFactory, new Flight("London", "Istanbul", "English Airlines", "200", date.plusDays(1), "19:00"));
		
		addFlight(sessionFactory, new Flight("London", "Paris", "English Airlines", "50", date.plusDays(1), "04:00"));
		addFlight(sessionFactory, new Flight("London", "Paris", "English Airlines", "50", date.plusDays(1), "13:00"));
		addFlight(sessionFactory, new Flight("London", "Paris", "English Airlines", "50", date.plusDays(1), "22:00"));
		
		addFlight(sessionFactory, new Flight("Paris", "London", "French Airlines", "150", date.plusDays(1), "03:30"));
		addFlight(sessionFactory, new Flight("Paris", "London", "French Airlines", "150", date.plusDays(1), "10:30"));
		addFlight(sessionFactory, new Flight("Paris", "London", "French Airlines", "150", date.plusDays(1), "18:30"));
		


		
		addFlight(sessionFactory, new Flight("Istanbul", "Paris", "Turkish Airlines", "100", date.plusDays(2), "08:00"));
		addFlight(sessionFactory, new Flight("Istanbul", "Paris", "Turkish Airlines", "100", date.plusDays(2), "13:00"));
		addFlight(sessionFactory, new Flight("Istanbul", "Paris", "Turkish Airlines", "100", date.plusDays(2), "21:00"));
		
		addFlight(sessionFactory, new Flight("Istanbul", "London", "Turkish Airlines", "200", date.plusDays(2), "10:00"));
		addFlight(sessionFactory, new Flight("Istanbul", "London", "Turkish Airlines", "200", date.plusDays(2), "15:00"));
		addFlight(sessionFactory, new Flight("Istanbul", "London", "Turkish Airlines", "200", date.plusDays(2), "23:00"));
		
		addFlight(sessionFactory, new Flight("Paris", "Istanbul", "French Airlines", "100", date.plusDays(2), "06:00"));
		addFlight(sessionFactory, new Flight("Paris", "Istanbul", "French Airlines", "100", date.plusDays(2), "15:00"));
		addFlight(sessionFactory, new Flight("Paris", "Istanbul", "French Airlines", "100", date.plusDays(2), "23:00"));
		
		addFlight(sessionFactory, new Flight("London", "Istanbul", "English Airlines", "200", date.plusDays(2), "02:00"));
		addFlight(sessionFactory, new Flight("London", "Istanbul", "English Airlines", "200", date.plusDays(2), "11:00"));
		addFlight(sessionFactory, new Flight("London", "Istanbul", "English Airlines", "200", date.plusDays(2), "19:00"));
		
		addFlight(sessionFactory, new Flight("London", "Paris", "English Airlines", "50", date.plusDays(2), "04:00"));
		addFlight(sessionFactory, new Flight("London", "Paris", "English Airlines", "50", date.plusDays(2), "13:00"));
		addFlight(sessionFactory, new Flight("London", "Paris", "English Airlines", "50", date.plusDays(2), "22:00"));
		
		addFlight(sessionFactory, new Flight("Paris", "London", "French Airlines", "150", date.plusDays(2), "03:30"));
		addFlight(sessionFactory, new Flight("Paris", "London", "French Airlines", "150", date.plusDays(2), "10:30"));
		addFlight(sessionFactory, new Flight("Paris", "London", "French Airlines", "150", date.plusDays(2), "18:30"));
		
		
		
		
		addFlight(sessionFactory, new Flight("Istanbul", "Paris", "Turkish Airlines", "100", date.plusDays(3), "08:00"));
		addFlight(sessionFactory, new Flight("Istanbul", "Paris", "Turkish Airlines", "100", date.plusDays(3), "13:00"));
		addFlight(sessionFactory, new Flight("Istanbul", "Paris", "Turkish Airlines", "100", date.plusDays(3), "21:00"));
		
		addFlight(sessionFactory, new Flight("Istanbul", "London", "Turkish Airlines", "200", date.plusDays(3), "10:00"));
		addFlight(sessionFactory, new Flight("Istanbul", "London", "Turkish Airlines", "200", date.plusDays(3), "15:00"));
		addFlight(sessionFactory, new Flight("Istanbul", "London", "Turkish Airlines", "200", date.plusDays(3), "23:00"));
		
		addFlight(sessionFactory, new Flight("Paris", "Istanbul", "French Airlines", "100", date.plusDays(3), "06:00"));
		addFlight(sessionFactory, new Flight("Paris", "Istanbul", "French Airlines", "100", date.plusDays(3), "15:00"));
		addFlight(sessionFactory, new Flight("Paris", "Istanbul", "French Airlines", "100", date.plusDays(3), "23:00"));
		
		addFlight(sessionFactory, new Flight("London", "Istanbul", "English Airlines", "200", date.plusDays(3), "02:00"));
		addFlight(sessionFactory, new Flight("London", "Istanbul", "English Airlines", "200", date.plusDays(3), "11:00"));
		addFlight(sessionFactory, new Flight("London", "Istanbul", "English Airlines", "200", date.plusDays(3), "19:00"));
		
		addFlight(sessionFactory, new Flight("London", "Paris", "English Airlines", "50", date.plusDays(3), "04:00"));
		addFlight(sessionFactory, new Flight("London", "Paris", "English Airlines", "50", date.plusDays(3), "13:00"));
		addFlight(sessionFactory, new Flight("London", "Paris", "English Airlines", "50", date.plusDays(3), "22:00"));
		
		addFlight(sessionFactory, new Flight("Paris", "London", "French Airlines", "150", date.plusDays(3), "03:30"));
		addFlight(sessionFactory, new Flight("Paris", "London", "French Airlines", "150", date.plusDays(3), "10:30"));
		addFlight(sessionFactory, new Flight("Paris", "London", "French Airlines", "150", date.plusDays(3), "18:30"));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
