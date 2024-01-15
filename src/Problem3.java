import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem3 {

    public static void main(String[] args) {
        File file = new File("D:\\CodeOfAdvent\\src\\input3.txt");
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
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
        searchData(temp,result);
        System.out.println("Result :"+result(result));

    }


    static void searchData(ArrayList<String > data,ArrayList<Integer> result) {
        //test for data
//        for (String item : data) {
//            System.out.println(item);
//        }
        //logic for fetching valid numbers
        for (int k = 0; k < data.size(); k++) {
            int i = 0;
            boolean flag = false;
            while (i < data.get(k).length()) {
                int j = i, num = 0,temp=i;
                if (Character.isDigit(data.get(k).charAt(i))) {

                    while (Character.isDigit(data.get(k).charAt(j + 1))) {

                        j += 1;
                        if(j+1>=data.get(k).length()){
                            break;
                        }
                    }

                    num = Integer.parseInt(data.get(k).substring(i, j + 1));


                    flag = checker(data.get(k), i, j);


                    //checking algo
                    if(!flag){
                    if(k==0){
                        String next=data.get(k+1);
                        flag=checker(next,i,j);


                    } else if (k==data.size()-1) {
                        String prev= data.get(k-1);
                        flag=checker(prev,i,j);

                    } else{
                        String next=data.get(k+1);
                        String prev= data.get(k-1);
                        if(checker(prev,i,j) ||checker(next,i,j)){
                            flag=true;
                        }

                     }
                    }

                    i = j+1;


                    System.out.println(num+" : "+flag);


                }
                if(temp==i){
                    i++;
                }
                if(flag){
                    result.add(num);
                }






            }

            System.out.println(k);
        }
    }


    static boolean checker(String str,int begin,int end){
        if(begin!=0 && end!=str.length()-1){
            for(int i=begin-1;i<=end+1;i++){
                if(!Character.isLetterOrDigit(str.charAt(i)) && str.charAt(i)!='.'){

                    return true;
                }

            }
        }
        else if (begin==0) {

            for(int i=begin;i<=end+1;i++){
                if(!Character.isLetterOrDigit(str.charAt(i)) && str.charAt(i)!='.'){

                    return true;
                }
            }

        }
        else {

            for(int i=begin-1;i<=end;i++){

                if(!Character.isLetterOrDigit(str.charAt(i)) && str.charAt(i)!='.'){
                    return true;
                }
            }

        }

    return false;
    }
    static int result(ArrayList<Integer> result){
        int sum=0;
        for(int item:result){
            sum+=item;
        }
        return sum;
    }
}
