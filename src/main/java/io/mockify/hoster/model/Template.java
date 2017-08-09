package io.mockify.hoster.model;

import java.util.List;

public class Template {
    int id;
    String name;
    String HTMLdata;
    String contentTag;

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

    List<Resource> resourceList;

}
