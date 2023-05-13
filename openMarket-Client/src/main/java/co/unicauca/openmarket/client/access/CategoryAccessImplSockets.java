/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.client.access;


import co.unicauca.openmarket.client.domain.Category;
import co.unicauca.openmarket.client.infra.OpenMarketSocket;
import co.unicauca.openmarket.commons.infra.Protocol;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brayan
 */
public class CategoryAccessImplSockets implements ICategoryAccess {

     private OpenMarketSocket mySocket;
    
    //private Connection conn;

    @Override
    public boolean save(Category newCategory) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
     /**
     * Crea la solicitud json de creación del customer para ser enviado por el
     * socket
     *
     * @param customer objeto customer
     * @return devulve algo como:
     * {"resource":"customer","action":"post","parameters":[{"name":"id","value":"980000012"},{"name":"fistName","value":"Juan"},...}]}
     */
    private String doCreateCustomerRequestJson(Category category) {

        Protocol protocol = new Protocol();
        protocol.setResource("category");
        protocol.setAction("post");
        /*
        protocol.addParameter("id", customer.getId());
        protocol.addParameter("fistName", customer.getFirstName());
        protocol.addParameter("lastName", customer.getLastName());
        protocol.addParameter("address", customer.getAddress());
        protocol.addParameter("email", customer.getEmail());
        protocol.addParameter("gender", customer.getGender());
        protocol.addParameter("mobile", customer.getMobile());
    */
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    
    
    
    /**
    * Convierte jsonCategory, proveniente del server socket, de json a un
    * objeto Category
    *
    * @param jsonCustomer objeto cliente en formato json
    */
    private Category jsonToCategory(String jsonCustomer) {

        Gson gson = new Gson();
        Category customer = gson.fromJson(jsonCustomer, Category.class);
        return customer;

    }

    
}
