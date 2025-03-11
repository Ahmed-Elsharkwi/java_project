import java.io.*;

public class FileConversionTask implements Runnable {
    private String inputFile;
    private String outputFormat;

    public FileConversionTask(String inputFile, String outputFormat) {
        this.inputFile = inputFile;
        this.outputFormat = outputFormat;
    }

    @Override
    public void run() {
        try {
            System.out.println("Converting " + inputFile + " to " + outputFormat);
            Thread.sleep(2000); // Simulate processing

            // Create output directory if not exists
            File outputDir = new File("output_files/");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            // Generate output file name
            String outputFile = "output_files/" + new File(inputFile).getName().replaceAll("\\..*$", "") + "." + outputFormat;

            // Read content from input file
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine(); // Ensure proper line breaks
            }

            reader.close();
            writer.close();

            System.out.println("Conversion to " + outputFormat + " completed: " + outputFile);
        } catch (Exception e) {
            System.err.println("Error converting file to " + outputFormat + ": " + e.getMessage());
        }
    }
}
