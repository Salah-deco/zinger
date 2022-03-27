package fsac.ms3i.zinger.exception;

public class PostCollectionException extends Exception {
    private static final long serialVersionUID = 1L;

    public PostCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "Post with " + id + " not found!";
    }
}
