package model;


import java.util.Date;

public class Song {
    Integer id;
    String name;
    Integer singerId;
    String album;
    Integer source;
    String url;
    String lyric;
    Date createTime;
    Date updateTime;

    public Song(Integer id, String name, Integer singerId, String album, Integer source, String url, String lyric, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.singerId = singerId;
        this.album = album;
        this.source = source;
        this.url = url;
        this.lyric = lyric;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Song(String name, Integer singerId, String album, Integer source, String url) {
        this.name = name;
        this.singerId = singerId;
        this.album = album;
        this.source = source;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", singerId=" + singerId +
                ", album='" + album + '\'' +
                ", source=" + source +
                ", url='" + url + '\'' +
                ", lyric='" + lyric + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
