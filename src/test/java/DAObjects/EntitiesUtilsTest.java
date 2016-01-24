package DAObjects;

import java.util.List;

public class EntitiesUtilsTest {
    EntitiesUtils collections;
    @org.junit.Test
    public void testGetDirections() throws Exception {
        collections = new EntitiesUtils();
        List<Direction> directions = collections.getDirections();

        for (Direction direction: directions){
            System.out.println(direction);
        }
    }
}