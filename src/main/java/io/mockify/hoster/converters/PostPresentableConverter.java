package io.mockify.hoster.converters;

import io.mockify.hoster.model.Post;
import io.mockify.hoster.view.PostPresentable;
import org.springframework.stereotype.Component;

@Component
public class PostPresentableConverter implements Converter<Post,PostPresentable> {
    @Override
    public PostPresentable convertForth(Post from) {
        PostPresentable to = new PostPresentable();

        to.setId(from.getId());
        to.setName(from.getName());
        to.setPostDate(from.getPostDate());
        to.setHtmlData(from.getHtmlData());
        to.setUrl(from.getUrl());

        return to;
    }

    @Override
    public Post convertBack(PostPresentable from) {
        Post to = new Post();

        to.setId(from.getId());
        to.setName(from.getName());
        to.setPostDate(from.getPostDate());
        to.setHtmlData(from.getHtmlData());
        to.setUrl(from.getUrl());

        return to;
    }
}
