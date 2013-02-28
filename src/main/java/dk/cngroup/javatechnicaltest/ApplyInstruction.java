package dk.cngroup.javatechnicaltest;

/**
 * Apply instruction, behaves like NOOP, but starts evaluation
 */
public class ApplyInstruction extends Instruction {
    public ApplyInstruction(int argument) {
        super(argument);
    }

    /**
     * During evaluation behaves like NOOP instruction
     *
     * @param partial Previous evaluation result (stack)
     * @return partial
     */
    @Override
    public int evaluate(int partial) {
        return partial;
    }

    /**
     * Gets information whether current instruction shall invoke evaluation
     *
     * @return Always TRUE for ApplyInstruction
     */
    @Override
    public boolean startsEvaluation() {
        return true;
    }

    @Override
    public String toString() {
        return "APPLY " + this.argument + " (skipped)";
    }
}
