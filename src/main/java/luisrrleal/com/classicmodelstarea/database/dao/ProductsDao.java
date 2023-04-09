package luisrrleal.com.classicmodelstarea.database.dao;

import javafx.collections.FXCollections;
import luisrrleal.com.classicmodelstarea.database.MySQLConnection;
import luisrrleal.com.classicmodelstarea.models.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;


public class ProductsDao extends MySQLConnection implements Dao<Product> {
    Connection conn = getConnection();

    @Override
    public Optional<Product> findById(int id) {
        return Optional.empty();
    }

    public List<Product> findAll(){
        List<Product> productsList = FXCollections.observableArrayList();
        String query = "select * from products";
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
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

    @Override
    public boolean save(Product record) {
        return false;
    }

    @Override
    public boolean update(Product record) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
