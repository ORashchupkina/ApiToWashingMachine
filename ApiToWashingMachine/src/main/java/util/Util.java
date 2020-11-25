package util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Util {

    int amountNewTasks = 100;

    // creating directory in user directory with name WashingMachine and folders
    public String creationDirectoryInCommonDir(String sTailDir) {
        String sUserDir = System.getProperty("user.home");
        String sCommonDir = sUserDir + "/WashingMachine";
        String sNewFullDir = sCommonDir + sTailDir;

        try {
            Path pathNewDir = Paths.get(sNewFullDir);
            if (!pathNewDir.toFile().exists()) {
                Files.createDirectories(pathNewDir);
                return pathNewDir.toString();
            } else
                return pathNewDir.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // creating new file in directory with new name of counter + suffix
    public String createNewFileWithNewName(String sFullDir, String suffix) {
        try {
            if (Paths.get(sFullDir).toFile().exists()) {
                for (int i = 0; i < amountNewTasks; i++) {
                    Path pathTaskDir = Paths.get(sFullDir + "/" + i + suffix);
                    if (!pathTaskDir.toFile().exists()) {
                        Files.createFile(pathTaskDir);
                        System.out.println("File " + pathTaskDir + " Created");
                        return pathTaskDir.toString();
                    }
                }
                throw new UserException("can't add new tasks. cause: many tasks");
            } else {
                throw new UserException("no directory: " + sFullDir);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createFile(String newFileWithDir) {
        try {
            Path pathTaskDir = Paths.get(newFileWithDir);
            if (!pathTaskDir.toFile().exists()) {
                Files.createFile(pathTaskDir);
                System.out.println("File " + pathTaskDir + " Created");
                return true;
            }
            else
                return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void writeToFile(String fileName, String text, Boolean isLineBreak) throws IOException {
        FileWriter writer = new FileWriter(fileName, true);
        writer.write(text);
        if(isLineBreak)
            writer.append("\n");
        writer.flush();
        writer.close();
    }

    public String readFrFile(String fileName) {
        StringBuilder resultBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(fileName)) {
            char[] buf = new char[256];
            int c;
            while ((c = reader.read(buf)) > 0) {
                if (c < 256) {
                    buf = Arrays.copyOf(buf, c);
                }
                System.out.print(buf);
                resultBuilder.append(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return resultBuilder.toString();
    }

    // get first file's name from directory
    public String getNameOfFileFrDir(String sFullDir, String suffix) {
        try {
            if (Paths.get(sFullDir).toFile().exists()) {
                for (int i = 0; i < amountNewTasks; i++) {
                    Path pathTaskDir = Paths.get(sFullDir + "/" + i + suffix);
                    if (pathTaskDir.toFile().exists()) {
                        System.out.println("File " + pathTaskDir + " found");
                        return pathTaskDir.toString();
                    }
                }
                return "no tasks";
            } else {
                throw new UserException("no directory: " + sFullDir);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void recursiveDelete(File dir) {
        if(dir.isDirectory()) {
            File[] files = dir.listFiles();
            for(File f: files){
                recursiveDelete(f);
            }
        }
        dir.delete();
    }
}
