public class Main {
    public static void main(String[] args) {
        String inputFile = "sample.txt";
        String[] outputFormats = {"pdf", "html", "docx"};

        ConversionManager manager = new ConversionManager();
        manager.startConversion(inputFile, outputFormats);
    }
}
