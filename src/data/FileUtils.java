package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;

/** FileUtils
 * Utility class used to read/write to files
 * @version 0.2
 */
public class FileUtils {
    /**
     * writeFile
     * Writes to a file given paramters
     * @param fileName String of filename, no path
     * @param content String of content being written to file
     */
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

    /**
     * readFile
     * Reads file 
     * @param fileName String of file name, no path
     * @return String of file contents
     */
    public static String readFile(String fileName){
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            return(reader.readLine());
        } catch (Exception e){
            e.printStackTrace();
            return("");
        }
    }
}