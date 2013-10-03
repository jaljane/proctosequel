/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proctosequel.query.om;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.tree.ParseTree;
import org.proctosequel.query.parsing.AddSpaceVisitor;

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
            result+="selectPart : " + visitor.getQuery() + "\n";
        }
        if(getFromPart()!=null){
            visitor.reset();
            visitor.visit(getFromPart());
            result+="fromPart : " + visitor.getQuery() +  "\n";            
        }
        if(getWherePart()!=null){
            visitor.reset();
            visitor.visit(getWherePart());
            result+="wherePart : " + visitor.getQuery() +  "\n";            
        }
        if(getGroupPart()!=null){
            visitor.reset();
            visitor.visit(getGroupPart());
            result+="groupByPart : " + visitor.getQuery()+  "\n";
        }

        return result; //To change body of generated methods, choose Tools | Templates.
    }

    
   
}
