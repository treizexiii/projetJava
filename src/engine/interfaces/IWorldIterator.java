package engine.interfaces;

import engine.Element;

public interface IWorldIterator {
    boolean hasNext();

    Element next();
}
