/**
 * The <code>PlannerManager</code> class contains the driver method for
 * the application.
 *
 * @author CodeReDarsh
 * <br>email: adarshcp2077@gmail.com
 */

import java.util.Scanner;
public class PlannerManager {

    //The variable in control of terminating the program
    private static boolean runCondition = true;

    //A new empty Planner object
    private static Planner p = new Planner();
    //A new empty Planner object to store a copy of the planner
    private static Planner backup = new Planner();

    /**
     * The driver method
     * @param args
     */
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        while(PlannerManager.runCondition){
            operationsMenu(stdin);
        }

        System.out.println("Program terminating successfully...");
        stdin.close();
    }

    /**
     * This method prints a list of all the available operations and accepts an input
     * @param stdin
     *  The Scanner variable used to accept input
     */
    public static void operationsMenu(Scanner stdin){

        System.out.println("""

                (A) Add Course
                (G) Get Course
                (R) Remove Course
                (P) Print Courses in Planner
                (F) Filter by Department Code
                (L) Look For Course
                (S) Size
                (B) Backup
                (PB) Print Courses in Backup
                (RB) Revert to Backup
                (Q) Quit
                """);

        String input = getString(stdin, "Enter a selection: ");
        System.out.println();

        switch (input.toLowerCase()){

            case "a":
                addCourse(p, stdin);
                break;

            case "g":
                getCourse(p, stdin);
                break;

            case "r":
                removeCourse(p, stdin);
                break;

            case "p":
                p.printAllCourses();
                break;

            case "f":
                filterCourse(p, stdin);
                break;

            case "l":
                plannerSearch(p, stdin);
                break;

            case "s":
                plannerSize(p);
                break;

            case "b":
                plannerBackup();
                break;

            case "pb":
                printBackup();
                break;

            case "rb":
                revert2Backup();
                break;

            case "q":
                runCondition = false;
                break;

            default:
                System.out.println("\nInvalid input!!! please enter letters" +
                        " that are operations from the menu...");
        }

    }

    /**
     * This method is called when the user inputs the (a/A) operation, it adds a new course to the planner at the specified position
     * @param p
     * The list in which the courses are stored
     * @param stdin
     * The Scanner variable used to accept input
     */
    public static void addCourse(Planner p, Scanner stdin) {
        String name = getString(stdin, "Enter course name: ");
        String department = getString(stdin, "Enter department: ");
        int code = getInt(stdin, "Enter course code: ", "Invalid course code!" +
                " Please enter an integer code");
        byte section = getByte(stdin, "Enter course section: ", "Invalid course " +
                "section!! Please enter an integer from 1 to 127");
        String instructor = getString(stdin, "Enter instructor: ");

        Course newCourse = new Course(name, department, code, section, instructor);

        while (true) {
            int position = getInt(stdin, "Enter position: ", "Please enter an" +
                    " integer position for the course");
            try {
                p.addCourse(newCourse, position);
                System.out.print("\n" + newCourse.getDepartment() + " " +
                        newCourse.getCode() + "." + newCourse.getSection() +
                        " successfully added to planner.\n");
                break;
            } catch (FullPlannerException ex) {
                System.out.println("\nCannot add anymore courses as planner is at" +
                        " max capacity!! remove some courses to make space...");
            } catch (IllegalArgumentException ex) {
                System.out.println("\nCould not add course to planner as:");
                if (p.size() == 0) {
                    System.out.println("Invalid position!! as there are currently " +
                            p.size() + " courses in the planner, please add courses in" +
                            " an ascending order sequence starting from 1!!");
                } else {
                    System.out.println("Invalid position!! as there are currently " +
                            p.size() + " courses in the planner,please add courses " +
                            "from either 1 to " + (p.size() + 1));
                }
            }
        }
    }

    /**
     * This method is called when the user inputs the (g/G) operation, to display the information of a course at a given position
     * @param p
     * The list in which the courses are stored
     * @param stdin
     * The Scanner variable used to accept input
     */
    public static void getCourse(Planner p, Scanner stdin){

        try {
            int position = getInt(stdin, "Enter position: ", "Please enter an integer position");
            Course course = p.getCourse(position);
            System.out.printf("%-3s %-30s %-15s %-5s %-10s %-30s %n",
                    "No.", "Course Name", "Department", "Code", "Section", "Instructor");

            System.out.println("------------------------------------------------" +
                    "--------------------------------");
            System.out.println(String.format("%-3d ", position) + course);
        } catch (IllegalArgumentException ex) {
            System.out.println("\nInvalid position!! as there are currently " +
                    p.size() + " courses in the planner,\nplease choose courses " +
                    "from either 1 to " + p.size());
        } catch (EmptyPlannerException ex) {
            System.out.println("\nPlanner is empty!!, please add courses to planner" +
                    "to get course details...");
        }
    }

    /**
     * This method is called when the user inputs the (r/R) operation, it removes a course at a given position
     * @param p
     * The list from which the course is to be removed
     * @param stdin
     * The Scanner variable used to collect input
     */
    public static void removeCourse(Planner p, Scanner stdin){

        int position = getInt(stdin, "Enter position: ", "Please enter an " +
                "integer position.");

        try {
            Course newCourse = p.getCourse(position);
            p.removeCourse(position);
            System.out.println("\n" + newCourse.getDepartment() + " " +
                    newCourse.getCode() + "." + newCourse.getSection() +
                    " successfully removed from planner.");
        } catch (EmptyPlannerException ex){

            System.out.println("\nPlanner is empty!!, please add courses to planner" +
                    "to remove courses...");
        } catch (IllegalArgumentException ex) {
            System.out.print("\nCould not remove course from planner as:\n");

            if (p.size() == 0) {
                System.out.println("Invalid operation!! as there are currently " +
                        "no courses in the planner, please add courses in" +
                        " an ascending order sequence starting from 1 " +
                        "in order to remove courses from it!!");
            } else {
                System.out.println("Invalid position!! as there are currently " +
                        p.size() + " courses in the planner, please add courses " +
                        "from either 1 to " + (p.size() + 1));
            }
        }

    }

    /**
     * This method is called when the user inputs the (f/F) operation, it displays courses that match the given department code
     * @param p
     * The list from which the course is to be removed
     * @param stdin
     * The Scanner variable used to collect input
     */
    public static void filterCourse(Planner p, Scanner stdin){

        String department = getString(stdin, "Enter department code: ");

        Planner.filter(p, department);
    }

    /**
     * This method is called when the user inputs the (l/L) operation, it determines whether the course with the given attributes is in the list.
     * @param p
     * The list form which teh course is to be searched
     * @param stdin
     * The Scanner variable used to collect input
     */
    public static void plannerSearch(Planner p, Scanner stdin){

        String name = getString(stdin, "Enter course name: ");
        String department = getString(stdin, "Enter department: ");
        int code = getInt(stdin, "Enter course code: ", "Invalid course code!!" +
                " please enter an integer codee");
        byte section = getByte(stdin, "Enter course section: ", "Invalid course" +
                " section!!! please enter an integer from 1 to 127");
        String instructor = getString(stdin, "Enter instructor: ");
        Course newCourse = new Course(name, department, code, section, instructor);

        try{
            if(p.exists(newCourse)){
                Course[] plannerCourses = p.getCourses();
                for (int i = 1; i <= p.size(); i++) {
                    if (newCourse.equals(plannerCourses[i])){
                        System.out.println("\n" + newCourse.getDepartment() + " " +
                            newCourse.getCode() + "." + newCourse.getSection()
                            + " is found in the planner at position " + i);
                    break;
                    }
                }
            } else {
                System.out.println("\nCourse not found in planner...");
            }

        } catch (EmptyPlannerException exception){
            System.out.println("\nCourse does not exist as planner is empty...");
        }

    }

    /**
     * This method is called when the user inputs the (s/S) operation, it determines the number of courses in the planner
     * @param p
     * The list whose size is to be determined
     */
    public static void plannerSize(Planner p){
        System.out.print("There are " + p.size() + " courses in the planner\n");
    }

    /**
     * This method is called when the user inputs the (b/B) operation, it creates a copy of the given Planner. Changes to the copy will not affect the original and vice versa.
     */
    public static void plannerBackup(){
        System.out.println("\n Created Backup of the current planner");
        backup = (Planner) (p.clone());
    }

    /**
     * This method is called when the user inputs the (pb/PB) operation, it displays all the courses from the backed-up list.
     */
    public static void printBackup(){
        backup.printAllCourses();
    }

    /**
     * This method is called when the user inputs the (rb/RB) operation, it reverts the current Planner to the backed up copy.
     */
    public static void revert2Backup(){
        p = (Planner) backup.clone();
    }

    /**
     * This method is used to collect integer type input from the terminal/console
     * @param stdin
     * The Scanner variable used to collect input
     * @param instruction
     * The message to be displayed
     * @param invalidMessage
     * The message to be displayed when input is invalid
     * @return
     * The inputted integer
     */
    private static int getInt(Scanner stdin, String instruction,
                              String invalidMessage) {
        while (true) {
            System.out.print(instruction);
            String line = stdin.nextLine();

            try {
                int value = Integer.parseInt(line);
                return value;
            } catch (NumberFormatException ex) {
                System.out.println(invalidMessage);
            }
        }
    }

    /**
     * This method is used to collect String type input from the terminal/console
     * @param stdin
     * The Scanner variable used to collect input
     * @param instruction
     * The message to be displayed
     * @return
     * The inputted String
     */
    private static String getString(Scanner stdin, String instruction) {
        System.out.print(instruction);
        String line = "";

        while (line.isBlank() || line.isEmpty()) {
            line = stdin.nextLine();
        }

        return line;
    }

    /**
     * This method is used to collect byte type input from the terminal/console
     * @param stdin
     * The Scanner variable used to collect input
     * @param instruction
     * The message to be displayed
     * @param invalidMessage
     * The message to be displayed when input is invalid
     * @return
     * The inputted byte type number
     */
    private static byte getByte(Scanner stdin, String instruction,
                                String invalidMessage) {
        while (true) {
            System.out.print(instruction);
            String line = stdin.nextLine();

            try {
                byte value = Byte.parseByte(line);
                return value;
            } catch (NumberFormatException ex) {
                System.out.println(invalidMessage);
            }
        }
    }

}
