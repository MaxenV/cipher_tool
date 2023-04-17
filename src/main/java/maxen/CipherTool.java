package maxen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CipherTool {
    private int[][] encryptedArray;
    private int[][] decryptedArray;
    private Set<Integer> allowedChars = new HashSet<Integer>(); // allowed characters default a-z,A-Z,0-9
    private String cipherName;
    private String cipherType;
    private int blockSize;
    private BinaryFile encryptedFile;
    private BinaryFile decryptedFile;

    // ANCHOR Counstructors CipherTool
    public CipherTool(int[] encryptedArray, String cipherName, String cipherType) {
        this.cipherName = cipherName;
        this.cipherType = cipherType;
        this.blockSize = 16;
        this.encryptedArray = make_blocks(encryptedArray);

        // Add default allowed chars
        addToAllowedChars(48, 57); // 0-9
        addToAllowedChars(65, 90); // A-Z
        addToAllowedChars(97, 122); // a-z
        addToAllowedChars(10); // endOfLine
        addToAllowedChars(0); // null

        // Create workingFiles
        this.encryptedFile = new BinaryFile("workingFiles/.workingEncrypted", true);
        this.decryptedFile = new BinaryFile("workingFiles/.workingDecrypted", true);
        this.encryptedFile.write_file(encryptedArray);
        updateDecriptFile();

        // openssl enc -aes-256-cbc -d -in ./workingFiles/.worworkingEncrypted -out
        // ./workingFiles/.workingDecrypted -K "abc" -iv "abc"
    }

    /**
     * Pseudo destructor should be called after the object is finished using.
     * Delete hidenfiles.
     */
    protected void destuctor() {
        this.encryptedFile.getBinFile().delete();
        this.decryptedFile.getBinFile().delete();
    }

    // ANCHOR Setters and getters CipherTool
    public int[][] getencryptedArray() {
        return encryptedArray;
    }

    public void setencryptedArray(int[] encryptedArray) {
        this.encryptedArray = make_blocks(encryptedArray);
    }

    public void setencryptedArray(int[][] encryptedArray) {
        this.encryptedArray = encryptedArray;
    }

    public int[][] getEncryptedArray() {
        return encryptedArray;
    }

    public void setEncryptedArray(int[][] encryptedArray) {
        this.encryptedArray = encryptedArray;
    }

    public int[][] getDecryptedArray() {
        return decryptedArray;
    }

    public void setDecryptedArray(int[][] decryptedArray) {
        this.decryptedArray = decryptedArray;
    }

    public String getCipherName() {
        return cipherName;
    }

    public void setCipherName(String cipherName) {
        this.cipherName = cipherName;
    }

    public String getCipherType() {
        return cipherType;
    }

    public void setCipherType(String cipherType) {
        this.cipherType = cipherType;
    }

    public Set<Integer> getAllowedChars() {
        return allowedChars;
    }

    public void setAllowedChars(Set<Integer> allowedChars) {
        this.allowedChars = allowedChars;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    // Adwenced getters, setters
    /**
     * Add one character code into allowed chars
     *
     * @param alChar
     */
    public void addToAllowedChars(int alChar) {
        this.allowedChars.add(alChar);
    }

    /**
     * Add compartment of allowed char codes.
     *
     * @param AlCharFrom
     * @param alCharTo
     */
    public void addToAllowedChars(int AlCharFrom, int alCharTo) {
        for (int i = AlCharFrom; i <= alCharTo; i++) {
            this.allowedChars.add(i);
        }
    }

    // ANCHOR Methods CipherTool

    /**
     * Change one dimensional array of ints into two dimensional array (blocks).
     * Inside demension is defined by this.blockSize.
     *
     * @param characters
     * @return resultArray[][]
     */
    private int[][] make_blocks(int[] characters) {
        ArrayList<int[]> resultList = new ArrayList<int[]>();
        int counter = 0;
        int[] inside = new int[this.blockSize];

        for (int i : characters) {

            inside[counter] = i;
            if (counter >= this.blockSize - 1) {

                resultList.add(inside);
                inside = new int[this.blockSize];
                counter = 0;
                continue;

            }
            counter++;
        }
        resultList.add(inside);

        int[][] resultArray = new int[resultList.size()][this.blockSize];
        resultArray = resultList.toArray(resultArray);

        return resultArray;
    }

    /**
     * Check if given block is broken.
     * Check if every char is in allowedChars.
     *
     * @return isBroken
     */
    public boolean isBroken(int[] block) {
        boolean isBroken = false;

        for (int i : block) {
            if (!this.allowedChars.contains((Integer) i)) {
                isBroken = true;
                break;
            }
        }

        return isBroken;
    }

    public int find_wrong(int[][] arr) {

        for (int i = 0; i < arr.length; i++) {
            if (isBroken(arr[i])) {
                return i;
            }
        }

        return -1;
    }

    private void updateDecriptFile() {
        try {
            String command = String.format("openssl enc -%s-%s -d -in %s -out %s -K \"abc\" -iv \"abc\" ",
                    this.cipherName,
                    this.cipherType,
                    this.encryptedFile.getBinFile().toPath().toString(),
                    this.decryptedFile.getBinFile().toPath().toString());

            BinaryFile.useCommand(command);
        } catch (InterruptedException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
