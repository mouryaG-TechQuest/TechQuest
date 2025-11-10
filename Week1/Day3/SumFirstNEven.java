package Day3;

import java.util.*;
public class SumFirstNEven {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();
        int i=0;
        while(a!=0){
            i+=2;
            a-=1;
        }
        System.out.println("Output:"+i);
    }
}
