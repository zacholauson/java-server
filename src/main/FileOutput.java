package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutput {
    public static void write(String baseDirectory, String fileName, String content, Boolean append)  {
        try {
            File file = new File(baseDirectory + fileName);

            if(!file.exists()){
                file.createNewFile();
            }

            FileWriter fileWriter       = new FileWriter(file.getName(), append);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write(content);
            bufferWriter.close();
            fileWriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
