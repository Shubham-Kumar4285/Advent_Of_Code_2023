import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Problem6 {
    List<Integer> times;
    List<Integer> totalDistance;

    public static void main(String[] args) {
        List<String[]> ls = new ArrayList<>();
        File input= new File("D:\\CodeOfAdvent\\src\\input6.txt");
        try {
            Scanner sc = new Scanner(input);
            while (sc.hasNextLine()) {
                ls.add(sc.nextLine().split(" "));
            }

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String[] item:ls
             ) {
            System.out.println(Arrays.toString(item));
        }
    }
}
