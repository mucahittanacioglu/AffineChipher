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
import java.util.Random;

/**
 *
 * @author Co
 */
public class Crypter {
    public void startCrypto (File inputFile,File outputFile)throws FileNotFoundException,IOException{
       
        String  inputStr ="",cryptedStr="";
        FileReader fr = new FileReader(inputFile);
        BufferedReader br= new BufferedReader(fr);
        
        int[] relativePrimes = {1,3,5,7,9,11,15,17,19,21,23,25};
        int [] constantB = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
        
        Random rand = new Random();

        int rnd1 = relativePrimes[rand.nextInt(relativePrimes.length-1)+0] ;
        int rnd2 = constantB[rand.nextInt(constantB.length-1)+0];
        
        FileWriter fw= new FileWriter(outputFile);
        BufferedWriter bw = new BufferedWriter(fw);
        
        while((inputStr=br.readLine())!=null){
            cryptedStr = start(inputStr,rnd1,rnd2);
            bw.write(cryptedStr);
            bw.newLine();
        }
        
        br.close();
       
        bw.close();
    }
  
    public String start (String input,int rnd1,int rnd2){
         
        
        
        char[] lowerCaseAlph = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] upperCaseAlph = "ABCDEFGHIJKLMNIPQESTUVWXYZ".toCharArray();
        
        int index;
        String cryptedStr="";
        
        
        
        for(int i = 0 ; i < input.length(); i++){
           
            if(getIndex(lowerCaseAlph ,input.charAt(i))!=-1){
               
                index =getIndex(lowerCaseAlph ,input.charAt(i));
                
                cryptedStr += lowerCaseAlph[crypto(index,rnd1,rnd2)];
                
            }
            else if(getIndex(upperCaseAlph ,input.charAt(i))!=-1){
                
                index =getIndex(upperCaseAlph ,input.charAt(i));
                
                cryptedStr += upperCaseAlph[crypto(index,rnd1,rnd2)];
            }
            else
                cryptedStr += input.charAt(i);
        }
         return cryptedStr;
         
        
    }
   
    
    
    public int getIndex(char[] alph,char a){
        for(int i = 0 ; i < alph.length; i++){
            if(a==alph[i]){
                return i;
            
            }
        }
        return -1;
        
    }
    public int crypto(int x,int a,int b){
        
        return (x*a+b)%26;
    }

    
}
