package Day3;

import java.util.Scanner;

public class pallindrome {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        int j=s.length()-1;
        int i=0;
        while(i<j){
            if(s.charAt(i++)!=s.charAt(j--)){
                System.out.println("Is not pallindrome");
                break;
            }

        }
        if(i>=j){
            System.out.println("Pallindrome");
        }

    }
    
}
