package luisrrleal.com.classicmodelstarea.database.dao;

import javafx.collections.FXCollections;
import luisrrleal.com.classicmodelstarea.database.MySQLConnection;
import luisrrleal.com.classicmodelstarea.models.Product;

import java.sql.*;
import java.util.List;
import java.util.Optional;


public class ProductsDao extends MySQLConnection {
    Connection conn = getConnection();

    public List<Product> findAll() {
        List<Product> productsList = FXCollections.observableArrayList();
        String query = "select * from products";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_code(rs.getString("productCode"));
                product.setProduct_name(rs.getString("productName"));
                product.setProduct_line(rs.getString("productLine"));
                product.setProduct_scale(rs.getString("productScale"));
                product.setProduct_vendor(rs.getString("productVendor"));
                product.setProduct_description(rs.getString("productDescription"));
                product.setQuantity_in_stock(rs.getInt("quantityInStock"));
                product.setBuy_price(rs.getFloat("buyPrice"));
                product.setMsrp(rs.getFloat("MSRP"));
                productsList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productsList;
    }


    public boolean save(String productCode, String productName, String productLine, String productScale, String productVendor,
                        String productDescription, String quantityInStock, String buyPrice, String MSRP) throws SQLException {

        String query = "Insert into products () values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, productCode);
        ps.setString(2, productName);
        ps.setString(3, productLine);
        ps.setString(4, productScale);
        ps.setString(5, productVendor);
        ps.setString(6, productDescription);
        ps.setInt(7, Integer.parseInt(quantityInStock));
        ps.setDouble(8, Double.parseDouble(buyPrice));
        ps.setDouble(9, Double.parseDouble(MSRP));
        ps.execute();
        return true;
    }

    public boolean update(String productCode, String productName, String productLine, String productScale, String productVendor,
                          String productDescription, String quantityInStock, String buyPrice, String MSRP) throws SQLException {
        String query = "UPDATE products" +
                " SET productName= ? " +
                ", productLine= ?" +
                ", productScale= ? " +
                ", productVendor= ?" +
                ", productDescription= ?" +
                ", quantityInStock= ?" +
                ", buyPrice= ? " +
                ", MSRP= ?" +
                "WHERE productCode = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, productName);
        ps.setString(2, productLine);
        ps.setString(3, productScale);
        ps.setString(4, productVendor);
        ps.setString(5, productDescription);
        ps.setInt(6, Integer.parseInt(quantityInStock));
        ps.setDouble(7, Double.parseDouble(buyPrice));
        ps.setDouble(8, Double.parseDouble(MSRP));

        ps.setString(9, productCode);
        ps.execute();
        return true;
    }

    public boolean delete(String product_code) throws SQLException {
        String query = "delete from products where productCode = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1,product_code);
        ps.execute();
        return true;
    }
}

