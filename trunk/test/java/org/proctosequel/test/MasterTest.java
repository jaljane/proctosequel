
package org.proctosequel.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    ProctosequelParseTest.class,
    MetadataTest.class,
})
public class MasterTest extends BaseTest{
    
    public MasterTest() {
    }

}