/**
 * The <code>FullPlannerException</code> class is a custom exception class which throws an exception when the planner is full
 *
 * @author CodeReDarsh
 * <br>email: adarshcp2077@gmail.com
 **/
public class FullPlannerException extends Exception {

    /**
     * This is the default constructor used to create a FullPlannerException object
     */
    public FullPlannerException() {
        super("Planner is full");
    }

    /**
     * This is a constructor used to create a FullPlannerExceptionObject
     * @param errorMessage
     */
    public FullPlannerException(String errorMessage){
        super(errorMessage);
    }
}
