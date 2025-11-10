package Day2;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;

public class ArrayListT {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ArrayList<Integer> arr=new ArrayList<Integer>();
        System.out.println("Enter 5 numbers:");
        for(int i=0;i<5;i++){
            arr.add(sc.nextInt());
        }
        // System.out.println("Enter one index to remove an element:");
        // int index=sc.nextInt();
        // System.out.println("Removed Element:"+arr.remove(index-1));
        // arr.forEach(System.out::println);
        // Iterator<Integer> ir=arr.iterator();
        // while(ir.hasNext()){
        //     System.out.println(ir.next());
        // }
        // Collections.sort(arr);
        // arr.forEach(System.out::println);
        // arr.stream().filter(a->a>45).forEach(System.out::println);
        Set<Integer> seen = new HashSet<>();

        arr.stream()
           .filter(n -> !seen.add(n))   // add() returns false if element already exists
           .collect(Collectors.toSet()) // collect only duplicates (unique ones)
           .forEach(System.out::println);
    
        int sum1=arr.stream().mapToInt(Integer::intValue).sum();
        int sum2=arr.stream().reduce(0,(a,b)->a+b);

        int[] sum3={0};
        arr.stream().forEach(n->sum3[0]+=n);


        double avg1=arr.stream().mapToInt(Integer::intValue).average().getAsDouble();
        double avg2 = arr.stream()
                 .mapToInt(Integer::intValue)
                 .average()
                 .orElse(0.0);
        System.out.println("Sum:"+sum1+"Average:"+avg1);
    }

}
