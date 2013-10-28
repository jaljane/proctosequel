
package org.proctosequel.parsing.om;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.tree.ParseTree;
import org.proctosequel.parsing.om.composite.Column;
import org.proctosequel.parsing.om.composite.Condition;
import org.proctosequel.parsing.om.composite.GroupBy;
import org.proctosequel.parsing.om.composite.TableJoinExpr;
import org.proctosequel.parsing.visitors.AddSpaceVisitor;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class Query {
    private String identifier;    
    private ParseTree selectPart;    
    private ParseTree fromPart;
    private ParseTree wherePart;
    private ParseTree groupPart;
    private List<Column> columns = new ArrayList<>();
    private List<TableJoinExpr> tableJoinExprs = new ArrayList<>();
    private List<Condition> conditions = new ArrayList<>();
    private GroupBy groupBy;
    private List<Query> dependsOn = new ArrayList<>();
    /**
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier the identifier to set
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }


    /**
     * @return the selectPart
     */
    public ParseTree getSelectPart() {
        return selectPart;
    }

    /**
     * @param selectPart the selectPart to set
     */
    public void setSelectPart(ParseTree selectPart) {
        this.selectPart = selectPart;
    }

    /**
     * @return the fromPart
     */
    public ParseTree getFromPart() {
        return fromPart;
    }

    /**
     * @param fromPart the fromPart to set
     */
    public void setFromPart(ParseTree fromPart) {
        this.fromPart = fromPart;
    }

    /**
     * @return the wherePart
     */
    public ParseTree getWherePart() {
        return wherePart;
    }

    /**
     * @param wherePart the wherePart to set
     */
    public void setWherePart(ParseTree wherePart) {
        this.wherePart = wherePart;
    }

    /**
     * @return the groupPart
     */
    public ParseTree getGroupPart() {
        return groupPart;
    }

    /**
     * @param groupPart the groupPart to set
     */
    public void setGroupPart(ParseTree groupPart) {
        this.groupPart = groupPart;
    }

    /**
     * @return the dependsOn
     */
    public List<Query> getDependsOn() {
        return dependsOn;
    }
    
    /**
     * @param dependsOn the dependsOn to set
     */
    public void setDependsOn(List<Query> dependsOn) {
        this.dependsOn = dependsOn;
    }
    
        @Override
    public String toString() {
        String result = "";
        AddSpaceVisitor visitor = new AddSpaceVisitor();
        if(getSelectPart()!=null){
            visitor.reset();
            visitor.visit(getSelectPart());
            result+="selectPart : " + visitor.getExpr() + "\n";
        }
        if(getFromPart()!=null){
            visitor.reset();
            visitor.visit(getFromPart());
            result+="fromPart : " + visitor.getExpr() +  "\n";            
        }
        if(getWherePart()!=null){
            visitor.reset();
            visitor.visit(getWherePart());
            result+="wherePart : " + visitor.getExpr() +  "\n";            
        }
        if(getGroupPart()!=null){
            visitor.reset();
            visitor.visit(getGroupPart());
            result+="groupByPart : " + visitor.getExpr()+  "\n";
        }

        return result; 
    }

    /**
     * @return the columns
     */
    public List<Column> getColumns() {
        return columns;
    }

    /**
     * @return the tableJoinExprs
     */
    public List<TableJoinExpr> getTableJoinExprs() {
        return tableJoinExprs;
    }

    /**
     * @return the conditions
     */
    public List<Condition> getConditions() {
        return conditions;
    }

    /**
     * @return the groupBy
     */
    public GroupBy getGroupBy() {
        return groupBy;
    }

    /**
     * @param groupBy the groupBy to set
     */
    public void setGroupBy(GroupBy groupBy) {
        this.groupBy = groupBy;
    }

    public String getSQL(){
        return "todo";
    }
    
   
}
