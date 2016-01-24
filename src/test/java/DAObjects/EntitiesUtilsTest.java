package DAObjects;

import java.util.List;

public class EntitiesUtilsTest {
    @org.junit.Test
    public void testGetDirections() throws Exception {
        List<Direction> directions = EntitiesUtils.getDirections();

        for (Direction direction: directions){
            System.out.println(direction);
        }
    }
}