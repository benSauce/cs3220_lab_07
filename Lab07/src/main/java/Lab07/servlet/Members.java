package Lab07.servlet;

import cs3220.model.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Members")
public class Members extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Members() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Print out
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><head><title>Members</title></head><body>");

		// Don't create a new session if it doesn't exist
		HttpSession session = request.getSession(false);
		if (session != null) {
			// Session exists
			out.println("<h1>Session exists</h1>");

			// retrieve session Attribute
			User currentUser = (User) session.getAttribute("currentUser");

			// if currentUser is null redirect to Login
			if (currentUser == null) {
				response.sendRedirect("Login");
			}

			// main body
			out.println("<p style='margin-left: 40px'>" + "Hi, " + currentUser.getName()
					+ ". Welcome to the Members Area!" + "</p>");

			out.println("<p style='margin-left: 40px'><a href='Logout'>Logout</a></p>");

			// Close body and html
			out.println("</body></html>");

		} else {
			// Session does not exist
			out.println("<h1>Session does NOT exists</h1>");
			response.sendRedirect("Login");
		}

	}

}
