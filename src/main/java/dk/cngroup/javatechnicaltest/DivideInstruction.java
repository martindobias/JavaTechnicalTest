package dk.cngroup.javatechnicaltest;

public class DivideInstruction extends Instruction {
    public DivideInstruction(int argument) {
        super(argument);
    }

    @Override
    public int evaluate(int partial) {
        return partial / this.argument;
    }

    @Override
    public String toString() {
        return "DIVIDE " + this.argument;
    }
}
