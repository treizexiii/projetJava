package engine.Dtos;

public class OrderDto {
    public String ref;
    public String moveTo;

    /**
     * Move order
     * 
     * @param ref    A letter to reference the player - see PlayerTag enum
     * @param moveTo A direction in which to move forward - see Directions enum
     */
    public OrderDto(String ref, String moveTo) {
        this.ref = ref;
        this.moveTo = moveTo;
    }
}
