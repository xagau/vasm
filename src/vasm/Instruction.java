package vasm;

class Instruction {
    public Instruction() {

    }

    public Instruction(int index, Command c, String opl, String opr) {
        setCommand(c);
        this.opl = opl;
        this.opr = opr;
        this.setIndex(index);
    }

    private Command command = Command.NULL;
    private String opl;
    private String opr;
    private String result = "";
    private int index = -1;

    public static Instruction parse(String line) {
        String split[] = line.split(" ");
        Instruction ret = new Instruction();

        Command c = Command.valueOf(split[0]);
        try { ret.setOpl(split[1]); } catch(Exception ex) { }
        try { ret.setOpr(split[2]); } catch(Exception ex) { }
        ret.setCommand(c);
        return ret;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public String getOpl() {
        return opl;
    }

    public void setOpl(String opl) {
        this.opl = opl;
    }

    public String getOpr() {
        return opr;
    }

    public void setOpr(String opr) {
        this.opr = opr;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
