import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class galaxy{
    int x;
    int y;

    public galaxy(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class  galaxyPair{
    galaxy a;
    galaxy b;
    int distance;
    public galaxyPair(galaxy a, galaxy b,int distance) {
        this.a = a;
        this.b = b;
        this.distance=distance;
    }
}

public class Problem11 {

    //part 1:
    //target -> expanding and then mapping
    // 1. expanding - row wise and column wise
    // 2. mapping - combination of targets  with the shortest distance for each
    ArrayList<ArrayList<String>> container = new ArrayList<>();
    ArrayList<galaxy> totalGalaxies=new ArrayList<>();
    ArrayList<galaxyPair> galaxyPairs= new ArrayList<>();




    //methods
    void printContainer(){
        container.iterator().forEachRemaining(strings-> {
            for (String s:strings
                 ) {
                System.out.print(s);
            }
            System.out.println(" ");
        });
    }
    //expand container;
    void expandContainer(){
        //column wise
        List<Integer> expandableColumns= new ArrayList<>();
        int count=0;
        for (int column =0;column<container.get(0).size();column++){
            boolean isExpandable=true;
            for (int i=0; i < container.size(); i++) {
                if(Objects.equals(container.get(i).get(column), "#")){
                    isExpandable=false;
                    break;
                }
            }
            if(isExpandable){
               expandableColumns.add(column+count);
                count++;
            }
        }
        container.forEach(strings -> expandableColumns.forEach(integer -> strings.add(integer,".")));

        //row wise
        List<Integer> expandableRows= new ArrayList<>();
        int count2=0;
        for (int i=0;i<container.size();i++){
            ArrayList<String> strings= container.get(i);
           if(strings.stream().distinct().count()<=1){
               expandableRows.add(i+ count2);
               count2++;
           }
       }
       expandableRows.forEach(integer -> container.add(integer,container.get(expandableRows.get(0))));
    }

    //calculating total number of galaxies with positions & mapping
    void calGalaxies(){

        for (int i = 0; i <container.size() ; i++) {
            for (int j = 0; j < container.get(i).size(); j++) {
                if(Objects.equals(container.get(i).get(j), "#")){
                    totalGalaxies.add(new galaxy(i,j));
                }
            }
        }
        for (int i = 0; i < totalGalaxies.size(); i++) {
            int nextPair=i+1;
            galaxy current=totalGalaxies.get(i);
            while(nextPair<totalGalaxies.size()){
                galaxy tempPair=totalGalaxies.get(nextPair);
                //distance
                int distance= Math.abs(current.x-tempPair.x)+Math.abs(current.y-tempPair.y);
                galaxyPairs.add(new galaxyPair(current,tempPair,distance));
                nextPair++;
            }

        }


    }
    void part1(){
        expandContainer();
        calGalaxies();
        int sum=0;
        for (galaxyPair gp:galaxyPairs
             ) {
            sum+=gp.distance;
        }
        System.out.println("Part 1 : "+sum);

    }









    public static void main(String[] args) throws FileNotFoundException {
        Problem11 obj = new Problem11();
        fetchData(obj);

        obj.part1();





    }
    static void fetchData(Problem11 obj){
        File file = new File("D:\\CodeOfAdvent\\src\\input11.txt");
        try{
        Scanner s = new Scanner(file);
        while(s.hasNextLine()){
            ArrayList<String> temp = new ArrayList<>(Arrays.stream(s.nextLine().split("")).toList());
            obj.container.add(temp);
        }}catch (Exception e){
            e.printStackTrace();
        }
    }
}
