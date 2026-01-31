package kz.aitu.myservice.repository;

import kz.aitu.myservice.config.DataBaseConfig;
import kz.aitu.myservice.entities.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountRepository {

    public static List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        try (Connection con = DataBaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet r = stmt.executeQuery();
            while (r.next()) {
                list.add(new CheckingAccount(
                        r.getString("account_number"),
                        r.getDouble("balance"),
                        r.getDouble("overdraft_limit"),
                        r.getInt("customer_id")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public static Account getById(int id) {
        String sql = "SELECT * FROM accounts WHERE account_id = ?";
         try (Connection con = DataBaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet r = stmt.executeQuery();
            if (r.next()) {
                return new CheckingAccount(
                        r.getString("account_number"),
                        r.getDouble("balance"),
                        r.getDouble("overdraft_limit"),
                        r.getInt("customer_id")
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static void add(String num, double bal, double lim, int cid) {
        String sql = "INSERT INTO accounts (account_number, balance, overdraft_limit, customer_id) VALUES (?, ?, ?, ?)";
         try (Connection con = DataBaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, num);
            stmt.setDouble(2, bal);
            stmt.setDouble(3, lim);
            stmt.setInt(4, cid);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void update(int id, String num, double bal, double lim) {
        String sql = "UPDATE accounts SET account_number = ?, balance = ?, overdraft_limit = ? WHERE account_id = ?";
         try (Connection con = DataBaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, num);
            stmt.setDouble(2, bal);
            stmt.setDouble(3, lim);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void patch(int id, Map<String, Object> updates) {
        StringBuilder sql = new StringBuilder("UPDATE accounts SET ");
        List<Object> params = new ArrayList<>();
        updates.forEach((key, value) -> {
            String column = key.equals("overdraftLimit") ? "overdraft_limit" :
                    key.equals("accountNumber") ? "account_number" : key;
            sql.append(column).append(" = ?, ");
            params.add(value);
        });
        sql.setLength(sql.length() - 2);
        sql.append(" WHERE account_id = ?");
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
        String sql = "DELETE FROM accounts WHERE account_id = ?";
         try (Connection con = DataBaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}