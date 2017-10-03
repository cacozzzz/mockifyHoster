package io.mockify.hoster.view;

public class TemplatePresentable {

    private int id;
    private String name;
    private String HTMLdata;
    private String contentTag;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TemplatePresentable that = (TemplatePresentable) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        if (HTMLdata != null ? !HTMLdata.equals(that.HTMLdata) : that.HTMLdata != null) return false;
        return contentTag != null ? contentTag.equals(that.contentTag) : that.contentTag == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (HTMLdata != null ? HTMLdata.hashCode() : 0);
        result = 31 * result + (contentTag != null ? contentTag.hashCode() : 0);
        return result;
    }
}
