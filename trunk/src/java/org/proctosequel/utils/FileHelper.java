
package org.proctosequel.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class FileHelper {
    
    public static String getContent(URL file) throws IOException{
        try(InputStream in = file.openStream()){
            return getContent(in);        
        }        
    }
    
    public static String getContent(File file) throws IOException{
        try(InputStream in = new FileInputStream(file)){
            return getContent(in);        
        }
        
    }

    public static String getContent(InputStream in) throws IOException{        
        StringBuilder sb = new StringBuilder();
        try(InputStreamReader reader = new InputStreamReader(in, "UTF-8")){
            int len;
            char[] buffer = new char[2*1024];
            while((len = reader.read(buffer))>=0 ){
                sb.append(buffer, 0,len);
            }
            reader.close();
            in.close();
            return sb.toString();            
        }
    }
    
}
