package dk.cngroup.javatechnicaltest;

import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    private List<Instruction> instructions = new ArrayList<Instruction>();
    private static final Pattern pattern = Pattern.compile("^\\s*(\\p{Alpha}+)\\s*(-?\\d+)\\s*$");

    public Calculator(String file, boolean quietly) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            int linenum = 0;
            while (true) {
                linenum++;

                String line = reader.readLine();
                if (line == null) break;

                Matcher matcher = pattern.matcher(line);
                if (!matcher.matches()) {
                    throw new IOException("Invalid input on line " + linenum + " - " + line);
                }

                String instructionText = matcher.group(1).toUpperCase();
                int argument = Integer.parseInt(matcher.group(2));
                try {
                    Instruction instruction = InstructionFactory.createInstruction(instructionText, argument);
                    instruction.process(this.instructions, quietly);
                } catch (Exception e) {
                    throw new IOException("Invalid input on line " + linenum + " - " + e.getMessage());
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("h", "help", false, "Displays the help ad quits");
        options.addOption("q", "quiet", false, "Disables evaluation progress output");

        CommandLineParser parser = new GnuParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println("Invalid arguments");
            System.err.println(e.getMessage());
            return;
        }

        if (cmd.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Calculator [-h] [-q] [file1 [file2 [file3 [...]]]]", options);
            return;
        }

        boolean quiet = cmd.hasOption("q");

        for (Object file : cmd.getArgList()) {
            try {
                if (!quiet) System.out.println("Processing file " + file);
                new Calculator((String) file, quiet).evaluate();
            } catch (IOException e) {
                System.err.println("Error processing file: " + file + " - " + e.getMessage());
            }
        }
    }

    private void evaluate() {

    }
}
