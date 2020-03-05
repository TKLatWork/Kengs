package fun.car.model;

public class Command {

    public Command(Boolean isTurn, Integer move) {
        this.isTurn = isTurn;
        this.move = move;
    }

    private Boolean isTurn;
    private Integer move;

    public Boolean getTurn() {
        return isTurn;
    }

    public Integer getMove() {
        return move;
    }
}
