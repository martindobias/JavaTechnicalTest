package dk.cngroup.javatechnicaltest;

/**
 * No-operation instruction, does nothing
 */
public class NoOpInstruction extends Instruction {
    public NoOpInstruction() {
        super(0);
    }

    /**
     * Does nothing
     *
     * @param partial Previous evaluation result (stack)
     * @return Previous result
     */
    @Override
    public int evaluate(int partial) {
        return partial;
    }

    @Override
    public String toString() {
        return "NOOP";
    }
}
