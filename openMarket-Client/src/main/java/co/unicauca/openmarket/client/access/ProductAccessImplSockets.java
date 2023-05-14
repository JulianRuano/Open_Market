package co.unicauca.openmarket.client.access;


import co.unicauca.openmarket.client.domain.Product;
import co.unicauca.openmarket.client.infra.OpenMarketSocket;
import co.unicauca.openmarket.commons.infra.JsonError;
import co.unicauca.openmarket.commons.infra.Protocol;
import com.google.gson.Gson;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Es una implementación que tiene libertad de hacer una implementación del
 * contrato. Lo puede hacer con Sqlite, postgres, mysql, u otra tecnología
 *
 * @author brayan majin, julian ruano
 */
public class ProductAccessImplSockets implements IProductAccess {

    private OpenMarketSocket mySocket;

    public ProductAccessImplSockets() {
       mySocket=new OpenMarketSocket();
    }

    @Override
    public boolean save(Product newProduct)throws Exception {
        boolean bandera=false;
        String jsonResponse = null;
        String requestJson = doSaveProductRequestJson(newProduct);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
         if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {

            if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Agregó correctamente, devuelve la cedula del customer 
                //return customer.getId();
                bandera=true;
            }

        }
         return bandera;
    }

    @Override
    public boolean edit(Product producto)throws Exception {
          boolean bandera=false;
        String jsonResponse = null;
        String requestJson = doEditproductRequestJson(producto);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
         if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse+"aqi estoy");
                throw new Exception(extractMessages(jsonResponse));
               
            } else {
                //Encontró el customer
                
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ("+producto.getName());
                bandera=true;
            }
        }
        return bandera;
    }

    @Override
    public boolean delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public Product findById(Long id)throws Exception {
        String jsonResponse = null;
        String requestJson = doFindProductIdRequestJson(id);
        System.out.println(requestJson);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
           
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
             //return null;
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
               throw new Exception(extractMessages(jsonResponse));
               //return null;
            } else {
                //Encontró el category
                Product product = jsonToProduct(jsonResponse);
                Logger.getLogger(CategoryAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ("+jsonResponse.toString()+ ")");
                return product;
            }
        } 
        
        
    }

    @Override
    public List<Product> findByName(String pname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> findByCategory(String categoryName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Crea la solicitud json de creación de la Product para ser enviado por el
     * socket
     *
     * @param Poduct objeto newProduct
     * @return devulve algo como:
     * {"resource":"product,"action":"post","parameters":[{"name":"id","value":"1"},{"name":"name","value":"lacteos"},...}]}
     */
    private String doSaveProductRequestJson(Product newProduct) {
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("post");
        protocol.addParameter("productId",newProduct.getProductId().toString());
        protocol.addParameter("name",newProduct.getName());
        protocol.addParameter("description", newProduct.getDescription());
        protocol.addParameter("CategoryId", newProduct.getCategoryId().toString());

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    
    /**
    * Convierte jsonProduct, proveniente del server socket, de json a un
    * objeto product
    *
    * @param jsonProduct objeto cliente en formato json
    */
    private Product jsonToProduct(String jsonProduct) {

        Gson gson = new Gson();
        Product product = gson.fromJson(jsonProduct, Product.class);
        return product;

    }
       
    
    private String extractMessages(String jsonResponse) {
        JsonError[] errors = jsonToErrors(jsonResponse);
        String msjs = "";
        for (JsonError error : errors) {
            msjs += error.getMessage();
        }
        return msjs;
    }

    /**
     * Convierte el jsonError a un array de objetos jsonError
     *
     * @param jsonError
     * @return objeto MyError
     */
    private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }
    /**
     * Crea una solicitud json para ser enviada por el socket
     * @param productId identificación del producto
     * @return solicitud de consulta del producto en formato Json, algo como:
     * {"resource":"product","action":"get","parameters":[{"name":"id","value":""}]}
     */
    private String doFindProductIdRequestJson(Long id) {
       Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("get");
        protocol.addParameter("productId",id.toString());

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }
    /**
     * Crea una solicitud json para ser enviada por el socket
     *
     *
     * @param producto un producto con los siguentes atributos:id, nombre, decripcion, idCategoria
     * @return solicitud de consulta del producto para editatr en formato Json, algo como:
     * {"resource":"product","action":"edit","parameters":[{"productId":"id","value":"1"}]}
     */
    private String doEditproductRequestJson(Product producto) {
        Protocol protocol =new Protocol();
        protocol.setResource("product");
        protocol.setAction("edit");
        protocol.addParameter("productId", producto.getProductId().toString());
        protocol.addParameter("name", producto.getName());
        protocol.addParameter("description", producto.getDescription());
        protocol.addParameter("categorId", producto.getCategoryId().toString());
        
        Gson gson=new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson; 
    }

    
}
