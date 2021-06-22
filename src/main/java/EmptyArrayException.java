class EmptyArrayException extends RuntimeException {

    EmptyArrayException() {
        super("Cannot perform this action on an empty array");
    }

    EmptyArrayException(String message) {
        super(message);
    }
}