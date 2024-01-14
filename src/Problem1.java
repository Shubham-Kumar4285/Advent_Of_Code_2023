import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.lang.*;

import static java.lang.constant.ConstantDescs.NULL;

public class Problem1 {
    static int sum=0,sum2=0;
    public static void main(String[] args) {
        Problem1 obj = new Problem1();

        String temp;
        Map<String, Integer> dict = new HashMap<String, Integer>();
        //Dictionary<String,Integer> dict=new Hashtable<>();
        dict.put("one",1);
        dict.put("two",2);
        dict.put("three",3);
        dict.put("four",4);
        dict.put("five",5);
        dict.put("six",6);
        dict.put("seven",7);
        dict.put("eight",8);
        dict.put("nine",9);
        File input= new File("D:\\CodeOfAdvent\\src\\input.txt");
        try {
            Scanner sc = new Scanner(input);
            while (sc.hasNextLine()) {
                temp=sc.nextLine();
                temp=fixString(temp,dict);
                temp=getNum(temp);


              //sum+=obj.newSol(temp,dict);
              sum2+=Integer.parseInt(temp);
            }

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("Daddy of Code of Advent : "+sum);
        System.out.println("GrandDaddy of Code of Advent  : "+sum2);

    }
    static String getNum(String str){
        String res="";
        char c;
        for(int i=0;i<str.length();i++){
            c=str.charAt(i);
            if(Character.isDigit(c)){
                res+=c;
                break;
            }

        }

        for(int i=str.length()-1;i>-1;i--){
            c=str.charAt(i);
            if(Character.isDigit(c)){
                res+=c;
                break;
            }

        }

       return res;

    }
    static String fixString(String str,Map<String,Integer> checkerList){
        String str2="";

        int i=0;
        while(i!=str.length()){

            boolean flag=false;
            for (Map.Entry<String, Integer> entry : checkerList.entrySet()) {
                String key = entry.getKey();
                int val = entry.getValue();
                if(str.charAt(i)==key.charAt(0)){

                    for(int k=i;k<str.length()+1;k++){
                        if(str.substring(i, k).equals(key)){


                            str2+=Integer.toString(val);
                            i=k-1;
                            flag=true;
                            break;
                        }
                    }
                }
            }
            if(flag){
                i++;
            }else{
                str2+=str.substring(i,i+1);
                i++;
            }
            //System.out.println(str2);
//            }
//            if(flag){
//                i=j;
//
//            }else{
//                j++;
//
//            }
//            if(j==str.length()){
//                str2
//            }

        }


        System.out.println(str2);
        return str2;
    }

    int newSol(String str,Map<String,Integer> checkerList){
        int r=0,l=0;
        boolean rflag=false,lflag=false;
        int i=0,j=str.length();

        while(i!=str.length() && j!=-1){
           // System.out.println("Start :"+i+" "+j);
            if(i<=str.length()-1 && !lflag){


            for (Map.Entry<String, Integer> entry : checkerList.entrySet()) {
                String key = entry.getKey();
                int val = entry.getValue();
                if(str.charAt(i)==key.charAt(0)){
                    System.out.println(str.charAt(i));

                    for(int k=i;k<str.length();k++){

                        if(str.substring(i, k+1).equals(key)){
                            //System.out.println(str.substring(i,k));
                            l=val;
                            lflag=true;
                            break;
                        }
                    }
                }
                if(lflag){
                    break;
                }
            }
            if (Character.isDigit(str.charAt(i)) && !lflag) {
                    l=Integer.parseInt(String.valueOf(str.charAt(i)));
                    lflag=true;


            }else{
                i++;
            }

            }

            if(j>=0 && !rflag){
            for (Map.Entry<String, Integer> entry : checkerList.entrySet()) {
                String key = entry.getKey();
                int val = entry.getValue();
                if(str.charAt(j-1)==key.charAt(key.length()-1)){
                    //System.out.println(key.charAt(key.length()-1));
                    for(int k=j-1;k>=0;k--){
                        //System.out.println(str.substring(k,j));
                        if(str.substring(k, j).equals(key)){
                            r=val;
                            rflag=true;
                            break;
                        }
                    }
                }
                if(rflag){
                    break;
                }
            }
            if (Character.isDigit(str.charAt(j-1)) && !rflag) {
                    r=Integer.parseInt(String.valueOf(str.charAt(j-1)));
                    rflag=true;


            }else{
                j--;
            }
            }
            if(rflag && lflag){
                break;
            }
            //System.out.println("End :"+i+" "+j);
        }

        return l*10+r;


    }
}
