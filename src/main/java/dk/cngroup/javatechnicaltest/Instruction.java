package dk.cngroup.javatechnicaltest;

import java.util.List;

public abstract class Instruction {
    protected int argument;

    public Instruction(int argument) {
        this.argument = argument;
    }

    public abstract int evaluate(int partial);

    public void process(List<Instruction> instructions, boolean quietly) {
        instructions.add(this);
    }
}
