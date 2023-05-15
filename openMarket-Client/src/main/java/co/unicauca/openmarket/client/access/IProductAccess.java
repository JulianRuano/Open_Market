
package co.unicauca.openmarket.client.access;


import co.unicauca.openmarket.client.domain.Product;
import java.util.List;


/**
 *
 * @author Libardo, Julio
 */
public interface IProductAccess {
    boolean save(Product newProduct)throws Exception;
    
    boolean edit(Product producto)throws Exception;
    
    boolean delete(Long id)throws Exception ;

    Product findById(Long id)throws Exception;
    
   List<Product> findByName(String pname)throws Exception;
    List<Product> findByCategory(Long categoryName)throws Exception;
    List<Product> findAll()throws Exception;
    

}
