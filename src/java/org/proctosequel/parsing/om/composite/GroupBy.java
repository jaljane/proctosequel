package org.proctosequel.parsing.om.composite;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class GroupBy {
    
    private List<String> exprs = new ArrayList<>();
    private String havingexpr;

    public GroupBy() {
    }

    /**
     * @return the exprs
     */
    public List<String> getExprs() {
        return exprs;
    }

    /**
     * @return the havingexpr
     */
    public String getHavingexpr() {
        return havingexpr;
    }

    /**
     * @param havingexpr the havingexpr to set
     */
    public void setHavingexpr(String havingexpr) {
        this.havingexpr = havingexpr;
    }
   
    public String getSQL (){
        String sql = exprs.get(0);
        for(int i=1;i<exprs.size();i++){
            sql+=", " + exprs.get(i);
        }
        sql+=(StringUtils.isEmpty(havingexpr)?"": " having " + havingexpr);
        return sql;
    }
    

}
