package Day5;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class miniProjectReadMarks {
    public static void main(String[] args) {
        String line;
        Map<String,Double> studentMarks=new HashMap<>();
        Path tempPath = Paths.get("Day5", "marks.csv");
        try(BufferedReader br=new BufferedReader(new FileReader(tempPath.toFile()));){
            br.readLine();
            while((line=br.readLine())!=null){
                String[] parts=line.split(",");
                if(parts.length==2){
                    String name=parts[0].trim();
                    double marks=Double.parseDouble(parts[1].trim());
                    studentMarks.put(name,marks);
                }
            }
        }catch(Exception e){
            System.out.println("An error occurred: "+e.getMessage());   
        }
            studentMarks.entrySet().stream().forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter student name to get marks:");
            String studentName=sc.nextLine().trim();
            if(studentMarks.containsKey(studentName)){
                System.out.println("Marks of "+studentName+" is "+studentMarks.get(studentName));
            }
            else{
                System.out.println("Student not found");
            }
            double avg=studentMarks.values().stream()
                .mapToDouble(Double::doubleValue)
                .average().getAsDouble()
                ;
            double sum=studentMarks.values().stream()
                .mapToDouble(Double::doubleValue)
                .reduce(0,(a,b)->a+b);
            System.out.println("Total sum of marks: "+sum);

            try(BufferedWriter bw=new BufferedWriter(new FileWriter("Day5/marksOutput.txt"))){
                bw.write("Average Marks: "+avg);
                bw.newLine();
                bw.write("Total Marks: "+sum);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        
    }
}
