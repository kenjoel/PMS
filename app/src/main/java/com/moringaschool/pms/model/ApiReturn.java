
package com.moringaschool.pms.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiReturn {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("headline")
    @Expose
    private String headline;
    @SerializedName("documentation")
    @Expose
    private String documentation;
    @SerializedName("imgurl")
    @Expose
    private String imgurl;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("createdat")
    @Expose
    private Long createdat;
    @SerializedName("formattedCreatedAt")
    @Expose
    private String formattedCreatedAt;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ApiReturn() {
    }

    /**
     * 
     * @param imgurl
     * @param createdat
     * @param author
     * @param documentation
     * @param formattedCreatedAt
     * @param id
     * @param headline
     */
    public ApiReturn(Long id, String headline, String documentation, String imgurl, String author, Long createdat, String formattedCreatedAt) {
        super();
        this.id = id;
        this.headline = headline;
        this.documentation = documentation;
        this.imgurl = imgurl;
        this.author = author;
        this.createdat = createdat;
        this.formattedCreatedAt = formattedCreatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Long createdat) {
        this.createdat = createdat;
    }

    public String getFormattedCreatedAt() {
        return formattedCreatedAt;
    }

    public void setFormattedCreatedAt(String formattedCreatedAt) {
        this.formattedCreatedAt = formattedCreatedAt;
    }

}
