import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Town_STUDENT_Test {
    
    private Town town1;
    private Town town2;
    private Town town3;

    @BeforeEach
    public void setUp() {
        town1 = new Town("goofy");
        town2 = new Town("goofy");
        town3 = new Town("goofy2");
    }

    @Test
    public void testConstructorAndGetName() {
        assertEquals("goofy", town1.getName());
        assertEquals("goofy2", town3.getName());
    }

    @Test
    public void testEqualsSameName() {
        assertTrue(town1.equals(town2));
    }

    @Test
    public void testEqualsDifferentName() {
        assertFalse(town1.equals(town3));
    }

    @Test
    public void testCompareToSameName() {
        assertEquals(0, town1.compareTo(town2));
    }

    @Test
    public void testCompareToDifferentName() {
        assertEquals(-1, town1.compareTo(town3));
    }

    @Test
    public void testHashCode() {
        assertEquals(town1.hashCode(), town2.hashCode());
        assertNotEquals(town1.hashCode(), town3.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("goofy", town1.toString());
    }
}