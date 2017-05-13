
package vigenerecipher;

import edu.duke.*;
import java.util.ArrayList;
import java.util.HashSet;

public class VigenereBreaker {
      
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String totalSlice = "";
        for (int i = whichSlice; i < message.length(); i = i+totalSlices){
            char newChar = message.charAt(i);
            totalSlice = totalSlice + newChar;
        }
        return totalSlice;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i = 0; i < klength; i++){
            String encryptedString = sliceString(encrypted, i, klength);
            CaesarCracker caesarCracker = new CaesarCracker(mostCommon);
            key[i]  = caesarCracker.getKey(encryptedString);
        }
        return key;
    }

    public void breakVigenere() {
        FileResource fr = new FileResource();
        String source = fr.asString();
        FileResource newFr = new FileResource();
        HashSet<String> dictionary = readDictionary(newFr);
        String decryptedMessage = breakForLanguage(source, dictionary);
        System.out.println(decryptedMessage);
        /*int klength = 4;
        char mostCommon = 'e';
        int[] keys = tryKeyLength(source, klength, mostCommon);
        VigenereCipher cipher = new VigenereCipher(keys);
        System.out.println(cipher.decrypt(source));  */ 
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> wordsList = new HashSet<>();
        for (String line : fr.lines()){
            wordsList.add(line.toLowerCase());
        }
        return wordsList;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        String[] array = message.toLowerCase().split("\\W+");
        int validWords = 0;
        for (int i = 0; i < array.length; i++){
            if (dictionary.contains(array[i])){
                validWords = validWords + 1;
            }
        }
        return validWords;        
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        String realDecrypted = "";
        int[] keys;
        int maxRealWords = 0;
        for (int i = 1; i <= 100; i++){
            keys = tryKeyLength(encrypted, i, 'e');
            VigenereCipher cipher = new VigenereCipher(keys);
            String decrypted = cipher.decrypt(encrypted);
            int realWords = countWords(decrypted, dictionary);
            if (realWords > maxRealWords){
                maxRealWords = realWords;
                realDecrypted = decrypted;
            }            
        }
        return realDecrypted;
    }
    
}
