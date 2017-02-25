package me.oskareriksson.ocsp;

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
    // Private fields
    private OCSPResp ocspResp;
    private BasicOCSPResp basicOCSPResp;
    private SingleResp[] singleResponses;
    private ASN1Primitive asn1Primitive;

    /**
     * Constructor for the Parser class. Creates the necessary objects for
     * printing information about the OCSP response.
     *
     * @param ocspByteArray The OCSP response (as a byte array) to be parsed
     * @throws IOException
     */
    public Parser(byte[] ocspByteArray) throws IOException, OCSPException {
        // Create OCSPResp and BasicOCSPResp objects from the OCSP response byte array and get the responses
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
     * @return The SingleResp array
     */
    public SingleResp[] getSingleResponses() {
        return singleResponses;
    }

    /**
     * Prints information from the OCSP response in the simplest possible form
     */
    public void printSimple() {
        System.out.println("Signature: " + Base64.toBase64String(basicOCSPResp.getSignature()));
        System.out.println("Issuer name hash: "
                + Base64.toBase64String(singleResponses[0].getCertID().getIssuerNameHash()));
        System.out.println("Issuer key hash: "
                + Base64.toBase64String(singleResponses[0].getCertID().getIssuerNameHash()));
        System.out.println("Certificate serial number: " + singleResponses[0].getCertID().getSerialNumber());
    }

    /**
     * Prints the dump string of the ASN1Primitive object
     */
    public void printDump() {
        System.out.println(ASN1Dump.dumpAsString(asn1Primitive, true));
    }
}
