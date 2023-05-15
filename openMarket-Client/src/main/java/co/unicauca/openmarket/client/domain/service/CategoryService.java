
package co.unicauca.openmarket.client.domain.service;

import co.unicauca.openmarket.client.access.ICategoryAccess;
import co.unicauca.openmarket.commons.domain.Category;


import java.util.List;
import javax.swing.JOptionPane;
import reloj.frameworkobsobs.Observado;

/**
 *
 * @author brayan majin, julian ruano
 */
public class CategoryService extends Observado{
    
    
    public CategoryService(){
        
    }
    private ICategoryAccess repository;
    
    public CategoryService(ICategoryAccess repository){
        this.repository=repository;
    }
    public boolean saveCategory (Long id,String name)throws Exception{
        Category newCategory=new Category();
        newCategory.setCategoryId(id);
        newCategory.setName(name);
        if(newCategory.getName().isBlank()){
            return false;
        }
        
        boolean result=repository.save(newCategory);
        this.notificar();
        return result;
    }
    public boolean editCategory(Long categoryId,Category cat) {
        
        //Validate product
        if(cat==null || cat.getName().isBlank()){
            return false;
        }
      
       
        return repository.edit(categoryId,cat);
    }
    
   public boolean deleteCategory(Long id){
       boolean result =repository.delete(id);
       this.notificar();
        return result;
    }  
    public Category findCategoryById(Long id)throws Exception{
        return repository.findById(id);
    }
       public List<Category> findAllCategories(){
        return repository.findAll();
    }
       
       public List<Category> findCategoriesByName(String name)throws Exception{
        return repository.findByName(name);
    }
       /*
       public boolean clearCategories(){
           return repository.clearCategories();
       }*/
}  
        
