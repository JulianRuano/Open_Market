
package co.edu.unicauca.openmarket.client.access;

import co.edu.unicauca.openmarket.client.domain.Category;
import co.edu.unicauca.openmarket.client.domain.Product;
import java.util.List;


/**
 *
 * @author Libardo, Julio
 */
public interface IProductRepository {
    boolean save(Product newProduct,Long categoryId);
    
    boolean edit(Long id, Product product, Long categoryId);
    
    boolean delete(Long id);

    Product findById(Long id);
    
   List<Product> findByName(String pname);
    List<Product> findByCategory(String categoryName);
    List<Product> findAll();
    

}
