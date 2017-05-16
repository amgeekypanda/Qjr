package geekypanda.com.qjr.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by prasath on 03-May-2017.
 */
public class FCategoryQuoteModel implements Parcelable {


    private Integer id;
    private Integer quote_id;
    private String name;


    private String group_value;
    private String text;
    private String big_image;
    private String small_image;

    public FCategoryQuoteModel(int anInt, String string, String string1, String string2, String string3) {
    }

    public FCategoryQuoteModel(int anInt, String string, String string1, String string2, String string3, String string4) {
    }

    public FCategoryQuoteModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(Integer quote_id) {
        this.quote_id = quote_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBig_image() {
        return big_image;
    }

    public void setBig_image(String big_image) {
        this.big_image = big_image;
    }

    public String getSmall_image() {
        return small_image;
    }

    public void setSmall_image(String small_image) {
        this.small_image = small_image;
    }



    public String getGroup_value() {
        return group_value;
    }

    public void setGroup_value(String group_value) {
        this.group_value = group_value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
