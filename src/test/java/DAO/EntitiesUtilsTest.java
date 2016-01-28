package DAO;

import DAO.Entities.Direction;
import DAO.Utils.Directions;

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