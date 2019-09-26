import by.belhard.j16.maven_example.DBManager;
import by.belhard.j16.maven_example.Person;
import by.belhard.j16.maven_example.Specialty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.SQLException;

public class Main {

    private static BufferedReader rdr =
            new BufferedReader(new InputStreamReader(System.in));
    private static DBManager dbManager;

    public static void main(String[] args) {

        try {
            dbManager = new DBManager();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String input = "";

        try {
            while (!input.equals("e")) {
                input = rdr.readLine();

                switch (input) {
                    case "a":
                        addNewPerson();
                        break;
                    case "d":
                        removePerson();
                        break;
                    case "l":
                        getAllPersons();
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQLException");
        }
    }

    private static void getAllPersons() throws SQLException {

        dbManager.getAll().forEach(System.out::println);
    }

    private static void removePerson() throws IOException, SQLException {

        System.out.println("Enter id");

        int id = Integer.parseInt(rdr.readLine());

        dbManager.removeById(id);
    }

    private static void addNewPerson() throws IOException, SQLException {

        System.out.println("Enter name, age, specialtyId");
        String[] splitted = rdr.readLine().split(" ");

        Person person = new Person();
        person.setName(splitted[0]);
        person.setAge(Integer.parseInt(splitted[1]));
        person.setDateOfEmployment(Date.valueOf("1989-5-9"));
        person.setSpecialty(Specialty.builder()
                .id(Integer.parseInt(splitted[2])).build());

        dbManager.create(person);
    }


}
