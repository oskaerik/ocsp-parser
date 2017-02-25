package me.oskareriksson.ocsp;

import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.util.ASN1Dump;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.cert.ocsp.OCSPResp;
import org.bouncycastle.cert.ocsp.SingleResp;
import org.bouncycastle.util.encoders.Base64;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Parser class takes an Online Certificate Status Protocol (OCSP) response
 * in the form of a byte array, parses it and prints the information it contains.
 *
 * @author Oskar Eriksson
 * @version 1.0
 */
public class Parser {
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
     * Prints information from the OCSP response in simple form
     */
    public void printSimple() {
        // Print message
        System.out.println("Mode: simple");
        System.out.println("-----------------------\n");

        // Print signature, name hash, key hash and certificate ID serial number
        System.out.println("Signature: " + Base64.toBase64String(basicOCSPResp.getSignature()));
        System.out.println("Issuer name hash: "
                + Base64.toBase64String(singleResponses[0].getCertID().getIssuerNameHash()));
        System.out.println("Issuer key hash: "
                + Base64.toBase64String(singleResponses[0].getCertID().getIssuerKeyHash()));
        System.out.println("Certificate serial number: " + singleResponses[0].getCertID().getSerialNumber());

        // Look for interesting information in the ASN1Primitive dump string (no repetitions)
        System.out.println("\nThe following unique information was found in the ASN1Primitive dump:");
        // Look for UTF8String
        for (String match : findMatches("UTF8String\\((.*?)\\)", ASN1Dump.dumpAsString(asn1Primitive, true))) {
            System.out.println("UTF-8 String: " + match);
        }
        // Look for UTCTime
        for (String match : findMatches("UTCTime\\((.*?)\\)", ASN1Dump.dumpAsString(asn1Primitive, true))) {
            System.out.println("UTC time: " + match);
        }
        // Look for DER Octet String
        for (String match : findMatches("DER Octet String\\[\\d+\\]\\s+(.*?)\n", ASN1Dump.dumpAsString(asn1Primitive, true))) {
            System.out.println("DER Octet String: " + match);
        }
    }

    /**
     * Prints the dump string of the ASN1Primitive object
     */
    public void printDump() {
        // Print message
        System.out.println("Mode: dump");
        System.out.println("-----------------------\n");

        // Print dump
        System.out.println(ASN1Dump.dumpAsString(asn1Primitive, true));
    }

    /**
     * Private helper method that returns a set of matches from a given regex.
     * A set is used to prevent multiple matches of the same information.
     *
     * @param pattern The pattern to be matched
     * @param input The string to be searched
     * @return A set containing the matches
     */
    private Set<String> findMatches(String pattern, String input) {
        Set<String> matches = new HashSet<>();
        Matcher matcher = Pattern.compile(pattern).matcher(input);

        // Find all matches in the string and add to the set
        while (matcher.find()) {
            matches.add(matcher.group(1));
        }

        return matches;
    }
}
