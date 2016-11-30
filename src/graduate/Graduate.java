package graduate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by concox on 11/28/16.
 */
public class Graduate {

    int myID;
    String myName;
    int myStudentID;
    String myGraduationYear;
    String mySchoolProgram;
    Double myGPA;
    String myEmail;
    boolean myTransferStatus;
    boolean myResponseFlag;
    List<String> myEmployers;
    List<String> myInternships;

    public Graduate(String theName, int theStudentID) {
        myName = theName;
        myStudentID = theStudentID;
    }

    public Graduate(String theName, int theStudentID, String theGraduationYear, Double theGPA,
                    String theEmail, boolean theTransferStatus, boolean theResponsiveness,
                    List<String> theEmployers, List<String> theInternships) {
        this(theName, theStudentID);
        myGraduationYear = theGraduationYear;
        myGPA = theGPA;
        myEmail = theEmail;
        myTransferStatus = theTransferStatus;
        myResponseFlag = theResponsiveness;
        myEmployers = theEmployers;
        myInternships = theInternships;
    }

    public static List<String> stringToList(String theString) {
        String[] splitStr = theString.split(",");
        List<String> stringList = new ArrayList();
        for (String s : splitStr){
            stringList.add(s);
        }
        return stringList;
    }

    public void setName(String theName) {
        myName = theName;
    }

    public void setStudentID(int theStudentID) {
        myStudentID = theStudentID;
    }

    public void setGraduationYear(String theGraduationYear) {
        myGraduationYear = theGraduationYear;
    }

    public void setSchoolProgram(String theSchoolProgram) {
        mySchoolProgram = theSchoolProgram;
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

    public int getStudentID() {
        return myStudentID;
    }

    public String getGraduationYear() {
        return myGraduationYear;
    }

    public String getSchoolProgram() {
        return mySchoolProgram;
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

    public String getEmployersAsString() {
        StringBuilder sb = new StringBuilder();
        for (String s : myEmployers) {
            sb.append(s);
            sb.append(",");
        }
        return sb.toString();
    }

    public String getInternshipsAsString() {
        StringBuilder sb = new StringBuilder();
        for (String s : myInternships) {
            sb.append(s);
            sb.append(",");
        }
        return sb.toString();
    }


}
