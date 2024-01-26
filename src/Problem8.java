import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;


public class Problem8 {
    String instruction;
    Map<String,List<String>> data = new HashMap<>();
    List<List<String>> rawData = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("D:\\CodeOfAdvent\\src\\input8.txt");
        Problem8 obj= new Problem8();
        Scanner s= new Scanner(file);
        List<String[]> items= new ArrayList<>();
        try{
            while(s.hasNextLine()){
                StringTokenizer tokenizer= new StringTokenizer(s.nextLine()," )(,=");
                List<String> temp = new ArrayList<>();
                while (tokenizer.hasMoreElements()){
                    temp.add(tokenizer.nextToken());
                }
                obj.rawData.add(temp);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        //System.out.println(obj.rawData);
        fetchData(obj);
        obj.part1();
        //System.out.println(obj.instruction);

    }

    private static void fetchData(Problem8 obj) {

        //System.out.println(temp);
        int i=0;
        StringBuilder str= new StringBuilder();
       while (!obj.rawData.get(i).isEmpty()){
           str.append(obj.rawData.get(i).get(0));
           i++;
       }

        //System.out.println(str);
       obj.instruction= String.valueOf(str);
       i++;
       while(i<obj.rawData.size()){
           List<String> temp=obj.rawData.get(i);
           List<String> toAdd=new ArrayList<>();
           toAdd.add(temp.get(1));
           toAdd.add(temp.get(2));
           obj.data.put(temp.get(0),toAdd);
           i++;
       }
        //System.out.println(obj.data);


    }
    void part1(){
        String current ="AAA";
        String end = "ZZZ";
        boolean flag=false;
        int count =0;
        int index=0;
        while(!flag){


            if(instruction.charAt(index)=='L'){
                current=data.get(current).get(0);
                count++;
            }
            else {
                current=data.get(current).get(1);
                count++;
            }


            index++;
            if(current.equals(end)){
            flag=true;

            }
            if(index>=instruction.length()){
            index=0;
            }


        }
        System.out.println("Part 1 : "+count);
    }
}


