import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConversionManager {
    public void startConversion(String inputFile, String[] outputFormats) {
        ExecutorService executor = Executors.newFixedThreadPool(outputFormats.length);

        for (String format : outputFormats) {
            executor.execute(new FileConversionTask(inputFile, format));
        }
        executor.shutdown();
    }
}

