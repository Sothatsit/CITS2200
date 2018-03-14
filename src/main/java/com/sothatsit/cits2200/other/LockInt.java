package com.sothatsit.cits2200.other;

public class LockInt implements Lock {

    private int combination;

    /**
     * Instantiate a new com.sothatsit.cits2200.other.Lock with the combination {@param combination}.
     *
     * @param combination the combination of the lock
     */
    public LockInt(int combination) {
        this.combination = combination;
    }

    /**
     * Open this lock using the combination {@param combination}.
     *
     * @param combination the combination to be attempted
     *
     * @return whether the combination matched
     */
    @Override
    public boolean open(int combination) {
        return combination == this.combination;
    }

    /**
     * Change this lock to the combination {@param newCombo} after
     * opening using the combination {@param currentCombo}.
     *
     * @param currentCombo the current combination of this lock
     * @param newCombo     the combination to change this lock to
     *
     * @return whether the combination was successfully changed
     */
    @Override
    public boolean changeCombo(int currentCombo, int newCombo) {
        if(!open(currentCombo))
            return false;

        this.combination = newCombo;
        return true;
    }
}
