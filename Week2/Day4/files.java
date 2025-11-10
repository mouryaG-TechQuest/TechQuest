package Day4;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
class FileNotTheirException extends FileNotFoundException{
    public FileNotTheirException(String message){
        super(message);
    }
}
public class files extends FileNotFoundException{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("File operations will be performed here.");
        Path tempPath = Paths.get("Day4", "temp.txt");
        try(BufferedReader br=new BufferedReader(new FileReader(tempPath.toFile()));){
            String line;
            while((line=br.readLine())!=null){
                System.out.println(line);
            }
            
        }  catch(FileNotTheirException e){
            System.out.println(e.getMessage());
        }catch (IOException e) {
            e.printStackTrace();
        }
        Path outputPath = Paths.get("Day4", "OutputTemp.txt");
        try(BufferedWriter bw= new BufferedWriter(new FileWriter((outputPath.toFile())));){
                bw.write("Hi i am done for the day");
                bw.newLine();
                bw.write("This is the second line");
        }catch (IOException e) {
            System.out.println("An error occurred during file writing: " + e.getMessage());
        }
    }
}
