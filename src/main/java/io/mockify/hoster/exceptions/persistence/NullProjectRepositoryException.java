package io.mockify.hoster.exceptions.persistence;

public class NullProjectRepositoryException extends RuntimeException {
    public NullProjectRepositoryException(String message) {
        super(message);
    }
}
