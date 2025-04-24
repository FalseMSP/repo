import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Road_STUDENT_Test {

    private Town townA;
    private Town townB;
    private Town townC;
    private Road road1;
    private Road road2;
    private Road road3;

    @BeforeEach
    public void setUp() {
        townA = new Town("TownA");
        townB = new Town("TownB");
        townC = new Town("TownC");

        road1 = new Road(townA, townB, 5, "Main Street");
        road2 = new Road(townA, townB, 10, "Main Street");
        road3 = new Road(townA, townC, 7, "Second Street");
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("Main Street", road1.getName());
        assertEquals(5, road1.getWeight());
        assertEquals(townA, road1.getHead());
        assertEquals(townB, road1.getTail());
    }

    @Test
    public void testEqualsSameName() {
        assertTrue(road1.equals(road2));
    }

    @Test
    public void testEqualsDifferentName() {
        assertFalse(road1.equals(road3));
    }

    @Test
    public void testHashCode() {
        assertEquals(road1.hashCode(), road2.hashCode());
    }

    @Test
    public void testContains() {
        assertTrue(road1.contains(townA));
        assertTrue(road1.contains(townB));
        assertFalse(road1.contains(townC));
    }

    @Test
    public void testToString() {
        assertEquals("Main Street", road1.toString());
    }
}
