package luisrrleal.com.classicmodelstarea.database.dao;

import javafx.collections.FXCollections;
import luisrrleal.com.classicmodelstarea.database.MySQLConnection;
import luisrrleal.com.classicmodelstarea.models.Client;
import luisrrleal.com.classicmodelstarea.models.Office;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class ClientsDao extends MySQLConnection{

    public Optional<Client> findById(int id) {
        return Optional.empty();
    }

    public List<Client> findAll() {
        String query = "select * from customers";
        return getList(query);
    }

    public boolean save(String customerNumber, String customerName, String contactLastName, String contactFirstName, String phone,
                        String addressLine1, String addressLine2, String city, String state, String postalCode, String country, String salesRepEmployeeNumber, String creditLimit ) throws SQLException {

        String query = "Insert into customers () values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, Integer.parseInt(customerNumber));
        ps.setString(2, customerName);
        ps.setString(3, contactLastName);
        ps.setString(4, contactFirstName);
        ps.setString(5, phone);
        ps.setString(6, addressLine1);
        ps.setString(7, addressLine2);
        ps.setString(8, city);
        ps.setString(9, state);
        ps.setString(10, postalCode);
        ps.setString(11, country);
        ps.setInt(12, Integer.parseInt(salesRepEmployeeNumber));
        ps.setDouble(13, Double.parseDouble(creditLimit));
        ps.execute();
        return true;
    }

    public boolean update(Client record) {
        return false;
    }

    public boolean delete(String customer_number) throws SQLException {
        String query = "DELETE FROM customers WHERE customerNumber="+customer_number+";";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.execute();
        return true;
    }

    public  List<Client> findTwentyBestClients(){
        String query = "select a.customerNumber, b.customerName, b.phone, b.addressLine1, b.addressLine2, " +
                "count(*) as numOrders from orders a " +
                "Inner Join customers b on a.customerNumber=b.customerNumber " +
                "group by customerNumber " +
                "order by count(*) desc limit 20;";

        List<Client> clientList = FXCollections.observableArrayList();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Client client = new Client();
                client.setCustomer_name(rs.getString("customerName"));
                client.setPhone(rs.getString("phone"));
                client.setAddress_line_1(rs.getString("addressLine1"));
                client.setAddress_line_2(rs.getString("addressLine2"));
                //MALA PRÁCTICA, PEDIR AYUDA DESPUÉS
                client.setCustomer_number(rs.getInt("numOrders"));
                clientList.add(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientList;
    }

    public List<Client> getList(String query){
        List<Client> clientList = FXCollections.observableArrayList();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Client client = new Client();
                client.setCustomer_number(rs.getInt("customerNumber"));
                client.setCustomer_name(rs.getString("customerName"));
                client.setContact_last_name(rs.getString("contactLastName"));
                client.setContact_first_name(rs.getString("contactFirstName"));
                client.setPhone(rs.getString("phone"));
                client.setAddress_line_1(rs.getString("addressLine1"));
                client.setAddress_line_2(rs.getString("addressLine2"));
                client.setCity(rs.getString("city"));
                client.setState(rs.getString("state"));
                client.setPostal_code(rs.getString("postalCode"));
                client.setCountry(rs.getString("country"));
                client.setSales_rep_employee_number(rs.getInt("salesRepEmployeeNumber"));
                client.setCredit_limit(rs.getDouble("creditLimit"));
                clientList.add(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientList;
    }
}
