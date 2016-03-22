package de.skerkewitz.appreview.service;





import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by tropper on 19/03/16.
 */
@Entity
@Table(name="review")
public class ReviewEntry {

    @Id
    private int id;

    private int appStoreId;
    private AppStoreCC countryCode;
    private Date date;
    private String author;
    private int rating;
    private String version;
    private String title;
    private String text;

    public ReviewEntry() {
    }

    public ReviewEntry(int appStoreId, int id, AppStoreCC appStoreCC, Date date, String author, int rating, String version, String title, String text) {
        this.appStoreId = appStoreId;
        this.id = id;
        this.countryCode = appStoreCC;
        this.date = date;
        this.author = author;
        this.rating = rating;
        this.version = version;
        this.title = title;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppStoreId() {
        return appStoreId;
    }

    public void setAppStoreId(int appStoreId) {
        this.appStoreId = appStoreId;
    }

    public AppStoreCC getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(AppStoreCC countryCode) {
        this.countryCode = countryCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ReviewEntry{" +
                "appStoreId=" + appStoreId +
                ", id=" + id +
                ", countryCode='" + countryCode + '\'' +
                ", date=" + date +
                ", author='" + author + '\'' +
                ", rating=" + rating +
                ", version='" + version + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
