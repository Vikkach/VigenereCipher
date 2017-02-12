/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vigenerecipher;

import edu.duke.FileResource;

/**
 *
 * @author Виктория
 */
public class VigenereCipherMain {
    
    public static void main(String[] args) {
        FileResource fr = new FileResource("secretmessage1.txt");
        String source = fr.asString();
        CaesarCipher caesarCipher =  new CaesarCipher(2);
        //System.out.println(caesarCipher.encrypt(source));
        CaesarCracker caesarCracker = new CaesarCracker();
        //System.out.println(caesarCracker.decrypt(source));
        //System.out.println(caesarCracker.getKey(source));
        int[] rome = {17, 14, 12, 4}; 
        VigenereCipher cipher = new VigenereCipher(rome);
        //String decrypted = cipher.decrypt(source);
        //System.out.println(decrypted);
        //System.out.println(cipher.encrypt(decrypted)); 
        VigenereBreaker breaker = new VigenereBreaker();
        breaker.tryKeyLength(source, 4, 'e');
        breaker.breakVigenere();
    }
    
}
