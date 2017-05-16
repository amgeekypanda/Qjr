    package geekypanda.com.qjr.Model;

    import android.os.Parcel;
    import android.os.Parcelable;

    /**
 * Created by prasath on 02-May-17.
 */
public class FAuthorQuoteModel implements Parcelable {

    private Integer id;
    private String name;
    private String group_value;
    private String wiki;
    private String occupation;
    private String text;
    private String big_image;
    private String small_image;

    public FAuthorQuoteModel(String string, String string1, String string2, String string3, String string4, String string5, String string6) {
    }


        protected FAuthorQuoteModel(Parcel in) {
            name = in.readString();
            group_value = in.readString();
            wiki = in.readString();
            occupation = in.readString();
            text = in.readString();
            big_image = in.readString();
            small_image = in.readString();
        }

        public static final Creator<FAuthorQuoteModel> CREATOR = new Creator<FAuthorQuoteModel>() {
            @Override
            public FAuthorQuoteModel createFromParcel(Parcel in) {
                return new FAuthorQuoteModel(in);
            }

            @Override
            public FAuthorQuoteModel[] newArray(int size) {
                return new FAuthorQuoteModel[size];
            }
        };

        public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki)  {

        this.wiki = wiki;
    }




    public String getGroup_value() {
        return group_value;
    }

    public void setGroup_value(String group_value) {
        this.group_value = group_value;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

 public FAuthorQuoteModel() {
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(name);
            parcel.writeString(group_value);
            parcel.writeString(wiki);
            parcel.writeString(occupation);
            parcel.writeString(text);
            parcel.writeString(big_image);
            parcel.writeString(small_image);
        }
    }
