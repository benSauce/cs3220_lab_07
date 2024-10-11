package Lab07.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Logout() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Print out
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><head><title>Members</title></head><body>");

		// main body
		out.println("<h3>We are logging out now.</h3>");

		HttpSession session = request.getSession(false); // Get the session if it exists

		if (session != null) {
			session.invalidate(); // Invalidate the session
		}

		// Close body and html
		out.println("</body></html>");

		// Redirect or forward to another page
		response.sendRedirect("Login");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
