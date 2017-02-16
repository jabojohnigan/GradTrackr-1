package graduate;

import java.util.List;

/**
 * Holds information about the graduate's current employer
 *
 * @author concox
 * @deprecated
 */
public class Employer {
    private String myName;
    private List<String> myRequiredSkills;
    private boolean myIsGraduateEmployed;
    private String myDateHired;

    /**
     * Creates an Employer object
     * @param theName of the employer
     * @param theSkills of the employer
     * @param theEmployed of the employer
     * @param theDateHired of he employer
     */
    public Employer(String theName, List<String> theSkills, boolean theEmployed,
                    String theDateHired) {
        myName = theName;
        myRequiredSkills = theSkills;
        myIsGraduateEmployed = theEmployed;
        myDateHired = theDateHired;
    }

    /**
     * Setters and getters
     */

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public void setMyRequiredSkills(List<String> myRequiredSkills) {
        this.myRequiredSkills = myRequiredSkills;
    }

    public void setMyIsGraduateEmployed(boolean myIsGraduateEmployed) {
        this.myIsGraduateEmployed = myIsGraduateEmployed;
    }

    public void setMyDateHired(String myDateHired) {
        this.myDateHired = myDateHired;
    }

    public String getMyName() {
        return myName;
    }

    public List<String> getMyRequiredSkills() {
        return myRequiredSkills;
    }

    public boolean isMyIsGraduateEmployed() {
        return myIsGraduateEmployed;
    }

    public String getMyDateHired() {
        return myDateHired;
    }
}
