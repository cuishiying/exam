package com.shanglan.exam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shanglan.exam.base.BaseEntity;
import com.shanglan.exam.enums.AppType;

import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * Created by cuishiying on 2017/7/4.
 */
@Entity
public class AppVersion extends BaseEntity{
    private static final long serialVersionUID = 2171675175489815965L;

    private AppType type;

    private String version;

    private String downloadUrl;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime uploadTime;

    private String description;

    private boolean forceUpdate;  //是否强制更新

    public AppType getType() {
        return type;
    }

    public void setType(AppType type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }
}
