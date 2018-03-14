public interface Lock {

    /**
     * Open this combination lock.
     *
     * @param combination the combination you are attempting
     *
     * @return whether the lock was successfully opened
     */
    public boolean open(int combination);

    /**
     * Change the combination of this lock.
     *
     * @param currentCombo the current combination of this lock
     * @param newCombo     the combination to change this lock to
     *
     * @return whether the combination was successfully changed.
     */
    public boolean changeCombo(int currentCombo, int newCombo);

}
