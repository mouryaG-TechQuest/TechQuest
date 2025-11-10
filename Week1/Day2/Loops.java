import java.util.Scanner;

class Loops{
    public static void main(Strings[] args) {
        Scanner sc=new Scanner(System.in);
        int a =sc.nextInt();
        System.out.println("Factorial:");
        int result=1;
        for(int i=1;i<=a;i++){
            result*=i;
        }
        // labeled break/Continue
        label1:
        while(true){
            label2:
            while(true){
                break label2;
            }
            break label1;
        }
        System.out.println(result);
    }
}