package report;

import graduate.Graduate;

import java.util.List;

/**
 * The data report class is an object containing the information that the user
 * requested.
 *
 * All methods are used to build a long string that can be returned as a 'report'.
 *
 * @author Vlad
 */
public class DataReport {

    private StringBuilder myReportQuery;

    /**
     * creates a new data report object.
     */
    public DataReport(){
        myReportQuery = new StringBuilder();
        myReportQuery.append("select * ");
        myReportQuery.append("from Graduate ");
    }

    /**
     * Searches and returns the name of the graduate to the string builder.
     * @param graduateName name of the graduate to be found
     */
    public void searchByName(String graduateName) {
        myReportQuery.append("where upper(graduateName) like '%");
        myReportQuery.append(graduateName.toUpperCase());
        myReportQuery.append( "%'");
    }

    /**
     * Searches and returns the id of the graduate to the string builder.
     *
     * Should not be used in the project. Id's are hidden.
     *
     * @param id of the graduates' DB entry
     */
    public void searchByID(String id ){
        myReportQuery.append("where id = '");
        myReportQuery.append(id);
        myReportQuery.append("'");
    }

    /**
     * Search for the graduate by their graduate ID.
     *
     * @param graduateID id of the graduate to be found.
     */
    public void searchByGraduateID(String graduateID ){
        myReportQuery.append("where graduateId = '");
        myReportQuery.append(graduateID);
        myReportQuery.append("'");
    }

    /**
     * Searches all graduates and returns them by their GPA.
     *
     * @param gpa the target GPA
     */
    public void searchByGPA(String gpa) {
        myReportQuery.append("where gpa = ");
        myReportQuery.append(gpa);
        myReportQuery.append("");
    }

    /**
     * Searches for the graduate by their graduation year
     *
     * @param graduationYear graduation year of the target graduate
     */
    public void searchByGraduationYear(String graduationYear) {
        myReportQuery.append("where graduationYear = '");
        myReportQuery.append(graduationYear);
        myReportQuery.append("'");
    }

    /**
     * Finds all the graduates that are transfer or not.
     *
     * @param transferStatus the target boolean.
     */
    public void searchByTransferStatus(int transferStatus) {
        myReportQuery.append("where transferStatus = ");
        myReportQuery.append(transferStatus);
        //myReportQuery.append("'");
    }

    /**
     * Searches the graduate DB by their employer.
     *
     * This won't work as intended.
     *
     * @param employer graduates employer's name to be included
     */
    public void searchByEmployer(String employer) {
        String upperEmp = employer.toUpperCase();
        myReportQuery.append("where upper(employers) like '%");
        myReportQuery.append(upperEmp);
        myReportQuery.append("%'");
    }

    /**
     * Searches the graduate DB by their internships
     *
     * same issue as employers.
     *
     * @param internship graduates internship's name to be included
     */
    public void searchByInternship(String internship) {
        String upperIntern = internship.toUpperCase();
        myReportQuery.append("where upper(internships) like '%");
        myReportQuery.append(upperIntern);
        myReportQuery.append("%'");
    }

    public String getReportQuery(){
        return myReportQuery.toString();
    }
}
