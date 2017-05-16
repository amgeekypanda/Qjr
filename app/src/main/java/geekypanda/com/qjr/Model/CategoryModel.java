package geekypanda.com.qjr.Model;

import java.io.Serializable;

/**
 * Created by Prasath on 3-may-2017.
 */
public class CategoryModel implements Serializable {


    private Integer id;
    private String name;
    private String group_value;
    private String big_image;
    private String small_image;
    private String available;

    public CategoryModel(int anInt, String string, String string1, String string2, String string3) {
    }

    public CategoryModel(int anInt, String string, String string1, String string2, String string3, String string4) {
    }

    public CategoryModel() {

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




    public String getGroup_value() {
        return group_value;
    }

    public void setGroup_value(String group_value) {
        this.group_value = group_value;
    }
}
