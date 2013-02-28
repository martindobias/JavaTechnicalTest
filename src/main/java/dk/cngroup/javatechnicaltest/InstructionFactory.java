package dk.cngroup.javatechnicaltest;

public class InstructionFactory {

    private enum InstructionCode {
        ADD, SUBSTRACT, MULTIPLY, DIVIDE, APPLY;
    }

    public static Instruction createInstruction(String instructionText, int argument) throws Exception {
        InstructionCode code;
        try {
            code = InstructionCode.valueOf(instructionText);
        } catch (IllegalArgumentException e) {
            throw new Exception("Unknown instruction: " + instructionText);
        }

        switch (code) {
            case ADD:
                return new AddInstruction(argument);
            case SUBSTRACT:
                return new SubtractInstruction(argument);
            case MULTIPLY:
                return new MultiplyInstruction(argument);
            case DIVIDE:
                return new DivideInstruction(argument);
            case APPLY:
                return new ApplyInstruction(argument);
            default:
                throw new Exception("Unhandled instruction: " + instructionText);
        }
    }
}
