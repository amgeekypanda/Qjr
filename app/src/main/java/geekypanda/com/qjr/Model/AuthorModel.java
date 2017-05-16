package geekypanda.com.qjr.Model;

/**
 * Created by devishen on 02-May-17.
 */
public class AuthorModel {

    private Integer id;
    private String name;
    private String group_value;
    private String wiki;
    private String occupation;
    private String available;



    public AuthorModel() {
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



    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
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
