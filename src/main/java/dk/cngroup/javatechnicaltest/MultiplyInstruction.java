package dk.cngroup.javatechnicaltest;

public class MultiplyInstruction extends Instruction {
    public MultiplyInstruction(int argument) {
        super(argument);
    }

    @Override
    public int evaluate(int partial) {
        return partial * this.argument;
    }

    @Override
    public String toString() {
        return "MULTIPLY " + this.argument;
    }
}
