package dk.cngroup.javatechnicaltest;

import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Calculator main class. Provides basic instruction evaluation capability as well as creation of instruction stack from
 * input.
 */
public class Calculator {

    /**
     * Instruction pattern
     */
    private static final Pattern pattern = Pattern.compile("^\\s*(\\p{Alpha}+)\\s*(-?\\d+)\\s*$");
    /**
     * Stack of recognized instructions for evaluation
     */
    private final List<Instruction> instructions = new ArrayList<Instruction>();

    /**
     * Creates Calculator and loads instruction stack from given input
     *
     * @param reader Input to read instructions from
     * @throws IOException In case of problems reading input, invalid syntax in input or unrecognized or unhandled
     * instructions
     */
    public Calculator(BufferedReader reader) throws IOException {
        // line number for enriched output
        int linenum = 0;
        while (true) {
            linenum++;

            String line = reader.readLine();
            if (line == null) break;

            // check if input line matches syntax
            Matcher matcher = pattern.matcher(line);
            if (!matcher.matches()) {
                // empty line is considered as valid NOOP instruction
                if (line.trim().equals("")) {
                    this.instructions.add(InstructionFactory.createNoOpInstruction());
                    continue;
                }
                throw new IOException("Invalid input on line " + linenum + " - " + line);
            }

            // parse input line
            String instructionText = matcher.group(1).toUpperCase();
            int argument = Integer.parseInt(matcher.group(2));
            try {
                // construct instruction form input
                Instruction instruction = InstructionFactory.createInstruction(instructionText, argument);
                this.instructions.add(instruction);
            } catch (Exception e) {
                throw new IOException("Invalid input on line " + linenum + " - " + e.getMessage());
            }
        }
    }

    /**
     * Application entry point.
     * usage: Calculator [-h] [-q] <file1> [file2 [file3 [...]]]
     * -h,--help    Displays the help ad quits
     * -q,--quiet   Disables evaluation progress output (debug)
     * Each file is opened and processed independently. First instructions are loaded into instruction stack, then stack
     * is evaluated. Results are printed on screen.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // command line argument parser setup
        Options options = new Options();
        options.addOption("h", "help", false, "Displays the help ad quits");
        options.addOption("q", "quiet", false, "Disables evaluation progress output (debug)");

        // process command line
        CommandLineParser parser = new GnuParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println("Invalid arguments");
            System.err.println(e.getMessage());
            return;
        }

        // respond to explicit help request or empty file list
        if (cmd.hasOption("h") || cmd.getArgList().isEmpty()) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Calculator [-h] [-q] <file1> [file2 [file3 [...]]]\n" +
                    "Processes all files given on command line and computes values according " +
                    "to instructions in the files", options);
            return;
        }

        final boolean quiet = cmd.hasOption("q");

        // process all files on input
        for (Object file : cmd.getArgList()) {
            try {
                if (!quiet) System.out.println("Processing file " + file);
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader((String) file));
                    new Calculator(reader).evaluate(quiet, new Report() {
                        /**
                         * Report result directly to screen
                         * @param position Line number of currently evaluated APPLY instruction
                         * @param result   Result of evaluation
                         */
                        @Override
                        public void report(int position, int result) {
                            System.out.println((!quiet ? "Result is " : "") + result);
                        }
                    });
                } finally {
                    // release the resource
                    if (reader != null) {
                        reader.close();
                    }
                }
            } catch (IOException e) {
                System.err.println("Error processing file: " + file + " - " + e.getMessage());
            }
        }
    }

    /**
     * Evaluate all instruction in stack.
     * Goes through all instructions and for every instruction, that can start evaluation computes a result value
     *
     * @param quietly TRUE if enriched output (debug) shall be disabled
     * @param report  Report instance for reporting results of evaluation. Can be NULL.
     */
    public void evaluate(boolean quietly, Report report) {
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            if (instruction.startsEvaluation()) {
                if (!quietly) System.out.println("Applying number " + instruction.getArgument());
                int result = this.evaluate(quietly, i, instruction.getArgument());
                if (report != null) report.report(i, result);
            }
        }
    }

    /**
     * Do partial evaluation from start of instruction stack to specified limit (usually APPLY instruction).
     *
     * @param quietly    TRUE if enriched output (debug) shall be disabled
     * @param limit      Maximal number of instructions to evaluate
     * @param startValue Original value for evaluation
     * @return Computed result
     */
    private int evaluate(boolean quietly, int limit, int startValue) {
        int result = startValue;
        for (int i = 0; i < Math.min(limit, instructions.size()); i++) {
            Instruction instruction = instructions.get(i);
            if (!quietly) System.out.println((i + 1) + ": " + instruction.toString());
            result = instruction.evaluate(result);
        }
        return result;
    }
}
