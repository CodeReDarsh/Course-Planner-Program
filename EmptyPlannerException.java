/**
 * The <code>EmptyPlannerException</code> is a custom exception class which throws an exception when the planner is empty
 *
 * @author CodeReDarsh
 * <br>email: adarshcp2077@gmail.com
 **/
public class EmptyPlannerException extends Exception{
    /**
     *this is the default constructor used to create an EmptyPlannerException object
     */
    public EmptyPlannerException(){
        super("Planner is empty");
    }

    /**
     * this is the constructor used to create an EmptyPlannerException object
     * @param errorMessage
     *  The string describing the exception
     */
    public EmptyPlannerException(String errorMessage){
        super(errorMessage);
    }

}
