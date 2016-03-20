package de.skerkewitz.appreview.service;

import java.util.Date;

/**
 * Created by tropper on 19/03/16.
 */
public class ReviewEntry {

    public final int id;
    public final AppStoreCC countryCode;
    public final Date date;
    public final String author;
    public final int rating;
    public final String version;
    public final String title;
    public final String text;

    public ReviewEntry(int id, AppStoreCC appStoreCC, Date date, String author, int rating, String version, String title, String text) {
        this.id = id;
        this.countryCode = appStoreCC;
        this.date = date;
        this.author = author;
        this.rating = rating;
        this.version = version;
        this.title = title;
        this.text = text;
    }

    @Override
    public String toString() {
        return "ReviewEntry{" +
                "id=" + id +
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
