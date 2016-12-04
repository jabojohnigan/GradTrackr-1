package tests;

import graduate.Graduate;
import graduate.GraduateCollection;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests the Graduate class. This mainly tests that the constructors are handling information properly.
 * Additionally, will test the GraduateDB class and make sure that everything is good there too.
 *
 * @author concox
 */
public class GraduateTest {
    /**
     * Tests the basic Graduate constructor.
     */
    @Test
    public void testConstructor1() {
        Graduate grad = new Graduate("Bob Smith", 123456);
        assertNotNull(grad);
    }
    /**
     * Tests the overloaded Graduate Constructor.
     */
    @Test
    public void testConstructor2() {
        List<String> employerList = new ArrayList<>();
        List<String> internshipList = new ArrayList<>();
        Graduate grad = new Graduate("Bob Smith", 123456, "2014",
                4.00, "test@test.com", true,
                true, internshipList, employerList);
        assertNotNull(grad);
    }
    /**
     * Tests an illegal argument constructor.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConstructor3() {
        Graduate grad = new Graduate("a", 0);
        assertNull(grad);
    }

    /**
     * Tests the overloaded with an invalid email.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConstructor4() {
        Graduate grad = new Graduate("Bob Smith", 12345, null, 0.0, "aoe", false, false, null, null);
        assertNotNull(grad);
    }

    /**
     * Tests the overloaded with few fields filled.
     */
    @Test
    public void testConstructor5() {
        Graduate grad = new Graduate("Bob Smith", 12345, null, 0.0, "test@test.com", false, false, null, null);
        assertNotNull(grad);
    }

    /**
     * Lists all the graduates on the database.
     */
    @Test
    public void testGetGraduates() {
        List<Graduate> gradList = GraduateCollection.getGraduates();
        assertNotNull(gradList);
    }

    /**
     * Searches for graduate by name.
     */
    @Test
    public void testSearchGraduate1() {
        List<Graduate> gradSearch = GraduateCollection.search("Test");
        assertNotNull(gradSearch);
    }

    /**
     * Searches for graduate by id.
     */
    @Test
    public void testSearchGraduate2() {
        Graduate gradRet = GraduateCollection.getGraduate("1");
        assertNotNull(gradRet);
    }

    /**
     * Add a graduate to the Database.
     */
    @Test
    public void testAddGraduate() {
        assertTrue(GraduateCollection.add(new Graduate("Bob Test", 12345)));
    }

    /**
     * Tests getting graduate by name and ID
     */
    @Test
    public void testFindGraduateByNameAndId() {
        List<Graduate> gradList = GraduateCollection.getGraduate("Bob Test", 12345);
        assertNotNull(gradList);
    }
}
