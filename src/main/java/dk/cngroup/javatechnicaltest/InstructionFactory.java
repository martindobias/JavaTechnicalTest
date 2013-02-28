package dk.cngroup.javatechnicaltest;

/**
 * Factory for creation of all instruction instances
 */
public class InstructionFactory {

    /**
     * Create instruction from instruction literal and argument
     *
     * @param instructionText Literal of instruction
     * @param argument        Argument of the instruction
     * @return Newly created instruction
     * @throws Exception In case of unrecognized or unhandled exception
     */
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
            case SUBTRACT:
                return new SubtractInstruction(argument);
            case MULTIPLY:
                return new MultiplyInstruction(argument);
            case DIVIDE:
                return new DivideInstruction(argument);
            case APPLY:
                return new ApplyInstruction(argument);
            case NOOP:
                return new NoOpInstruction();
            default:
                throw new Exception("Unhandled instruction: " + instructionText);
        }
    }

    /**
     * Arbitrary method to created NOOP instruction
     *
     * @return New NOOP instruction
     */
    public static Instruction createNoOpInstruction() {
        return new NoOpInstruction();
    }

    /**
     * Internal enumeration of all instructions
     */
    private enum InstructionCode {
        ADD, SUBTRACT, MULTIPLY, DIVIDE, APPLY, NOOP
    }
}
