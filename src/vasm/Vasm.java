/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vasm;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Sean
 */
public class Vasm {

    public void execute(Program p){
        
        int cursor = 0; //p.getCursor();
        p.setCursor(cursor);
        while(p.getCursor() <= p.instructionSet.size()) {
            cursor = p.getCursor();
            Instruction instruction = p.get(cursor);
            String cmd = instruction.getCommand().toString();
        
            if( cmd.equals(Command.DECLARE.toString())){
                // replace with a lookup.
                Variable variable = new Variable();
                variable.setName(instruction.getOpl());
                variable.setType(Type.valueOf(instruction.getOpr()));
                if( variable.getType().equals(Type.DECIMAL)) {
                    variable.setValue("0.0");
                }
                else if( variable.getType().equals(Type.LONG)) {
                    variable.setValue("0");
                }
                else if( variable.getType().equals(Type.INTEGER)) {
                    variable.setValue("0");
                }
                else if( variable.getType().equals(Type.BYTE)) {
                    variable.setValue("0");
                }
                else if( variable.getType().equals(Type.STRING)){
                    variable.setValue("");
                }
                p.varSet.put(instruction.getOpl(), variable);
                p.setCursor(++cursor);
            }
            else if( cmd.equals(Command.JUMP.toString())){
                String label = instruction.getOpl();
                Instruction labelInstruction = p.labelSet.get(label);
                int cursorByLabel = labelInstruction.getIndex();
                p.setCursor(cursorByLabel);
            }
            else if( cmd.equals(Command.PRINT.toString())){
                try {
                    Variable v = p.varSet.get(instruction.getOpl());
                    System.out.print(v.getValue());
                } catch(Exception ex) {
                    System.out.print(instruction.getOpl());
                }
                p.setCursor(++cursor);
            }
            else if( cmd.equals(Command.CRLF.toString())){
                try {
                    System.out.println();
                } catch(Exception ex) {
                }
                p.setCursor(++cursor);
            }
            else if( cmd.equals(Command.SET.toString())){
                Variable v = p.varSet.get(instruction.getOpl());
                String ro = instruction.getOpr();
                ro = ro.replaceAll("\"", "");
                v.setValue(ro);
                p.setCursor(++cursor);
            }
            else if( cmd.equals(Command.ADD.toString())){
                Variable v = p.varSet.get(instruction.getOpl());
                instruction.setResult("" + (Integer.parseInt(v.getValue()) + Integer.parseInt(instruction.getOpr())));
                v.setValue(instruction.getResult());
                p.setCursor(++cursor);
            }
            else if( cmd.equals(Command.SLEEP.toString())){
                try {
                    Thread.sleep(Long.parseLong(instruction.getOpl()));
                } catch(Exception ex) { }
                p.setCursor(++cursor);
            }
            else if( cmd.equals(Command.IF.toString())){
                String vv1 = "";
                String vv2 = "";
                try { Variable v1 = p.varSet.get(instruction.getOpl()); vv1 = v1.getValue(); } catch(Exception ex) { vv1 = instruction.getOpl(); }
                try { Variable v2 = p.varSet.get(instruction.getOpr()); vv2 = v2.getValue(); } catch(Exception ex) { vv2 = instruction.getOpr();}
                if( vv1.equals(vv2)){
                } else {
                    ++cursor;
                }
                p.setCursor(++cursor);
            }
            else if( cmd.equals(Command.MULTIPLE.toString())){
                Variable v = p.varSet.get(instruction.getOpl());
                instruction.setResult("" + (Integer.parseInt(v.getValue()) * Integer.parseInt(instruction.getOpr())));
                v.setValue(instruction.getResult());
                p.setCursor(++cursor);
            }
            else if( cmd.equals(Command.DIVIDE.toString())){
                Variable v = p.varSet.get(instruction.getOpl());
                instruction.setResult("" + (Integer.parseInt(v.getValue()) / Integer.parseInt(instruction.getOpr())));
                v.setValue(instruction.getResult());
                p.setCursor(++cursor);
            }
            else if( cmd.equals(Command.SUBTRACT.toString())){
                Variable v = p.varSet.get(instruction.getOpl());
                instruction.setResult("" + (Integer.parseInt(v.getValue()) - Integer.parseInt(instruction.getOpr())));
                v.setValue(instruction.getResult());
                p.setCursor(++cursor);
            }
            else if( cmd.equals(Command.LABEL.toString())){
                p.setCursor(++cursor);

            }
            else if( cmd.equals(Command.NULL.toString())){
                p.setCursor(++cursor);
            }
            else if( cmd.equals(Command.EXIT.toString())){
                p = null;
                return;
            }
        }

    }

    public static void print(Program p)
    {
        ArrayList set = (ArrayList)p.instructionSet;
        for(int i = 0; i < set.size(); i++ ) {
            System.out.println(set.get(i));
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic her

        try {
            Loader loader = new Loader(new File("./file.vasm"));

            Program p = loader.compile();

            print(p);

            Vasm v = new Vasm();
            v.execute(p);

        } catch(Exception ex) {
            ex.getMessage();
        }
        
    }
    
}
