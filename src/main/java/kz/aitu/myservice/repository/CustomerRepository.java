package kz.aitu.myservice.repository;

import kz.aitu.myservice.entities.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerRepository {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "nurkhan2222";

    public static List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet r = stmt.executeQuery();
            while (r.next()) {
                list.add(new Customer(r.getInt("customer_id"), r.getString("name"), r.getString("address")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public static Customer getById(int id) {
        String sql = "SELECT * FROM customers WHERE customer_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet r = stmt.executeQuery();
            if (r.next()) {
                return new Customer(r.getInt("customer_id"), r.getString("name"), r.getString("address"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static void add(String name, String address) {
        String sql = "INSERT INTO customers (name, address) VALUES (?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void update(int id, String name, String address) {
        String sql = "UPDATE customers SET name = ?, address = ? WHERE customer_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void patch(int id, Map<String, Object> updates) {
        StringBuilder sql = new StringBuilder("UPDATE customers SET ");
        List<Object> params = new ArrayList<>();
        updates.forEach((key, value) -> {
            sql.append(key).append(" = ?, ");
            params.add(value);
        });
        sql.setLength(sql.length() - 2);
        sql.append(" WHERE customer_id = ?");
        params.add(id);
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void delete(int id) {
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}