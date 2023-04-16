package luisrrleal.com.classicmodelstarea.database.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> findById(int id);

    boolean existsItem(String id);

    List<T> findAll();
    boolean save(T record);

    boolean save(String productCode, String productName, String productLine, String productScale, String productVendor, String productDescription, String quantityInStock, String buyPrice, String MSRP);

    boolean update(T record);
    boolean delete (int id);
}
