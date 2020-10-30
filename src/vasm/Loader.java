package vasm;

import java.io.*;
import java.net.MalformedURLException;

public class Loader {
    Program program = new Program();

    InputStream stream = null;

    public Loader (File file) throws MalformedURLException, IOException {
        this(file.toURI().toURL().openStream());
    }
    public Loader (FileInputStream fstream) {
        this((InputStream)fstream);
    }
    public Loader(InputStream stream)
    {
        this.stream = stream;
    }

    public Program compile()
    {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            String line = "";
            int index = 0;
            while ((line = reader.readLine()) != null){
                Instruction instruction = Instruction.parse(line);
                instruction.setIndex(index++);
                program.add(instruction);
            }

            reader.close();
            stream.close();
            return program;

        } catch(Exception ex) {
            System.out.println("Exception:" + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
}
