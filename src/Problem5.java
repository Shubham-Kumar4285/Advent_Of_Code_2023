import jdk.jfr.Unsigned;
import org.w3c.dom.ranges.Range;

import java.io.File;
import java.time.temporal.ValueRange;
import java.util.*;

class myClass{
    ArrayList<Integer> seeds;
    ArrayList<Map<Integer,Integer>> collection;

}
public class Problem5 {
    public static void main(String[] args) {
        String temp = null;
        ArrayList<String[]> temp2=new ArrayList<>();

        File input= new File("D:\\CodeOfAdvent\\src\\input5.txt");
        ArrayList<customClass> collection = new ArrayList<>();

        try {
            Scanner sc = new Scanner(input);
            while (sc.hasNextLine()) {
                temp=sc.nextLine();
                String[] res=temp.split(" ");
                temp2.add(res);
            }

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        printData(temp2);



    }
    static void printData(ArrayList<String[]> temp ){
        ArrayList<Long> seed=new ArrayList<>();
        ArrayList<Map<Long,Long>> collection = new ArrayList<>();
        //seeds number fetching
        for (String[] item:temp
             ) {
            boolean flag = false;
            for (String item2:item
                 ) {
                if(item2.isBlank()){
                    flag=true;
                    break;
                }
//                System.out.println(item2);
                try {
                    Long num= Long.parseLong(item2);
                    seed.add(num);

                }catch (Exception e){
                    continue;
                }

            }
        if(flag){
            break;
        }}

        //System.out.println(seed);

        //maps fetching

//        for (int j=0;j<temp.size();j++
//             ) {
//            String[] item =temp.get(j);
//            boolean flag=false;
//            for (int i=0;i<item.length;i++) {
//
//                if(item[i].contains("map")){
//                    flag=true;
//                }
//            }
//            if(flag && j!= temp.size()-1){
//                System.out.println();
//                Map<Long,Long> tempMap= new HashMap<>();
//                for (int k=j+1;k<temp.size();k++
//                ) { if(temp.get(k).length==1){
//                    break;
//                }
////                    System.out.println(temp.get(k)[0]); -- destination
////                    System.out.println(temp.get(k)[1]); --source
////                    System.out.println(temp.get(k)[2]); --length
//
//                    try{
//                        Long source=Long.parseLong(temp.get(k)[1]);
//                        Long destination=Long.parseLong(temp.get(k)[0]);
//                        Long length=Long.parseLong(temp.get(k)[2]);
//                        while(length!=0){
//                            tempMap.put(source,destination);
//                            source++;
//                            destination++;
//                            length--;
//                        }
//
//                    }catch (Exception e){
//                        System.out.println("Error occurred at mapping ");
//                    }
//                    //System.out.println(" ");
//
//                }
//                collection.add(tempMap);
//
//
//            }
//
//
//        }
//

        //test for collection
//        for(Map<Integer,Integer> map:collection){
//            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//                System.out.println(entry.getKey()+" :"+entry.getValue());
//            }
//        }

        //final for minimum
        ArrayList<Long> res=new ArrayList<>();
        for (Long num:seed){
            Long target=num;
        for (int j=0;j<temp.size();j++
        ) {
            String[] item =temp.get(j);
            boolean flag=false;
            for (int i=0;i<item.length;i++) {

                if(item[i].contains("map")){
                    flag=true;
                }
            }
            if(flag && j!= temp.size()-1){
                for (int k=j+1;k<temp.size();k++
                ) {
                    if (temp.get(k).length == 1) {
                        break;
                    }
//                    System.out.println(temp.get(k)[0]); -- destination
//                    System.out.println(temp.get(k)[1]); --source
//                    System.out.println(temp.get(k)[2]); --length

                    try {
                        Long source = Long.parseLong(temp.get(k)[1]);
                        Long destination = Long.parseLong(temp.get(k)[0]);
                        Long length = Long.parseLong(temp.get(k)[2]);
//
                        if(target==source){
                            target=destination;
                            break;
                        }
                        if(target>source && target<source+length){
//                            while (length!=0){
//                                if(target.equals(source)){
//                                    target=destination;
//                                    break;
//                                }
//                                source++;
//                                destination++;
//                                length--;
//                            }
                            target=destination+(target-source);
                            break;
                        }
                      //  System.out.println(source+" : "+destination+" :"+length);

                    } catch (Exception e) {
                        System.out.println("Error occurred at mapping ");
                    }
                    //System.out.println(" ");

                }


            }


        }
        res.add(target);

        }
        //checking algo
      //  System.out.println(res);
        Long min=res.get(0);
       for (Long item:res){
           if(item<min){
               min=item;
           }
       }
        System.out.println("Part 1 : "+min);
//







    }

}
