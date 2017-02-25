# OCSP Parser
## What is this?
This is a basic client that prints information from an OCSP response using the Bouncy Castle Cryptography Library.

## How to use
#### Prerequisites
Make sure Java is installed, then clone the repository.

#### Input
Make sure you have the OCSP response you want to parse in a file in the `input` directory. An example file is included with the name `input.in`.

#### Run
The file `ocsp-parser.jar` contains the client as a standalone jar file. To run the client, open a terminal and navigate to the root directory of the project. Then run: `java -jar ocsp-parser.jar NAME_OF_FILE MODE`. The output will be printed to the terminal.

If you have Gradle installed, you can run the client using the command: `gradle run -Pargs="NAME_OF_FILE MODE"`.

There are two modes to run the client in, the default `simple` mode, which also runs if the `MODE` argument is ignored. Then there is the `dump` mode that outputs the whole OCSP response.

For example, if you would like to run the included example file with the jar in `simple` mode, run: `java -jar ocsp-parser.jar input.in simple` or just leave out the MODE argument: `java -jar ocsp-parser.jar input.in`.

Another example, if you want to run the `dump` mode using Gradle: `gradle run -Pargs="input.in dump"`.