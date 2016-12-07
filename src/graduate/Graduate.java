package graduate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Graduate class holds information relevant to the gladuate to be stored in the system.
 * This information will be displayed to the user when they look up an individual.
 *
 * @author concox
 */
public class Graduate {

    //values have default values so that they can be easily identified.
    private int myID;
    private String myName;
    private int myGraduateID;
    private String myGraduationYear = "0000";
    private Double myGPA = 0.00;
    private String myEmail = "empty@noemail.com";
    private boolean myTransferStatus = false;
    private boolean myResponseFlag = false;
    private List<String> myEmployers = new ArrayList<>();
    private List<String> myInternships = new ArrayList<>();

    /**
     * creates a new graduate object. Holds information about the graduate.
     * @param theName of the graduate, cant be null or short
     * @param theGraduateID of the graduate cant be null or short
     */
    public Graduate(String theName, int theGraduateID) {
        if(theName.length() < 3) {
            throw new IllegalArgumentException("Invalid name");
        } else myName = theName;
        myGraduateID = theGraduateID;
    }

    /**
     * creates a new graduate object. Holds information about the graduate.
     * @param theName of the graduate, cant be null or short
     * @param theGraduateID of the graduate cant be null or short
     * @param theGraduationYear of the graduate
     * @param theGPA of the graduate
     * @param theEmail of the graduate cant be null or short
     * @param theTransferStatus of the graduate
     * @param theResponsiveness of the graduate
     * @param theEmployers of the graduate
     * @param theInternships of the graduate
     */
    public Graduate(String theName, int theGraduateID, String theGraduationYear, Double theGPA,
                    String theEmail, boolean theTransferStatus, boolean theResponsiveness,
                    List<String> theEmployers, List<String> theInternships) {
        this(theName, theGraduateID);
        if (theEmail.length() < 6) {
            throw new IllegalArgumentException("Invalid Email");
        } else myEmail = theEmail;
        // all of these are not required
        if(myGraduationYear == null) {
            myGraduationYear = "n/a";
        } else myGraduationYear = theGraduationYear;
        myGPA = theGPA;
        myTransferStatus = theTransferStatus;
        myResponseFlag = theResponsiveness;

        if(theEmployers == null) {
            myEmployers = new ArrayList<>();
        } else myEmployers = theEmployers;
        if(theInternships == null) {
            myInternships = new ArrayList<>();
        } else myInternships = theInternships;
    }

    /**
     * Creates a list from a String, every item is separate by a ','.
     * @param theString the string to be parsed
     * @return a new List
     */
    public static List<String> stringToList(String theString) {
        String[] splitStr = theString.split(",");
        List<String> stringList = new ArrayList<>();
        Collections.addAll(stringList, splitStr);
        return stringList;
    }

    /**
     * Creates a string from the employer list, sperates each item by a ','.
     * @return the string of items
     */
    public String getEmployersAsString() {
        StringBuilder sb = new StringBuilder();
        for (String s : myEmployers) {
            sb.append(s);
            sb.append(",");
        }
        return sb.toString();
    }

    /**
     * Creates a string from the internship list, sperates each item by a ','.
     * @return a string of items.
     */
    public String getInternshipsAsString() {
        StringBuilder sb = new StringBuilder();
        for (String s : myInternships) {
            sb.append(s);
            sb.append(", ");
        }
        return sb.toString().substring(0, sb.length() - 2);
    }

    /**
     * Setters and getters.
     */

    public void setName(String theName) {
        myName = theName;
    }

    public void setGraduateID(int theGraduateID) {
        myGraduateID = theGraduateID;
    }

    public void setGraduationYear(String theGraduationYear) {
        myGraduationYear = theGraduationYear;
    }

    public void setGPA(Double theGPA) {
        myGPA = theGPA;
    }

    public void setEmail(String theEmail) {
        myEmail = theEmail;
    }

    public void setTransferStatus(boolean theTransferStatus) {
        myTransferStatus = theTransferStatus;
    }

    public void setResponseFlag(boolean theResponseFlag) {
        myResponseFlag = theResponseFlag;
    }

    public void setEmployers(List<String> theEmployers) {
        myEmployers = theEmployers;
    }

    public void setInternships(List<String> theInternships) {
        myInternships = theInternships;
    }

    public void setID(int theID) {
        myID = theID;
    }

    public int getID(){ return myID; }

    public String getName() {
        return myName;
    }

    public int getGraduateID() {
        return myGraduateID;
    }

    public String getGraduationYear() {
        return myGraduationYear;
    }

    public Double getGPA() {
        return myGPA;
    }

    public String getEmail() {
        return myEmail;
    }

    public boolean isTransferStatus() {
        return myTransferStatus;
    }

    public boolean isResponseFlag() {
        return myResponseFlag;
    }

    public List<String> getEmployers() {
        return myEmployers;
    }

    public List<String> getInternships() {
        return myInternships;
    }

}
