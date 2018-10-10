package org.pflb.vault;

/**
 * Created by user1 on 10.10.2018.
 */
public class CustomRpgExeption extends RuntimeException {
    public CustomRpgExeption(String nonZero) {
        super(nonZero);
    }
}
