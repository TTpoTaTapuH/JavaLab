import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/ServletAppl"})
public class Servlet1 extends HttpServlet {
static String ast;
static boolean b;
static long counter;
static int cycle;
String fio ="";
String group ="";
/**
* Processes requests for both HTTP <code>GET</code> and <code>POST</code>
* methods.*
* @param request servlet request
* @param response servlet response
* @throws ServletException if a servlet-specific error occurs
* @throws IOException if an I/O error occurs
*/
public Servlet1(){
Servlet1.ast = "a static var c=";
Servlet1.b = false;
Servlet1.counter = 0;
Servlet1.cycle = 0;
}
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
response.setContentType("text/html;charset=UTF-8");
try (PrintWriter out = response.getWriter()) {
/* TODO output your page here. You may use following sample code. */
out.println("<!DOCTYPE html>");
out.println("<html>");
out.println("<head>");
out.println("<title>"+fio+" гр"+group+"</title>");
out.println("</head>");
out.println("<body>");
out.println("<h1>ServletAppl" + request.getServletPath() + "</h1>");
//=============I
if (Servlet1.b) Servlet1.b = false;
else Servlet1.b = true;
Servlet1.counter++;
//if(Servlet1.cycle < 5){
Servlet1.cycle ++;
//}else{
//Servlet1.cycle = 1;
//}
out.println("<h3> Servlet1.ast + Servlet1.b :" + Servlet1.ast + Servlet1.b + "</h3>");
out.println("<h3> Servlet1.counter : " + Servlet1.counter + "</h3>");
out.println("<h3> Servlet1.cycle :" + Servlet1.cycle + "</h3>");
String[][] tbl = new String[3][3];
for(int i=0; i<3; i++){
for(int j=0; j<3; j++){
tbl[i][j] = Integer.toString(i+1) + "&" + Integer.toString(j+1);
}
}
String prm1 = request.getParameter("prm1");
String prm2 = request.getParameter("prm2");
String prm3 = request.getParameter("prm3");
fio = request.getParameter("fio");
group = request.getParameter("group");
String num = request.getParameter("num");
if((request.getParameter("prm1").equals("5"))){Servlet1.cycle = 1;}
if(Servlet1.cycle>6){out.println("<h1>Дальнейшее уменьшение невозможно!</h1>");}
else{
out.println("<h" + Servlet1.cycle + "><table>"+
"<tr>" + tbl[0][0] + "</tr></br>" + "<tr>prm1=" + prm1 + "</tr></br>" + "<tr>1.3</tr></br>" +
"<tr>prm2=" + prm2 + "</tr></br>" + "<tr>pr2</tr></br>" + "<tr>2.3</tr></br>" +
"<tr>prm3=" + prm3 + "</tr></br>" + "<tr>3.2</tr></br>" + "<tr>" + tbl[2][2] + "</tr></br>" +
"</table></h" + Servlet1.cycle + ">");
}
String[] numbers=num.split(",");
int list1=0;
        int list2=0;
        for(String x:numbers){
            if(Integer.parseInt(x)%2 == 0 || Integer.parseInt(x)<0){
                list1+=Integer.parseInt(x);
            }
            if(Integer.parseInt(x)%2 == 1 || Integer.parseInt(x)<0){
                list2+=Integer.parseInt(x);
            }
        }
        out.println("<h1>сумма чётных и отрицательных: "+list1+"</h1>");
        out.println("<h1>сумма нечётных и отрицательных: "+list2+"</h1>");
//=============II
out.println("</body>");
out.println("</html>");
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
processRequest(request, response);}
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