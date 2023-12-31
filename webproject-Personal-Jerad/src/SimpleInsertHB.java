import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Info;
import util.UtilDBBilek;

@WebServlet("/SimpleInsertHB")
public class SimpleInsertHB extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SimpleInsertHB() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String deadline = request.getParameter("deadline").trim();
      String description = request.getParameter("description").trim();
      String assignee = request.getParameter("assignee").trim();
      UtilDBBilek.createTasks(deadline, description, assignee);

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f5f5f5\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      out.println("<li> Deadline: " + deadline);
      out.println("<li> Description: " + description);
      out.println("<li> Assignee: " + assignee);
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + viewWebName + ">View All Tasks</a> <br>");
      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Tasks</a> <br>");
      out.println("<a href=/" + projectName + "/" + insertWebName + ">Create Task</a> <br>");
      out.println("<a href=/" + projectName + "/" + deleteWebName + ">Delete Task</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
