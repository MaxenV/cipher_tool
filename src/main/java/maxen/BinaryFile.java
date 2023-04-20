package maxen;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFile {

    private int[] binaryArray;
    private File BinFile;

    public BinaryFile(String filePath) {

        try {
            this.BinFile = new File(filePath);

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

    public BinaryFile(String filePath, boolean create) {

        try {
            this.BinFile = new File(filePath);

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

    private int[] readFromFile(File input_file) throws IOException {
        FileInputStream read;
        List<Integer> listOfBytes = new ArrayList<Integer>();

        read = new FileInputStream(input_file);
        DataInputStream r = new DataInputStream(read);
        int byt;
        while ((byt = r.read()) != -1) {
            listOfBytes.add(byt);
        }

        read.close();
        int[] result = listOfBytes.stream().mapToInt(i -> i).toArray();

        return result;

    }

    private void setBinaryArray(int[] binaryArray) {

        this.binaryArray = binaryArray;
    }

    public int[] getBinaryArray() {
        return this.binaryArray;
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

    public void write_file(Integer[] content) throws FileNotFoundException, IOException {
        FileOutputStream write = new FileOutputStream(this.BinFile.toPath().toString());
        DataOutputStream w = new DataOutputStream(write);

        for (Integer charWrite : content) {
            w.writeByte(charWrite);
        }
        w.close();
    }

    public void write_file(int[] content) {
        FileOutputStream write;
        try {
            write = new FileOutputStream(this.BinFile.toPath().toString());
            DataOutputStream w = new DataOutputStream(write);

            for (int charWrite : content) {
                w.writeByte(charWrite);
            }
            w.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write_file(int[][] content) {
        FileOutputStream write;
        try {
            write = new FileOutputStream(this.BinFile.toPath().toString());
            DataOutputStream w = new DataOutputStream(write);

            for (int[] block : content) {
                for (int charWrite : block) {

                    w.writeByte(charWrite);
                }
            }
            w.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static public void useCommand(String commandText) throws IOException, InterruptedException {

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", commandText);
        Process pr = processBuilder.start();
        pr.waitFor();
    }

}