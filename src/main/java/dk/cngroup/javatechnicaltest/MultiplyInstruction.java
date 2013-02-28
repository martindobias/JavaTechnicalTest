package dk.cngroup.javatechnicaltest;

/**
 * Multiply instruction, multiplies previous result by argument
 */
public class MultiplyInstruction extends Instruction {
    public MultiplyInstruction(int argument) {
        super(argument);
    }

    /**
     * Multiplies previous result by argument
     *
     * @param partial Previous evaluation result (stack)
     * @return partial * argument
     */
    @Override
    public int evaluate(int partial) {
        return partial * this.argument;
    }

    @Override
    public String toString() {
        return "MULTIPLY " + this.argument;
    }
}
