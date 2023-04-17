package luisrrleal.com.classicmodelstarea.database.dao;

import javafx.collections.FXCollections;
import luisrrleal.com.classicmodelstarea.database.MySQLConnection;
import luisrrleal.com.classicmodelstarea.models.Office;
import luisrrleal.com.classicmodelstarea.models.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class OfficesDao extends MySQLConnection implements Dao<Office>{
    Connection conn = getConnection();

    @Override
    public Optional<Office> findById(int id) {
        return Optional.empty();
    }

    @Override
    public boolean existsItem(String id) {
        return false;
    }

    @Override
    public List<Office> findAll() {
        String query = "select * from offices";
        return getList(query);
    }

    public List<Office> getOfficesWithEmployees(){
        String query = "select a.officeCode, b.employeeNumber, b.firstName, b.lastName, b.extension, b.email, b.officeCode, b.reportsTo, b.jobTitle, " +
                "from offices a " +
                "Inner Join employees b on a.officeCode=b.officeCode";
        return getList(query);
    }

        @Override
    public boolean save(Office record) {
        return false;
    }

    @Override
    public boolean save(String productCode, String productName, String productLine, String productScale, String productVendor, String productDescription, String quantityInStock, String buyPrice, String MSRP) {
        return false;
    }

    @Override
    public boolean update(Office record) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    public List<Office> getList(String query){
        List<Office> officesList = FXCollections.observableArrayList();

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Office office = new Office();
                office.setOffice_code(rs.getString("officeCode"));
                office.setCity(rs.getString("city"));
                office.setPhone(rs.getString("phone"));
                office.setAddress_line_1(rs.getString("addressLine1"));
                office.setAddress_line_2(rs.getString("officeCode"));
                office.setState(rs.getString("state"));
                office.setCountry(rs.getString("country"));
                office.setPostal_code(rs.getString("postalCode"));
                office.setTerritory(rs.getString("territory"));
                officesList.add(office);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return officesList;
    }
}
