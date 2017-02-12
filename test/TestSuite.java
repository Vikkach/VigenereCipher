/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Виктория
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({JUnitTest.class})
public class TestSuite {

    public static void listOfTests() {
        JUnitTest jUnitTest = new JUnitTest();
        jUnitTest.sliceStringTest();
        jUnitTest.caesarCipher();
        jUnitTest.breakVigenereTest();
    }   
    
}
