
package com.appsinventiv.newsapp.NetworkResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("_type")
    @Expose
    private String type;
    @SerializedName("webSearchUrl")
    @Expose
    private String webSearchUrl;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("isBreakingNews")
    @Expose
    private Boolean isBreakingNews;
    @SerializedName("query")
    @Expose
    private Query query;
    @SerializedName("newsSearchUrl")
    @Expose
    private String newsSearchUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWebSearchUrl() {
        return webSearchUrl;
    }

    public void setWebSearchUrl(String webSearchUrl) {
        this.webSearchUrl = webSearchUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Boolean getIsBreakingNews() {
        return isBreakingNews;
    }

    public void setIsBreakingNews(Boolean isBreakingNews) {
        this.isBreakingNews = isBreakingNews;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public String getNewsSearchUrl() {
        return newsSearchUrl;
    }

    public void setNewsSearchUrl(String newsSearchUrl) {
        this.newsSearchUrl = newsSearchUrl;
    }

}
