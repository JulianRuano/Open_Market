
package co.unicauca.openmarket.client.presentation.commands;

import co.unicauca.openmarket.commons.domain.Category;
import co.unicauca.openmarket.client.domain.service.CategoryService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julian ruano
 */
public class OMDeleteCategoryCommand extends OMCommand {
   
    private Long idCategory;
    private Category cC;
    private CategoryService cS;
    boolean result=false;
    public OMDeleteCategoryCommand(Long idCategory, CategoryService cS){
        this.idCategory = idCategory;
        this.cS = cS;
    }

    @Override
    public void make() {
        try {
            cC = cS.findCategoryById(idCategory);
            result = cS.deleteCategory(idCategory);
        } catch (Exception ex) {
            Logger.getLogger(OMAddCategoryCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void unmake() {
        try {
            result = cS.saveCategory(cC.getCategoryId(), cC.getName());
        } catch (Exception ex) {
            Logger.getLogger(OMDeleteCategoryCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean result(){
        return result;
    } 
}
