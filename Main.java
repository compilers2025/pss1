import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java Main <file-path>");
            System.exit(1);
        }

        String filePath = args[0];
        try {
            String code = new String(Files.readAllBytes(Paths.get(filePath))); // Read file content
            Scanner scanner = new Scanner(code);

            Token token;
            while ((token = scanner.getToken()) != null) {
                System.out.println(token);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
