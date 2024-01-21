import jdk.jfr.Unsigned;

import java.io.File;
import java.time.temporal.ValueRange;
import org.apache.commons.lang3.Range;
import java.util.*;
import java.lang.Object;
class myClass{
    ArrayList<ArrayList<Long>> seeds;
    ArrayList<ArrayList<ArrayList<Long>>> mappings;
    ArrayList<ArrayList<Long>> resSeeds;
    public myClass(){
        seeds= new ArrayList<>();
        mappings=new ArrayList<>();
        resSeeds=new ArrayList<>();
    }

    public void result(){
        Long min=Long.MAX_VALUE;
        for (ArrayList<Long> item:seeds
             ) {
            if(item.get(0)<min){
                min=item.get(0);
            }
        }
        System.out.println(min);
    }



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
        myClass obj= new myClass();
        printData(temp2,obj);
//        Range<Integer> range = Range.between(100,200);
//        Range<Integer> range2 = Range.between(120,250);
//        System.out.println(range2.intersectionWith(range));

        //working--
        //System.out.println(obj.seeds);
        //System.out.println(obj.mappings);
        printData2(obj,0,0,obj.seeds.size());
        //System.out.println(obj.resSeeds);
        //System.out.println(obj.seeds);
        //System.out.println(obj.resSeeds);
        System.out.print("Part 2 : ");
        obj.result();




    }
    static void printData(ArrayList<String[]> temp ,myClass obj){
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

        //test for collection-for small dataset only
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
                     //System.out.println(source+" : "+destination+" :"+length);

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
        //System.out.println(seed);

        //part2:
        int kp=0;
        Map<Long,Long> seeds2= new HashMap<>();
        while(kp<seed.size()){
            if(kp%2==0){
                Long num=seed.get(kp);
                Long count=seed.get(kp+1);
                seeds2.put(num,count);

            }
            kp++;
        }

        ArrayList<Long> res2=new ArrayList<>();

        ArrayList<ArrayList<Long>> NewSeeds=new ArrayList<>();
        for (Map.Entry<Long,Long> entry : seeds2.entrySet()) {
            Long count =entry.getValue();
            Long start=entry.getKey();

                    ArrayList<Long> temp2=new ArrayList<>();
                    temp2.add(start);
                    temp2.add(start+count-1);
                    NewSeeds.add(temp2);



        }
        //System.out.println(NewSeeds);
       // System.out.println(res2);
        //[[86, 89], [60, 60], [96, 96], [77, 79], [20, 21], [58, 58], [67, 67], [63, 66], [85, 88], [68, 72], [93, 93]]



        obj.seeds=NewSeeds;

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
                ArrayList<ArrayList<Long>> tseeds= new ArrayList<>();
                for (int k=j+1;k<temp.size();k++
                ) {
                    if (temp.get(k).length == 1) {

                        break;
                    }

                    try {
                        Long source = Long.parseLong(temp.get(k)[1]);
                        Long destination = Long.parseLong(temp.get(k)[0]);
                        Long length = Long.parseLong(temp.get(k)[2]);

//
                        ArrayList<Long> temp2= new ArrayList<>();
                        temp2.add(source);
                        temp2.add(destination);
                        temp2.add(length);
                        tseeds.add(temp2);
                       // System.out.println(temp2);

                    } catch (Exception e) {
                        System.out.println("Error occurred at mapping ");
                    }

                }


                obj.mappings.add(tseeds);
            }



        }

    }
    static void printData2(myClass obj,int mappingIndex,int seedIndex,int count){
        if(seedIndex==obj.seeds.size()){
            return;
        }
        else {
            if(count==0){
                return;}
            Long lb = obj.seeds.get(seedIndex).get(0);
            Long ub = obj.seeds.get(seedIndex).get(1);
            for (int i = mappingIndex; i < obj.mappings.size(); i++) {

                for (ArrayList<Long> item : obj.mappings.get(i)
                ) {
                    Long source = item.get(0);
                    Long destination = item.get(1);
                    Long length = item.get(2);
                    Range<Long> mapping = Range.between(source, source + length - 1);
                    Range<Long> target = Range.between(lb, ub);
                    if (mapping.isOverlappedBy(target)) {
                        Range<Long> newRange = mapping.intersectionWith(target);
                        Long a = newRange.getMinimum();
                        Long b = newRange.getMaximum();
                        if (Objects.equals(a, lb) && Objects.equals(b, ub)) {
                            lb = lb + (destination - source);
                            ub = ub + (destination - source);
                            break;
                        } else if (Objects.equals(a, lb)) {
                            ArrayList<Long> discardedRange = new ArrayList<>();
                            discardedRange.add(b + 1);
                            discardedRange.add(ub);
                            //function call
//                            if (mappingIndex == obj.mappings.size() - 1 && ) {
//                                obj.resSeeds.add(discardedRange);
//                                break;
//                            } else {
                                obj.seeds.add(discardedRange);
                                printData2(obj, i , obj.seeds.size() - 1,count);
//                            }
                            lb = a + (destination - source);
                            ub = b + (destination - source);
                            break;

                        } else if (Objects.equals(b, ub)) {
                            ArrayList<Long> discardedRange = new ArrayList<>();
                            discardedRange.add(lb);
                            discardedRange.add(a - 1);
                            //function call
//                            if (mappingIndex == obj.mappings.size() - 1) {
//                                obj.resSeeds.add(discardedRange);
//
//                            } else {
//                                obj.seeds.add(discardedRange);
//                                printData2(obj, i , obj.seeds.size() - 1);
//                            }
                            obj.seeds.add(discardedRange);
                            printData2(obj, i , obj.seeds.size() - 1,count);
                            lb = a + (destination - source);
                            ub = b + (destination - source);
                            break;
                        } else if (a > lb && b < ub) {
                            ArrayList<Long> discardedRange1 = new ArrayList<>();
                            ArrayList<Long> discardedRange2 = new ArrayList<>();
                            discardedRange1.add(lb);
                            discardedRange1.add(a - 1);

                            discardedRange2.add(b + 1);
                            discardedRange2.add(ub);

                            //2function calls
//                            if (mappingIndex == obj.mappings.size() - 1) {
//                                obj.resSeeds.add(discardedRange1);
//                                obj.resSeeds.add(discardedRange2);
//                            } else {
                                obj.seeds.add(discardedRange1);
                                obj.seeds.add(discardedRange2);
                                printData2(obj, i, obj.seeds.size() - 2,count);
                                printData2(obj, i , obj.seeds.size() - 1,count);

                                lb = a + (destination - source);
                                ub = b + (destination - source);
                                break;




                        }
                    }
                }


            }
            ArrayList<Long> temp=new ArrayList<>();
            temp.add(lb);
            temp.add(ub);
            //System.out.println("********************");
            //System.out.println(obj.seeds.get(seedIndex));
            //System.out.println(temp);
            obj.seeds.set(seedIndex,temp);

            printData2(obj,0,seedIndex+1,--count);

        }

    }
}
