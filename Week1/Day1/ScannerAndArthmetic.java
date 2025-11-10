import java.util.*;

public class ScannerAndArthmetic {

    public static void main(String[] args) {
        //Scanner
        /*
         * 
         * 
         * Scanner (in java.util) is a handy token-based input parser that can read primitive types and strings from many sources: InputStream (e.g., System.in), File, Readable, or String. It tokenizes input using a delimiter pattern (by default whitespace).

            1. How it works (conceptual)

            Scanner reads characters from a source and splits them into tokens using a delimiter (default: any whitespace).

            Each nextXxx() method parses the next token according to a parse rule (e.g., nextInt() parses token as integer).

            It internally uses regular expressions (Pattern) to match tokens and delimiters.

            It maintains locale information (affects decimal separators) and can be told to use a different delimiter Pattern.
         * 
         * 
         * 
         */
        Scanner sc=new Scanner(System.in);
        int a,b,c,d,e;
        System.out.println("Enter 1st Number:");
        a=sc.nextInt();
        System.out.println("Enter 2nd Number:");
        b=sc.nextInt();
        System.out.println("Enter 3rd Number:");
        c=sc.nextInt();
        System.out.println("Enter 4rth Number:");
        d=sc.nextInt();
        System.out.println("Enter 5th Number:");
        e=sc.nextInt();
        System.out.println("Sum of 5 numbers: " +String.valueOf(a+b+c+d+e));

        // Precendence
        /*
         * 
         * 
         * . Full precedence table (high → low) with associativity

        (from highest precedence to lowest; operators on same line have same precedence)

        Postfix: expr++, expr--
        Associativity: left-to-right
        Example: a++

        Unary: ++expr, --expr, +expr, -expr, ~, !, (type) cast, typeof? (no)
        Associativity: right-to-left
        Example: ++a, -b, !(x>0), (int)x

        Multiplicative: *, /, %
        Associativity: left-to-right
        Example: a * b / c % d

        Additive: +, -
        Associativity: left-to-right
        Example: a + b - c

        Shift: <<, >>, >>>
        Associativity: left-to-right

        Relational: <, >, <=, >=, instanceof
        Associativity: left-to-right

        Equality: ==, !=
        Associativity: left-to-right

        Bitwise AND: & (also used for booleans, non-short-circuit)
        Associativity: left-to-right

        Bitwise XOR: ^
        Associativity: left-to-right

        Bitwise OR: |
        Associativity: left-to-right

        Logical AND: && (short-circuit)
        Associativity: left-to-right

        Logical OR: || (short-circuit)
        Associativity: left-to-right

        Ternary conditional: ? :
        Associativity: right-to-left

        Assignment: =, +=, -=, *=, /=, %=, <<=, >>=, >>>=, &=, ^=, |=
        Associativity: right-to-left
         * 
         */
        System.out.println(a+b);
        System.out.println(a-b);
        System.out.println(a*b);
        System.out.println(a/b);
        System.out.println((a%b));
        System.out.println((int)(a/b));

    }
    
}
