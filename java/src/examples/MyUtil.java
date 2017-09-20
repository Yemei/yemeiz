package examples;

import java.io.BufferedReader;
import java.io.FileReader;

public final class MyUtil {
    private MyUtil(){
        throw new AssertionError();
    }
    public static int countWordInFiles(String filename,String word){
        int count=0;
        try(FileReader fr=new FileReader(filename)){
            try(BufferedReader br=new BufferedReader(fr)){
                String line=null;
                while((line=br.readLine())!=null){
                    int index=-1;
                    if(line.length()>word.length() && (index=line.indexOf(word))>=0){
                        count++;
                        line=line.substring(index+word.length());
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }
    public static void main(String[] args){
       String filename="D:\\test1.txt";
       String word="bb";
       int count=MyUtil.countWordInFiles(filename,word);
       System.out.println(count);
    }
}
