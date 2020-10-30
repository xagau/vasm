package vasm;

import java.util.ArrayList;
import java.util.HashMap;

class Program {

    public HashMap<String, Instruction> labelSet = new HashMap<String, Instruction>();
    public HashMap<String, Variable> varSet = new HashMap<String, Variable>();
    /**
     * @return the cursor
     */
    public int getCursor() {
        return cursor;
    }

    /**
     * @param cursor the cursor to set
     */
    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

    ArrayList instructionSet = new ArrayList();

    public void add(Instruction instruction) {
        if( instruction.getCommand().equals(Command.LABEL)){
            labelSet.put(instruction.getOpl(), instruction);
            instructionSet.add(instruction);
        } else {
            instructionSet.add(instruction);
        }
    }

    private int cursor = 0;

    public Instruction get(int index) {
        return (Instruction) instructionSet.get(index);
    }
}
