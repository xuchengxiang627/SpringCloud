import java.util.*;

public class Test {

    public static void main(String[] args) {

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        System.out.println(list1==list2);
        System.out.println(list1.equals(list2));
        list1.add(1);
        list1.add(2);
        list1.add(3);
        System.out.println(list1==list2);
        System.out.println(list1.equals(list2));
        Map<List<Integer>, Integer> map = new HashMap<>();
        map.put(list1, 1);


        list2.add(1);
        list2.add(2);
        list2.add(3);
        System.out.println(list1==list2);
        System.out.println(list1.equals(list2));
        System.out.println(map.containsKey(list2));

        System.out.println(new Date() == new Date());
        System.out.println(new Date().equals(new Date()));

    }
}
