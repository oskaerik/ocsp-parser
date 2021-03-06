package me.oskareriksson.ocsp;

import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.cert.ocsp.OCSPResp;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * A test class that tests the Parser class, making sure the OCSP response is valid.
 *
 * @author Oskar Eriksson
 * @version 1.0
 */
public class ParserTest {
    private byte[] ocspResponse;
    private Parser parser;

    /**
     * Constructor for the ParserTest class, uses the example file
     * example1.in to run the unit tests.
     *
     * @throws IOException
     * @throws OCSPException
     */
    public ParserTest() throws IOException, OCSPException {
        ocspResponse = new FileToByteArray("example1.in").getByteArray();
        parser = new Parser(ocspResponse);
    }

    /**
     * Assert that the OCSPResp object has a successful status
     */
    @Test
    public void verifyOcspRespStatus() {
        assertEquals("Non-successful status of OCSPResp", OCSPResp.SUCCESSFUL, parser.getOcspResp().getStatus());
    }

    /**
     * Assert that the BasicOCSPResponse object has one response
     */
    @Test
    public void verifySingleRespLength() {
        assertEquals("Length of SingleResp object was not 1", 1, parser.getSingleResponses().length);
    }

    /**
     * Assert that the response status is good
     */
    @Test
    public void verifySingleRespStatus() {
        assertEquals("Bad response status", null, parser.getSingleResponses()[0].getCertStatus());
    }
}