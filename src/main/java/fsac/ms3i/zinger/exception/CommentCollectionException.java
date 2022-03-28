package fsac.ms3i.zinger.exception;

public class CommentCollectionException extends Exception {
    private static final long serialVersionUID = 1L;

    public CommentCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "Comment with " + id + " not found!";
    }
}
