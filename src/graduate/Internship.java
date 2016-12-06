package graduate;

import java.util.List;

/**
 * Holds information about the graduates internship
 * @deprecated
 *
 * @author concox
 */
@SuppressWarnings("unesed")
public class Internship {
    private String myCompany;
    private String myInternshipTitle;
    private List<String> myIntershipQualifications;
    private boolean myIsPaid;
    private String myDateStarted;
    private String myDateEnded;
    private String myIternshipLocation;

    /**
     * Creates an internship object
     * @param theCompany of the internship
     * @param theTitle of the internship
     * @param myQualifications of the internship
     * @param thePaid of the internship
     * @param theDateStarted of the internship
     * @param theDateEnded of the internship
     * @param theLocation of the internship
     */
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

    /**
     * Setters and getters
     */

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
