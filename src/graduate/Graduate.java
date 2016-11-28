package graduate;

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
    List<Internships> myInternships;

    public Graduate(String theName, int theId) {
        myName = theName;
        myStudentID = theId;
    }

}
