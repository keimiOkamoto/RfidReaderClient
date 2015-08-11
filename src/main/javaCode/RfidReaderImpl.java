package javaCode;


import java.util.Scanner;

public class RfidReaderImpl implements RfidReader {
    private static RfidReader rfidReader = null;

    private RfidReaderImpl() {
    }

    public static RfidReader getInstance() {
        if (rfidReader == null) return rfidReader = new RfidReaderImpl();
        else return rfidReader;
    }

    @Override
    public String readCard(Scanner scanner) {
        String readerInput = scanner.next();

        if (readerInput.equals("") || readerInput.isEmpty()) return null;
        return readerInput;
    }
}
