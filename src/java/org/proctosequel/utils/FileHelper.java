
package org.proctosequel.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class FileHelper {
    
    
    public static String getContent(File file) throws IOException{
        StringBuilder sb = new StringBuilder();
        InputStreamReader in = new InputStreamReader(new FileInputStream(file), "UTF-8");
        int len;
        char[] buffer = new char[2*1024];
        while((len = in.read(buffer))>=0 ){
            sb.append(buffer, 0,len);
        }
        return sb.toString();
    }
    
}
