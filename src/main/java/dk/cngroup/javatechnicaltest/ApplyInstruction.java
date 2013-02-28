package dk.cngroup.javatechnicaltest;

import java.util.List;

public class ApplyInstruction extends Instruction {
    public ApplyInstruction(int argument) {
        super(argument);
    }

    @Override
    public int evaluate(int partial) {
        throw new RuntimeException("APPLY instruction cannot be evaluated"); // this shall never happen
    }

    @Override
    public void process(List<Instruction> instructions, boolean quietly) {
        int partial = this.argument;
        int linenum = 1;
        if (!quietly) System.out.println("Applying number " + partial);
        for (Instruction instruction : instructions) {
            if (!quietly) System.out.println(linenum++ + ": " + instruction.toString());
            partial = instruction.evaluate(partial);
        }
        System.out.println((!quietly ? "Result is " : "") + partial);
    }
}
