/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.duke.FileResource;
import java.util.HashSet;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import vigenerecipher.CaesarCipher;
import vigenerecipher.CaesarCracker;
import vigenerecipher.VigenereBreaker;

/**
 *
 * @author Виктория
 */
public class JUnitTest {
    
    public JUnitTest() {
    }
    @Test
    public void sliceStringTest(){
        VigenereBreaker breaker = new VigenereBreaker();
        String source = "abcdefghijklm";
        String[] answers = {"adgjm", "behk", "cfil", "aeim", "bfj", "cgk", "dhl", "afk", "bgl", "chm", "di", "ej"};
        int k = 0;
        for (int i = 0; i <=2; i++){
            String testResult = breaker.sliceString(source, i, 3);
            String fromArray = answers[k];
            assertEquals(testResult, fromArray);
            k++;
        }
        for (int i = 0; i <=3; i++){
            String testResult = breaker.sliceString(source, i, 4);
            String fromArray = answers[k];
            assertEquals(testResult, fromArray);
            k++;
        }
        for (int i = 0; i <=4; i++){
            String testResult = breaker.sliceString(source, i, 5);
            String fromArray = answers[k];
            assertEquals(testResult, fromArray);
            k++;
        }        
        
    }
    @Test
    public void caesarCipher(){
        FileResource fr = new FileResource("titus-small.txt");
        String source = fr.asString();
        int key = 2;
        CaesarCipher caesarCipher =  new CaesarCipher(key);
        String encrypted = caesarCipher.encrypt(source);
        CaesarCracker caesarCracker = new CaesarCracker();
        String decrypted = caesarCracker.decrypt(encrypted);
        int newKey = caesarCracker.getKey(encrypted);
        assertEquals(decrypted, source);
        assertEquals(newKey, key);
        
    }
    @Test
    public void tryKeyLengthTest(){
        FileResource fr = new FileResource("athens_keyflute.txt");
        String source = fr.asString();
        VigenereBreaker breaker = new VigenereBreaker();
        int klength = 5;
        char mostCommon = 'e';
        int[] resKeys = breaker.tryKeyLength(source, klength, mostCommon);
        int[] testKeys = {5, 11, 20, 19, 4};
        Assert.assertArrayEquals(resKeys, testKeys);
    }
    
    @Test
    public void countWordsTest(){
        FileResource fr = new FileResource("athens.txt");
        FileResource frDic = new FileResource("dictionaries/English");
        String source = fr.asString();
        VigenereBreaker breaker = new VigenereBreaker();
        HashSet<String> dictionary = breaker.readDictionary(frDic);
        int realWords = breaker.countWords(source, dictionary);
        assertEquals(376, realWords);        
    }
    @Test
    public void breakForLanguageTest(){
        FileResource frDecrypted = new FileResource("athens_keyflute.txt");
        String sourceDec = frDecrypted.asString();
        FileResource frEncrypted = new FileResource("athens.txt");
        String sourceEnc = frEncrypted.asString();
        FileResource frDic = new FileResource("dictionaries/English");
        VigenereBreaker breaker = new VigenereBreaker();
        HashSet<String> dictionary = breaker.readDictionary(frDic);
        String decryptedMessage = breaker.breakForLanguage(sourceDec, dictionary);
        assertEquals(sourceEnc, decryptedMessage);
    }

}
