package kz.aitu.myservice.repository;

import kz.aitu.myservice.config.DataBaseConfig;
import kz.aitu.myservice.entities.Bank;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BankRepository {


    public static List<Bank> getAll() {
        List<Bank> list = new ArrayList<>();
        String sql = "SELECT * FROM banks";
        try (Connection con = DataBaseConfig.getConnection();
              PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet r = stmt.executeQuery();
            while (r.next()) {
                list.add(new Bank(r.getInt("bank_id"), r.getString("name"), r.getString("country")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public static Bank getById(int id) {
        String sql = "SELECT * FROM banks WHERE bank_id = ?";
         try (Connection con = DataBaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet r = stmt.executeQuery();
            if (r.next()) {
                return new Bank(r.getInt("bank_id"), r.getString("name"), r.getString("country"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static void add(String name, String country) {
        String sql = "INSERT INTO banks (name, country) VALUES (?, ?)";
         try (Connection con = DataBaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, country);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void update(int id, String name, String country) {
        String sql = "UPDATE banks SET name = ?, country = ? WHERE bank_id = ?";
         try (Connection con = DataBaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, country);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void patch(int id, Map<String, Object> updates) {
        StringBuilder sql = new StringBuilder("UPDATE banks SET ");
        List<Object> params = new ArrayList<>();
        updates.forEach((key, value) -> {
            sql.append(key).append(" = ?, ");
            params.add(value);
        });
        sql.setLength(sql.length() - 2);
        sql.append(" WHERE bank_id = ?");
        params.add(id);
         try (Connection con = DataBaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void delete(int id) {
        String sql = "DELETE FROM banks WHERE bank_id = ?";
         try (Connection con = DataBaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}