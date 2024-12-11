
package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO 
{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    //Operaciones CRUD
    /**
     * Listar empleados habilitados
     * @param mostrarTodo SI es TRUE muestra todos los usuarios y si es FALSE solo los habilitados con valor 1
     * @return Una lista de usuarios dependiendo el valor de "mostrarTodo"
     **/
    
    public Producto buscar (int id)
    {
        Producto p=new Producto();
        String sql="select * from producto where IdProducto="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setStock(rs.getInt(4));
                p.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
        }
        return p;
    }
    
    
    public int actualizarstock( int id , int stock)
    {
        String sql="update producto set stock=? where idproducto=?";
        try 
        {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1,stock);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
        catch (Exception e) 
        {
        }
        return r;
    }
    public List listar(boolean mostrarTodo){
        String sql="select * from producto";
        if(!mostrarTodo){
            sql+=" where estado = 1";
        }
        List<Producto>lista=new ArrayList<>();
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while (rs.next()) {
                Producto pro=new Producto();
                pro.setId(rs.getInt(1));
                pro.setNombres(rs.getString(2));
                pro.setPrecio(rs.getDouble(3));
                pro.setStock(rs.getInt(4));
                pro.setEstado(rs.getString(5));
                lista.add(pro);
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    // Metodo para mostrar todos los empleados
    public List listar(){
        return this.listar(false);
    }
    
    public int agregar(Producto pro){ 
        String sql="insert into producto (Nombres, Precio,Stock,Estado)values(?,?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, pro.getNombres());
            ps.setDouble(2, pro.getPrecio());
            ps.setInt(3, pro.getStock());
            ps.setString(4, pro.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
     public Producto listarId(int id){
        // agregar un IF que liste todo.
        Producto pro=new Producto();
        String sql="select * from producto where IdProducto="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                pro.setId(rs.getInt(1));
                pro.setNombres(rs.getString(2));
                pro.setPrecio(rs.getDouble(3));
                pro.setStock(rs.getInt(4));
                pro.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
        }
        return pro;
    }
     public int actualizar(Producto pro){
         
        String sql="update producto set  Nombres=?, Precio=? , Stock=? ,Estado=? where IdProducto=?";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, pro.getNombres());
            ps.setDouble(2, pro.getPrecio());
            ps.setInt(3, pro.getStock());
            ps.setString(4, pro.getEstado());
            ps.setInt(5, pro.getId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
     
    public void delete(int id){
        String sql="update producto set estado = 0 where IdProducto="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
