package engine;

public class StaticElement extends Element {
    public StaticElementType Type;

    public StaticElement(int x, int y, StaticElementType type) {
        super(x, y);
        this.Type = type;
    }

}
