
package org.proctosequel.parsing.om.composite;

import java.util.Objects;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class AliasedData {
    
    private String alias;
    private String expr;

    public AliasedData(String alias, String expr) {
        this.alias = alias;
        this.expr = expr;
    }


    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @return the expr
     */
    public String getExpr() {
        return expr;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        if(! (obj.getClass() == this.getClass())){
            return false;
        }
        AliasedData other = (AliasedData)obj;
       return (alias == null ? other.alias == null : alias.equals(other.alias)) && (expr == null ? other.expr == null : expr.equals(other.expr));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.alias);
        hash = 37 * hash + Objects.hashCode(this.expr);
        return hash;
    }
    
    
        
}
