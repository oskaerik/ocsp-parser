import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.util.ASN1Dump;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.cert.ocsp.OCSPResp;
import org.bouncycastle.cert.ocsp.SingleResp;
import org.bouncycastle.util.encoders.Base64;

import java.io.IOException;

/**
 * The Parser class takes an Online Certificate Status Protocol (OCSP) response
 * in the form of a byte array, parses it and prints the information it contains.
 *
 * @author Oskar Eriksson
 * @version 1.0
 */
public class Parser {
    OCSPResp ocspResp;
    BasicOCSPResp basicOCSPResp;
    SingleResp[] singleResponses;

    /**
     * Constructor for the Parser class
     *
     * @param ocspByteArray The OCSP response (as a byte array) to be parsed
     * @throws IOException
     */
    public Parser(byte[] ocspByteArray) throws IOException, OCSPException {
        // Create a BasicOCSPResp object from the OCSP response and get the ASN.1 encoded representation
        ocspResp = new OCSPResp(ocspByteArray);
        basicOCSPResp = (BasicOCSPResp) ocspResp.getResponseObject();
        singleResponses = basicOCSPResp.getResponses();
        byte[] asnByteArray = basicOCSPResp.getEncoded();

        // Create an ASN1Primitive object from the ASN.1 encoded byte array
        ASN1Primitive asnPrimitive = ASN1Primitive.fromByteArray(asnByteArray);

        // System.out.println(ASN1Dump.dumpAsString(asnPrimitive, true));
    }

    /**
     * @return The OCSPResp object
     */
    public OCSPResp getOcspResp() {
        return ocspResp;
    }

    /**
     * @return The BasicOCSPResp object
     */
    public BasicOCSPResp getBasicOCSPResp() {
        return basicOCSPResp;
    }

    /**
     * @return The SingleResp array
     */
    public SingleResp[] getSingleResponses() {
        return singleResponses;
    }
}
