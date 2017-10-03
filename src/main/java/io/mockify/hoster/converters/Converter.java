package io.mockify.hoster.converters;

public interface Converter <F, S>{
    S convertForth(F from);
    F convertBack(S from);
}
