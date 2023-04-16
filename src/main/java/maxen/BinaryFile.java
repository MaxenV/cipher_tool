package maxen;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFile {

    private Integer[] binaryArray;
    private File BinFile;

    public BinaryFile(String fileName) {

        try {
            this.BinFile = new File(fileName);

            setBinaryArray(readFromFile(this.BinFile));
        } catch (FileNotFoundException e) {
            System.out.println("File was not found.");
            // e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Can't create binary array from file content.");
            // e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Something went wrong");
            System.out.println(e.getMessage());
        } finally {
        }
    }

    public BinaryFile(String fileName, boolean create) {

        try {
            this.BinFile = new File(fileName);

            if (create) {
                this.BinFile.createNewFile();
            }

            setBinaryArray(readFromFile(this.BinFile));
        } catch (FileNotFoundException e) {
            System.out.println("File was not found.");
            // e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Can't create binary array from file content.");
            // e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Something went wrong");
            System.out.println(e.getMessage());
        } finally {
        }
    }

    private List<Integer> readFromFile(File input_file) throws IOException {
        FileInputStream read;
        List<Integer> listOfBytes = new ArrayList<Integer>();

        read = new FileInputStream(input_file);
        DataInputStream r = new DataInputStream(read);
        int byt;
        while ((byt = r.read()) != -1) {
            listOfBytes.add(byt);
        }

        read.close();
        return listOfBytes;
    }

    private void setBinaryArray(List<Integer> listInt) {

        this.binaryArray = new Integer[listInt.size()];
        this.binaryArray = listInt.toArray(this.binaryArray);
    }

    public Integer[] getBinaryArray() {
        return binaryArray;
    }

    public String get_text() {
        String result = "";

        for (int byteInt : binaryArray) {
            result += (char) byteInt;
        }

        return result;
    }

    public File getBinFile() {
        return BinFile;
    }

    public void setBinFile(File binFile) {
        this.BinFile = binFile;
        try {
            setBinaryArray(readFromFile(this.BinFile));
        } catch (IOException e) {
            System.out.println("Can't create binary array from file content.");
            // e.printStackTrace();
        }
    }

    static public void write_file(String filePath, Integer[] content) throws FileNotFoundException, IOException {
        FileOutputStream write = new FileOutputStream(filePath);
        DataOutputStream w = new DataOutputStream(write);

        for (Integer charWrite : content) {
            w.writeByte(charWrite);
        }
        w.close();
    }

}