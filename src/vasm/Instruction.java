package vasm;

class Instruction {
    public Instruction() {

    }

    public String toString(){
        return "index:" + index + " Command:" + command.toString() + " operand left:" + opl + " operand right:" + opr;
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

        if( line.indexOf("\"") == -1 ){
            // No Strings. Easy Peasy.
            try {
                String val = split[0];
                ret.setCommand(Command.valueOf(val));
            } catch(Exception ex) { }

            try {
                String val = split[1];
                ret.setOpl(val);
            } catch(Exception ex) { }
            try {
                String val = split[2];
                ret.setOpr(val);
            } catch(Exception ex) {
                ret.setOpr(null);
            }

            return ret;
        }


        // Not so easy.

        //System.out.println("Parse:" + line);
        Command c = Command.valueOf(split[0]);
        int j = 1;
        try {
            if( split[1].indexOf("\"") != -1) {
                int idx = line.indexOf(" ");
                String val = line.substring(idx, line.length());
                for(int i = 0; i < val.length(); i++ ){
                    if( val.charAt(i) == ' '){
                        j++;
                    }
                }
                val = val.trim();
                ret.setCommand(c);
                ret.setOpl(val);
                ret.setOpr(null);
                return ret;
            }
            else {
                ret.setCommand(c);
                ret.setOpl(split[1]);
                j = 1;
            }
        } catch(Exception ex) { }
        try {
            String val = split[j+1];

            if( val.startsWith("\"")){
                int index = line.indexOf("\"");
                for(int i = index; i < line.length(); i++ ) {
                    val += line.charAt(i);
                    if( line.charAt(i) == '\"' ){
                        break;
                    }
                }
            }

            ret.setOpr(val);
        } catch(Exception ex) { ret.setOpr(null); }
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
