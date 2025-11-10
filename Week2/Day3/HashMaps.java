package Day3;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class Student{
    String name;
    double marks;
    Student(String name,double marks){
        this.name=name;
        this.marks=marks;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setMarks(double marks) {
        this.marks = marks;
    }
    public String getName() {
        return name;
    }
    public double getMarks() {
        return marks;
    }
    



}

public class HashMaps {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        HashMap<String,Double> h=new LinkedHashMap<>();
        System.out.println("Enter number of Students:");
        int n=sc.nextInt();
        for(int i=0;i<n;i++){
            System.out.println("Enter name of Student "+(i+1)+":");
            String name=sc.next();
            System.out.println("Enter marks of Student "+(i+1)+":");
            double marks=sc.nextDouble();
            h.put(name,marks);
        }
        System.out.println("Enter a student name;");
        String stu=sc.next();
        if(h.containsKey(stu)){
            System.out.println("Marks of "+stu+" is "+h.get(stu));
        }
        else{
            System.out.println("Student not found");
        }

        HashMap<String,Integer> freq=new HashMap<>();
        for(String kString:h.keySet()){
            if(freq.containsKey(kString)){
                freq.put(kString,freq.get(kString)+1);
            }
            else{
                freq.put(kString,1);
            }
        }
        for(Entry<String,Integer> e:freq.entrySet()){
            System.out.println(e.getKey()+":"+e.getValue());
        }
        h.entrySet().stream()
            .filter(e -> e.getValue() >= 50)
            .forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
        Map<String, Double> filtered =h.entrySet().stream()
            .filter(e -> e.getValue() >= 50)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        
    }
}
