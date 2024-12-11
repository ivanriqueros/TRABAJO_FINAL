
package Controlador;

import Modelo.Producto;
import Modelo.ProductoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ControladorProducto extends HttpServlet {
    Producto pro=new Producto();
    ProductoDAO  pdao=new ProductoDAO();
    int idp=0;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("Producto")) {
            switch (accion) {
                case "Listar":
                    List lista = pdao.listar();
                    request.setAttribute("productos", lista);
                    break;
                case "Agregar":
                    String nom = request.getParameter("txtNombres");
                    double pre = Double.parseDouble(request.getParameter("txtPrecio"));
                    int stock = Integer.parseInt(request.getParameter("txtStock"));
                    String est = request.getParameter("txtEstado");
                   
                    pro.setNombres(nom);
                    pro.setPrecio(pre);
                    pro.setStock(stock);
                    pro.setEstado(est);
                    pdao.agregar(pro);
                    request.getRequestDispatcher("ControladorProducto?menu=Producto&accion=Listar").forward(request, response);
                    break;
                    
                case "Editar":
                    idp=Integer.parseInt(request.getParameter("id"));
                    Producto  p=pdao.listarId(idp);
                    request.setAttribute("producto", p);
                    request.getRequestDispatcher("ControladorProducto?menu=Producto&accion=Listar").forward(request, response);
                    break;
                    
                case "Actualizar":
                    String nom2 = request.getParameter("txtNombres");
                    double pre2 = Double.parseDouble(request.getParameter("txtPrecio"));
                    int stock2 = Integer.parseInt(request.getParameter("txtStock"));
                    String est2 = request.getParameter("txtEstado");
                   
                   
                    pro.setNombres(nom2);
                    pro.setPrecio(pre2);
                    pro.setStock(stock2);
                    pro.setEstado(est2);
                    pro.setId(idp);
                    pdao.actualizar(pro);
                    
                    request.getRequestDispatcher("ControladorProducto?menu=Producto&accion=Listar").forward(request, response);
                    break;
                    
                case "Delete":
                    idp=Integer.parseInt(request.getParameter("id"));
                    pdao.delete(idp);
                    request.getRequestDispatcher("ControladorProducto?menu=Producto&accion=Listar").forward(request, response);
                    
                    break;
                default:
                    throw new AssertionError();
            }
            
            request.getRequestDispatcher("Producto.jsp").forward(request, response);
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
