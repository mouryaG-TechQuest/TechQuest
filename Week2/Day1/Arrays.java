package Day1;
import java.nio.channels.Pipe.SourceChannel;
import java.util.*;

class Arrays{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of Students:");
        int[] marks= new int[sc.nextInt()];
        int max=-1;
        int min=Integer.MAX_VALUE;
        int sum=0;
        int len=marks.length;
        int temp_len=len;
        int[] reverse=new int[len];
        for(int i=0; i<marks.length;i++){
            System.out.println("Enter marks of "+i+" Student:");
            marks[i]=sc.nextInt();
            if(min>i){
                min=i;
            }
            if(max<i){
                max=i;
            }
            sum+=i;
            reverse[--temp_len]=i;
        }
        System.out.println("Max value :" + max);
        System.out.println("Minimum values :"+ min);
        System.out.println("Sum of all marks:"+sum);
        System.out.println(("Average marks of Student:"+(sum/(marks.length))));
        System.out.println("Marks in original order:");
        for(int m:marks){
            System.out.println(m);
        }
        System.out.println("Marks in reverse order:");
        for(int m:reverse){
            System.out.println(m);
        }
        System.out.println("Enter an element to search in array:");
        int key=sc.nextInt();
        boolean notFound=true;
        int index=0;
        for(int i: marks){
            if(key==i){
                System.out.println("Key"+key+" found at index "+(index+1));
                notFound=false;
                break;
            }
        }
        if(notFound){
            System.out.println("Key "+key+" not found");
        }
    }
}