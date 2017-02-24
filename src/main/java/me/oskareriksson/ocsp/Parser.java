package me.oskareriksson.ocsp;

import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.cert.ocsp.OCSPResp;
import org.bouncycastle.cert.ocsp.SingleResp;

import java.io.IOException;

/**
 * The me.oskareriksson.ocsp.Parser class takes an Online Certificate Status Protocol (OCSP) response
 * in the form of a byte array, parses it and prints the information it contains.
 *
 * @author Oskar Eriksson
 * @version 1.0
 */
public class Parser {
    OCSPResp ocspResp;
    BasicOCSPResp basicOCSPResp;
    SingleResp[] singleResponses;
    ASN1Primitive asn1Primitive;

    /**
     * Constructor for the me.oskareriksson.ocsp.Parser class
     *
     * @param ocspByteArray The OCSP response (as a byte array) to be parsed
     * @throws IOException
     */
    public Parser(byte[] ocspByteArray) throws IOException, OCSPException {
        // Create OCSPResp and BasicOCSPResp objects from the OCSP Response byte array and get the responses
        ocspResp = new OCSPResp(ocspByteArray);
        basicOCSPResp = (BasicOCSPResp) ocspResp.getResponseObject();
        singleResponses = basicOCSPResp.getResponses();

        // Create an ASN1Primitive object from the BasicOCSPResp object
        byte[] asnByteArray = basicOCSPResp.getEncoded();
        asn1Primitive = ASN1Primitive.fromByteArray(asnByteArray);

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

    /**
     * @return The ASN1Primitive object
     */
    public ASN1Primitive getAsn1Primitive() {
        return asn1Primitive;
    }
}
