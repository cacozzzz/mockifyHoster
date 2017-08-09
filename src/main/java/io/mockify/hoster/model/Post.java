package io.mockify.hoster.model;

public class Post {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHtmlData() {
        return htmlData;
    }

    public void setHtmlData(String htmlData) {
        this.htmlData = htmlData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    int id;
    String htmlData;
    String name;
    String url;
}
