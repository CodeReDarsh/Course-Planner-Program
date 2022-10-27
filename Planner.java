/**
 * The <code>Planner</code> class constructs and contains all the methods to
 * manipulate <code>Planner</code> objects which store <code>Course</code> objects. It represents
 * a planner storing courses.
 *
 * @author CodeReDarsh
 * <br>email: adarshcp2077@gmail.com
 **/
public class Planner {
    //The max limit for the number of courses that can be in a planner
    public final int MAX_COURSES = 50;

    //The number of courses currently in the planner
    private int itemsCurrentlyInList;

    //An array of Course type containing the information about all the courses in the form of course objects
    private Course[] courses;

    /**
     * This the default constructor used to create a new Planner object
     */
    public Planner() {
        this.courses = new Course[MAX_COURSES + 1];
        this.itemsCurrentlyInList = 0;
    }

    /**
     * This is a constructor used to create a new Planner object with no course objects in it
     * @param courses
     *  The array containing all the courses stored in the planner
     */
    public Planner (Course[] courses){
        this.courses = courses;
        itemsCurrentlyInList = 0;
    }

    /**
     * This method determines the number of courses currently in the list
     * @return itemsCurrentlyInList
     *  The number of courses in the list
     */
    public int size(){
        return itemsCurrentlyInList;
    }

    /**
     * This method adds new courses to the planner
     * @param newCourse
     *  The Course object to be added
     * @param position
     *  The position at which the course should be added
     * @throws FullPlannerException
     *  when the planner is full
     * @throws IllegalArgumentException
     *  when an incorrect position is not within valid range
     */
    public void addCourse(Course newCourse, int position)
            throws FullPlannerException{

        if(itemsCurrentlyInList >= MAX_COURSES)
            throw new FullPlannerException("Indicates that there is no more " +
                    "room in the Planner to record an additional course");

        if(position < 1 || position > itemsCurrentlyInList + 1)
            throw new IllegalArgumentException("Position is not within valid" +
                    "range");

        if(position == itemsCurrentlyInList + 1)
            courses[itemsCurrentlyInList + 1] = newCourse;
        else {
            for (int i = itemsCurrentlyInList; i >= position; i--)
                courses[i + 1] = courses[i];
            courses[position] = newCourse;
        }

        itemsCurrentlyInList++;
    }

    /**
     * This method adds a new course to the end of the list of courses currently in the planner
     * @param newCourse
     *  The course to be added
     * @throws FullPlannerException
     *  When the planner is full
     */
    public void addCourse(Course newCourse) throws FullPlannerException {
        addCourse(newCourse, (itemsCurrentlyInList + 1));
    }

    /**
     * This method removes courses from a desired position in the planner
     * @param position
     *  The position from which a course should be removed
     * @throws EmptyPlannerException
     *  When there are no courses in the planner
     * @throws IllegalArgumentException
     *  When the input position is not within valid range
     */
    public void removeCourse(int position) throws EmptyPlannerException{

        if(itemsCurrentlyInList == 0)
            throw new EmptyPlannerException("No items in list");

        if(position < 1 || position > itemsCurrentlyInList)
            throw new IllegalArgumentException("Position is not within valid" +
                    "range");
        else {
            for (int i = position; i < itemsCurrentlyInList; i++)
                courses[i] = courses[i + 1];

            courses[itemsCurrentlyInList] = null;
        }
        itemsCurrentlyInList--;
    }

    /**
     * This method returns the information of a course stored in a particular position in the planner
     * @param position
     *  The position where the course is stored
     * @return
     * The details of the course at the specified position
     * @throws EmptyPlannerException
     *  when the planner is empty
     * @throws IllegalArgumentException
     *  when the input position is not within valid range
     */
    public Course getCourse(int position) throws EmptyPlannerException {

        if(this.itemsCurrentlyInList == 0)
            throw new EmptyPlannerException("No items in list");

        if(position < 1 || position > itemsCurrentlyInList)
            throw new IllegalArgumentException("Position is not within valid" +
                    "range");

        return this.courses[position];
    }

    /**
     * This method prints all the courses that are within the specified department
     * @param planner
     *  the list of courses to search in
     * @param department
     *  the 3 letter department code for a Course
     */
    public static void filter(Planner planner, String department){

        System.out.printf("%-3s %-30s %-15s %-5s %-10s %-30s %n",
                "No.", "Course Name", "Department", "Code", "Section",
                "Instructor");

        System.out.println("------------------------------------------------" +
                "--------------------------------");

        for (int i = 1; i <= planner.itemsCurrentlyInList; i++) {
            if((planner.courses[i].getDepartment()).equals(department)) {
                System.out.println(String.format("%-3d ", i)
                        + planner.courses[i]);
            }
        }
    }

    /**
     * This method checks whether a certain course is already in the list
     * @param course
     *  The course we are looking for
     * @return
     * True if the Planner contains this Course, false otherwise
     * @throws EmptyPlannerException
     *  When there are no courses in the list, hence false by default
     */
    public boolean exists(Course course) throws EmptyPlannerException{

        if(this.itemsCurrentlyInList == 0)
            throw new EmptyPlannerException("No items in list");

        for (int i = 1; i <= itemsCurrentlyInList; i++) {
            if(courses[i].equals(course))
                return true;
        }

        return false;
    }

    /**
     * Creates a copy of this Planner. Subsequent changes to the copy will not affect the original and vice versa.
     * Preconditions:
     * This Planner object has been instantiated.
     * @return
     * A copy (backup) of this Planner object
     */
    public Object clone(){
        Planner copy = new Planner();

        for (int i = 1; i <= this.itemsCurrentlyInList; i++){
            copy.courses[i] = (Course) ((this.courses[i]).clone());
        }
        copy.itemsCurrentlyInList = this.itemsCurrentlyInList;
        return copy;
    }

    /**
     * This method prints a neatly formatted table of each item in the list with its position number than shown in the sample output.
     * Preconditions:
     * This Planner has been instantiated.
     * Postconditions:
     * Displays a neatly formatted table of each course from the Planner.
     */
    public void printAllCourses(){
        System.out.println(this);
    }

    /**
     * Gets the String representation of this Planner object, which is a neatly formatted table of each Course in the Planner on its own line with its position number, as shown in the sample output.
     * @return
     * The String representation of this Planner object.
     */
    public String toString(){
        String ret = String.format("%-3s %-30s %-15s %-5s %-10s %-30s" +
                        "%n---------------------------------------------------------------------------------- %n",
                        "No.", "Course Name", "Department", "Code",
                "Section", "Instructor"); ;
        for (int i = 1; i <= this.itemsCurrentlyInList; i++) {
            ret += (String.format("%3d ", i)
                    + this.courses[i]);
            ret += "\n";
        }
        return ret;
    }

    /**
     * This method checks whether the courses in the planner being compared are the same
     * @param o
     *  The list being compared
     * @return
     *  true if they are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planner planner = (Planner) o;
        for (int i = 0; i < planner.itemsCurrentlyInList; i++) {
            if(!((planner.courses[i]).equals(this.courses[i])))
                return false;
        }
        return true;
    }

    /**
     * This is a getter method for the array containing all the courses
     * @return
     *  The course array
     */
    public Course[] getCourses() {
        return courses;
    }

    /**
     * Sets the course array
     * @param courses
     *  The courses the list is set to
     */
    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

}
