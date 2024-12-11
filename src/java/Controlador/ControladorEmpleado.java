
package Controlador;

import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import java.io.IOException;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ControladorEmpleado extends HttpServlet {
    Empleado em=new Empleado();
    EmpleadoDAO  edao=new EmpleadoDAO();
    int ide=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("Empleado")) {
            switch (accion) {
                case "Listar":
                    List lista = edao.listar();
                    request.setAttribute("empleados", lista);
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTel");
                    String est = request.getParameter("txtEstado");
                    String user = request.getParameter("txtUser");
                   
                    em.setDni(dni);
                    em.setNombres(nom);
                    em.setTelefono(tel);
                    em.setEstado(est);
                    em.setUser(user);
                   
                    edao.agregar(em);
                    request.getRequestDispatcher("ControladorEmpleado?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    ide=Integer.parseInt(request.getParameter("id"));
                    Empleado  e=edao.listarId(ide);
                    request.setAttribute("empleado", e);
                    request.getRequestDispatcher("ControladorEmpleado?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                    
                case "Actualizar":
                    String dni2 = request.getParameter("txtDni");
                    String nom2 = request.getParameter("txtNombres");
                    String tel2 = request.getParameter("txtTel");
                    String est2 = request.getParameter("txtEstado");
                    String user2 = request.getParameter("txtUser");
                   
                    em.setDni(dni2);
                    em.setNombres(nom2);
                    em.setTelefono(tel2);
                    em.setEstado(est2);
                    em.setUser(user2);
                    em.setId(ide);
                    edao.actualizar(em);
                    
                    
                    request.getRequestDispatcher("ControladorEmpleado?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    ide=Integer.parseInt(request.getParameter("id"));
                    edao.delete(ide);
                    request.getRequestDispatcher("ControladorEmpleado?menu=Empleado&accion=Listar").forward(request, response);
                    
                    break;
                default:
                    throw new AssertionError();
            }
            
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }
        
        
        
      
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
