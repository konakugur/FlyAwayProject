package com.konak.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/makePayment")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/");
		request.setAttribute("errorMessage", "You need to be signed in to make this operation.");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("cardMonth") == null ) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/");
			request.setAttribute("errorMessage", "You need to be signed in to make this operation.");
			dispatcher.forward(request, response);
		}
		
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");
		writer.println("<html><head><style>h1 {color: green}</style></head><body>");
		writer.println("<h1>Payment Successful. Please be at the airport at least 1 hour before your flight and take necessary measures.</h1>");
		writer.println("<div><a href='login.jsp'>See other flights.</a>");
		writer.println("</body></html");
	}

}
