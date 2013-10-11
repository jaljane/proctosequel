/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.proctosequel.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.proctosequel.parsing.utils.Constants;

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
    public static String insertSpaces(List<String> list){
        if(list.isEmpty()){
            return "";
        }
        String result = list.get(0);
        for(int i=1;i<list.size();i++){
            result+=" " + list.get(i);
        }
        return result;
    }
    
    public static boolean containsWord(String str, String word){
        return StringUtils.split(str, Constants.DELIMITER_CHARS)!=null 
                && Arrays.asList(StringUtils.split(str, Constants.DELIMITER_CHARS)).contains(word) ;
        
    }
    
}
