package dk.cngroup.javatechnicaltest;

public class AddInstruction extends Instruction {
    public AddInstruction(int argument) {
        super(argument);
    }

    @Override
    public int evaluate(int partial) {
        return partial + this.argument;
    }

    @Override
    public String toString() {
        return "ADD " + this.argument;
    }
}
