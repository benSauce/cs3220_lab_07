package Lab07.servlet;

import cs3220.model.Event;
import cs3220.model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/Login", loadOnStartup = 1)
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	public void init() throws ServletException {
		// Create List of Event type
		List<User> users = new ArrayList<>();
		// Create two Event objects from sample display
		char[] charArray = { 'a', 'b', 'c', 'd' };
		users.add(new User("John Doe", "jdoe", charArray));
		char[] charArray2 = { 'e', 'f', 'g', 'h' };
		users.add(new User("Mike Brown", "mbro", charArray2));
		getServletContext().setAttribute("users", users);
	}

	private boolean checkMatch(String username, char[] password) {
		List<User> users = (List<User>) getServletContext().getAttribute("users");
		for (User user : users)
			if ((user.getName() == username) && user.getPassword() == password) {
				return true;
			}
		return false;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get users List
		List<User> users = (List<User>) getServletContext().getAttribute("users");

		// Print out
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Login</title></head><body>");

		// begin form
		out.println("");
		out.println("<form action ='Login' method='post'>");
		out.println("<p style='margin-left: 40px'>" + "Username: <input name='username' type='text'>" + "    </p>");
		out.println("<p style='margin-left: 40px'>" + "Password: <input name='password' type='password'>" + "    </p>");
		out.println("<p style='margin-left: 40px'>" + "<input name='login' type='submit' value='login'>" + "    </p>");
		for (User user : users) {
			out.println(user.getName());
		}
		out.println("");

		// begin table body

		// forEach list element
		/*
		 * for (User user : users) { out.println("<tr>"); out.println("<td>" +
		 * event.getName() + "</td>"); out.println("<td>" +
		 * formatter.format(event.getEventDate()) + "</td>"); out.println("<td>" +
		 * event.getCreatedBy() + "</td>"); out.println("</tr>"); }
		 */

		// close form
		out.println("</form>");

		// Close body and html
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get the session object
		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		char[] password = (request.getParameter("password")).toCharArray();
		;
		// Check if username & password match user list
		if (checkMatch(username, password)) {
			// Set an attribute in the session
			session.setAttribute("username", username);
			// send user back to ListEvents.java
			response.sendRedirect("Members");
		} // close if
		else {
			response.sendRedirect("Login");
		} // end else

	}

}
