package java;

import java.util.Scanner;

public class RfidReaderImpl implements RfidReader {
    private static Scanner scanner;
    private static RfidReader rfidReader = null;

    private RfidReaderImpl() {
        scanner = new Scanner(System.in);
    }

    public static RfidReader getInstance() {
        if (rfidReader == null) return rfidReader = new RfidReaderImpl();
        else return rfidReader;
    }

    @Override
    public String readCard() {
        String readerInput = scanner.next();

        if (!readerInput.equals("") || !readerInput.isEmpty()) return readerInput;
        return null;
    }
}
