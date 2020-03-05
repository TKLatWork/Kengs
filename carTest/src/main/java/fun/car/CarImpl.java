package fun.car;

import fun.car.etc.OutOfBoundariesException;
import fun.car.model.Command;
import fun.car.model.Direction;

public class CarImpl implements Car {

    public CarImpl(int x, int y, Direction direction, CarPark carPark) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.carPark = carPark;
    }

    private int x;
    private int y;
    private Direction direction;
    private CarPark carPark;

    public Car move(Command command) throws OutOfBoundariesException {
        if(command.getTurn()){
            turnClockWise();
        }
        //move
        if(command.getMove() != null && command.getMove() > 0){
            for (int i = 0; i < command.getMove(); i++) {
                moveOneStep();
            }
        }
        return this;
    }

    private void turnClockWise(){
        switch (direction){
            case EAST: direction = Direction.SOUTH;break;
            case WEST: direction = Direction.NORTH;break;
            case NORTH: direction = Direction.EAST;break;
            case SOUTH: direction = Direction.WEST;break;
        }
    }

    private void moveOneStep() throws OutOfBoundariesException {
        switch (direction){
            case EAST: x++;break;
            case WEST: x--;break;
            case NORTH: y++;break;
            case SOUTH: y--;break;
        }
        //check
        if (x < 1 || y < 1 || x > carPark.getX() || y > carPark.getY()){
            throw new OutOfBoundariesException(this);
        }
    }

    public int getPositionX() {
        return x;
    }

    public int getPositionY() {
        return y;
    }

    public Direction getOrientation() {
        return direction;
    }

}
