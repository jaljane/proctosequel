package org.proctosequel.test;

import org.junit.Test;
import org.proctosequel.main.db.MetadataCache;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class MetadataTest extends BaseTest{
    
    public MetadataTest() {
    }
    
    @Test
    public void getMetadataCache(){
        System.out.println(MetadataCache.getInstance().getSchemaTableMapping());
        System.out.println(MetadataCache.getInstance().getTableColumnsMapping());
        System.out.println(MetadataCache.getInstance().getTableDbColumnsMapping());
    }

}