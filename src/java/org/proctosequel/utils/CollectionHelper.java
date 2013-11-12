/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proctosequel.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class CollectionHelper {
    
    public static List[] cartesianProduct(List left, List[] right){
        if(right.length == 0){
            return (List[]) left.toArray(new List[]{});
        }
        List result = new ArrayList();
        for(Object elementLeft : left){
               
                for(Object elementRight : right[0]){                   
                    List resultElement = new ArrayList();
                    if(elementLeft instanceof List){
                        resultElement = new ArrayList((List)elementLeft);
                    }else {
                        resultElement.add(elementLeft);
                    } 
                    resultElement.add(elementRight);
                   result.add(resultElement);               
                }
                
        }
        return cartesianProduct(result, Arrays.asList(right).subList(1, right.length).toArray(new List[]{}) );        
    }
    public static List[] cartesianProduct(List[] list){
        return cartesianProduct(list[0], Arrays.asList(list).subList(1, list.length).toArray(new List[]{}));
    }
    
    
    public static void main(String[] args){
        List[] result = cartesianProduct(new List[]{
            new ArrayList(){{
                add("m1");
                add("m2");
            }}, new ArrayList(){{
                add("f1");
                add("f2");
                add("f3");
                add("f4");
            }}   
            , new ArrayList(){{
                add("b1");
                add("b2");
                add("b3");
            }}  
        });
        System.out.println(Arrays.deepToString(result));
        System.out.println(result.length);
    }
    
}
