/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.config;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class ProcToSequelConfigurator {
    
    public static void configure(String resourcePath) throws IOException{
        Properties properties = new Properties();
        properties.load(ProcToSequelConfigurator.class.getResourceAsStream(resourcePath));
        System.getProperties().put("proctosequel.datasource.naming", properties.get("datasource"));
        System.getProperties().put("proctosequel.schemas", properties.get("schemas"));               
    }
    
}
