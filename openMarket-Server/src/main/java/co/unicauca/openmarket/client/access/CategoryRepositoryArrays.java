/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.client.domain.Category;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brayan
 */
public class CategoryRepositoryArrays implements ICategoryRepository{
        private static List<Category> category;
    public CategoryRepositoryArrays() {
        if (category == null){
            category = new ArrayList();
        }
        
        if (category.size() == 0){
           // inicializar();
        }
    }
     public void inicializar() {
        category.add(new Category(10L, "Andrea"));
        category.add(new Category(11L, "Libardo"));
        category.add(new Category(12L, "Carlos"));
        

    }
    
    @Override
    public boolean save(Category newCategory) {
        category.add(newCategory);
        return true;
    }

    @Override
    public boolean edit(Long id, Category category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
