package graduate;

import java.util.List;

/**
 * Created by noxor on 11/28/16.
 *
 * Probably don't need these classes.
 */
public class Employer {
    String myName;
    List<String> myRequiredSkills;
    boolean myIsGraduateEmployed;
    String myDateHired;

    public Employer(String theName, List<String> theSkills, boolean theEmployed,
                    String theDateHired) {
        myName = theName;
        myRequiredSkills = theSkills;
        myIsGraduateEmployed = theEmployed;
        myDateHired = theDateHired;
    }

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
