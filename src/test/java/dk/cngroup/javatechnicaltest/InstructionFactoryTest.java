package dk.cngroup.javatechnicaltest;

import org.junit.Assert;
import org.junit.Test;

public class InstructionFactoryTest {
    @Test
    public void testCreateAddInstruction() throws Exception {
        Instruction add = InstructionFactory.createInstruction("ADD", 5);
        Assert.assertEquals("Add", 5, add.evaluate(0));
    }

    @Test
    public void testCreateDivInstruction() throws Exception {
        Instruction div = InstructionFactory.createInstruction("DIVIDE", 10);
        Assert.assertEquals("Divide", 10, div.evaluate(100));
    }

    @Test
    public void testCreateFailInstruction() throws Exception {
        try {
            InstructionFactory.createInstruction("POKE", 0);
        } catch (Exception e) {
            return;
        }
        Assert.fail();
    }
}
