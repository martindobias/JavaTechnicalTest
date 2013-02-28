package dk.cngroup.javatechnicaltest;

/**
 * Add instruction, adds argument to previous result
 */
public class AddInstruction extends Instruction {
    public AddInstruction(int argument) {
        super(argument);
    }

    /**
     * Adds argument to previous result
     *
     * @param partial Previous evaluation result (stack)
     * @return partial + argument
     */
    @Override
    public int evaluate(int partial) {
        return partial + this.argument;
    }

    @Override
    public String toString() {
        return "ADD " + this.argument;
    }
}
