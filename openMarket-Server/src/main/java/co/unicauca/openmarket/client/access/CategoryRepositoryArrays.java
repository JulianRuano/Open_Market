/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.client.domain.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author brayan majin, julian ruano
 */
public final class CategoryRepositoryArrays implements ICategoryRepository{
        private static List<Category> category;
    public CategoryRepositoryArrays() {
        if (category == null){
            category = new ArrayList();
        }
        
        if (category.isEmpty()){
           inicializar();
        }
    }
   
    public void inicializar() {

    }
    
    @Override
    public boolean save(Category newCategory) {
        category.add(newCategory);
        return true;
    }

    @Override
    public boolean edit(Long id, Category prmCategory) {
       for (int i = 0; i < category.size(); i++) {
            if (category.get(i).getCategoryId().equals(id)) {
                category.set(i, prmCategory);
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean delete(Long id) {
       for (int i = 0; i < category.size(); i++) {
            if (category.get(i).getCategoryId().equals(id)) {
                category.remove(i);
                return true;
            }
        }
        return false;
    }


    @Override
    public Category findById(Long id) {
       for (Category OCategory : category) {
            if (OCategory.getCategoryId().equals(id)) {
                return OCategory;
            }
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        if (category.isEmpty())
            return null;      
        return category;
    }

    @Override
    public List<Category> findByName(String name) {
    List<Category> filteredCategories = category.stream()
            .filter(c -> c.getName().contains(name))
            .collect(Collectors.toList());
    return filteredCategories.isEmpty() ? null : filteredCategories;
    }

    
}
