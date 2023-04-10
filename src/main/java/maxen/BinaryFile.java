package maxen;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFile {

    private Integer[] binaryArray;

    public BinaryFile(String fileName) {

        try {
            File file = new File(fileName);

            setBinary(file);
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

        System.out.println("End of reading");
    }

    public void setBinary(File input_file) throws IOException, Exception {
        FileInputStream read;
        List<Integer> listOfBytes = new ArrayList<Integer>();

        read = new FileInputStream(input_file);
        DataInputStream r = new DataInputStream(read);
        int byt;
        while ((byt = r.read()) != -1) {
            listOfBytes.add(byt);
        }

        read.close();
        this.binaryArray = new Integer[listOfBytes.size()];
        this.binaryArray = listOfBytes.toArray(this.binaryArray);
    }

    public void setBinary(ArrayList<Integer> inputArray) throws Exception {
        boolean correct = true;
        for (Integer abyte : inputArray.toArray(this.binaryArray)) {
            if (abyte >= 0 && abyte <= 255) {
                correct = false;
                break;
            }
        }
        if (correct) {
            this.binaryArray = inputArray.toArray(this.binaryArray);
        } else {
            throw new Exception("Wrong input array");
        }
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

    static public void write_file(String filePath, Integer[] content) throws FileNotFoundException, IOException {
        FileOutputStream write = new FileOutputStream(filePath);
        DataOutputStream w = new DataOutputStream(write);

        for (Integer charWrite : content) {
            w.writeByte(charWrite);
        }
        w.close();
    }
}