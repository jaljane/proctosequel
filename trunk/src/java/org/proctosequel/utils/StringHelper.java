/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class StringHelper {

    
    public static String[] splitAndUpperCase(String str, String delimiter){
        String[] array = StringUtils.split(str, delimiter);
        List<String> buffer = new ArrayList<>();
        if(array!=null){
            List<String> list = Arrays.asList(array);
            for(String item : list){
                buffer.add(item.toUpperCase());
            }
            return buffer.toArray(new String[]{});
        }
        return null;
    }
    
    
}
