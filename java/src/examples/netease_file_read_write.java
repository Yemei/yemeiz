package examples;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class netease_file_read_write {
    public void containsWord(String inFile, String outFile, String word) {
        try (FileReader fr = new FileReader(inFile)) {
            try (BufferedReader br = new BufferedReader(fr)) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    int index = -1;
                    while (line.length() > word.length() && (index = line.indexOf(word)) >= 0)
                        writeToFile(outFile, line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String filename, String line) {
        try (FileWriter fw = new FileWriter(filename)) {
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String inFile = "D:\\test1.txt";
        String outFile = "D:\\test2.txt";
        String word = "error";
        new netease_file_read_write().containsWord(inFile, outFile, word);
    }
}
