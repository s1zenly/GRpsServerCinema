package ru.hse.software.cinemaworld;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class GRpcServerApplication
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public GRpcServerApplication(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( GRpcServerApplication.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
