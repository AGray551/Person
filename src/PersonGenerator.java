import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\personData.csv");

        boolean done = false;

        do {
            String ID = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits]: ");
            String firstName = SafeInput.getNonZeroLenString(in, "Enter the first name: ");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter the last name: ");
            String titleName = SafeInput.getNonZeroLenString(in, "Enter the title name: ");
            int birth = SafeInput.getRangedInt(in, "Enter the year of birth: ", 1000, 9999);

            Person person = new Person(ID, firstName, lastName, titleName, birth);
            people.add(person);

            done = SafeInput.getYNConfirm(in, "Are you done?");

        } while (!done);

        for (Person p : people)
            System.out.println(p);

        try {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for (Person person : people) {
                String csvRecord = person.toCSVRecord();
                writer.write(csvRecord, 0, csvRecord.length());
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}