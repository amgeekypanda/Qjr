package geekypanda.com.qjr.Model;

/**
 * Created by prasath on 02-May-17.
 */
public class AuthorGroupModel {



    private Integer id;


    private String group_value;
    private String available;




    public String getGroup_value() {
        return group_value;
    }

    public void setGroup_value(String group_value) {
        this.group_value = group_value;
    }

    public AuthorGroupModel() {
    }

    public AuthorGroupModel(int anInt, String string, String string1) {
    }

    public AuthorGroupModel(String string) {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }







    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }




}
