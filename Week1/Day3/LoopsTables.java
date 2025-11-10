package Day3;
import java.util.*;

public class LoopsTables {
     public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();
        int i=1;
        while(i<=10){
            System.out.println(a+" x "+i+" = "+(a*i++));
        }
    }
}
    

