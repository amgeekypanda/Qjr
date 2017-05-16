package geekypanda.com.qjr.Model;

/**
 * Created by devishen on 03-May-17.
 */
public class CategoryToQuoteModel {


    private Integer id;
    private String category_id;
    private String quote_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(String quote_id) {
        this.quote_id = quote_id;
    }


}
