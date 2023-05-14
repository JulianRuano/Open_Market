/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.server.infra.tcpip;
import co.unicauca.openmarket.client.domain.Category;
import co.unicauca.openmarket.commons.infra.Protocol;
import co.unicauca.openmarket.domain.services.CategoryService;
import co.unicauca.strategyserver.infra.ServerHandler;
import co.unicauca.openmarket.commons.infra.JsonError;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;




/**
 *
 * @author brayan
 */
public class OpenMarketHandler extends ServerHandler {
    /**
     * Servicio de categoria
     */
    private static CategoryService service;
    
    public OpenMarketHandler() {
    }

    /**
     * Procesar la solicitud que proviene de la aplicación cliente
     *
     */
    @Override
    public String processRequest(String requestJson) {
        // Convertir la solicitud a objeto Protocol para poderlo procesar
        Gson gson = new Gson();  
        Protocol protocolRequest;
        protocolRequest = gson.fromJson(requestJson, Protocol.class);
        String response="";
        switch (protocolRequest.getResource()) {
            case "category" -> {
                if (protocolRequest.getAction().equals("get")) {
                    // Consultar una categoria
                    response = processGetCategory(protocolRequest);
                }

                if (protocolRequest.getAction().equals("post")) {
                    // Agregar una categoria    
                    response = processPostCategory(protocolRequest);

                }
                if (protocolRequest.getAction().equals("edit")){
                    // Editar
                    response = processEditCategory(protocolRequest);
                } 
                if(protocolRequest.getAction().equals("delete")){
                    response = processDeleteCategory(protocolRequest);
                }
                break;
            }
        }
        return response;
    }
    
    /**
     * Procesa la solicitud de consultar una categoria
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private String processGetCategory(Protocol protocolRequest) {
        // Extraer la cedula del primer parámetro
        Long id = Long.parseLong(protocolRequest.getParameters().get(0).getValue()) ;
        Category category = service.findById(id);
        if (category == null) {
            String errorJson = generateNotFoundErrorJson();
            return errorJson;
        } else {
            return objectToJSON(category);
        }
    }
    
    /**
     * Procesa la solicitud de agregar una categoria
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private String processPostCategory(Protocol protocolRequest) {
        Category category = new Category();
        // Reconstruir La categoria a partir de lo que viene en los parámetros
        category.setCategoryId(Long.parseLong(protocolRequest.getParameters().get(0).getValue()));
        category.setName(protocolRequest.getParameters().get(1).getValue());
        boolean response = getService().save(category);
        String respuesta=String.valueOf(response);
        return respuesta;
    }
    
    
    private String processEditCategory(Protocol protocolRequest){
       // Editar el name de la categoria
        Long id = Long.parseLong(protocolRequest.getParameters().get(0).getValue());
        String name = protocolRequest.getParameters().get(1).getValue();
        Category newCategory = new Category(id, name);
        boolean response = service.edit(id, newCategory);
        String respuesta=String.valueOf(response);
        return respuesta;
    }
    private String processDeleteCategory(Protocol protocolRequest){
       // Eliminar una categoria 
       Long id = Long.parseLong(protocolRequest.getParameters().get(0).getValue());
       boolean response = service.delete(id);
       String respuesta=String.valueOf(response);
       return respuesta;
    }
    
    
    /**
     * Genera un ErrorJson de cliente no encontrado
     *
     * @return error en formato json
     */
    private String generateNotFoundErrorJson() {
        List<JsonError> errors = new ArrayList<>();
        JsonError error = new JsonError();
        error.setCode("404");
        error.setError("NOT_FOUND");
        error.setMessage("Categoria no encontrada. ID no existe");
        errors.add(error);

        Gson gson = new Gson();
        String errorsJson = gson.toJson(errors);

        return errorsJson;
    }
    
     /**
     * @return the service
     */
    public CategoryService getService() {
        return service;
    }
    /**
     * @param service the service to set
     */
    public void setService(CategoryService service) {
        this.service = service;
    } 
    
   
}
