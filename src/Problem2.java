import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem2 {


    public static void main(String[] args) {
        //A string with multiple delimiters
        String str = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";

        //Specified the delimiters inside brackets, there is a
        //whitespace after # to consider space as delimiter as well
        String[] strArray = str.split("[:;]");
        Map<Integer,ArrayList<int[]>> data = new HashMap<Integer, ArrayList<int[]>>();
        int count=0;
        ArrayList<Integer> indices = new ArrayList<>();
        //test runs
        File input= new File("D:\\CodeOfAdvent\\src\\input2.txt");
        try {
            Scanner sc = new Scanner(input);
            while (sc.hasNextLine()) {
                pushData(data,sc.nextLine(),indices);
            }
         //   count=AllowedGames(data,12,13,14);

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(powerSet(data))
        AllowedGames(data)
        ;
//       for (int item:indices){
//           count+=item;
//       }
//        System.out.println(count);


        //System.out.println(strArray[1].indexOf("blue")-2);

//        for(String s: strArray){
//            System.out.println(s);
//        }
    }

    static int AllowedGames(Map<Integer, ArrayList<int[]>> data){
        int sum=0;
        for (Map.Entry<Integer, ArrayList<int[]>> entry :data.entrySet()) {
            int key=entry.getKey();

            ArrayList<int[]> temp=entry.getValue();
            boolean possibleGame= true;
            for (int[] item:temp) {
                System.out.print(key+" : "+item[0]+" "+item[1]+" "+item[2]);
                }
            System.out.print(":\n");
            }
        System.out.println(" ");

//            if(possibleGame){
//                System.out.println(key);
//                sum+=key;
//            }




        return sum;
    }
    static void pushData(Map<Integer, ArrayList<int[]>> data,String str,ArrayList<Integer> indices){
                String[] strArray = str.split("[:;]");
                int si=str.indexOf("Game")+5;
                int ti=si;

                while(Character.isDigit(str.charAt(ti+1))){
                    ti+=1;
                }
                int index =Integer.parseInt(str.substring(si,ti+1));
                //System.out.println(index);
                ArrayList<int[]> temp = new ArrayList<>();
                for(int i=1;i< strArray.length;i++){
                    int r=0,g=0,b=0;
                    if(strArray[i].contains("red")){
                        int sIndex=strArray[i].indexOf("red")-2;
                        int tIndex=sIndex;

                        while(Character.isDigit(strArray[i].charAt(tIndex-1))){
                            tIndex-=1;
                        }
                        r=Integer.parseInt(strArray[i].substring(tIndex,sIndex+1));
                    }
                    if(strArray[i].contains("blue")){
                        int sIndex=strArray[i].indexOf("blue")-2;
                        int tIndex=sIndex;

                        while(Character.isDigit(strArray[i].charAt(tIndex-1))){
                            tIndex-=1;
                        }
                        b=Integer.parseInt(strArray[i].substring(tIndex,sIndex+1));

                    }
                    if(strArray[i].contains("green")){
                        int sIndex=strArray[i].indexOf("green")-2;
                        int tIndex=sIndex;

                        while(Character.isDigit(strArray[i].charAt(tIndex-1))){
                            tIndex-=1;
                        }
                        g=Integer.parseInt(strArray[i].substring(tIndex,sIndex+1));

                    }
                    int[] arr={r,g,b};
                    temp.add(arr);


                }
                boolean flag=true;
        for (int[] item:temp
             ) {
            if(item[0]>12 || item[1]>13 || item[2]>14){
                flag= false;
            }
        }
        if(flag){
            indices.add(index);
        }



        data.put(index,temp);



    }

    static  int powerSet(Map<Integer, ArrayList<int[]>> data){
        int sum=0;
        for (Map.Entry<Integer, ArrayList<int[]>> entry :data.entrySet()) {
            int key=entry.getKey();
            ArrayList<int[]> val=entry.getValue();
            int r=0,g=0,b=0;
            for(int[] item:val){
               // System.out.print(r+" "+g+" "+b);
                if(item[0]>r){
                    r=item[0];
                }
                if(item[1]>g){
                    g=item[1];
                }
                if(item[2]>b){
                    b=item[2];
                }
            }
            sum+=r*g*b;

        }
        return  sum;


}}
