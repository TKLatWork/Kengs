package fun.car.etc;

import fun.car.Car;

public class OutOfBoundariesException extends Exception {

    public OutOfBoundariesException(Car car){
        super("OutOfBoundaries at"
                + car.getPositionX() + "/" + car.getPositionY()
                + ", heading " + car.getOrientation().name());
    }

}
