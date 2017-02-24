import org.bouncycastle.asn1.util.ASN1Dump;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.util.encoders.Base64;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The Main class contains the main method for the OCSP Parser client.
 *
 * @author Oskar Eriksson
 * @version 1.0
 */
public class Main {
    /**
     * Main method for the OCSP Parser client
     *
     * @param args Command line arguments
     * @throws IOException
     * @throws OCSPException
     */
    public static void main(String[] args) throws IOException, OCSPException {
        // Read the input file specified in the command line arguments
        byte[] ocspResponse = Files.readAllBytes(Paths.get("input/" + args[0]));

        // Create a new Parser object with the OCSP response byte array
        Parser parser = new Parser(ocspResponse);


        System.out.println("Signature: " + Base64.toBase64String(parser.getBasicOCSPResp().getSignature()));
        System.out.println("Issuer name hash: "
                + Base64.toBase64String(parser.getSingleResponses()[0].getCertID().getIssuerNameHash()));
        System.out.println("Issuer key hash: "
                + Base64.toBase64String(parser.getSingleResponses()[0].getCertID().getIssuerNameHash()));
        System.out.println("Serial number: " + parser.getSingleResponses()[0].getCertID().getSerialNumber());

    }
}
