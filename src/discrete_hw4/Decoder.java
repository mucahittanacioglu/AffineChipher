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
import java.util.Arrays;

/**
 *
 * @author Co
 */
public class Decoder {
    public void startDecode(File input,File dictonary,File output)throws FileNotFoundException,IOException{
        
        
        int a,b;
        String inputStr="",decodedStr="",tempStrng = "";
        int[]values = new int[2];
        FileReader fr = new FileReader(input);
        BufferedReader br= new BufferedReader(fr);
        
        
        
        FileWriter fw = new FileWriter(output);
        BufferedWriter bw = new BufferedWriter(fw);
        
        tempStrng = br.readLine();
        
        values=findAandB(tempStrng,dictonary);
        a = values[0];
        b=values[1];
        bw.write(start(tempStrng,a,b));
        bw.newLine();
        while((inputStr = br.readLine())!=null){
            decodedStr = start(inputStr,a,b);
            bw.write(decodedStr);
            bw.newLine();
        }
        br.close();
        bw.close();
        
    }
    public String start(String input,int a, int b)throws FileNotFoundException,IOException{
        
        
       
            
        
        
        
        
            return decodeAll(input,a,b);
        }
    
        public int getIndex(char[] alph,char a){
        for(int i = 0 ; i < alph.length; i++){
            if(a==alph[i]){
                return i;
            
            }
        }
        return -1;
        
    }
       public String decode(String input,char [] lowerCaseAlph, char[]upperCaseAlph,int a , int b){
          
          int index = 0;
          String decodedStr ="";
        
          for(int i = 0 ; i < input.length(); i++){
            
            if(getIndex(lowerCaseAlph ,input.charAt(i))!=-1){
               
                index =getIndex(lowerCaseAlph ,input.charAt(i));
                
                decodedStr += lowerCaseAlph[decodeIndex(index,a,b)];
               
            }
            else if(getIndex(upperCaseAlph ,input.charAt(i))!=-1){
                
                index =getIndex(upperCaseAlph ,input.charAt(i));
                
                decodedStr += upperCaseAlph[decodeIndex(index,a,b)];
            }
            else
                decodedStr += input.charAt(i);
        }
         return decodedStr;
           
           
       }
       public int decodeIndex(int x, int a , int b){
           while((x-b)%a!=0 || (x-b)<0)
               x+=26;
           return((x-b)/a)%26;
       }

    private boolean isInDicto(String decodedStr, File dicto) throws FileNotFoundException , IOException {
        String dicton = "";
        FileReader fr = new FileReader(dicto);
        BufferedReader br = new BufferedReader(fr);
        while((dicton = br.readLine())!=null)
            if(decodedStr.equalsIgnoreCase(dicton))
                return true;
        
        return false;
    }
        
    public String decodeAll(String input,int a,int b){
        int index = 0;
        char [] lowerCaseAlph = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char [] upperCaseAlph = "ABCDEFGHIJKLMNIPQESTUVWXYZ".toCharArray();
          String decodedStr ="";
        
          for(int i = 0 ; i < input.length(); i++){
            
            if(getIndex(lowerCaseAlph ,input.charAt(i))!=-1){
               
                index =getIndex(lowerCaseAlph ,input.charAt(i));
                
                decodedStr += lowerCaseAlph[decodeIndex(index,a,b)];
               
            }
            else if(getIndex(upperCaseAlph ,input.charAt(i))!=-1){
                
                index =getIndex(upperCaseAlph ,input.charAt(i));
                
                decodedStr += upperCaseAlph[decodeIndex(index,a,b)];
            }
            else
                decodedStr += input.charAt(i);
        }
         return decodedStr;
    }

    private int[] findAandB(String input,File dicto) throws FileNotFoundException,IOException {
        String [] words = input.split(" ");
        int [][] values = new int[words.length][2];
        int [] temp = new int [words.length];
        int temp2 [] = new int [2];
        int value;
        int[] relativePrimes = {1,3,5,7,9,11,15,17,19,21,23,25};
        int [] constantB = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
        
        char [] lowerCaseAlph = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char [] upperCaseAlph = "ABCDEFGHIJKLMNIPQESTUVWXYZ".toCharArray();
        String decodedStr ="";
        
        for(int i = 0 ; i < words.length  ; i++){
            for(int a = 0 ; a < relativePrimes.length ; a++){
               for(int b = 0 ; b < constantB.length ; b++){
                  
                  decodedStr = decode(words[i],lowerCaseAlph,upperCaseAlph,relativePrimes[a],constantB[b]);
                  
                  if(isInDicto(decodedStr,dicto)){
                    
                    values[i][0] = relativePrimes[a];
                    values[i][1]=constantB[b];
                    
                   
                  }
                     
               }
            
           }
            
        }
        
        for(int i = 0 ; i< values.length;i++){
            temp[i]=values[i][0];
        }
        value = getPopularElement(temp);
        
        for(int i = 0 ; i< values.length;i++){
            if(value==values[i][0]){
                temp2[0]=value;
                temp2[1]= values[i][1];
            }
        }
        return temp2;
    }
    
    public int getPopularElement(int[] a)
{
  int count = 1, tempCount;
  int popular = a[0];
  int temp = 0;
  outer: for (int i = 0; i < (a.length - 1); i++)
  {
    temp = a[i];
    tempCount = 0;
    for (int j = 1; j < a.length; j++)
    {
      if (temp == a[j])
        tempCount++;
    }
    if (tempCount > count)
    {
      popular = temp;
      count = tempCount;
    }
    if(popular>10)
        break outer;
    
  }
  return popular;
}
    
}
