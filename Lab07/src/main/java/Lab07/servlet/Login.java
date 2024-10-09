package Lab07.servlet;

import cs3220.model.Event;
import cs3220.model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/Login", loadOnStartup = 1)
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		// Create List of Event type
		List<User> users = new ArrayList<>();
		// Create two Event objects from sample display
		String str = "abcd";
		users.add(new User("John Doe", "jdoe", str.toCharArray()));
		str = "efgh";
		users.add(new User("Mike Brown", "mbro", str.toCharArray()));
		getServletContext().setAttribute("users", users);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get events List
		@SuppressWarnings("unchecked")
		List<User> events = (List<User>) getServletContext().getAttribute("users");

		// Print out
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Login</title></head><body>");
		out.println("<p style=\"padding-left: 40px;\"><strong>Events</strong></p>");
		out.println(
				"<p style=\"padding-left: 40px;\"><a class=\"inline_disabled\" href=\"AddEvent\" target=\"_blank\">New Event</a></p>");
		out.println("<div style=\"padding-left: 40px;\">");
		out.println("<table border=\"1\" cellspacing=\"2\" cellpadding=\"5\">");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th>Event</th>");
		out.println("<th>Date</th>");
		out.println("<th>Created By</th>");
		out.println("</tr>");
		out.println("</thead>");
		// begin table body
		out.println("<tbody>");

		// forEach list element
		for (Event event : events) {
			out.println("<tr>");
			out.println("<td>" + event.getName() + "</td>");
			out.println("<td>" + formatter.format(event.getEventDate()) + "</td>");
			out.println("<td>" + event.getCreatedBy() + "</td>");
			out.println("</tr>");
		}

		// close table
		out.println("</tbody>");
		out.println("</table>");
		out.println("</div>");
		out.println("</body></html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
