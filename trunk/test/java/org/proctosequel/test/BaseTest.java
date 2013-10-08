/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proctosequel.test;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
import org.junit.Before;
import org.proctosequel.config.ProctosequelPropertiesConfigurator;
import tyrex.tm.RuntimeContext;

/**
 *
 * @author Jamel
 */
public class BaseTest{
    
    public static Logger log = Logger.getLogger(BaseTest.class);
    
    private Connection connection ;
    

    
    public BaseTest() {
    }
    
    @Before
    public void setUp() {
        try{
            RuntimeContext runCtx = RuntimeContext.getRuntimeContext();
            Context jndiCtx = runCtx.getEnvContext();
            jndiCtx = jndiCtx.createSubcontext( "comp" );
            jndiCtx = jndiCtx.createSubcontext( "env" );
            jndiCtx = jndiCtx.createSubcontext( "jdbc" );
            oracle.jdbc.pool.OracleDataSource dataSource = new OracleConnectionPoolDataSource();
            dataSource.setDataSourceName("oraclehr");
            dataSource.setURL("jdbc:oracle:thin:@localhost:1521:XE");
            dataSource.setUser("hr");
            dataSource.setPassword("hr");            
            jndiCtx.bind( "oraclehr", dataSource );
            ProctosequelPropertiesConfigurator.configure("/org/proctosequel/proctosequel.properties");
            DOMConfigurator.configure(getClass().getResource("/log4j.xml") );
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }           
    }
    
    @After
    public void tearDown() {
        try {
            if(connection!=null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
                    
        }
    }

}