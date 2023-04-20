package maxen;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        BinaryFile sz_testFile = new BinaryFile("workingFiles/zs_damage_test.txt", false);
        CipherTool example = new CipherTool(sz_testFile.getBinaryArray(), "aes-256", "cbc", "abc", "abc");

        System.out.println("Damaged:");
        System.out.println(example.getDecryptedFile().get_text());
        System.out.println("\n\n");

        example.repair();

        System.out.println("Fixed:");
        System.out.println(example.getDecryptedFile().get_text());

        // Delete program files
        example.destuctor();
    }
}
