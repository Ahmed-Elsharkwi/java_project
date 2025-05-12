//public class Main {
//    public static void main(String[] args) {
//        String inputFile = "sample.txt";
//        String[] outputFormats = {"md", "html", "docx"};
//
//        ConversionManager manager = new ConversionManager();
//        manager.startConversion(inputFile, outputFormats);
//    }
//}
public class Main {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            ConverterGUI.main(args);
        });
    }
}
