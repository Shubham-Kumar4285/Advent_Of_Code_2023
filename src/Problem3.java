import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem3 {

    public static void main(String[] args) {
        File file = new File("D:\\CodeOfAdvent\\src\\input3.txt");
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<ArrayList<Integer>> result2 = new ArrayList<>();
        Map<Integer,ArrayList<Integer>> res = new HashMap<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                temp.add(s.nextLine());


            }

            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //test area
        searchData(temp, result);
        System.out.println("Result :" + result(result));
        searchData2(temp,res);
        System.out.println("Result 2 :"+final2(res));

    }


    static void searchData(ArrayList<String> data, ArrayList<Integer> result) {
        //test for data
//        for (String item : data) {
//            System.out.println(item);
//        }
        //logic for fetching valid numbers
        for (int k = 0; k < data.size(); k++) {
            int i = 0;
            boolean flag = false;
            while (i < data.get(k).length()) {
                int j = i, num = 0, temp = i;
                if (Character.isDigit(data.get(k).charAt(i))) {

                    while (Character.isDigit(data.get(k).charAt(j + 1))) {

                        j += 1;
                        if (j + 1 >= data.get(k).length()) {
                            break;
                        }
                    }

                    num = Integer.parseInt(data.get(k).substring(i, j + 1));


                    flag = checker(data.get(k), i, j);



                    //checking algo
                    if (!flag) {
                        if (k == 0) {
                            String next = data.get(k + 1);
                            flag = checker(next, i, j);


                        } else if (k == data.size() - 1) {
                            String prev = data.get(k - 1);
                            flag = checker(prev, i, j);

                        } else {
                            String next = data.get(k + 1);
                            String prev = data.get(k - 1);
                            if (checker(prev, i, j) || checker(next, i, j)) {
                                flag = true;
                            }

                        }
                    }

                    i = j + 1;


                    //  System.out.println(num+" : "+flag);


                }
                if (temp == i) {
                    i++;
                }
                if (flag) {
                    result.add(num);
                }


            }

            // System.out.println(k);
        }
    }


    static boolean checker(String str, int begin, int end) {
        if (begin != 0 && end != str.length() - 1) {
            for (int i = begin - 1; i <= end + 1; i++) {
                if (!Character.isLetterOrDigit(str.charAt(i)) && str.charAt(i) != '.') {

                    return true;
                }

            }
        } else if (begin == 0) {

            for (int i = begin; i <= end + 1; i++) {
                if (!Character.isLetterOrDigit(str.charAt(i)) && str.charAt(i) != '.') {

                    return true;
                }
            }

        } else {

            for (int i = begin - 1; i <= end; i++) {

                if (!Character.isLetterOrDigit(str.charAt(i)) && str.charAt(i) != '.') {
                    return true;
                }
            }

        }

        return false;
    }

    static int result(ArrayList<Integer> result) {
        int sum = 0;
        for (int item : result) {
            sum += item;
        }
        return sum;
    }

    static ArrayList<Integer> checker2(String str,int index){
        ArrayList<Integer> arrayList= new ArrayList<>();
        if(!Character.isDigit(str.charAt(index))){
        if (index != 0 && index != str.length() - 1) {




            //left
           for(int i=index;i>=index-1;i--){

               if(Character.isDigit(str.charAt(i))){
                   int target=i;
                   int end=i;
                   while(end>=0 && end-1!=-1){
                       if(Character.isDigit(str.charAt(end-1))){
                           end-=1;
                       }
                       else {
                           break;
                       }
                   }
                   int num=Integer.parseInt(str.substring(end,target+1));
                   arrayList.add(num);

                   break;

               }


           }
            //right
            for(int i=index;i<=index+1;i++){

                if(Character.isDigit(str.charAt(i))){
                    int target=i;
                    int end=i;
                    while(end<=str.length()-1 && end+1!=str.length()){
                        if(Character.isDigit(str.charAt(end+1))){
                            end+=1;
                        }else {
                            break;
                        }

                    }
                    int num=Integer.parseInt(str.substring(target,end+1));
                    arrayList.add(num);

                    break;

                }


            }



        } else if (index == 0) {

            for (int i = index; i <= index+1; i++) {
                if (Character.isDigit(str.charAt(i))) {
                        int target=i;
                        int end=i;
                        while(end<=str.length()-1){
                            if(Character.isDigit(str.charAt(end+1))){
                                end+=1;
                            }
                            end++;
                        }
                        int num=Integer.parseInt(str.substring(target,end+1));
                        arrayList.add(num);

                        break;
                }
            }

        }else{
            for (int i = index; i >= index-1; i--) {
                if (Character.isDigit(str.charAt(i))) {
                    int target=i;
                    int end=i;
                    while(end>=0){
                        if(Character.isDigit(str.charAt(end-1))){
                            end-=1;
                        }else {
                            break;
                        }

                    }
                    int num=Integer.parseInt(str.substring(end,target+1));
                    arrayList.add(num);

                    break;
                }
            }

        }
            //centre code
        }else{
            if(Character.isDigit(str.charAt(index+1)) && !Character.isDigit(str.charAt(index-1))){
                int target=index;
                int end=index;
                while(end<=str.length()-1){

                    if(Character.isDigit(str.charAt(end+1))){
                        end+=1;
                    }else {
                        break;
                    }

                }
                int num=Integer.parseInt(str.substring(target,end+1));
                arrayList.add(num);
            }else if(Character.isDigit(str.charAt(index-1)) && !Character.isDigit(str.charAt(index+1))){
                int target=index;
                int end=index;
                while(end>=0){
                    if(Character.isDigit(str.charAt(end-1))){
                        end-=1;
                    }else {
                        break;
                    }

                }
                int num=Integer.parseInt(str.substring(end,target+1));
                arrayList.add(num);
            }else{
                int target=index;
                int end=index;
                while(end>=0){
                    if(Character.isDigit(str.charAt(end-1))){
                        end-=1;
                    }else {
                        break;
                    }

                }
                while(target<=str.length()-1){

                    if(Character.isDigit(str.charAt(target+1))){
                        target+=1;
                    }else {
                        break;
                    }

                }
                int num=Integer.parseInt(str.substring(end,target+1));
                arrayList.add(num);
            }


            }



        //check
//        for (int item:arrayList
//             ) {
//            System.out.print(item+" ");
//
//        }
//        System.out.println(" ");
        //System.out.println(arrayList);

        return arrayList;
    }
    static void searchData2(ArrayList<String> data,Map<Integer,ArrayList<Integer>> res) {
//        for (String item : data) {
//            System.out.println(item);
//        }
        int count =0;
        for(int k=0;k<data.size();k++){
            for(int i=0;i<data.get(k).length();i++){
                if(data.get(k).charAt(i)=='*'){
                   // System.out.println("for k = "+k);
                    ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
                        temp.add(checker2(data.get(k),i));
                    if(k!=0 && k!=data.size()){
                        temp.add(checker2(data.get(k+1),i));
                        temp.add(checker2(data.get(k-1),i));
                    } else if (k==0) {
                        temp.add(checker2(data.get(k+1),i));

                    }else{
                        temp.add(checker2(data.get(k-1),i));
                    }
                    ArrayList<Integer> temp2=new ArrayList<>();
                    for(ArrayList<Integer> item:temp){
                        if(!temp.isEmpty()){
                            temp2.addAll(item);


                        }
                    }
//                    if(temp2.size()==2){
//                    System.out.println("count "+count+" : "+temp2);
//                    }
                    res.put(count,temp2);
                    count+=1;

                }
            }




        }
    }

    static  int final2(Map<Integer,ArrayList<Integer>> res) {
        int ans = 0;
        for (Map.Entry<Integer, ArrayList<Integer>> entry :res.entrySet()) {
            int key=entry.getKey();
            ArrayList<Integer> val=entry.getValue();
            int prod=1;
            if(val.size()==2){
                //System.out.println(key+" : "+val.get(0)*val.get(1));
                ans+=(val.get(0)*val.get(1));
            }

        }
        return ans;
    }
}














