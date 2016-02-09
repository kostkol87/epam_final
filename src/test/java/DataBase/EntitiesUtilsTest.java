package dataBase;

import dataBase.entities.Direction;
import dataBase.DAO.Directions;

import java.util.List;

public class EntitiesUtilsTest {
    @org.junit.Test
    public void testGetDirections() throws Exception {
        List<Direction> directions = new Directions().getDirections();

        for (Direction direction: directions){
            System.out.println(direction);
        }
    }
}