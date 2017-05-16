package geekypanda.com.qjr.Model;

import java.io.Serializable;

/**
 * Created by devishen on 07-May-17.
 */
public class BannerModel implements Serializable {


    private Integer id;
    private String name;
    private String big_image;
    private String small_image;
    private String available;



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

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

}
