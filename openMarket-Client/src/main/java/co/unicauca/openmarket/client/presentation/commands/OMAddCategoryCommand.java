/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.client.presentation.commands;

import co.unicauca.openmarket.client.domain.Category;
import co.unicauca.openmarket.client.domain.service.CategoryService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julian ruano
 */
public class OMAddCategoryCommand extends OMCommand {
    
    private Category cC;
    private CategoryService cS;
    boolean result=false;
    public OMAddCategoryCommand(Category cC, CategoryService cS){
        this.cC = cC;
        this.cS = cS;
    }

    @Override
    public void make() {
        try {          
            result = cS.saveCategory(cC.getCategoryId(), cC.getName());
        } catch (Exception ex) {
            Logger.getLogger(OMAddCategoryCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void unmake() {
        result = cS.deleteCategory(cC.getCategoryId());
    }
    
    public boolean result(){
        return result;
    }
}
