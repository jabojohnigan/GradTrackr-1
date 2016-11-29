package graduate;

import java.util.List;

/**
 * Created by concox on 11/28/16.
 */
public class Graduate {


    String myName;
    int myStudentID;
    String myGraduationYear;
    String mySchoolProgram;
    Double myGPA;
    String myEmail;
    boolean myTransferStatus;
    boolean myResponseFlag;
    List<Employer> myEmployers;
    List<Internship> myInternships;

    public Graduate(String theName, int theId) {
        myName = theName;
        myStudentID = theId;
    }

    public Graduate(String theName, int theId, String theGraduationYear, Double theGPA, String theEmail,
                    boolean theTransferStatus, boolean theResponsiveness, List<Employer> theEmployers,
                    List<Internship> theInternships) {
        this(theName, theId);
        myGraduationYear = theGraduationYear;
        myGPA = theGPA;
        myEmail = theEmail;
        myTransferStatus = theTransferStatus;
        myResponseFlag = theResponsiveness;
        myEmployers = theEmployers;
        myInternships = theInternships;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public void setMyStudentID(int myStudentID) {
        this.myStudentID = myStudentID;
    }

    public void setMyGraduationYear(String myGraduationYear) {
        this.myGraduationYear = myGraduationYear;
    }

    public void setMySchoolProgram(String mySchoolProgram) {
        this.mySchoolProgram = mySchoolProgram;
    }

    public void setMyGPA(Double myGPA) {
        this.myGPA = myGPA;
    }

    public void setMyEmail(String myEmail) {
        this.myEmail = myEmail;
    }

    public void setMyTransferStatus(boolean myTransferStatus) {
        this.myTransferStatus = myTransferStatus;
    }

    public void setMyResponseFlag(boolean myResponseFlag) {
        this.myResponseFlag = myResponseFlag;
    }

    public void setMyEmployers(List<Employer> myEmployers) {
        this.myEmployers = myEmployers;
    }

    public void setMyInternships(List<Internship> myInternships) {
        this.myInternships = myInternships;
    }

    public String getMyName() {
        return myName;
    }

    public int getMyStudentID() {
        return myStudentID;
    }

    public String getMyGraduationYear() {
        return myGraduationYear;
    }

    public String getMySchoolProgram() {
        return mySchoolProgram;
    }

    public Double getMyGPA() {
        return myGPA;
    }

    public String getMyEmail() {
        return myEmail;
    }

    public boolean isMyTransferStatus() {
        return myTransferStatus;
    }

    public boolean isMyResponseFlag() {
        return myResponseFlag;
    }

    public List<Employer> getMyEmployers() {
        return myEmployers;
    }

    public List<Internship> getMyInternships() {
        return myInternships;
    }
}
