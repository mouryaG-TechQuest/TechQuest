package Day4;
import java.util.*;
import java.io.*;

public class newDataAppend {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter data to append to file:");
        String data=sc.nextLine();
        File file=new File("Day4/OutputTemp.txt");
        try(FileWriter fw=new FileWriter(file,true);
            BufferedWriter bw=new BufferedWriter(fw);
            PrintWriter out=new PrintWriter(bw);){
                out.println("");
                out.println(data);
                System.out.println("Data appended successfully.");
        }catch(IOException e){
            System.out.println("An error occurred: "+e.getMessage());
        }
    }
}