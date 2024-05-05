package others;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuditManagement {
    public static void writeToFile( String row) {
        try (FileWriter fw = new FileWriter("src/others/audit.csv", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(row);
            bw.write(";");
            bw.write(LocalDateTime.now() + "" );
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}