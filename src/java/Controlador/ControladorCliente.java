
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ControladorCliente extends HttpServlet {
    Cliente em=new Cliente();
    ClienteDAO  cdao=new ClienteDAO();
    int idc=0;
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        
        if (menu.equals("Cliente")) {
            switch (accion) {
                case "Listar":
                    List lista = cdao.listar();
                    request.setAttribute("clientes", lista);
                    break;
                    
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String dir = request.getParameter("txtDireccion");
                    String est = request.getParameter("txtEstado");
                   
                   
                    em.setDni(dni);
                    em.setNombres(nom);
                    em.setDireccion(dir);
                    em.setEstado(est);
                   
                    cdao.agregar(em);
                    request.getRequestDispatcher("ControladorCliente?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                    
                case "Editar":
                    idc=Integer.parseInt(request.getParameter("id"));
                    Cliente  c=cdao.listarId(idc);
                    request.setAttribute("cliente", c);
                    request.getRequestDispatcher("ControladorCliente?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                    
                case "Actualizar":
                    String dni2 = request.getParameter("txtDni");
                    String nom2 = request.getParameter("txtNombres");
                    String dir2 = request.getParameter("txtDireccion");
                    String est2 = request.getParameter("txtEstado");
               
                   
                    em.setDni(dni2);
                    em.setNombres(nom2);
                    em.setDireccion(dir2);
                    em.setEstado(est2);
                  
                    em.setId(idc);
                    cdao.actualizar(em);
                    
                    
                    request.getRequestDispatcher("ControladorCliente?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                    
                case "Delete":
                    idc=Integer.parseInt(request.getParameter("id"));
                    cdao.delete(idc);
                    request.getRequestDispatcher("ControladorCliente?menu=Cliente&accion=Listar").forward(request, response);
                    
                    break;
                default:
                    throw new AssertionError();
            }
            
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
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
