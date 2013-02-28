package dk.cngroup.javatechnicaltest;

/**
 * Interface to track computation result
 */
public interface Report {
    /**
     * Invoked for every computation result
     *
     * @param position Line number of currently evaluated APPLY instruction
     * @param result   Result of evaluation
     */
    public void report(int position, int result);
}
