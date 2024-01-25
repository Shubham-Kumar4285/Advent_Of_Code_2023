import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Problem9 {
    List<List<Integer>> data= new ArrayList<>();
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("D:\\CodeOfAdvent\\src\\input9.txt");
        Problem9 obj= new Problem9();
        Scanner s= new Scanner(file);
        try{
            while(s.hasNextLine()){
                fetchData(obj,s.nextLine());

            }
        }catch (Exception e){
            System.out.println(e);
        }
        //System.out.println(obj.data);
        obj.Part1();






    }

    static void fetchData(Problem9 obj,String s) {
        StringTokenizer tokenizer = new StringTokenizer(s," ");
        List<Integer> tempList=new ArrayList<>();
        while (tokenizer.hasMoreTokens()){
            tempList.add(Integer.parseInt(tokenizer.nextToken()));
        }
        obj.data.add(tempList);


    }


    static void calculateIteration(List<Integer> ls,List<Integer> res,List<Integer> res2){
        List<List<Integer>> tempdata= new ArrayList<>();
        tempdata.add(ls);
        List<Integer> temp= new ArrayList<>(List.copyOf(ls));
        while(!temp.stream().allMatch(integer -> integer==0)){
            List<Integer> newList=new ArrayList<>();
            for(int i=1;i<temp.size();i++){
                newList.add(temp.get(i)-temp.get(i-1));
            }
            //update
            tempdata.add(newList);
            temp.clear();
            temp.addAll(newList);
        }
        //reverse logic -forward
        //System.out.println(tempdata);
        for(int i=tempdata.size()-2;i>-1;i--){
            int nextLast=tempdata.get(i+1).size()-1;
            int current=tempdata.get(i).size()-1;
            tempdata.get(i).add(tempdata.get(i+1).get(nextLast)+tempdata.get(i).get(current));
        }
        res.add(tempdata.get(0).get(tempdata.get(0).size()-1));

        //reverse Logic -backwards
        for(int i=tempdata.size()-2;i>-1;i--){
            int newData=tempdata.get(i).get(0)-tempdata.get(i+1).get(0);
            tempdata.get(i).add(0,newData);
        }
        res2.add(tempdata.get(0).get(0));
        //System.out.println(tempdata);


    }
    void Part1(){
        List<Integer> res = new ArrayList<>();
        List<Integer> res2 = new ArrayList<>();
        for (List<Integer> ls:data
             ) {
            calculateIteration(ls,res,res2);
        }
        //result
        //System.out.println(res);
        int sum = res.stream().mapToInt(Integer::intValue).sum();
        int sum2 = res2.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Part 1 : "+sum);
        System.out.println("Part 2 : "+sum2);
    }

}
