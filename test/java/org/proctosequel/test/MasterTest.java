
package org.proctosequel.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Jamel
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    ProcToSequelParseTest.class,
    MetadataTest.class,
})
public class MasterTest extends BaseTest{
    
    public MasterTest() {
    }

}