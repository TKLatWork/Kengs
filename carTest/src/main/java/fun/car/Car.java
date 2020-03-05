package fun.car;

import fun.car.etc.OutOfBoundariesException;
import fun.car.model.Command;
import fun.car.model.Direction;

public interface Car {

    Car move(Command command) throws OutOfBoundariesException;
    int getPositionX();
    int getPositionY();
    Direction getOrientation();

}
