package dk.cngroup.javatechnicaltest;

/**
 * Abstract class for all instructions
 */
public abstract class Instruction {
    /**
     * Argument of the instruction
     */
    final int argument;

    Instruction(int argument) {
        this.argument = argument;
    }

    public int getArgument() {
        return argument;
    }

    /**
     * Evaluates the instruction
     *
     * @param partial Previous evaluation result (stack)
     * @return Updated result after evaluation
     */
    public abstract int evaluate(int partial);

    /**
     * Gets information whether current instruction shall invoke evaluation
     *
     * @return TRUE in case of APPLY instruction, otherwise FALSE
     */
    public boolean startsEvaluation() {
        return false;
    }
}
