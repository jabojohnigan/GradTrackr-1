package graduate;

import java.util.List;

/**
 * Created by noxor on 11/28/16.
 *
 * Probably don't need these classes
 */
public class Internship {
    String myCompany;
    String myInternshipTitle;
    List<String> myIntershipQualifications;
    boolean myIsPaid;
    String myDateStarted;
    String myDateEnded;
    String myIternshipLocation;

    public Internship(String theCompany, String theTitle, List<String> myQualifications, boolean thePaid,
                      String theDateStarted, String theDateEnded, String theLocation) {
        myCompany = theCompany;
        myInternshipTitle = theTitle;
        myIntershipQualifications = myQualifications;
        myIsPaid = thePaid;
        myDateStarted = theDateStarted;
        myDateEnded = theDateEnded;
        myIternshipLocation = theLocation;
    }

    public void setMyCompany(String myCompany) {
        this.myCompany = myCompany;
    }

    public void setMyInternshipTitle(String myInternshipTitle) {
        this.myInternshipTitle = myInternshipTitle;
    }

    public void setMyIntershipQualifications(List<String> myIntershipQualifications) {
        this.myIntershipQualifications = myIntershipQualifications;
    }

    public void setMyIsPaid(boolean myIsPaid) {
        this.myIsPaid = myIsPaid;
    }

    public void setMyDateStarted(String myDateStarted) {
        this.myDateStarted = myDateStarted;
    }

    public void setMyDateEnded(String myDateEnded) {
        this.myDateEnded = myDateEnded;
    }

    public void setMyIternshipLocation(String myIternshipLocation) {
        this.myIternshipLocation = myIternshipLocation;
    }

    public String getMyCompany() {

        return myCompany;
    }

    public String getMyInternshipTitle() {
        return myInternshipTitle;
    }

    public List<String> getMyIntershipQualifications() {
        return myIntershipQualifications;
    }

    public boolean isMyIsPaid() {
        return myIsPaid;
    }

    public String getMyDateStarted() {
        return myDateStarted;
    }

    public String getMyDateEnded() {
        return myDateEnded;
    }

    public String getMyIternshipLocation() {
        return myIternshipLocation;
    }
}
