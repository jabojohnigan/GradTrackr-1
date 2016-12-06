package report;

import graduate.Graduate;

import java.util.List;

/**
 * Created by noxor on 12/2/16.
 * @author noxor
 * @author Vlad
 */
public class DataReport {

    private StringBuilder myReportQuery;

    public DataReport(){
        myReportQuery = new StringBuilder();
        myReportQuery.append("select * ");
        myReportQuery.append("from Graduate ");
    }
    public void searchByName(String graduateName) {
        myReportQuery.append("where upper(graduateName) like '%");
        myReportQuery.append(graduateName.toUpperCase());
        myReportQuery.append( "%'");
    }

    public void searchByID(String id ){
        myReportQuery.append("where id = '");
        myReportQuery.append(id);
        myReportQuery.append("'");
    }

    public void searchByGraduateID(String graduateID ){
        myReportQuery.append("where graduateId = '");
        myReportQuery.append(graduateID);
        myReportQuery.append("'");
    }

    public void searchByGPA(String gpa) {
        myReportQuery.append("where gpa = '");
        myReportQuery.append(gpa);
        myReportQuery.append("'");
    }

    public void searchByGraduationYear(String graduationYear) {
        myReportQuery.append("where graduationYear = '");
        myReportQuery.append(graduationYear);
        myReportQuery.append("'");
    }

    public void searchByTransferStatus(boolean transferStatus) {
        myReportQuery.append("where transferStatus = '");
        myReportQuery.append(transferStatus);
        myReportQuery.append("'");
    }

    public void searchByEmployer(String employer) {
        myReportQuery.append("where upper(employers) like '%");
        myReportQuery.append(employer.toUpperCase());
        myReportQuery.append("%'");
    }

    public void searchByInternship(String internship) {
        myReportQuery.append("where upper(internships) like '%");
        myReportQuery.append(internship.toUpperCase());
        myReportQuery.append("%'");
    }

    public String getReportQuery(){
        return myReportQuery.toString();
    }
}
