package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;

/* FileUtils
 * Utility class used to read/write to files
 */
public class FileUtils {

    public static void writeFile(String fileName, String content){
 		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.write(content);
		} catch (FileNotFoundException f){
            try{
                File newFile = new File(fileName);
                newFile.createNewFile();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
         catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String readFile(String fileName){
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            return(reader.readLine());
        } catch (Exception e){
            e.printStackTrace();
            return("");
        }
    }
}


/*
 		try (BufferedWriter writer = new BufferedWriter(new FileWriter(PASSWORD_FILE))) {
			writer.write(changePwBox.getText());
		}
 */