package Day4;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
public class CopyContentOneToOther {
    public static void main(String[] args) {
        try(BufferedReader br=new BufferedReader(new FileReader("Day4/temp.txt"));
            BufferedWriter bw=new java.io.BufferedWriter(new FileWriter("Day4/CopyOfTemp.txt"));){
                String line;
                while((line=br.readLine())!=null){
                    bw.write(line);
                    bw.newLine();
                }
                System.out.println("Content copied successfully.");
    }catch (Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}
    
}
