package Day4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.Buffer;
import java.util.*;
public class CountWords {
    public static void main(String[] args) {
        try(BufferedReader br=new BufferedReader(new FileReader("Day4/OutputTemp.txt"))){
            String line;
            HashMap<String,Integer> wordCount=new HashMap<>();
            while((line=br.readLine())!=null){
                String[] words=line.split(" ");
                for(String word:words){
                    word=word.toLowerCase();
                    if(wordCount.containsKey(word)){
                        wordCount.put(word,wordCount.get(word)+1);
                    }
                    else{
                        wordCount.put(word,1);
                    }
                }
            }
            System.out.println("Word Count:");
            for(Map.Entry<String,Integer> entry:wordCount.entrySet()){
                System.out.println(entry.getKey()+": "+entry.getValue());
            }
    }    catch(Exception e){
            System.out.println("An error occurred: "+e.getMessage());
        }
    }
}
