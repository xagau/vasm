# vasm
Virtual Assembly Simple Machine Code

This is a very simple programming language I created for fun. You can alter your program by altering file.vasm

You can run it by running: 
```
java Vasm
```

You can compile Vasm by running (from the source folder):
```
javac Vasm.java
```

Make sure file.vasm (your program) and Vasm are in the same directory. You can do hello world vasm by changing the code to

```
PRINT "HelloWorld"
```

Note - this is only a toy and has lots of issues with it. I wouldn't use it without further modication as it has problems with the strings, among other things. It is basically incompete. I just wrote this for an exercise. I have a couple of ideas that could make it useful one day. It is not really suitable for anything except playing around.

The sample program that comes with it, 

```
DECLARE Y INTEGER
LABEL begin
DECLARE X STRING
SET X "COUNTING:"
ADD Y 1
PRINT X
PRINT Y
CRLF
SLEEP 1000
IF Y 10
JUMP quit
JUMP begin
LABEL quit
EXIT
```

Should count to 10 and then quit/exit.

