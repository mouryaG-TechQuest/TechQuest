package Day3;

import java.util.Scanner;

public class VowelsString {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        int count=0;
        for(char c:s.toCharArray()){
            if("aeiou".indexOf(c)>-1){
                count++;
            }
        }
        System.out.println(count);
    }
}
