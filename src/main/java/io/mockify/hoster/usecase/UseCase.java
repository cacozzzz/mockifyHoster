package io.mockify.hoster.usecase;

public interface UseCase<T, R> {

    R execute(T t);

}
