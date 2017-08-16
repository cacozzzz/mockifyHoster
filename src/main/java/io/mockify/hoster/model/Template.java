package io.mockify.hoster.model;

import java.util.List;

public class Template {

    private int id;
    private String name;
    private String HTMLdata;
    private String contentTag;
    private List<Resource> resourceList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHTMLdata() {
        return HTMLdata;
    }

    public void setHTMLdata(String HTMLdata) {
        this.HTMLdata = HTMLdata;
    }

    public String getContentTag() {
        return contentTag;
    }

    public void setContentTag(String contentTag) {
        this.contentTag = contentTag;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Template template = (Template) o;

        if (id != template.id) return false;
        if (name != null ? !name.equals(template.name) : template.name != null) return false;
        if (HTMLdata != null ? !HTMLdata.equals(template.HTMLdata) : template.HTMLdata != null) return false;
        if (contentTag != null ? !contentTag.equals(template.contentTag) : template.contentTag != null) return false;
        return resourceList != null ? resourceList.equals(template.resourceList) : template.resourceList == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (HTMLdata != null ? HTMLdata.hashCode() : 0);
        result = 31 * result + (contentTag != null ? contentTag.hashCode() : 0);
        result = 31 * result + (resourceList != null ? resourceList.hashCode() : 0);
        return result;
    }
}
