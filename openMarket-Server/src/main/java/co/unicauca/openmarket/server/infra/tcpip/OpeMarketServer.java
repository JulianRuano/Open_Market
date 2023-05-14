/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.server.infra.tcpip;
import co.unicauca.openmarket.client.access.CategoryRepository;
import co.unicauca.openmarket.client.access.CategoryRepositoryArrays;
import co.unicauca.openmarket.client.access.ProductRepositoryArrays;
import co.unicauca.openmarket.domain.services.CategoryService;
import co.unicauca.openmarket.domain.services.ProductService;
import co.unicauca.strategyserver.infra.ServerSocketMultiThread;
import java.util.Scanner;

/**
 *
 * @author brayan majin, julian ruano
 */
public class OpeMarketServer {
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el puerto de escucha");
        int port = teclado.nextInt();
        ServerSocketMultiThread myServer = new ServerSocketMultiThread(port);
        OpenMarketHandler myHandler = new OpenMarketHandler();
       myHandler.setService(new CategoryService(new CategoryRepositoryArrays()));
        myHandler.setServiceProduct(new ProductService(new ProductRepositoryArrays()));
        myServer.setServerHandler(myHandler);
        myServer.startServer();
    }
}
