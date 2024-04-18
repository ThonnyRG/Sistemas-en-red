package Servidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbManager {
    private Connection conexion;

    public DbManager() {
        String url = "jdbc:sqlite:D:\\Documents\\Sistemas en red\\Proyecto01Servidor\\db.sqlite";
        try {
            conexion = DriverManager.getConnection(url);
            System.out.println("Conexión a BD exitosa.");
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int eliminar(String tabla, Map<String, Object> where) {
        StringBuilder sql = new StringBuilder("DELETE FROM " + tabla);
        if (where != null && !where.isEmpty()) {
            sql.append(" WHERE ");
            int i = 0;
            for (String columna : where.keySet()) {
                Object dato = where.get(columna);
                if (dato instanceof Number || dato instanceof Boolean) {
                    sql.append(columna).append(" = ? ");
                } else {
                    sql.append(columna).append(" = ? ");
                }
                if (i < where.size() - 1) {
                    sql.append("AND ");
                }
                i++;
            }
        }
        
        return 0;
    }

    public int insertar(String tabla, Map<String, Object> datos) {
        StringBuilder sql = new StringBuilder("INSERT INTO " + tabla + " (");
        StringBuilder values = new StringBuilder(") VALUES (");
        for (String columna : datos.keySet()) {
            sql.append(columna).append(",");
            values.append("?,");
        }
        sql.deleteCharAt(sql.length() - 1).append(values.deleteCharAt(values.length() - 1)).append(")");
        try (PreparedStatement statement = conexion.prepareStatement(sql.toString())) {
            int index = 1;
            for (Object valor : datos.values()) {
                statement.setObject(index++, valor);
            }
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
            return -1; // Otra opción podría ser lanzar una excepción
        }
    }
    public int actualizar(String tabla, Map<String, Object> datos) {
        return insertar(tabla, datos); // Simplemente reutilizamos el método insertar, ya que un UPDATE es similar a un INSERT
    }
    public int actualizar(String tabla, Map<String, Object> datos, Map<String, Object> where) {
        StringBuilder sql = new StringBuilder("UPDATE " + tabla + " SET ");
        for (String columna : datos.keySet()) {
            sql.append(columna).append(" = ?,");
        }
        sql.deleteCharAt(sql.length() - 1).append(" WHERE ");
        for (String columna : where.keySet()) {
            sql.append(columna).append(" = ? AND ");
        }
        sql.delete(sql.length() - 5, sql.length()); // Eliminamos el último "AND"
        try (PreparedStatement statement = conexion.prepareStatement(sql.toString())) {
            int index = 1;
            for (Object valor : datos.values()) {
                statement.setObject(index++, valor);
            }
            for (Object valor : where.values()) {
                statement.setObject(index++, valor);
            }
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
            return -1; // Otra opción podría ser lanzar una excepción
        }
    }

    public int eliminar(String tabla) {
        String sql = "DELETE FROM " + tabla;
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
            return -1; // Otra opción podría ser lanzar una excepción
        }
    }

    public List<Map<String, Object>> listar(String tabla) {
        List<Map<String, Object>> registros = new ArrayList<>();
        String sql = "SELECT * FROM " + tabla;
        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> fila = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(i);
                    fila.put(columnName, value);
                }
                registros.add(fila);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registros;
    }

    public List<Map<String, Object>> listar(String tabla, Map<String, Object> where) {
        List<Map<String, Object>> registros = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM " + tabla);
        if (where != null && !where.isEmpty()) {
            sql.append(" WHERE ");
            for (String columna : where.keySet()) {
                sql.append(columna).append(" = ? AND ");
            }
            sql.delete(sql.length() - 5, sql.length()); // Eliminamos el último "AND"
        }
        try (PreparedStatement statement = conexion.prepareStatement(sql.toString())) {
            int index = 1;
            if (where != null) {
                for (Object valor : where.values()) {
                    statement.setObject(index++, valor);
                }
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                while (resultSet.next()) {
                    Map<String, Object> fila = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        Object value = resultSet.getObject(i);
                        fila.put(columnName, value);
                    }
                    registros.add(fila);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registros;
    }

    public Map<String, Object> buscarUno(String tabla, Map<String, Object> where) {
        List<Map<String, Object>> resultados = listar(tabla, where);
        if (!resultados.isEmpty()) {
            return resultados.get(0);
        }
        return null;
    }
}
