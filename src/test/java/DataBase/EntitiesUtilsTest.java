package DataBase;

import DataBase.Entities.Direction;
import DataBase.DAO.Directions;

import java.util.List;

public class EntitiesUtilsTest {
    @org.junit.Test
    public void testGetDirections() throws Exception {
        List<Direction> directions = Directions.getDirections();

        for (Direction direction: directions){
            System.out.println(direction);
        }
    }
}