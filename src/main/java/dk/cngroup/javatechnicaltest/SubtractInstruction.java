package dk.cngroup.javatechnicaltest;

/**
 * Subtract instruction, subtract previous result by argument
 */
public class SubtractInstruction extends Instruction {
    public SubtractInstruction(int argument) {
        super(argument);
    }

    /**
     * Subtract previous result by argument
     *
     * @param partial Previous evaluation result (stack)
     * @return partial - argument
     */
    @Override
    public int evaluate(int partial) {
        return partial - this.argument;
    }

    @Override
    public String toString() {
        return "SUBTRACT " + this.argument;
    }
}
