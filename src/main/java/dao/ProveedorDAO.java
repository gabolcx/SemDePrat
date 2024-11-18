package dao;

import models.Proveedor;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    public List<Proveedor> obtenerTodosLosProveedores() {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedores";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Proveedor proveedor = new Proveedor(
                        rs.getString("cuit_proveedor"),
                        rs.getString("nombre_proveedor"),
                        rs.getString("categoria"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
                proveedores.add(proveedor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores
        }

        return proveedores;
    }

    public List<Proveedor> buscarProveedores(String cuit, String nombre, String categoria) {
        List<Proveedor> proveedores = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM proveedores WHERE 1=1");

        if (!cuit.isEmpty()) {
            sql.append(" AND cuit_proveedor LIKE ?");
        }
        if (!nombre.isEmpty()) {
            sql.append(" AND nombre_proveedor LIKE ?");
        }
        if (!categoria.isEmpty()) {
            sql.append(" AND categoria LIKE ?");
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int index = 1;
            if (!cuit.isEmpty()) {
                stmt.setString(index++, "%" + cuit + "%");
            }
            if (!nombre.isEmpty()) {
                stmt.setString(index++, "%" + nombre + "%");
            }
            if (!categoria.isEmpty()) {
                stmt.setString(index++, "%" + categoria + "%");
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Proveedor proveedor = new Proveedor(
                        rs.getString("cuit_proveedor"),
                        rs.getString("nombre_proveedor"),
                        rs.getString("categoria"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
                proveedores.add(proveedor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores
        }

        return proveedores;
    }

    public void insertarProveedor(Proveedor proveedor) {
        String sql = "INSERT INTO proveedores (cuit_proveedor, nombre_proveedor, categoria, direccion, telefono, email) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, proveedor.getCuitProveedor());
            stmt.setString(2, proveedor.getNombreProveedor());
            stmt.setString(3, proveedor.getCategoria());
            stmt.setString(4, proveedor.getDireccion());
            stmt.setString(5, proveedor.getTelefono());
            stmt.setString(6, proveedor.getEmail());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores
        }
    }

    public void actualizarProveedor(Proveedor proveedor) {
        String sql = "UPDATE proveedores SET nombre_proveedor = ?, categoria = ?, direccion = ?, telefono = ?, email = ? WHERE cuit_proveedor = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, proveedor.getNombreProveedor());
            stmt.setString(2, proveedor.getCategoria());
            stmt.setString(3, proveedor.getDireccion());
            stmt.setString(4, proveedor.getTelefono());
            stmt.setString(5, proveedor.getEmail());
            stmt.setString(6, proveedor.getCuitProveedor());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores
        }
    }

    public void eliminarProveedor(String cuitProveedor) {
        String sql = "DELETE FROM proveedores WHERE cuit_proveedor = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cuitProveedor);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores
        }
    }
}
