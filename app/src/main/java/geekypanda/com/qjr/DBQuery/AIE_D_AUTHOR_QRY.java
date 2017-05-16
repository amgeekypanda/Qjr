package geekypanda.com.qjr.DBQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import geekypanda.com.qjr.Model.AuthorGroupModel;
import geekypanda.com.qjr.Model.AuthorModel;
import geekypanda.com.qjr.Model.AuthorQuoteModel;
import geekypanda.com.qjr.Model.FAuthorQuoteModel;

/**
 * Created by devishen on 03-May-17.
 */
public class AIE_D_AUTHOR_QRY {



    // TABLE_AUTHOR Columns names
    private static final String a_id = "id";
    private static final String a_name = "name";
    private static final String a_group = "group";
    private static final String a_wiki = "wiki";
    private static final String a_occupation = "occupation";



    // TABLE_AUTHOR_GROUP Columns names
    private static final String ag_id = "id";
    private static final String ag_group = "group";
    private static final String ag_available = "available";


    // TABLE_AUTHOR_QUOTE
    private static final String aq_id = "id";
    private static final String aq_text = "text";
    private static final String aq_name = "name";
    private static final String aq_small_image = "small_image";
    private static final String aq_big_image = "big_image";

    //Table names
    private static final String TABLE_AUTHOR = "AUTHOR";
    private static final String TABLE_AUTHOR_GROUP = "AUTHOR_GROUP";
    private static final String TABLE_AUTHOR_QUOTE = "AUTHOR_QUOTE";


    //insert values newly once table is created #TABLE_AUTHOR
    public void insertAuthor(AuthorModel category_model, Context context) {
        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(a_id, category_model.getId());
        values.put(a_name, category_model.getName());
        values.put(a_group, category_model.getGroup_value());
        values.put(a_wiki, category_model.getWiki());
        values.put(a_occupation, category_model.getOccupation());

        db.insert(TABLE_AUTHOR, null, values);
        db.close();

        Gson gson = new Gson();

    }

    //insert values newly once table is created #TABLE_AUTHOR_GROUP
    public void insertAuthorGroup(AuthorGroupModel category_model, Context context) {
        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ag_id, category_model.getId());
        values.put(ag_group, category_model.getGroup_value());
        values.put(ag_available, category_model.getAvailable());

        db.insert(TABLE_AUTHOR_GROUP, null, values);
        db.close();

        Gson gson = new Gson();

    }

    //insert values newly once table is created #TABLE_AUTHOR_QUOTE
    public void insertAuthorQuote(AuthorQuoteModel category_model, Context context) {
        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(aq_id, category_model.getId());
        values.put(aq_name, category_model.getName());
        values.put(aq_text, category_model.getText());
        values.put(aq_big_image, category_model.getBig_image());
        values.put(aq_small_image, category_model.getSmall_image());

        db.insert(TABLE_AUTHOR_QUOTE, null, values);
        db.close();

        Gson gson = new Gson();

    }


    ////////////////////////////////////////////////////////////////////////////////////////////

    //delete all records of the table
    public void deleteAllAuthor(Context context) {

        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_AUTHOR);
        db.close();
    }

    //delete all records of the table
    public void deleteAllAuthorGroup(Context context) {

        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_AUTHOR_GROUP);
        db.close();
    }

    //delete all records of the table
    public void deleteAllAuthorQuote(Context context) {

        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_AUTHOR_QUOTE);
        db.close();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    //get all records from categorygroup table
    public List<AuthorGroupModel> getAuthorGroup(Context context) {
        List<AuthorGroupModel> aie_m_category = new ArrayList<AuthorGroupModel>();
        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getWritableDatabase();

        String sql="SELECT group FROM author_group where available = 0";
        Cursor cursor = db.rawQuery(sql,null);

        AuthorGroupModel newCategory = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {

                    newCategory = new AuthorGroupModel(cursor.getString(0));
                    aie_m_category.add(newCategory);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return aie_m_category;
    }

    //get all category related quote records from category and quote table
    public ArrayList<FAuthorQuoteModel> getAuthorQuote(Context context) {
        ArrayList<FAuthorQuoteModel> aie_m_carts = new ArrayList<FAuthorQuoteModel>();
        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getWritableDatabase();

        String sql = "SELECT a.name,a.group,a.wiki,a.occupation,b.text,b.big_image,b.small_image FROM author a join author_quote b on a.name = b.name";
        Cursor cursor = db.rawQuery(sql,null);

        FAuthorQuoteModel newCart = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    newCart = new FAuthorQuoteModel(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));

                    aie_m_carts.add(newCart);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return aie_m_carts;
    }

}
