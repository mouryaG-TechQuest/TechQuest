import java.util.*;
public class Strings {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        String reverse="";
        int i=0;
        // Strings
        /*
         * 
         * String   is a sequence of characters
         * every String is an object of the java.lang.String class.
         * String Constant Pool (SCP)

            SCP is a special area inside the Java heap memory for storing unique string literals.
            If you have created a String using new, you can move it to the SCP using .intern() method.
            String s1 = new String("Java");
        String s2 = s1.intern();
        String s3 = "Java";

        System.out.println(s2 == s3); // true (both now in SCP)

         */
        // for(char c:s ){}
        for(char c:s.toCharArray()){}

        // error
        // s.chars().forEach(st->{reverse=(char)st+reverse;});

        reverse = s.chars()
                  .mapToObj(c -> (char) c)
                  .reduce("", (rev, ch) -> ch + rev, (r1, r2) -> r2 + r1);

        // System.out.println(reverse);
        // while(reverse.length()!=s.length()){
        //     reverse=s.charAt(i++)+reverse;
        // }
        System.out.println("Reverse String:" +reverse);
    }
}
