import java.util.*;
public class TypeCasting {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int temperature;
        System.out.println("Enter Temperatur in Celsius:");
        temperature=sc.nextInt();
        // TypeCasting
        /*
         * 
         * Type casting in Java is the process of converting a variable from one data type to another.

        Sometimes Java does it automatically → called implicit casting or type promotion

        Sometimes you must do it explicitly → called explicit casting or narrowing
         * 
         * 
         * 
         * 
         */
        System.out.println("temperature in Farenheit:"+(((((float)9/5)*(temperature))+32)));
    }
}
