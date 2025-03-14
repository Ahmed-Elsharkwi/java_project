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
            Thread.sleep(2000);


            File outputDir = new File("output_files/");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            String outputFile = "output_files/" + new File(inputFile).getName().replaceAll("\\..*$", "") + "." + outputFormat;


            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();

            System.out.println("Conversion to " + outputFormat + " completed: " + outputFile);
        } catch (Exception e) {
            System.err.println("Error converting file to " + outputFormat + ": " + e.getMessage());
        }
    }
}
