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
 * Interacts with sql database - specifically handles queries about graduates.
 *
 * @author concox
 */
public class GraduateDB {
    private Connection mConnection;
    private List<Graduate> mGraduateList;

    /**
     * Adds a new graduate to the Graduate table.
     * @param graduate to be added to the table.
     * @return Returns "Added Graduate Successfully" or "Error adding graduate: " with the sql exception.
     */
    public String addGraduate(Graduate graduate) {
        String sql = "insert into Graduate(graduateName, graduateId, graduationYear, gpa, email, " +
                "transferStatus, responsive, employers, internships) " +
                "values (?, ?, ?, ?, ?, ?, ?, ? , ?); ";

        if (mConnection == null) {
            try {
                mConnection = DataConnection.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        PreparedStatement preparedStatement;
        try {
            preparedStatement = mConnection.prepareStatement(sql);
            preparedStatement.setString(1, graduate.getName());
            preparedStatement.setInt(2, graduate.getGraduateID());
            preparedStatement.setString(3, graduate.getGraduationYear());
            preparedStatement.setDouble(4, graduate.getGPA());
            preparedStatement.setString(5, graduate.getEmail());
            preparedStatement.setBoolean(6, graduate.isTransferStatus());
            preparedStatement.setBoolean(7, graduate.isResponseFlag());
            preparedStatement.setString(8, graduate.getEmployersAsString());
            preparedStatement.setString(9, graduate.getInternshipsAsString());
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
     * @return list of graduates from the database
     * @throws SQLException if a connection to the database cannot be made
     */
    public List<Graduate> getGraduates() throws SQLException {
        if (mConnection == null) {
            mConnection = DataConnection.getConnection();
        }
        Statement stmt = null;
        String query = "select * " + "from Graduate ";

        mGraduateList = new ArrayList<>();
        try {
            stmt = mConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("graduateName");
                int graduateID = rs.getInt("graduateId");
                String graduationYear = rs.getString("graduationYear");


              //  String program = rs.getString("program");
                Double gpa = rs.getDouble("gpa");
                String email = rs.getString("email");
                boolean transferStatus = rs.getBoolean("transferStatus");
                boolean responsive = rs.getBoolean("responsive");
                String employers = rs.getString("employers");
                String internships = rs.getString("internships");
                Graduate graduate = new Graduate(name, graduateID, graduationYear, gpa, email,
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
    public List<Graduate> getGraduates(String graduateName, int graduateID) throws SQLException {
        List<Graduate> filterList = new ArrayList<>();
        if (mGraduateList == null) {
            getGraduates();
        }
        graduateName = graduateName.toLowerCase();
        for (Graduate graduate : mGraduateList) {
            if (graduate.getName().toLowerCase().contains(graduateName) &&
                    graduate.getGraduateID() == graduateID) {
                filterList.add(graduate);
            }
        }
        return filterList;
    }

    /**
     * Returns all graduates that contain the search keyword.
     * @param name either first or last name
     * @return list of graduates that match.
     * @throws SQLException if the database connection is null
     */
    public List<Graduate> getGraduates(String name) throws SQLException {
        List<Graduate> filterList = new ArrayList<>();
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
     * @param id the graduate's database id
     * @return graduate the graduate with the the corresponding id
     * @throws SQLException if the database connection is null
     */
    public Graduate getGraduate(String id) throws SQLException {
        if (mConnection == null) {
            mConnection = DataConnection.getConnection();
        }
        Statement stmt = null;
        String query = "select * " + "from Graduate where id = " + id + ";";

        try {
            stmt = mConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                String name = rs.getString("graduateName");
                int graduateID = rs.getInt("graduateID");
                String graduationYear = rs.getString("graduationYear");
                Double gpa = rs.getDouble("gpa");
                String email = rs.getString("email");
                boolean transferStatus = rs.getBoolean("transferStatus");
                boolean responsive = rs.getBoolean("responsive");
                String employers = rs.getString("employers");
                String internships = rs.getString("internships");
                return new Graduate(name, graduateID, graduationYear, gpa, email,
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

