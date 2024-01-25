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


    static void calculateIteration(List<Integer> ls,List<Integer> res){
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
        //reverse logic
        //System.out.println(tempdata);
        for(int i=tempdata.size()-2;i>-1;i--){
            int nextLast=tempdata.get(i+1).size()-1;
            int current=tempdata.get(i).size()-1;
            tempdata.get(i).add(tempdata.get(i+1).get(nextLast)+tempdata.get(i).get(current));
        }
        res.add(tempdata.get(0).get(tempdata.get(0).size()-1));


    }
    void Part1(){
        List<Integer> res = new ArrayList<>();
        for (List<Integer> ls:data
             ) {
            calculateIteration(ls,res);
        }
        //result
        //System.out.println(res);
        int sum = res.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Part 1 : "+sum);
    }

}
