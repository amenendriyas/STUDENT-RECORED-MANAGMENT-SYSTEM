import java.io.*;
import java.util.*;

public class StudentManagementSystem {

    private static final String DIRECTORY = "StudentData";
    private static final String TEXT_FILE = DIRECTORY + "/students.txt";
    private static final String BINARY_FILE = DIRECTORY + "/students.dat";
    private static final String OBJECT_FILE = DIRECTORY + "/students.ser";
    private static final String BACKUP_FILE = DIRECTORY + "/backup.ser";

    private static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {

        createFiles();

        loadObjects();

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== STUDENT RECORD MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Generate Report");
            System.out.println("7. File Properties");
            System.out.println("8. Backup Records");
            System.out.println("9. Exit");

            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addStudent(sc);
                    break;

                case 2:
                    searchStudent(sc);
                    break;

                case 3:
                    updateStudent(sc);
                    break;

                case 4:
                    deleteStudent(sc);
                    break;

                case 5:
                    displayStudents();
                    break;

                case 6:
                    generateReport();
                    break;

                case 7:
                    displayFileProperties();
                    break;

                case 8:
                    backupRecords();
                    break;

                case 9:
                    saveTextFile();
                    saveBinaryFile();
                    saveObjects();
                    System.out.println("Data Saved.");
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    private static void createFiles() {

        try {
            File dir = new File(DIRECTORY);

            if (!dir.exists())
                dir.mkdir();

            new File(TEXT_FILE).createNewFile();
            new File(BINARY_FILE).createNewFile();
            new File(OBJECT_FILE).createNewFile();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addStudent(Scanner sc) {

        try {
            System.out.print("Student ID: ");
            String id = sc.nextLine();

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Department: ");
            String dept = sc.nextLine();

            System.out.print("GPA: ");
            double gpa = sc.nextDouble();
            sc.nextLine();

            students.add(new Student(id, name, dept, gpa));

            System.out.println("Student Added.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchStudent(Scanner sc) {

        System.out.print("Enter ID: ");
        String id = sc.nextLine();

        for (Student s : students) {
            if (s.getStudentId().equals(id)) {
                System.out.println(s);
                return;
            }
        }

        System.out.println("Student Not Found.");
    }

    private static void updateStudent(Scanner sc) {

        System.out.print("Enter ID: ");
        String id = sc.nextLine();

        for (Student s : students) {

            if (s.getStudentId().equals(id)) {

                System.out.print("New Name: ");
                s.setName(sc.nextLine());

                System.out.print("New Department: ");
                s.setDepartment(sc.nextLine());

                System.out.print("New GPA: ");
                s.setGpa(sc.nextDouble());
                sc.nextLine();

                System.out.println("Updated Successfully.");
                return;
            }
        }

        System.out.println("Student Not Found.");
    }

    private static void deleteStudent(Scanner sc) {

        System.out.print("Enter ID: ");
        String id = sc.nextLine();

        Iterator<Student> iterator = students.iterator();

        while (iterator.hasNext()) {

            Student s = iterator.next();

            if (s.getStudentId().equals(id)) {
                iterator.remove();
                System.out.println("Deleted Successfully.");
                return;
            }
        }

        System.out.println("Student Not Found.");
    }

    private static void displayStudents() {

        if (students.isEmpty()) {
            System.out.println("No Records.");
            return;
        }

        for (Student s : students)
            System.out.println(s);
    }

    private static void generateReport() {

        if (students.isEmpty()) {
            System.out.println("No Records.");
            return;
        }

        double highest = students.get(0).getGpa();
        double lowest = students.get(0).getGpa();
        double total = 0;

        for (Student s : students) {

            if (s.getGpa() > highest)
                highest = s.getGpa();

            if (s.getGpa() < lowest)
                lowest = s.getGpa();

            total += s.getGpa();
        }

        System.out.println("\n===== REPORT =====");
        System.out.println("Total Students: " + students.size());
        System.out.println("Highest GPA: " + highest);
        System.out.println("Lowest GPA: " + lowest);
        System.out.println("Average GPA: " + (total / students.size()));
    }

    private static void saveTextFile() {

        try (PrintWriter pw = new PrintWriter(TEXT_FILE)) {

            for (Student s : students) {

                pw.println(
                        s.getStudentId() + "," +
                        s.getName() + "," +
                        s.getDepartment() + "," +
                        s.getGpa());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void saveBinaryFile() {

        try (DataOutputStream dos =
                     new DataOutputStream(
                             new FileOutputStream(BINARY_FILE))) {

            dos.writeInt(students.size());

            for (Student s : students) {

                dos.writeUTF(s.getStudentId());
                dos.writeUTF(s.getName());
                dos.writeUTF(s.getDepartment());
                dos.writeDouble(s.getGpa());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void saveObjects() {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(
                             new FileOutputStream(OBJECT_FILE))) {

            oos.writeObject(students);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadObjects() {

        try (ObjectInputStream ois =
                     new ObjectInputStream(
                             new FileInputStream(OBJECT_FILE))) {

            students = (ArrayList<Student>) ois.readObject();

        } catch (Exception ignored) {
        }
    }

    private static void displayFileProperties() {

        File file = new File(OBJECT_FILE);

        System.out.println("File Name: " + file.getName());
        System.out.println("Path: " + file.getAbsolutePath());
        System.out.println("Size: " + file.length() + " bytes");
        System.out.println("Last Modified: " + new Date(file.lastModified()));
    }

    private static void backupRecords() {

        try (
                BufferedInputStream bis =
                        new BufferedInputStream(
                                new FileInputStream(OBJECT_FILE));

                BufferedOutputStream bos =
                        new BufferedOutputStream(
                                new FileOutputStream(BACKUP_FILE))
        ) {

            int data;

            while ((data = bis.read()) != -1) {
                bos.write(data);
            }

            System.out.println("Backup Created Successfully.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}