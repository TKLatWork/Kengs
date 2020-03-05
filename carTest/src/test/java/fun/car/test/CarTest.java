package fun.car.test;

import fun.car.Car;
import fun.car.CarImpl;
import fun.car.CarPark;
import fun.car.etc.OutOfBoundariesException;
import fun.car.model.Command;
import fun.car.model.Direction;
import org.junit.jupiter.api.Test;

import static fun.car.model.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CarTest {

    CarPark carPark = new CarPark(4, 4);

    private void assertCar(Car car, int x, int y, Direction direction){
        assertEquals(direction, car.getOrientation());
        assertEquals(x, car.getPositionX());
        assertEquals(y, car.getPositionY());
    }

    @Test
    public void criteriaOne() throws OutOfBoundariesException {
        Car car = new CarImpl(1, 1, NORTH, carPark);
        car.move(new Command(true, 0));
        assertCar(car,1, 1, EAST);
    }

    @Test
    public void criteriaTwo() throws OutOfBoundariesException {
        Car car = new CarImpl(1, 1, NORTH, carPark);
        car.move(new Command(false, 1));
        assertCar(car,1, 2, NORTH);
    }

    @Test
    public void criteriaThree() throws OutOfBoundariesException {
        Car car = new CarImpl(1, 1, EAST, carPark);
        car.move(new Command(false, 1));
        assertCar(car,2, 1, EAST);
    }

    @Test
    public void criteriaFour(){
        Car car = new CarImpl(1, 1, WEST, carPark);
        OutOfBoundariesException ex = null;
        try {
            car.move(new Command(false, 1));
        }catch (OutOfBoundariesException e){
            ex = e;
        }
        assertNotNull(ex);
    }

    @Test
    public void criteriaFive() throws OutOfBoundariesException {
        Car car = new CarImpl(1, 1, EAST, carPark);
        car.move(new Command(false, 2));
        assertCar(car,3, 1, EAST);
    }

}
