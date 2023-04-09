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
    public List<Office> findAll() {
        List<Office> officesList = FXCollections.observableArrayList();
        String query = "select * from offices";

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

        @Override
    public boolean save(Office record) {
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
}
