# OCSP Parser
## What is this?
This is a basic client that prints information from an OCSP response using the Bouncy Castle Cryptography Library.

## How to use
### Prerequisites
Make sure you have installed Java.

### Input
Make sure you have the OCSP response you want to parse in a file in the `input` directory. An example file is included with the name `input.in`.

### Run
The client is packaged in the file `ocsp-parser.jar`. To run the client, open a terminal and navigate to the root folder of the project. Then run: `java -jar ocsp-parser.jar NAME_OF_FILE`. The output will be printed to the terminal.