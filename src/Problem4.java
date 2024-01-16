import java.io.File;
import java.util.*;
class customClass{
    int index ;
    ArrayList<Integer> wins;
    ArrayList<Integer> vals;
    ArrayList<Integer> temp=new ArrayList<>();


    customClass(int index,ArrayList<Integer> wins,ArrayList<Integer> vals ){
        this.index=index;
        this.vals=vals;
        this.wins=wins;

    }
    static int checker(ArrayList<customClass> arrayList){
        int sum=0;
        for (customClass obj:arrayList
             ) {
            int match=0;
            for(int i=0;i<obj.wins.size();i++){
                for(int j=0;j<obj.vals.size();j++){
                    if(obj.wins.get(i)==obj.vals.get(j)){
                        if(match==0){
                            match=1;
                        }else{
                            match*=2;
                        }
                    }

                }
            }
            sum+=match;

        }



        return sum;
    }
    static void checker2(ArrayList<customClass> arrayList,ArrayList<Integer> temp){
        ArrayList<Integer> temp2 = temp;
        ArrayList<Integer> temp3 = new ArrayList<>(temp.size());
        for (customClass obj:arrayList
        ) {
            temp.add(obj.index);
            temp3.add(1);

        }

        for(int i=0;i<temp.size();i++){
            int count = temp.get(i);
            if(count/temp.get(i)==0){
                continue;
            }else {

                int bound = matcher(arrayList, i, temp);
                for (int k = i + 1; k <= bound; k++) {
                    temp3.set(k, temp3.get(k) + 1);
                }
                //System.out.println(temp3);
                int key=i;
                for (int j=key;j<=matcher(arrayList,key,temp);j++){
                    int count2=temp3.get(j);
                    while(count2>=2){
                        int bound2 = matcher(arrayList, j, temp);
                        for (int k = i + 1; k <= bound2; k++) {
                            temp3.set(k, temp3.get(k) + 1);
                        }
                        count2--;

                    }
                }

                }
            System.out.println(temp3);



            break;


        }







    }
    static int matcher(ArrayList<customClass> arrayList,int index,ArrayList<Integer> temp){
        int match=0;
        customClass obj=arrayList.get(temp.get(index)-1);
            for(int i=0;i<obj.wins.size();i++){
                for(int j=0;j<obj.vals.size();j++){
                    if(obj.wins.get(i)==obj.vals.get(j)){
                        match+=1;
                    }

                }


        }
            return match;
    }

    static void result2(ArrayList<Integer> temp){


        //System.out.println(temp.size());

        }
    }



public class Problem4 {
    public static void main(String[] args) {
        String temp = null;

        File input= new File("D:\\CodeOfAdvent\\src\\input4.txt");
        ArrayList<customClass> collection = new ArrayList<>();

        try {
            Scanner sc = new Scanner(input);
            while (sc.hasNextLine()) {
                temp=sc.nextLine();
                convertData(collection,temp);
            }

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
      //  System.out.println(customClass.checker(collection));
        customClass.checker2(collection,collection.get(0).temp);
        customClass.result2(collection.get(0).temp);




    }
    static void convertData(ArrayList<customClass> collection,String str){
        boolean winFlag=true;
        ArrayList<Integer> wins=new ArrayList<>();
        ArrayList<Integer> vals=new ArrayList<>();
        int index=-1;
        String[] arr=str.split("[ :]");
//        for (String item:arr
//             ) {
//            System.out.println(item);
//        }
        int k=1;
        while(arr[k].isEmpty() || arr[k].isBlank()){
            k++;
        }
        index=Integer.parseInt(arr[k]);
        int i=k+1;
        while(!Objects.equals(arr[i], "|")){
            if(arr[i].isEmpty() || arr[i].isBlank()){
                i++;
            }else{
            wins.add(Integer.parseInt(arr[i]));
            i++;}
        }
        i++;
        while(i<arr.length){
            if(arr[i].isEmpty()){
                i++;
            }else{
            vals.add(Integer.parseInt(arr[i]));
            i++;}
        }

      collection.add(new customClass(index,wins,vals));
      //  System.out.println(index +" : "+wins+" "+vals);

    }


}
