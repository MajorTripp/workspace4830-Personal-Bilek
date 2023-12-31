import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.TaskBilek;
import util.Info;
import util.UtilDBBilek;

@WebServlet("/SimpleSearchHB")
public class SimpleSearchHB extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SimpleSearchHB() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("keyword").trim();

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Tasks";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f5f5f5\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");

      List<TaskBilek> listTasks = null;
      if (keyword != null && !keyword.isEmpty()) {
         listTasks = UtilDBBilek.listTasks(keyword);
      } else {
         listTasks = UtilDBBilek.listTasks();
      }
      display(listTasks, out);
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + viewWebName + ">View All Tasks</a> <br>");
      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Tasks</a> <br>");
      out.println("<a href=/" + projectName + "/" + insertWebName + ">Create Task</a> <br>");
      out.println("<a href=/" + projectName + "/" + deleteWebName + ">Delete Task</a> <br>");
      out.println("</body></html>");
   }

   void display(List<TaskBilek> listTasks, PrintWriter out) {
      for (TaskBilek taskBilek : listTasks) {
         System.out.println("[DBG] " + taskBilek.getId() + ", " //
               + taskBilek.getDeadline() + ", " //
               + taskBilek.getDescription() + ", " //
         	   + taskBilek.getAssignee()) ;

         out.println("<li>" + taskBilek.getId() + ", " //
               + taskBilek.getDeadline() + ", " //
               + taskBilek.getDescription() + ", " //
         	   + taskBilek.getAssignee() + "</li>");
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
