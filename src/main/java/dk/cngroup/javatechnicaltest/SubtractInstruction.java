package dk.cngroup.javatechnicaltest;

public class SubtractInstruction extends Instruction {
    public SubtractInstruction(int argument) {
        super(argument);
    }

    @Override
    public int evaluate(int partial) {
        return partial - this.argument;
    }

    @Override
    public String toString() {
        return "SUBTRACT " + this.argument;
    }
}
