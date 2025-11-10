import java.util.*;
public class IfElse {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int a,b,c;
        a=sc.nextInt();
        b=sc.nextInt();
        c=sc.nextInt();

        //
        /*
         * 
         * 
         * Java if condition must be boolean; cannot use 0 or 1 directly.

        Use else if to handle multiple sequential conditions.

        else is optional; executes if all previous conditions are false.

        Logical operators &&, ||, ! allow combining boolean expressions.

        Nested if is allowed but can be flattened for clarity.

        Ternary operator is a shorthand for simple if-else.
         * 
         * 
         */

        if(a>b && a>c){
            System.out.println("A is largest");
        }
        else if((b>a) && (b>c)){
            System.out.println("B is largest");
        }
        else{
            System.out.println("C is largest");
        }
        System.out.println("Largest Value: "+( (a>b && a>c)?(a):((b>c)?b:c)));
    }
}
