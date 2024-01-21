import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Problem6 {
    List<Integer> times;
    List<Integer> totalDistance;
    public Problem6(){
        times=new ArrayList<>();
        totalDistance=new ArrayList<>();
    }

    public static void main(String[] args) {
        List<String> ls = new ArrayList<>();
        File input= new File("D:\\CodeOfAdvent\\src\\input6.txt");
        try {
            Scanner sc = new Scanner(input);
            while (sc.hasNextLine()) {
                ls.add(sc.nextLine());
            }

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        for (String[] item:ls
//             ) {
//            System.out.println(Arrays.toString(item));
//        }
       Problem6 obj=new Problem6();
        fetchData(ls,obj);
//        System.out.println(obj.times);
//        System.out.println(obj.totalDistance);
        part1(obj);

//        String str2 = obj.times.stream().map(i->i.toString()).collect(Collectors.joining(""));
//        System.out.println(str2);
        part2(obj);

    }

    static  void fetchData(List<String> inputs,Problem6 obj){
        StringTokenizer tokenizer= new StringTokenizer(inputs.get(0)," Time:");
        StringTokenizer tokenizer2= new StringTokenizer(inputs.get(1)," Distance:");
        while (tokenizer.hasMoreTokens()){
            obj.times.add(Integer.parseInt(tokenizer.nextToken()));
        }
        while (tokenizer2.hasMoreTokens()){
            obj.totalDistance.add(Integer.parseInt(tokenizer2.nextToken()));
        }
    }

     static int matchingWays(int time,int distance){
        int ways=0;

        for (int i = 1; i < time; i++) {
            int remainingTime =time-i;
            if(remainingTime*i>distance){
                ways+=1;
            }
        }
        return ways;
    }
    static Long matchingWays2(Long time,Long distance){
        Long ways=0L;

        for (int i = 1; i < time; i++) {
            Long remainingTime =time-i;
            if(remainingTime*i>distance){
                ways+=1;
            }
        }
        return ways;
    }
    static void part1(Problem6 obj){
        int prod=1;
        for(int i=0;i<obj.times.size();i++){
            prod*=matchingWays(obj.times.get(i),obj.totalDistance.get(i));
        }
        System.out.println("Part 1 : "+prod);
    }
    static void part2(Problem6 obj){
        Long time = Long.parseLong(obj.times.stream().map(i->i.toString()).collect(Collectors.joining("")));
        Long distance =Long.parseLong( obj.totalDistance.stream().map(i->i.toString()).collect(Collectors.joining("")));
        System.out.println("Part 2 : "+matchingWays2(time,distance));

    }
}
