package model.data;

import model.entity.Product;

import java.util.List;

public interface IProductDAO {

    List<Product> findALL();
    boolean save(Product product);
    boolean update(Product product);
    boolean delete(Product product);
    boolean updateStatusById(Product product);
    List<Product> findALLByStatusActive();

}
