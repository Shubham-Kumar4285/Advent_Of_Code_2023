import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


class  points{
    int x,y;
    elements type;
    points up;
    points down;
    points left;
    points right;


    public points(int x, int y, elements type) {
        this.x = x;
        this.y = y;
        this.type = type;
        up=null;
        down=null;
        left=null;
        right=null;

    }
    public points(){}
}

enum elements{
    VERTICAL_PIPE, // | 0,1
    HORIZONTAL_PIPE, // - 2,3
    NORTH_WEST_PIPE, // J  0,2
    NORTH_EAST_PIPE, // L  0,3
    SOUTH_WEST_PIPE, // 7  2,1
    SOUTH_EAST_PIPE, // F  3,1
    GROUND, // .
    STARTING_POSITION;  // S

}


public class Problem10 {

    List<List<Character>> area =new ArrayList<>();
    List<List<points>> area2 =new ArrayList<>();
    List<Integer> possiblePaths= new ArrayList<>();
    Map<points,points> cyclePath=new HashMap<>();
    Map<points,List<points>> newData= new HashMap<>();
    Map<points,List<points>> newMap= new HashMap<>();
    elements pipes;
    int[] startingPos={0,0};


    public static void main(String[] args) {
        Problem10 obj = new Problem10();
        File input = new File("D:\\CodeOfAdvent\\src\\input10.txt");
        try {
            Scanner s = new Scanner(input);
            while (s.hasNextLine()) {

                List<Character> temp = s.nextLine().chars().mapToObj(value -> (char) value).toList();
                obj.area.add(temp);

            }

            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        points root = obj.initPoints(); // for area 2 - of points

        //System.out.println(obj.area);
        obj.setArea2Points();
        points root2 = obj.calculatePoints();
        obj.traversal(root2);
//        for (List<points> ls: obj.area2){
//            for (points pt :ls){
//                if(pt.type!=elements.GROUND){
//                    System.out.println(pt.type);
//                    System.out.println(pt.up);
//                    System.out.println(pt.down.x+" "+pt.down.y);
//                    System.out.println(pt.left.type);
//                    System.out.println(pt.right.type);
//                    System.out.println("**************");
//                }
//            }
//        }


    }


    void setArea2Points(){
        for (int i = 0; i <area2.size() ; i++) {
            for (int j = 0; j < area2.get(i).size(); j++) {
                points point=area2.get(i).get(j);
                int x=point.x;
                int y=point.y;
                elements type=point.type;
                points up=null;
                points down =null;
                points left=null;
                points right =null;

                if(type!=elements.GROUND){
                if(x==0){
                    //firstLine

                    if(y==0){//left corner
                        down=area2.get(x+1).get(y);
                        right=area2.get(x).get(y+1);

                    }else if(y==area2.get(x).size()-1){//right corner
                        down=area2.get(x+1).get(y);
                        left=area2.get(x).get(y-1);

                    }else{//mid
                        down=area2.get(x+1).get(y);
                        left=area2.get(x).get(y-1);
                        right=area2.get(x).get(y+1);

                    }
                }
                else if(x==area2.size()-1){
                    //last Line
                    if(y==0){//left corner
                        up=area2.get(x-1).get(y);
                        right=area2.get(x).get(y+1);

                    }else if(y==area2.get(x).size()-1){//right corner
                        up=area2.get(x-1).get(y);
                        left=area2.get(x).get(y-1);

                    }else{//mid
                        up=area2.get(x-1).get(y);
                        left=area2.get(x).get(y-1);
                        right=area2.get(x).get(y+1);

                    }
                }else{
                    //mid portion
                    if(y==0){//left corner
                        up=area2.get(x-1).get(y);
                        down=area2.get(x+1).get(y);
                        right=area2.get(x).get(y+1);

                    }else if(y==area2.get(x).size()-1){//right corner
                        up=area2.get(x-1).get(y);
                        down=area2.get(x+1).get(y);
                        left=area2.get(x).get(y-1);


                    }else{//mid
                        up=area2.get(x-1).get(y);
                        down=area2.get(x+1).get(y);
                        right=area2.get(x).get(y+1);
                        left=area2.get(x).get(y-1);

                    }

                }


             area2.get(i).get(j).up=up;
             area2.get(i).get(j).down=down;
             area2.get(i).get(j).left=left;
             area2.get(i).get(j).right=right;
            }
        }
        }
    }
    elements pipesTypes(Character c){
        switch (c){
            case '-'-> {
                return elements.HORIZONTAL_PIPE;
            }
            case '|'-> {
                return elements.VERTICAL_PIPE;
            }
            case 'J'-> {
                return elements.NORTH_WEST_PIPE;
            }
            case 'L'-> {
                return elements.NORTH_EAST_PIPE;
            }
            case 'F'-> {
                return elements.SOUTH_EAST_PIPE;
            }
            case '7'-> {
                return elements.SOUTH_WEST_PIPE;
            }
            case '.'-> {
                return elements.GROUND;
            }
            case 'S'-> {
                return elements.STARTING_POSITION;
            }
        }
        return null;
    }




    points initPoints(){
        points root=null;
        for (int i = 0; i <area.size() ; i++) {
            List<points> temp=new ArrayList<>();
            for (int j = 0; j < area.get(i).size(); j++) {
                points pt= new points(i,j,pipesTypes(area.get(i).get(j)));
                temp.add(pt);
                if(pt.type==elements.STARTING_POSITION){
                    root=pt;
                }
            }
            area2.add(temp);
        }
        return root;
    }


    void traversal(points starting){
        boolean isFound=false;
        //to be manually set according to position of s
        points currentPoint =newMap.get(starting).get(0);
        points prevPoint =starting;

        int count=1;
        while(!isFound){
            List<points> ls=new ArrayList<>();
            try{
                ls.addAll(returnPoints(currentPoint));
                if(ls.contains(prevPoint)){
                    points finalPrevPoint = prevPoint;
                    ls.removeIf(points -> points== finalPrevPoint);
                }
                if(ls.get(0)==null){
                    break;
                }


            }catch (Exception e){
                e.printStackTrace();
                System.out.println(prevPoint.type+" : "+prevPoint.x+" "+prevPoint.y);

                System.out.println(currentPoint.type+" : "+currentPoint.x+" "+currentPoint.y);

                break;
            }
            if (ls.get(0) == starting) {
                count++;
                isFound = true;
            } else {
                points temp = currentPoint;
                currentPoint = ls.get(0);
                prevPoint = temp;
                count++;
            }

        }
        System.out.println("Part 1 : "+(count/2));


    }

    points calculatePoints(){
        points pt=null;
        for (List<points> line:area2
             ) {
            for (points point:line
                 ) {
                newMap.put(point,returnPoints(point));
                if(point.type==elements.STARTING_POSITION){
                    pt=point;
                }

            }
        }
        return pt;
    }

    List<points> returnPoints(points point){
        if(point!=null){
            if(point.type!=elements.GROUND){
            List<points> ls = new ArrayList<>();
            switch (point.type){

                case VERTICAL_PIPE -> {
                    ls.add(point.up);
                    ls.add(point.down);
                    return ls;

                }
                case HORIZONTAL_PIPE -> {
                    ls.add(point.left);
                    ls.add(point.right);
                    return ls;
                }
                case NORTH_WEST_PIPE -> {
                    ls.add(point.up);
                    ls.add(point.left);
                    return ls;
                }
                case NORTH_EAST_PIPE -> {
                    ls.add(point.up);
                    ls.add(point.right);
                    return ls;
                }
                case SOUTH_WEST_PIPE -> {
                    ls.add(point.down);
                    ls.add(point.left);
                    return ls;
                }
                case SOUTH_EAST_PIPE -> {
                    ls.add(point.down);
                    ls.add(point.right);
                    return ls;
                }
                case STARTING_POSITION -> {
                    ls.add(point.up);
                    ls.add(point.down);
                    ls.add(point.left);
                    ls.add(point.right);
                    return ls;
                }
            }
            }
        }
        return List.of();
    }


}
