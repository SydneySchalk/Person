import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class personReader {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();

        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);


        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            String csvFilePath = chooser.getSelectedFile().getAbsolutePath();

            ArrayList<String[]> data = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] rowData = line.split(",");
                    data.add(rowData);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("ID#         Firstname   Lastname    Title       YOB");
            System.out.println("=====================================================");
            for (String[] row : data) {
                for (String cell : row) {

                    System.out.printf("%-12s", cell);
                }
                System.out.println();
            }
        } else {
            System.out.println("No file selected.");
        }
    }
}
