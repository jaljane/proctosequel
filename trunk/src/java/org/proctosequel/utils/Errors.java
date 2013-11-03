
package org.proctosequel.utils;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public interface Errors {
    String set_var_error_msg = "no (or wrong) sql statement in setvar instruction. please check eventual antlr error.";
    String set_var_unsupported_parenthesis_error_msg = "unsupported parenthesis grouping in setvar instruction";
    String bad_string_litteral_error_msg = "bad string litteral.";
    String unkown_varname = "unknown varname %s.";
    String bad_export_to_function_expr = "bad exportToFunction instruction.";
    String bad_sql_part_fragment="varname %s - bad or unsupported fragment %s";
    String bad_parenthesis_join_fragment="varname %s - unsupported parenthesis at join expression (unless nested select statement).";
    String bad_or_unsupported_join_fragment = "varname %s - bad or unsupported join fragment.";
    String bad_or_unsupported_eq_match = "varname %s - bad equal match.";
    String bad_or_unsupported_groupby = "varname %s - bad group by fragment.";
    String bad_or_unsupported_logic_condition = "varname %s - bad condition fragment.";
}
