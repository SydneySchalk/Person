import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {
   public static void main(String[] args) {

      ArrayList<Person> peopleList = new ArrayList<>();
      Scanner in = new Scanner(System.in);
      String filePath = "C:\\Users\\tadpo\\IdeaProjects\\Person\\src\\personData.txt";

      boolean done = false;

      do {

         String IDNum = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits]");
         String firstName = SafeInput.getNonZeroLenString(in, "Enter the first name");
         String lastName = SafeInput.getNonZeroLenString(in, "Enter the last name");
         String title = SafeInput.getNonZeroLenString(in, "Enter the title");
         int YOB = SafeInput.getRangedInt(in, "Enter the year of birth", 1000,9999);

         done = SafeInput.getYNConfirm(in, "Are you done?");

         Person person = new Person(IDNum, firstName, lastName, title, YOB);
         peopleList.add(person);
      } while (!done);


      in.close();


      try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
         for (Person person : peopleList) {

            String csvRecord = String.format("%s,%s,%s,%s,%d\n", person.getIDNum(), person.getFirstName(),
                    person.getLastName(), person.getTitle(), person.getYOB());
            writer.write(csvRecord);
         }
      } catch (IOException e) {
         e.printStackTrace();
      }

      System.out.println("Data written to " + filePath);
   }
}
