package io.mockify.hoster.converters;

import io.mockify.hoster.model.Resource;
import io.mockify.hoster.view.ResourcePresentable;
import org.springframework.stereotype.Component;

@Component
public class ResourcePresentableConverter implements Converter<Resource, ResourcePresentable> {
    @Override
    public ResourcePresentable convertForth(Resource from) {
        ResourcePresentable to = new ResourcePresentable();

        to.setId(from.getId());
        to.setName(from.getName());
        to.setSize(from.getSize());
        to.setType(from.getType());
        to.setUrl(from.getUrl());

        return to;
    }

    @Override
    public Resource convertBack(ResourcePresentable from) {
        Resource to = new Resource();

        to.setId(from.getId());
        to.setName(from.getName());
        to.setSize(from.getSize());
        to.setType(from.getType());
        to.setUrl(from.getUrl());

        return to;
    }
}
