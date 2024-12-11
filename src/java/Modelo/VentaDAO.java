package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
     public List listar(boolean mostrarTodo) {
        String sql = "select * from ventas";
        if (!mostrarTodo) {
            sql += " where estado = 1";
        }
        List<Venta> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Venta ve = new Venta();
                ve.setId(rs.getInt(1));
                ve.setIdcliente(rs.getInt(2));
                ve.setIdempleado(rs.getInt(3));
                ve.setNumserie(rs.getString(4));
                ve.setFecha(rs.getString(5));
                ve.setMonto(rs.getDouble(6));
                ve.setEstado(rs.getString(7));
                lista.add(ve);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    // Metodo para mostrar todos los empleados
    public List listar() {
        return this.listar(false);
    }
    public String GenerarSerie(){
        String numeroserie="";
        String sql="select max(NumeroSerie) from ventas";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                numeroserie=rs.getString(1);
            }
        } catch (Exception e) {
        }
        return numeroserie;
    }
    public String IdVentas(){
        String idventas="";
        String sql="select ifnull(max(IdVentas), 0) from ventas";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                idventas=rs.getString(1);
            }
        } catch (Exception e) {
        }
        return idventas;
    }
    public int guardarVenta(Venta ve){
        String sql;
        sql = "INSERT INTO ventas(IdCliente,IdEmpleado,NumeroSerie,FechaVentas,Monto,Estado)VALUES(?,?,?,?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, ve.getIdcliente());
            ps.setInt(2, ve.getIdempleado());
            ps.setString(3, ve.getNumserie());
            ps.setString(4, ve.getFecha());
            ps.setDouble(5, ve.getMonto());
            ps.setString(6, ve.getEstado());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }
    public int guardarDetalleventas(Venta venta){
        String sql="insert into detalle_ventas(IdVentas, IdProducto,Cantidad, PrecioVenta)values(?,?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, venta.getId());
            ps.setInt(2, venta.getIdproducto());
            ps.setInt(3, venta.getCantidad());
            ps.setDouble(4, venta.getPrecio());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
     public void delete(int id){
        String sql="update ventas set estado = 0 where IdVentas="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public List listarDetalle(boolean mostrarTodo) {
        String sql = "select * from detalle_ventas";
        if (!mostrarTodo) {
            sql += " where estado = 1";
        }
        List<Venta> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setItem(rs.getInt(1));
                venta.setId(rs.getInt(2));
                venta.setIdproducto(rs.getInt(3));
                venta.setCantidad(rs.getInt(4));
                venta.setPrecio(rs.getDouble(5));
                lista.add(venta);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    // Metodo para mostrar todos los empleados
    public List listarDetalle() {
        return this.listar(false);
    }
   
    
}
