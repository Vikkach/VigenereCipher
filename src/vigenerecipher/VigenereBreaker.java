
package vigenerecipher;

import edu.duke.*;

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
        int klength = 4;
        char mostCommon = 'e';
        int[] keys = tryKeyLength(source, klength, mostCommon);
        VigenereCipher cipher = new VigenereCipher(keys);
        System.out.println(cipher.decrypt(source));   
    }
    
}
