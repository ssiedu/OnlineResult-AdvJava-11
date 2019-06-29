import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResultProcessor extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out=response.getWriter();
        
        //reads-the-request
            String s=request.getParameter("rno");
        //process-the-request
            String sql="SELECT * FROM student where rno=?";
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/resultdata","root","root");
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(s));
                ResultSet rs=ps.executeQuery();
                boolean b=rs.next();
                if(b==true){
                String s1=rs.getString(1);//rno
                String s2=rs.getString(2);//name
                int m1=rs.getInt(3);
                int m2=rs.getInt(4);
                int m3=rs.getInt(5);
                int total=m1+m2+m3;
                
                out.println("<html><body><h3>Semester Result</h3>");
                out.println("<hr>");
                out.println("<table border=2>");
                out.println("<tr>");
                out.println("<td>RollNo</td>");
                out.println("<td>"+s1+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>Name</td>");
                out.println("<td>"+s2+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>Maths</td>");
                out.println("<td>"+m1+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>CompSc</td>");
                out.println("<td>"+m2+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>English</td>");
                out.println("<td>"+m3+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>Total</td>");
                out.println("<td>"+total+"</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<hr>");
                out.println("<a href=index.html>Home</a>");
                out.println("<marquee><h4>college will reopen on 15th July</h4></marquee>");
                out.println("</body>");
                out.println("</html>");
                }else{
                    out.println("Invalid Roll Number");
                }
                
                out.close();
                con.close();
                
             
            }catch(Exception e){
                out.println(e);
            }
        
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
