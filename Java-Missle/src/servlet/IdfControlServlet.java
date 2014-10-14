package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import war.controller.WarUIEventsListener;
import main.Program;

@WebServlet(name = "IdfControlServlet", urlPatterns = { "/IdfControlServlet" })
public class IdfControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// interceptMissile button was clicked
		if (request.getParameter("interceptMissile") != null){
			String ironDomeId = request.getParameter("ironDomeId");
			String missileId = request.getParameter("missileId");
		       // do something			
			try {
				out.println("<html>");
				out.println("<h1>destroyLauncher button clicked</h1>");
				out.println("</html>");
//				for (WarUIEventsListener l : allListeners) {
//					l.addInterceptionToUI(missileId, ironDomeId);
//				}
			} finally {
				out.close();
			}
		 }
		
		
		// destroyLauncher button was clicked
		else if (request.getParameter("destroyLauncher") != null){
		       // do something
			try {
				out.println("<html>");
				out.println("<h1>destroyLauncher button clicked</h1>");
				out.println("</html>");
			} finally {
				out.close();
			}
		 }
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
