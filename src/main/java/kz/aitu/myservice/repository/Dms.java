package kz.aitu.myservice.repository;

import kz.aitu.myservice.entities.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dms {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "nurkhan2222";

    public static List<Bank> getAllBanks() {
        List<Bank> list = new ArrayList<>();
        String sql = "SELECT * FROM banks";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet r = stmt.executeQuery();
            while (r.next()) {
                list.add(new Bank(r.getInt("bank_id"), r.getString("name"), r.getString("country")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public static Bank getBankById(int id) {
        String sql = "SELECT * FROM banks WHERE bank_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet r = stmt.executeQuery();
            if (r.next()) {
                return new Bank(r.getInt("bank_id"), r.getString("name"), r.getString("country"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static void addBank(String name, String country) {
        String sql = "INSERT INTO banks (name, country) VALUES (?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, country);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void updateBank(int id, String name, String country) {
        String sql = "UPDATE banks SET name = ?, country = ? WHERE bank_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, country);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void deleteBank(int id) {
        String sql = "DELETE FROM banks WHERE bank_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet r = stmt.executeQuery();
            while (r.next()) {
                list.add(new Customer(
                        r.getInt("customer_id"),
                        r.getString("name"),
                        r.getString("address")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public static Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customers WHERE customer_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet r = stmt.executeQuery();
            if (r.next()) {
                return new Customer(
                        r.getInt("customer_id"),
                        r.getString("name"),
                        r.getString("address")
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static void addCustomer(String name, String address) {
        String sql = "INSERT INTO customers (name, address) VALUES (?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void updateCustomer(int id, String name, String address) {
        String sql = "UPDATE customers SET name = ?, address = ? WHERE customer_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void deleteCustomer(int id) {
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static List<Account> getAllAccounts() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
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

    public static Account getAccountById(int id) {
        String sql = "SELECT * FROM accounts WHERE account_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
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

    public static void addAccount(String number, double balance, double overdraftLimit, int customerId) {
        String sql = "INSERT INTO accounts (account_number, balance, overdraft_limit, customer_id) VALUES (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, number);
            stmt.setDouble(2, balance);
            stmt.setDouble(3, overdraftLimit);
            stmt.setInt(4, customerId);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void updateAccount(int id, String number, double balance, double overdraftLimit) {
        String sql = "UPDATE accounts SET account_number = ?, balance = ?, overdraft_limit = ? WHERE account_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, number);
            stmt.setDouble(2, balance);
            stmt.setDouble(3, overdraftLimit);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void deleteAccount(int id) {
        String sql = "DELETE FROM accounts WHERE account_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}