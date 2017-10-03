package io.mockify.hoster.converters;

import io.mockify.hoster.model.Template;
import io.mockify.hoster.view.TemplatePresentable;
import org.springframework.stereotype.Component;

@Component
public class TemplatePresentableConverter implements Converter<Template, TemplatePresentable> {
    @Override
    public TemplatePresentable convertForth(Template from) {
        TemplatePresentable to = new TemplatePresentable();

        to.setId(from.getId());
        to.setName(from.getName());
        to.setContentTag(from.getContentTag());
        to.setHTMLdata(from.getHTMLdata());

        return to;
    }

    @Override
    public Template convertBack(TemplatePresentable from) {
        Template to = new Template();

        to.setId(from.getId());
        to.setName(from.getName());
        to.setContentTag(from.getContentTag());
        to.setHTMLdata(from.getHTMLdata());

        return to;
    }
}
