package Assigment2;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Assignment2 {
    /*
    a program to count how many files/folders are there inside one folder.
    •	the count method should take a parameter called Criteria like this: count(Criteria criteria){}
    •	For Criteria class, multiple conditions should be included such as: folder path, includeSubFolder or not, the extension of the file be counted and so on.
    •	Optional: Take the input from keyboard.
    •	Take care of the invalid inputs. Exception handling.
    •	Get proper result displayed.
    ”There are XXX file(s) and XXX folder(s) inside folder XXX with extension XXX.” or something user friendly.
     */
    public static int[] count(Criteria criteria) {
        int fileCount = 0;
        int folderCount = 0;
        try {
            Path path = Paths.get(criteria.folderPath);
            if (Files.isDirectory(path)) {
                DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(criteria.folderPath));
                for (Path subPath : stream) {
                    if (Files.isRegularFile(subPath) && subPath.toString().toLowerCase().endsWith(criteria.extension)) {
                        fileCount++;
                    }
                    if (Files.isDirectory(subPath)) {
                        folderCount += 1;
                        if (criteria.includeSubFolder) {
                            int[] result = count(new Criteria(subPath.toString(), criteria.includeSubFolder, criteria.extension));
                            fileCount += result[0];
                            folderCount += result[1];
                        }
                    }

                }
            } else {
                throw new IllegalArgumentException("Path is not folder path");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new int[] {fileCount, folderCount};
    }

    public static void main(String[] args) throws IOException {
        int[] arr = null;
        Criteria criteria = new Criteria("C:/Users/Owner/Pictures", true, ".PNG");
        arr = count(criteria);
        System.out.println(arr[0]);
        if (arr[0] != 0) {
            String inform = String.format("There are %d file(s) with extension %s and %d folder(s) inside folder %s ",
                    arr[0], criteria.extension,arr[1], criteria.folderPath);
            System.out.println(inform);
        }
    }
}

class Criteria {
    String folderPath;
    Boolean includeSubFolder;
    String extension;

    Criteria(String path, Boolean include, String ext ) {
        folderPath = path;
        includeSubFolder = include;
        extension = ext.toLowerCase();
    }
}
