package io.lasantha.filegen;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Generates files with given file-size with dummy data.
 *
 */
public class FileGenerator
{
    private static final int BUFFER_SIZE = 4096;
    private final String filename;
    private final long fileSize;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: FileGenerator [file_name] [file_size_in_bytes]");
            return;
        }

        String filename = args[0];
        long fileSize = Long.parseLong(args[1]);

        FileGenerator generator = new FileGenerator(filename, fileSize);
        generator.generate();
    }

    private FileGenerator(String filename, long fileSize) {
        this.filename = filename;
        this.fileSize = fileSize;
    }

    private void generate() {
        Random random = new Random();
        FileOutputStream fos = null;
        String dest = getFilePath().toString();
        try {
            fos = new FileOutputStream(dest, true);

            long contentLength = -1;
            byte[] buffer;
            long delta;
            int bufferSize;
            while (contentLength < fileSize) {
                delta = fileSize - contentLength;
                bufferSize = delta > BUFFER_SIZE ? BUFFER_SIZE : (int) delta;
                buffer = new byte[bufferSize];
                random.nextBytes(buffer);
                fos.write(buffer);
                contentLength += bufferSize;

                System.out.print("\rGenerating the file: " + (Math.round(contentLength * 100) / fileSize)  + "% completed.");
            }
            System.out.println("\nFile generated at " + dest);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    System.out.println("Error occurred: " + e.getMessage());
                }
            }
        }
    }

    private Path getFilePath() {
        return Paths.get("", filename);
    }
}
