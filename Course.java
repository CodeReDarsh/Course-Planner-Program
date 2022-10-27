/**
 * The <code>Course</code> class constructs and contains all the methods to
 * manipulate <code>Course</code> objects. It represents a course which has a name, department,
 * code, section and instructor.
 *
 * @author CodeReDarsh
 * <br>email: adarshcp2077@gmail.com
 **/
public class Course implements Cloneable{
    private String name;
    private String department;
    private int code;
    private byte section;
    private String instructor;

    /**
     * This is a constructor used to create a new Course object
     * @param name
     *  The name of the course
     * @param department
     *  The department to which the course belongs
     * @param code
     *  The course code
     * @param section
     *  The course section
     * @param instructor
     *  The instructor for the course
     */
    public Course (String name, String department, int code, byte section,
                   String instructor){
        this.name = name;
        this.department = department;
        this.code = code;
        this.section = section;
        this.instructor = instructor;
    }

    /**
     * This method creates a shallow copy of a Course object
     * @return
     * A copy of the course object it was invoked by
     */
    public Object clone(){
        return new Course(this.name, this.department, this.code,
                this.section, this.instructor);
    }

    /**
     * This method checks whether 2 course objects are equal
     * @param obj
     * The object to be compared
     * @return boolean
     * The parameter determining whether they are equal
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == this)
            return true;

        if(obj instanceof Course){

            Course myCourse = (Course)obj;

            return ((name.equals(myCourse.getName())) &&
            (department.equals(myCourse.getDepartment())) &&
            (instructor.equals(myCourse.getInstructor())) &&
            (code == myCourse.getCode()) &&
            (section == myCourse.getSection()));

        }

        return false;
    }

    /**
     * This is the toString method of the Course class
     * <br> it is overridden to show the declared output
     * @return
     * A string representation of the Course's data
     */
    public String toString(){
        return String.format("%-30s %-15s %-5d %-10d %-30s ", name ,department, code, section, instructor);
    }

    /**
     * This is a getter method for the course name
     * @return
     * The course name
     */
    public String getName() {
        return name;
    }

    /**
     * This is a getter method for the course department
     * @return
     * The department to which the course belongs
     */
    public String getDepartment() {
        return department;
    }

    /**
     * This is a getter method for the course code
     * @return
     * The course code
     */
    public int getCode() {
        return code;
    }

    /**
     * This is a getter method for the course section
     * @return
     * The course section
     */
    public byte getSection() {
        return section;
    }

    /**
     * This is a getter method for the instructor name
     * @return
     * The instructor name
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * Sets course name
     * @param name
     *  The name of the course
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets course department
     * @param department
     *  The department to which the course belongs
     * @throws IllegalArgumentException
     *  when the department code is greater than 3 letters
     */
    public void setDepartment(String department) {
        if(department.length() > 3)
            throw new IllegalArgumentException("Invalid department code");
        this.department = department;
    }

    /**
     * Sets course instructor
     * @param instructor
     *  The instructor of the course
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    /**
     * Sets course code
     * @param code
     *  The course code
     * @throws IllegalArgumentException
     *  When the course code is negative
     */
    public void setCode(int code) {
        if (this.code < 0)
            throw new IllegalArgumentException("input can't be negative");
        this.code = code;

    }

    /**
     * Sets the course section
     * @param section
     *  The course section
     * @throws IllegalArgumentException
     *  when the course section is negative
     */
    public void setSection(byte section) {
        if(this.section < 0)
            throw new IllegalArgumentException("input can't be negative");
        this.section = section;
    }

    /**
     * This method throws an exception if input is negative
     * @param inputNumber
     *  The number to be checked
     * @return isNegative
     *  Shows whether the input is negative
     * @throws IllegalArgumentException
     *  When the input is negative
     */
    public static boolean isNegative(int inputNumber){
        if (inputNumber < 0){
            throw new IllegalArgumentException("Input cannot be negative");
        }
        return true;
    }


}

