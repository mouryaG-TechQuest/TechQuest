import java.util.*;
public class SwitchCalculator {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int a,b;
        
        breakwhile:
        while(true){
            System.out.println("Enter a:");
            a=sc.nextInt();
            System.out.println("Enter b");
            b=sc.nextInt();
            System.out.println("Enter an operation:1.ADD 2.SUBTRACT 3.DIVIDE 4.MODULO 5.MULTIPLY");
            switch (sc.nextInt()) {
                case 1:
                    System.out.println(a+b);
                    break;
                case 2:
                    System.out.println(a-b);
                    break;
                case 3:
                    System.out.println(a/b);
                    break;
                case 4:
                    System.out.println(a%b);
                    break;
                case 5:
                    System.out.println(a*b);
                    break;
                default:
                    break breakwhile;
            }
        }
    }
}
