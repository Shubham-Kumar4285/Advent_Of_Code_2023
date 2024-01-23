import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

class constants{
    int Five_Kind=1;
    int Four_Kind=2;
    int Full_House=2;
    int Three_Kind=3;
    int Two_pair=3;
    int one_pair=4;
    int High_Card=5;
    List<Integer> kinds=Arrays.asList(1,2,2,3,3,4,5);
    List<Character> comparator=Arrays.asList('A','K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3','2');

    constants(){
    }

}
public class Problem7 {
    Map<String,Integer> bidMap= new HashMap<>();
    //List<Integer> counts= new ArrayList<>();
    List<Set<Integer>> bidReferences= new ArrayList<>();
    Map<String,Integer>  finalMap=new HashMap<>();
    Map<String,List<Integer>>  finalType=new HashMap<>();
    Map<String,Integer> rankMap= new HashMap<>();
    constants obj = new constants();

    Problem7(){

    }

    void countReference(){
        List<String> temp=bidMap.keySet().stream().toList();
        for (String s : temp) {
            List<Character> uniqueCards=s.chars().distinct().mapToObj(c->(char)c).toList();
            List<Integer> counts=countOcurrences(uniqueCards,s);
            finalMap.put(s,uniqueCards.size());
            finalType.put(s,counts);

        }
        //System.out.println(finalMap);
        //System.out.println(finalType);

    }

    void compareSort(){

        List<Map.Entry<String, Integer>> list = new LinkedList<>(finalMap.entrySet());
        Collections.sort(list, new Comparator<>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (true) {
//compare two object and return an integer
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });
        Map<String, Integer> sortedMap = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        List<String> orderedlist= new ArrayList<>(sortedMap.keySet().stream().toList());
//        for (Map.Entry<String, Integer> entry : sortedMap.entrySet())
//        {
//            System.out.println(entry.getKey() +"\t"+entry.getValue());
//        }
        //System.out.println(orderedlist);
        for(int i=0;i<orderedlist.size();i++){
            //System.out.println("Loop chl rha hai vai");
            int currentElem=i;
            int maxCount=Collections.max(finalType.get(orderedlist.get(currentElem)));
            int count=i-1;
            while(count>-1){


                int prevCount=Collections.max(finalType.get(orderedlist.get(count)));
                if(maxCount>prevCount){
                    Collections.swap(orderedlist,currentElem,count);
                    currentElem=count;



                } else if (maxCount==prevCount) {
                    int maxOccur=Collections.frequency(finalType.get(orderedlist.get(currentElem)),maxCount);
                    int prevOccur=Collections.frequency(finalType.get(orderedlist.get(count)),prevCount);
                    List<Integer> maxOccurList=finalType.get(orderedlist.get(currentElem));
                    Collections.sort(maxOccurList);
                    List<Integer> prevOccurList=finalType.get(orderedlist.get(count));
                    Collections.sort(prevOccurList);

                    if(maxOccur>prevOccur){
                        Collections.swap(orderedlist,currentElem,count);
                        currentElem=count;


                    } else {
                        maxOccur=maxOccurList.get(maxOccurList.size()-2);
                        prevOccur=prevOccurList.get(maxOccurList.size()-2);

                        if(maxOccur>prevOccur){
                            Collections.swap(orderedlist,currentElem,count);
                            currentElem=count;


                        }
                        else  if(prevOccur==maxOccur){
                        String current=orderedlist.get(currentElem);
                        String prev=orderedlist.get(count);
                        boolean flag=checkString(current,prev,obj.comparator);
                        if(flag){
                            Collections.swap(orderedlist,currentElem,count);
                            currentElem=count;

                        }


                    }
                    }



                }

                count--;



            }


        }
        //System.out.println(orderedlist);
        Long rank= (long) orderedlist.size();

        Long res=0L;
        for (String item:orderedlist
             ) {
            res+=bidMap.get(item)*rank;
            rank--;

        }

        System.out.println("Part 1 : "+res);


    }
    boolean checkString(String current,String prev,List<Character> comparator){
        for (int i=0;i<current.length();i++){
            int prevIndex=comparator.indexOf(prev.charAt(i));
            int currentIndex=comparator.indexOf(current.charAt(i));
            if(currentIndex<prevIndex){
                return true;
            } else if (prevIndex<currentIndex) {
                return false;
            }
        }
        return false;
    }
    void swap(List<String> list,int i,int j){
        String temp=list.get(i);
        list.set(i,list.get(j));
        list.set(j,temp);
    }










    List<Integer> countOcurrences(List<Character> ls,String str){
        List<Integer> integers = new ArrayList<>();
        for (Character c:ls
             ) {
            int count= (int) str.chars().filter(ch->ch==c).count();
            integers.add(count);
        }
        return  integers;
    }
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("D:\\CodeOfAdvent\\src\\input7.txt");
        Problem7 obj= new Problem7();

        Scanner s= new Scanner(file);
        try{
            while(s.hasNextLine()){
                fetchData(obj,s.nextLine());

            }
        }catch (Exception e){
            System.out.println(e);
        }
        //System.out.println(obj.bidMap);
        obj.countReference();
        obj.compareSort();

    }
    static void fetchData(Problem7 obj,String str){
        StringTokenizer key= new StringTokenizer(str," ");
        obj.bidMap.put(key.nextToken(), Integer.valueOf(key.nextToken()));

    }
}
