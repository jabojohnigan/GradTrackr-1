package graduate;

import data.GraduateDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Static class that holds commands for the gui to call. Handles all information regarding graduates.
 *
 * @author concox
 */
public class GraduateCollection {
    /**
     * The graduate database to use.
     */
    private static GraduateDB mGraduateDB;
    /**
     * Search for a graduate by either their first or last name
     * @param name either first or last name to search for
     * @return a list of graduates with a matching first/last name
     */
    public static List<Graduate> search(String name) {
        List<Graduate> list = new ArrayList<>();
        if (mGraduateDB == null) {
            mGraduateDB = new GraduateDB();
        }
        try {
            return mGraduateDB.getGraduates(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Add a graduate to the database
     * @param graduate to be added to the database
     * @return true or false
     */
    public static boolean add(Graduate graduate) {
        if (mGraduateDB == null) {
            mGraduateDB = new GraduateDB();
        }

        String message = mGraduateDB.addGraduate(graduate);
        return !message.startsWith("Error adding Graduate:");
    }

    /**
	 * Modify the particular column of the Graduate with the given data.
	 * 
	 * @param graduate Graduate to modify
	 * @param column the field in the table to modify
	 * @param data the value for the field
	 * @return true or false
	 */
	public static boolean update(Graduate graduate, String column, Object data) {
		if (mGraduateDB == null) {
			mGraduateDB = new GraduateDB();
		}
		// Don't let the users change name.
		if (!(column.equals("name"))) {
			String message = mGraduateDB.updateGraduate(graduate, column, data);
			if (message.startsWith("Error updating graduate: ")) {
				return false;
			} else if (message.startsWith("Updated Graduate Successfully")){
				return true;
			}
		}
		return false;
	}
	
	public static boolean remove(int graduateId) {
		if (mGraduateDB == null) { 
			mGraduateDB = new GraduateDB();
		}
		
		String message = mGraduateDB.removeGraduate(graduateId);
		boolean status = false;
		if (message.startsWith("Error removing graduate:")) {
			status = false;
		} else if (message.startsWith("Removed Graduate Successfully")) {
			status = true;
		}
		return status;
	}
	
    
    /**
     * Return a graduate with the given id, null otherwise.
     *
     * @param id graduate to look for
     * @return graduate
     */
    public static Graduate getGraduate(String id) {
        if (mGraduateDB == null) {
            mGraduateDB = new GraduateDB();
        }
        try {
            return mGraduateDB.getGraduate(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Return a graduate with the given id, null otherwise.
     *
     * @param theGraduateName graduate name to look for
     * @param theGraduateId graduate id to look for
     * @return graduate
     */
    public static List<Graduate> getGraduate(String theGraduateName, int theGraduateId) {
        if (mGraduateDB == null) {
            mGraduateDB = new GraduateDB();
        }
        try {
            return mGraduateDB.getGraduates(theGraduateName, theGraduateId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all graduates from the database
     * @return A list of all the graduates
     */
    public static List<Graduate> getGraduates() {
        if (mGraduateDB == null) {
            mGraduateDB = new GraduateDB();
        }
        try {
            return mGraduateDB.getGraduates();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
