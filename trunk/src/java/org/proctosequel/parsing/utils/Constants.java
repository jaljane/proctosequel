
package org.proctosequel.parsing.utils;

import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class Constants {
    public static String VAR_NAME_REGEX = "\\$[a-zA-Z0-9_\\.]+";
    public static String WORD_REGEX = "[a-zA-Z0-9_]+";
    public static String DELIMITER_CHARS = " \r\n\t,;().+-*/";
    public static String[] QUALIFIER_SEP_CHARS = new String[]{".", ":"} ;
    public static Map<String, String> replaceTokenMaps = new HashMap<String, String>() {
        {
            put("exportquery", "exportQuery");
            put("exportresult", "exportResult");
            put("primarykey", "primaryKey");
        }
    };  

}
