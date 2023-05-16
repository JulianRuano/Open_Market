/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author brayan
 */
public class ProductRepositoryArrays implements IProductRepository{
   
     private static List<Product> productos;

    public ProductRepositoryArrays() {
        if (productos== null){
            productos = new ArrayList();
        }
        if (productos.size() == 0){
           inicializar();
        }
    }
        
    public void inicializar(){

    }  
    @Override
    public boolean save(Product newProduct) {
       productos.add(newProduct);
       return true;
    }

    @Override
    public boolean edit(Product newProduct) {
       for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getProductId().equals(newProduct.getProductId())) {
                productos.set(i, newProduct);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
       for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getProductId().equals(id)) {
                productos.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Product findById(Long id) {
        for (Product OProduct : productos) {
            if (OProduct.getProductId().equals(id)) {
                return OProduct;
            }
        }
        return null;
    }

    @Override
    public List<Product> findByName(String pname) {
         List<Product> filteredProducts = productos.stream()
            .filter(c -> c.getName().contains(pname))
            .collect(Collectors.toList());
    return filteredProducts.isEmpty() ? null : filteredProducts;
    }
    
    @Override
    public List<Product> findByCategory(Long categoryId) {
        List<Product>listaProductos=new ArrayList<>();
       for(Product OProducto:productos){
          if (OProducto.getCategoryId().equals(categoryId)){
              listaProductos.add(OProducto);
          }
       }
      
       return listaProductos;
    }

    @Override
    public List<Product> findAll() {
        if (productos.isEmpty())
            return null;      
        return productos;
    }
    
}
