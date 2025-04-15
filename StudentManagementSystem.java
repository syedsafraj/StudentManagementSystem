import java.util.*;

class Student {
    int id;
    String name;
    String department;
    ArrayList<String[]> attendance = new ArrayList<>();

    Student(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    void showInfo() {
        System.out.println("ID: " + id + ", Name: " + name + ", Department: " + department);
    }

    void showAttendance() {
        if (attendance.isEmpty()) {
            System.out.println("No attendance recorded.");
        } else {
            for (String[] record : attendance) {
                System.out.println("Date: " + record[0] + " - " + record[1]);
            }
        }
    }
}

public class StudentManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<String[]> timetable = new ArrayList<>();
    static ArrayList<String[]> examSchedule = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n---- Login ----");
            System.out.println("1. Admin");
            System.out.println("2. Faculty");
            System.out.println("3. Student");
            System.out.println("4. Exit");
            System.out.print("Select role: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: adminPanel(); break;
                case 2: facultyPanel(); break;
                case 3: studentPanel(); break;
                case 4: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void adminPanel() {
        while (true) {
            System.out.println("\n-- Admin Panel --");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Set Timetable");
            System.out.println("4. Set Exam Schedule");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1: addStudent(); break;
                case 2: viewStudents(); break;
                case 3: setTimetable(); break;
                case 4: setExamSchedule(); break;
                case 5: return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    static void addStudent() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        students.add(new Student(id, name, dept));
        System.out.println("Student added.");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            for (Student s : students) {
                s.showInfo();
            }
        }
    }

    static void setTimetable() {
      
        int days = 5;  
        int periods = 8; 
    
        for (int i = 1; i <= days; i++) {
            System.out.print("Enter Day " + i + " (e.g., Monday): ");
            String day = sc.nextLine();
    
            
            for (int j = 1; j <= periods; j++) {
                System.out.print("Enter subject for Period " + j + " of " + day + ": ");
                String subject = sc.nextLine();
                timetable.add(new String[]{day, subject});
            }
            System.out.println(day + " timetable added.\n");
        }
        System.out.println("Timetable updated.");
    }

    static void setExamSchedule() {
        System.out.print("How many exams to add? ");
        int count = sc.nextInt(); sc.nextLine();
        for (int i = 0; i < count; i++) {
            System.out.print("Enter Date: ");
            String date = sc.nextLine();
            System.out.print("Enter Subject: ");
            String subject = sc.nextLine();
            examSchedule.add(new String[]{date, subject});
        }
        System.out.println("Exam schedule updated.");
    }

    static void facultyPanel() {
        while (true) {
            System.out.println("\n-- Faculty Panel --");
            System.out.println("1. Mark Attendance");
            System.out.println("2. View Attendance");
            System.out.println("3. Back");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1: markAttendance(); break;
                case 2: viewAllAttendance(); break;
                case 3: return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    static void markAttendance() {
        System.out.print("Enter Date (dd-mm-yyyy): ");
        String date = sc.nextLine();
        for (Student s : students) {
            System.out.print("Is " + s.name + " present? (yes/no): ");
            String ans = sc.nextLine();
            s.attendance.add(new String[]{date, ans.equalsIgnoreCase("yes") ? "Present" : "Absent"});
        }
        System.out.println("Attendance marked.");
    }

    static void viewAllAttendance() {
        for (Student s : students) {
            System.out.println("Attendance for " + s.name + ":");
            s.showAttendance();
        }
    }

    static void studentPanel() {
        System.out.print("Enter your ID: ");
        int id = sc.nextInt(); sc.nextLine();
        Student s = null;
        for (Student st : students) {
            if (st.id == id) {
                s = st;
                break;
            }
        }

        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        while (true) {
            System.out.println("\n-- Student Panel --");
            System.out.println("1. View My Attendance");
            System.out.println("2. View Timetable");
            System.out.println("3. View Exam Schedule");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1: s.showAttendance(); break;
                case 2: viewTimetable(); break;
                case 3: viewExamSchedule(); break;
                case 4: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void viewTimetable() {
        if (timetable.isEmpty()) {
            System.out.println("No timetable added.");
        } else {
            System.out.println("Day\tSubject");
            for (String[] entry : timetable) {
                System.out.println(entry[0] + "\t" + entry[1]);
            }
        }
    }

    static void viewExamSchedule() {
        if (examSchedule.isEmpty()) {
            System.out.println("No exam schedule added.");
        } else {
            System.out.println("Date\tSubject");
            for (String[] entry : examSchedule) {
                System.out.println(entry[0] + "\t" + entry[1]);
            }
        }
    }
}
