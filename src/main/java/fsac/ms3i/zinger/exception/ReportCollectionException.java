package fsac.ms3i.zinger.exception;

public class ReportCollectionException extends Exception {
    private static final long serialVersionUID = 1L;

    public ReportCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "Report with " + id + " not found!";
    }

    public static String ReportInvalid() {
        return "Invalid report!";
    }
}
