package data;

import graduate.Graduate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Interactis with sql database - specfically handles queries about graduates
 * @author concox
 *
 */
public class GraduateDB {
    private Connection mConnection;
    private List<Graduate> mGraduateList;

    /**
     * Adds a new graduate to the Graduate table.
     * @param graduate
     * @return Returns "Added Graduate Successfully" or "Error adding graduate: " with the sql exception.
     */
    public String addGraduate(Graduate graduate) {
        String sql = "insert into Graduate(studentName, id, graduationYear, program , gpa, email, " +
                "transferStatus, responsive, employers, internships) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ? , ?); ";

        if (mConnection == null) {
            try {
                mConnection = DataConnection.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = mConnection.prepareStatement(sql);
            preparedStatement.setString(1, graduate.getName());
            preparedStatement.setInt(2, graduate.getStudentID());
            preparedStatement.setString(3, graduate.getGraduationYear());
            preparedStatement.setString(4, graduate.getSchoolProgram());
            preparedStatement.setDouble(5, graduate.getGPA());
            preparedStatement.setString(6, graduate.getEmail());
            preparedStatement.setBoolean(7, graduate.isTransferStatus());
            preparedStatement.setBoolean(8, graduate.isResponseFlag());
            preparedStatement.setString(9, graduate.getEmployersAsString());
            preparedStatement.setString(10, graduate.getInternshipsAsString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error adding graduate: " + e.getMessage();
        }
        return "Added Graduate Successfully";
    }

    /**
     * Retrieves all graduates from the Graduate table.
     *
     * @return list of graduates
     * @throws SQLException
     */
    public List<Graduate> getGraduates() throws SQLException {
        if (mConnection == null) {
            mConnection = DataConnection.getConnection();
        }
        Statement stmt = null;
        String query = "select * " + "from Graduate ";

        mGraduateList = new ArrayList<Graduate>();
        try {
            stmt = mConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("graduateId");
                String name = rs.getString("name");
                int studentID = rs.getInt("studentID");
                String graduationYear = rs.getString("graduationyYear");
                String program = rs.getString("program");
                Double gpa = rs.getDouble("gpa");
                String email = rs.getString("email");
                boolean transferStatus = rs.getBoolean("transferStatus");
                boolean responsive = rs.getBoolean("responsive");
                String employers = rs.getString("employers");
                String internships = rs.getString("internships");
                Graduate graduate = new Graduate(name, studentID, graduationYear, gpa, email,
                        transferStatus, responsive, Graduate.stringToList(employers),
                        Graduate.stringToList(internships));
                graduate.setID(id);
                mGraduateList.add(graduate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return mGraduateList;
    }

    /**
     * Returns all graduates that contain the search keyword.
     *
     * @return list of graduates that match.
     * @throws SQLException
     */
    public List<Graduate> getGraduates(String name, int studentID) throws SQLException {
        List<Graduate> filterList = new ArrayList<Graduate>();
        if (mGraduateList == null) {
            getGraduates();
        }
        name = name.toLowerCase();
        for (Graduate graduate : mGraduateList) {
            if (graduate.getName().toLowerCase().contains(name) &&
                    graduate.getStudentID() == studentID) {
                filterList.add(graduate);
            }
        }
        return filterList;
    }

    /**
     * Returns all graduates that contain the search keyword.
     * @param name either first or last name
     * @return list of graduates that match.
     * @throws SQLException
     */
    public List<Graduate> getGraduates(String name) throws SQLException {
        List<Graduate> filterList = new ArrayList<Graduate>();
        if (mGraduateList == null) {
            getGraduates();
        }
        name = name.toLowerCase();
        for (Graduate graduate : mGraduateList) {
            if (graduate.getName().toLowerCase().contains(name)) {
                filterList.add(graduate);
            }
        }
        return filterList;
    }

    /**
     * Retrieve the graduate with the given id or null if not found.
     * @param id
     * @return graduate
     * @throws SQLException
     */
    public Graduate getGraduate(String id) throws SQLException {
        if (mConnection == null) {
            mConnection = DataConnection.getConnection();
        }
        Statement stmt = null;
        String query = "select * " + "from Graduate where name = '" + id + "';";

        try {
            stmt = mConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                String name = rs.getString("name");
                int studentID = rs.getInt("studentID");
                String graduationYear = rs.getString("graduationyYear");
                String program = rs.getString("program");
                Double gpa = rs.getDouble("gpa");
                String email = rs.getString("email");
                boolean transferStatus = rs.getBoolean("transferStatus");
                boolean responsive = rs.getBoolean("responsive");
                String employers = rs.getString("employers");
                String internships = rs.getString("internships");
                return new Graduate(name, studentID, graduationYear, gpa, email,
                        transferStatus, responsive, Graduate.stringToList(employers),
                        Graduate.stringToList(internships));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return null;
    }

}

