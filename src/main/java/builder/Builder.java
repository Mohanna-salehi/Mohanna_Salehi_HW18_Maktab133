package builder;

import model.User;

public interface Builder<T> {
    Builder reset();
    T result() throws IllegalAccessException;

}
