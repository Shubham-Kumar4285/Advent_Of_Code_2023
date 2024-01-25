import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
class Node{
    String str;
    int value;
    List<Integer> counts;
    List<Character> distinctCharacters;

    public Node(String str, int value, List<Integer> counts, List<Character> distinctCharacters) {
        this.str = str;
        this.value = value;
        this.counts = counts;
        this.distinctCharacters = distinctCharacters;
    }
}

class constants{
    int Five_Kind=0;
    int Four_Kind=1;
    int Full_House=2;
    int Three_Kind=3;
    int Two_pair=4;
    int one_pair=5;
    int High_Card=6;
    List<Integer> kinds=Arrays.asList(1,2,2,3,3,4,5);
    List<Character> comparator=Arrays.asList('A','K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3','2');
    List<Character> comparator2=Arrays.asList('A','K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3','2','J');

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
    List<Node> collection=new ArrayList<>();
    List<List<String>> typesList= new ArrayList<>();
    List<String> fivehand =new ArrayList<>();
    List<String> fourhand =new ArrayList<>();
    List<String> fullhand =new ArrayList<>();
    List<String> threehand =new ArrayList<>();
    List<String> twoPairhand =new ArrayList<>();
    List<String> onepairhand =new ArrayList<>();
    List<String> Highhand =new ArrayList<>();

    Problem7(){

    }

    void countReference(){
        List<String> temp=bidMap.keySet().stream().toList();
        for (String s : temp) {
            List<Character> uniqueCards=s.chars().distinct().mapToObj(c->(char)c).toList();
            List<Integer> counts=countOcurrences(uniqueCards,s);
            finalMap.put(s,uniqueCards.size());
            finalType.put(s,counts);

            Node newNode= new Node(s,bidMap.get(s),counts,uniqueCards);
            collection.add(newNode);

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
    int typeIndex(String str) {
        int currentMax = Collections.max(finalType.get(str));
        List<Node> temp = collection.stream().filter(part2 -> part2.str == str).toList();
        List<Integer> counts = new ArrayList<>();

        for(Character c:temp.get(0).distinctCharacters){
            counts.add((int)str.chars().filter(value -> value==c).count());

        }
//        System.out.println("*************");
//        System.out.println(str);
//        System.out.println(counts);
//        System.out.println(temp.get(0).distinctCharacters);
//        System.out.println("*************");

        Character currentChar = temp.get(0).distinctCharacters.get(counts.indexOf(currentMax));
        if (str.contains("J")) {
            if (currentChar == 'J') {
                if (counts.size() != 1) {

                    Collections.sort(counts);
//                    System.out.println("*****************");
//                    System.out.println(counts);
//                    System.out.println(str);
                    currentMax = currentMax + counts.get(counts.size() - 2);
                    //System.out.println(currentMax);
//                    System.out.println("*****************");

                }
            } else {
                int offset = (int) str.chars().filter(value -> value == 'J').count();
                currentMax = currentMax + offset;
//                System.out.println("*****************");
//                System.out.println(counts);
//                System.out.println(str);
//                System.out.println(currentMax);
//                System.out.println("*****************");
            }
        }
        //update condition for case j
//        System.out.println("*************");
//        System.out.println(str);
//        System.out.println(counts);
//        System.out.println(currentMax);
//        System.out.println(temp.get(0).distinctCharacters);
        if(currentChar=='J'){
            if(counts.size()!=1){
            currentChar=temp.get(0).distinctCharacters.stream().filter(character -> character!='J').toList().get(0);
            }
            String newStr=str.replace("J", String.valueOf(currentChar));
        }
        String newStr=str.replace("J", String.valueOf(currentChar));
        //System.out.println(newStr);
        List<Character> newDistinctCharacter=newStr.chars().mapToObj(c->(char)c).distinct().toList();
        List<Integer> newCounts=new ArrayList<>();
        //System.out.println(newDistinctCharacter);
        for (Character item:newDistinctCharacter
             ) {
            newCounts.add((int)newStr.chars().filter(value -> value==item).count());
        }
//        System.out.println(newCounts);
//
//        System.out.println("*************");
        //new checker





        //switch = 0-five ,1-four of kind  ,2-full house ,3-three kind ,4-two pair , 5-one pair ,6-high card

        switch (currentMax) {
            case 5 -> {
                return 0;
            }
            case 4 -> {
                return 1;
            }
            case 3 -> {
                if (newCounts.contains(2)) {
                    return 2;

                } else {
                    return 3;
                }
            }
            case 2 -> {
                int tempCount=Collections.frequency(newCounts,2);
                if (tempCount==2) {
                    return 4;
                } else {
                    return 5;
                }
            }
            case 1 -> {
                return 6;
            }
        }
        return -1;

    }

    void addTypes(){
        //init
        for (Node node:collection
             ) {
            String key= node.str;
            int index =typeIndex(key);
            //System.out.println(key+" : "+index);
            if(index!=-1){
                switch (index) {
                    case 0 -> fivehand.add(key);
                    case 1 -> fourhand.add(key);
                    case 2 -> fullhand.add(key);
                    case 3 -> threehand.add(key);
                    case 4 -> twoPairhand.add(key);
                    case 5 -> onepairhand.add(key);
                    case 6 -> Highhand.add(key);
                }
            }
            else {
                System.out.println(key);
            }

        }
        typesList.add(fivehand);
        typesList.add(fourhand);
        typesList.add(fullhand);
        typesList.add(threehand);
        typesList.add(twoPairhand);
        typesList.add(onepairhand);
        typesList.add(Highhand);

        for (List<String> ls:typesList
             ) {


            for (int i = 0; i < ls.size(); i++
            ) {
                int current = i;
                int prev = i - 1;
                while (prev > -1) {
                    boolean flag = checkString(ls.get(current), ls.get(prev), obj.comparator2);
                    if (flag) {
                        Collections.swap(ls, current, prev);
                        current = prev;
                    }

                    prev--;
                }
            }
        }
        //System.out.println(typesList);
        List<String> finalRes= new ArrayList<>();
        for (List<String> ls:typesList
             ) {
            finalRes.addAll(ls);
        }
        //System.out.println(finalRes);
        Long rank= (long) finalRes.size();

        Long res=0L;
        for (List<String> item1:typesList
        ) { for(String item:item1){
            res+=bidMap.get(item)*rank;
            rank--;
        }

        }

        System.out.println("Part 2 : "+res);


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
        obj.addTypes();


    }
    static void fetchData(Problem7 obj,String str){
        StringTokenizer key= new StringTokenizer(str," ");
        obj.bidMap.put(key.nextToken(), Integer.valueOf(key.nextToken()));

    }


}