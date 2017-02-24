package me.oskareriksson.ocsp;

import org.bouncycastle.asn1.util.ASN1Dump;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.util.encoders.Base64;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The me.oskareriksson.ocsp.Main class contains the main method for the OCSP me.oskareriksson.ocsp.Parser client.
 *
 * @author Oskar Eriksson
 * @version 1.0
 */
public class Main {
    /**
     * me.oskareriksson.ocsp.Main method for the OCSP me.oskareriksson.ocsp.Parser client
     *
     * @param args Command line arguments
     * @throws IOException
     * @throws OCSPException
     */
    public static void main(String[] args) throws IOException, OCSPException {
        // Read the input file specified in the command line arguments
        byte[] ocspResponse = Files.readAllBytes(Paths.get("input/" + args[0]));

        // Create a new me.oskareriksson.ocsp.Parser object with the OCSP response byte array
        Parser parser = new Parser(ocspResponse);


        System.out.println("Signature: " + Base64.toBase64String(parser.getBasicOCSPResp().getSignature()));
        System.out.println("Issuer name hash: "
                + Base64.toBase64String(parser.getSingleResponses()[0].getCertID().getIssuerNameHash()));
        System.out.println("Issuer key hash: "
                + Base64.toBase64String(parser.getSingleResponses()[0].getCertID().getIssuerNameHash()));
        System.out.println("Certificate serial number: " + parser.getSingleResponses()[0].getCertID().getSerialNumber());


        System.out.println();
        System.out.println("The OCSP Response:");
        System.out.println(ASN1Dump.dumpAsString(parser.getAsn1Primitive(), true));
    }
}
