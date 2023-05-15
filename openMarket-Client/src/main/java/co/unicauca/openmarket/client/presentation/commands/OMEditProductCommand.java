/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.client.presentation.commands;

import co.unicauca.openmarket.client.domain.Product;
import co.unicauca.openmarket.client.domain.service.ProductService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julian_Banano
 */
public class OMEditProductCommand extends OMCommand {
    private Product pP, backupProducto;
    private ProductService pS;
    boolean result=false;
    public OMEditProductCommand(Product pP, ProductService pS){
        this.pP = pP;
        this.pS = pS;       
    }
    
    
    @Override
    public void make() {
        try {
            backupProducto = pS.findProductById(pP.getProductId());
            result = pS.editProduct(pP.getProductId(), pP.getName(), pP.getDescription(),pP.getCategoryId());
        } catch (Exception ex) {
            Logger.getLogger(OMEditProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void unmake() {
        try {
            result = pS.editProduct(backupProducto.getProductId(), backupProducto.getName(), backupProducto.getDescription(),backupProducto.getCategoryId());
        } catch (Exception ex) {
            Logger.getLogger(OMEditProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean result(){
        return result;
    }
}
