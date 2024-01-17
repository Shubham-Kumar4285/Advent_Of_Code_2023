import java.io.File;
import java.util.*;





class newClass{
    ArrayList<Integer> indices;
    ArrayList<Integer> count;
    ArrayList<customClass> collection;
    public newClass(ArrayList<customClass> collection){
        indices = new ArrayList<>();
        count= new ArrayList<>();
        this.collection=collection;
        for (customClass obj:collection
             ) {
            indices.add(obj.index);
            count.add(1);
        }

    }

    public void showCount(){
        System.out.println(count);
    }
    public void showIndices(){
        System.out.println(indices);
    }
    public void PrintREs(){
        int sum=0;
        for (int item:count
             ) {
            sum+=item;
        }
        System.out.println(sum);
    }
}
class customClass{
    int index ;
    ArrayList<Integer> wins;
    ArrayList<Integer> vals;


    customClass(int index,ArrayList<Integer> wins,ArrayList<Integer> vals ){
        this.index=index;
        this.vals=vals;
        this.wins=wins;

    }

    public customClass() {

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
    static void checker2(ArrayList<customClass> arrayList,ArrayList<Integer> indices,ArrayList<Integer> count,int begin,int end){


        for(int i=begin;i<=end;i++){
            int matches =matcher(arrayList,i,indices);
            //System.out.println(matches);
            if(matches>0){
                for (int j=i+1;j<=i+matches;j++){
                    count.set(j,count.get(j)+1);

                }

                checker2(arrayList,indices,count,i+1,i+matches);


            }

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


        System.out.println(temp);

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


        newClass obj = new newClass(collection);
        customClass.checker2(collection,obj.indices,obj.count,0,obj.indices.size()-1);
        obj.showIndices();
        obj.showCount();
        obj.PrintREs();






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
