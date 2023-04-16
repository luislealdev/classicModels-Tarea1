package luisrrleal.com.classicmodelstarea.database.dao;

import javafx.collections.FXCollections;
import luisrrleal.com.classicmodelstarea.database.MySQLConnection;
import luisrrleal.com.classicmodelstarea.models.Client;
import luisrrleal.com.classicmodelstarea.models.Office;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class ClientsDao extends MySQLConnection implements Dao<Client>{

    @Override
    public Optional<Client> findById(int id) {
        return Optional.empty();
    }

    @Override
    public boolean existsItem(String id) {
        return false;
    }

    @Override
    public List<Client> findAll() {
        String query = "select * from customers";
        return getList(query);
    }

    @Override
    public boolean save(Client record) {
        return false;
    }

    @Override
    public boolean save(String productCode, String productName, String productLine, String productScale, String productVendor, String productDescription, String quantityInStock, String buyPrice, String MSRP) {
        return false;
    }

    @Override
    public boolean update(Client record) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
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
