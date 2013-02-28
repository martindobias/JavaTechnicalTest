package dk.cngroup.javatechnicaltest;

/**
 * Divide instruction, divides previous result by argument
 */
public class DivideInstruction extends Instruction {
    public DivideInstruction(int argument) {
        super(argument);
    }

    /**
     * Divides previous result by argument
     *
     * @param partial Previous evaluation result (stack)
     * @return partial / argument
     */
    @Override
    public int evaluate(int partial) {
        return partial / this.argument;
    }

    @Override
    public String toString() {
        return "DIVIDE " + this.argument;
    }
}
