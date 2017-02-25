# OCSP Parser
## What is this?
This is a basic client that prints information from an OCSP response using the Bouncy Castle Cryptography Library.

## How to use
#### Prerequisites
Make sure Java is installed, then clone the repository.

#### Input
Make sure you have the OCSP response you want to parse in a file in the `input` directory. There are three example files included, `example1.in`, `example2.in` and `example3.in`.

#### Run
The file `ocsp-parser.jar` contains the client as a standalone jar file. To run the client, open a terminal and navigate to the root directory of the project. Then run: `java -jar ocsp-parser.jar $FILENAME $MODE`. The output will be printed to the terminal.

If you have Gradle installed, you can run the client using the command: `gradle run -Pargs="$FILENAME $MODE"`.

There are two modes, the default `simple` mode, which also runs if the `$MODE` argument is ignored. Then there is the `dump` mode that outputs the whole OCSP response dump string.

For example, if you would like to run the included example file `example1.in` with the jar in `simple` mode, run:
```
java -jar ocsp-parser.jar example1.in simple
```
Or just leave out the `$MODE` argument: 
```
java -jar ocsp-parser.jar example1.in
```

Another example, if you want to parse the file `example2.in` using the `dump` mode with Gradle:
```
gradle run -Pargs="example2.in dump"
```