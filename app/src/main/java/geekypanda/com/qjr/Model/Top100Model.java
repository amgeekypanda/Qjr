package geekypanda.com.qjr.Model;

/**
 * Created by prasath on 02-May-17.
 */
public class Top100Model {


    private Integer id;
    private String text;
    private String big_image;
    private String small_image;
    private String available;

    public Top100Model(int anInt, String string, String string1, String string2, String string3) {

    }

    public String getSmall_image() {
        return small_image;
    }

    public void setSmall_image(String small_image) {
        this.small_image = small_image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }


}



