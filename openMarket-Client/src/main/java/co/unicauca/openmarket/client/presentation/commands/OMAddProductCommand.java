package co.unicauca.openmarket.client.presentation.commands;

import co.unicauca.openmarket.client.domain.Product;
import co.unicauca.openmarket.client.domain.service.ProductService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julian ruano
 */
public class OMAddProductCommand extends OMCommand{
    private Product pP;
    private ProductService pS;
    boolean result=false;
    public OMAddProductCommand(Product pP, ProductService pS){
        this.pP = pP;
        this.pS = pS;
    }
    
    
    @Override
    public void make() {
        try {
            result = pS.saveProduct(pP.getProductId(), pP.getName(), pP.getDescription(),pP.getCategoryId());
        } catch (Exception ex) {
            Logger.getLogger(OMAddProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void unmake() {
        try {
            result = pS.deleteProduct(pP.getProductId());
        } catch (Exception ex) {
            Logger.getLogger(OMAddProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void redo() {
        
    }
    
    public boolean result(){
        return result;
    }
}
