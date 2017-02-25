package me.oskareriksson.ocsp;

import org.bouncycastle.util.encoders.Base64;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * The FileToByteArray class parses a Base64 encoded file, decodes it and creates a byte array.
 *
 * @author Oskar Eriksson
 * @version 1.0
 */
public class FileToByteArray {
    byte[] byteArray;

    /**
     * Constructor for the FileToByteArray class. It takes a filename as a parameter
     * and creates a byte array from the Base64 encoded file.
     *
     * @throws IOException
     */
    public FileToByteArray(String fileName) throws IOException {
        // Read all the lines of the file into a list
        List<String> lines = Files.readAllLines(Paths.get("input/" + fileName));

        // Use StringBuilder to concatenate all the lines of the file to a single string
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            stringBuilder.append(line);
        }

        // Decode the Base64 string to a byte array
        byteArray = Base64.decode(stringBuilder.toString());
    }

    /**
     * @return The byte array
     */
    public byte[] getByteArray() {
        return byteArray;
    }
}
