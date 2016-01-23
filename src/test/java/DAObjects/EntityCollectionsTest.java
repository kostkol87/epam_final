package DAObjects;

import java.util.List;

public class EntityCollectionsTest {
    EntityCollections collections;
    @org.junit.Test
    public void testGetDirections() throws Exception {
        collections = new EntityCollections();
        List<Direction> directions = collections.getDirections();

        for (Direction direction: directions){
            System.out.println(direction);
        }
    }
}