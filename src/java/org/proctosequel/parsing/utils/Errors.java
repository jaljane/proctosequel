
package org.proctosequel.parsing.utils;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public interface Errors {
    String set_var_error_msg = "no (or wrong) sql statement in setvar instruction. please check eventual antlr error.";
    String set_var_superfluous_parenthesis_error_msg = "superfluous parenthesis grouping in setvar instruction";
    String bad_string_litteral_error_msg = "bad string litteral.";
    String unkown_varname = "unknown varname %s.";
    String bad_export_to_function_expr = "bad exportToFunction instruction.";
    String bad_sql_part_fragment="varname %s - bad or unsupported fragment %s";
}
