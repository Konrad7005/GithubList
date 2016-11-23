package com.example.konrad.repositories;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Konrad on 2016-11-21.
 */
public class GithubRepository {

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    private long id;
    private String name;
    @SerializedName("html_url")
    private String htmlUrl;

    public long getId(){
        return id;

    }
}
