/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discrete_hw4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Co
 */
public class Discrete_Hw4_coder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        File input = new File("input1.txt");
        File input2 = new File("input2.txt");
        File deCrypted = new File("DecodedInput.txt");
        File crypted = new File("CryptedInput.txt");
        
        File dic = new File("HW4_dictionary.txt");

        Crypter cr = new Crypter();
        Decoder dc = new Decoder();

        cr.startCrypto(input2, crypted);
        dc.startDecode(crypted, dic, deCrypted);

    }

}
