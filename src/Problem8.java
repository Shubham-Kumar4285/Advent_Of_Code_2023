import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;


public class Problem8 {
    String instruction;
    Map<String,List<String>> data = new HashMap<>();
    List<List<String>> rawData = new ArrayList<>();
    List<String> currents= new ArrayList<>();

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
        //obj.part2();
        obj.part2New();

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
           if(temp.get(0).endsWith("A")){
               obj.currents.add(temp.get(0));
           }
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
    void part2(){
        // array - currents
        //goal - update that array till all elements end with Z
        boolean flag=checkAll();
        Long count =0L;
        int index=0;
        System.out.println(currents);
        while(true){


            if(instruction.charAt(index)=='L'){
                UpdateLeft();
                count++;
            }
            else {
                UpdateRight();
                count++;
            }


            index++;
            if(checkAll()){
                break;

            }
            if(index>=instruction.length()){
                index=0;
            }
            System.out.println(currents);


        }
        System.out.println("Part 2 : "+count);
    }

    private boolean checkAll() {
        for (String item:currents
             ) {
            if(!item.endsWith("Z")){
                return false;
            }
        }
        return true;
    }
    private void UpdateLeft(){
        for (int i = 0; i <currents.size() ; i++) {
            String replaced= data.get(currents.get(i)).get(0);
            currents.set(i,replaced);
        }
    }
    private void UpdateRight(){
        for (int i = 0; i <currents.size() ; i++) {
            String replaced= data.get(currents.get(i)).get(1);
            currents.set(i,replaced);
        }
    }
    void part2New(){
        List<Long> counts=new ArrayList<>();
        for (int i=0;i<currents.size();i++){
            long count =0L;
            String current =currents.get(i);
            int index=0;
            while (!current.endsWith("Z")) {
                if (instruction.charAt(index) == 'L') {
                    current = data.get(current).get(0);
                    count++;
                } else {
                    current = data.get(current).get(1);
                    count++;
                }
                index++;
                if (index >= instruction.length()) {
                    index = 0;
                }
            }
            counts.add(count);
        }
        //System.out.println(counts);
        System.out.println("Part 2 :"+findLCM(counts));

    }
    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // Function to find the LCM of an array of numbers
    public static long findLCM(List<Long> arr) {
        long lcm = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            long currentNumber = arr.get(i);
            lcm = (lcm * currentNumber) / gcd(lcm, currentNumber);
        }
        return lcm;
    }


}


