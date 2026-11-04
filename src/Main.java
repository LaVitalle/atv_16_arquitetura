public class Main {

    public static void main(String[] args) {
        ReportGenerator csvReport = new CsvReport();
        ReportGenerator htmlReport = new HtmlReport();
        ReportGenerator textReport = new TextReport();

        System.out.println("=== CSV ===");
        csvReport.generate();

        System.out.println("\n=== HTML ===");
        htmlReport.generate();

        System.out.println("\n=== Text ===");
        textReport.generate();
    }
}
