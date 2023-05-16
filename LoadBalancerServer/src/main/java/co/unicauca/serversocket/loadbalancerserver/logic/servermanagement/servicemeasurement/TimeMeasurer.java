
package co.unicauca.serversocket.loadbalancerserver.logic.servermanagement.servicemeasurement;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author julian ruano
 */
public class TimeMeasurer {
    
    private List<Long> tiempos;
   
    public TimeMeasurer(){  
        this.tiempos = new ArrayList<>();
    }
    
    public void addTime(Long time){
        this.tiempos.add(time);       
    }
    
    public long getAverageResponse() {
        long Total = 0;
        
        for (long valor : tiempos) {
            Total += valor;
        }
        
        return (Long) (Total / tiempos.size()); 
    }
    
}

