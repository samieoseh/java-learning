import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Demo1 {
    public static void main(String a[]) throws InterruptedException {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        nums.add(3);
        nums.add(89);
        nums.add(22);
        System.out.println(nums.get(0));
        System.out.println(nums);

        Set<Integer> uniqueNums = new TreeSet<Integer>();
        uniqueNums.add(3);
        uniqueNums.add(6);
        uniqueNums.add(3);
        uniqueNums.add(8);
        uniqueNums.add(98);

        System.out.println(uniqueNums);

        Map<String, Integer> students = new HashMap<String, Integer>();
        students.put("Samuel", 56);
        students.put("Dan", 65);
        students.put("Samuel", 67);
        
        System.out.println(students);

        System.out.println(students.keySet());

        for (String key : students.keySet()) {
            System.out.println(key + " : " + students.get(key));
        }

        Comparator<Integer> com = new Comparator<Integer>() {
            public int compare(Integer i, Integer j) {
                
                if (i % 10 > j % 10) 
                    return 1;
                else 
                    return -1;
            }
        };
        Collections.sort(nums, com);
        System.out.println(nums);


        List<String> strs = new ArrayList<>();
        Comparator<String> com2 = new Comparator<String>() {
            public int compare(String s1, String s2) {
                if (s1.length() > s2.length()) 
                    return 1;
                else 
                    return -1;
            }
        };
        
        strs.add("Hello");
        strs.add("Samuel");
        strs.add("John");

        Collections.sort(strs, com2);

        System.out.println(strs);
    } 
}
