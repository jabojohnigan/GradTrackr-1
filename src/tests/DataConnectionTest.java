package tests;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

import data.DataConnection;

/**
 * Tests the data connection.
 * @mabraham
 */
@SuppressWarnings("unused")
public class DataConnectionTest {

    @Before
    public void setUp() throws Exception {
    }

    /**
     * Tests the data connection.
     */
    @Test
    public void testConnection() {
        try {
            Connection conn = DataConnection.getConnection();
            assertNotNull(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

