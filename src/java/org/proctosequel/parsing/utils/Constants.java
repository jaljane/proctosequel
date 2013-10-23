
package org.proctosequel.parsing.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class Constants {
    public static String VAR_NAME_REGEX = "\\$[a-zA-Z0-9_\\.]+";
    public static String WORD_REGEX = "[a-zA-Z0-9_]+";
    public static String DELIMITER_CHARS = " \r\n\t,;().+-*/";
    public static Map<String, String> replaceTokenMaps = new HashMap<String, String>() {
        {
            put("exportquery", "exportQuery");
            put("exportresult", "exportResult");
            put("primarykey", "primaryKey");
        }
    };      
}