package me.oskareriksson.ocsp;

import org.bouncycastle.cert.ocsp.OCSPException;

import java.io.IOException;

/**
 * The Main class contains the main method for the OCSP Parser client.
 * It takes two arguments, the first is the name of the file to be parsed, and the
 * second (optional) is which mode to run (simple/dump).
 * If there's no second argument, it runs the simple mode.
 *
 * @author Oskar Eriksson
 * @version 1.0
 */
public class Main {

    /**
     * Main method for the OCSP Parser client, reads the input file and runs the correct mode
     *
     * @param args Command line arguments
     * @throws IOException
     * @throws OCSPException
     */
    public static void main(String[] args) throws IOException, OCSPException {
        // Read the input file specified in the command line arguments
        byte[] ocspResponse = new FileToByteArray(args[0]).getByteArray();

        // Create a new Parser object with the OCSP response byte array
        Parser parser = new Parser(ocspResponse);

        // Display message
        System.out.println("----- OCSP Parser -----");
        System.out.println("Parsing file: " + args[0]);

        if (args.length == 1 || args[1].equals("simple")) {
            // If the client runs in simple mode, output information from the OCSP response in simple form
            parser.printSimple();
        } else if (args[1].equals("dump")) {
            // If the client runs in dump mode, dump the OCSP response using dumpAsString
            parser.printDump();
        } else {
            // The mode argument was invalid, no such mode
            throw new IOException("No such mode: " + args[1]);
        }
    }
}
