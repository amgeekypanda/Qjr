package geekypanda.com.qjr.DBQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import geekypanda.com.qjr.Model.CategoryGroupModel;
import geekypanda.com.qjr.Model.CategoryModel;
import geekypanda.com.qjr.Model.FCategoryQuoteModel;
import geekypanda.com.qjr.Model.CategoryToQuoteModel;
import geekypanda.com.qjr.Model.QuoteModel;
import geekypanda.com.qjr.Model.Top100Model;


/**
 * Created by Prasath on 03-May-2017.
 */
public class AIE_D_CATEGORY_QRY {

    private AIE_D_CATEGORY_QRY dbHelper;

    // TABLE_CATEGORY Columns names
    private static final String c_id = "id";
    private static final String c_name = "name";
    private static final String c_group_value = "group_value";
    private static final String c_big_image = "big_image";
    private static final String c_small_image = "small_image";
    private static final String c_available = "available";

    // TABLE_CATEGORY_GROUP Columns names
    private static final String cg_id = "id";
    private static final String cg_group_value = "group_value";
    private static final String cg_available = "available";

    // TABLE_CATEGORYTOQUOTE Columns names
    private static final String ctq_id = "id";
    private static final String ctq_category_id = "category_id";
    private static final String ctq_quote_id = "quote_id";


    // TABLE_QUOTE Columns names
    private static final String q_id = "id";
    private static final String q_text = "text";
    private static final String q_big_image = "big_image";
    private static final String q_small_image = "small_image";
    private static final String q_available = "available";


    // TABLE_TOP100 Columns names
    private static final String top_id = "id";
    private static final String top_text = "text";
    private static final String top_small_image = "small_image";
    private static final String top_big_image = "big_image";
    private static final String top_available = "available";

    //tables
    private static final String TABLE_CATEGORY = "CATEGORY";
    private static final String TABLE_CATEGORY_GROUP = "CATEGORY_GROUP";
    private static final String TABLE_CATEGORYTOQUOTE = "CATEGORYTOQUOTE";
    private static final String TABLE_QUOTE = "QUOTE";
    private static final String TABLE_TOP100 = "TOP100";


    //insert values newly once table is created #TABLE_CATEGORY
    public void insertCategory(ArrayList<CategoryModel> category_model, Context context) {

        String sql = "INSERT INTO " + TABLE_CATEGORY
                + " VALUES (?,?,?,?,?,?);";
        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        for (int idx = 0; idx < category_model.size(); idx++) {
            Gson gson = new Gson();

            statement.clearBindings();
            statement.bindLong(1, category_model.get(idx).getId());
            statement.bindString(2, category_model.get(idx).getName());
            statement.bindString(3, category_model.get(idx).getGroup_value());

            try {
                if (category_model.get(idx).getSmall_image() != null) {
                    statement.bindString(4, category_model.get(idx).getBig_image());
                } else {
                    statement.bindString(4, "");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            try {
                if (category_model.get(idx).getSmall_image() != null) {
                    statement.bindString(5, category_model.get(idx).getSmall_image());
                } else {
                    statement.bindString(5, "");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            statement.bindString(6, category_model.get(idx).getAvailable());
            statement.execute();
        }
        db.setTransactionSuccessful();
        db.endTransaction();


    }

    //insert values newly once table is created #TABLE_CATEGORY_GROUP
    public void insertCategoryGroup(ArrayList<CategoryGroupModel> categoryGroupModel, Context context) {

        String sql = "INSERT INTO " + TABLE_CATEGORY_GROUP
                + " VALUES (?,?,?);";
        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        for (int idx = 0; idx < categoryGroupModel.size(); idx++) {
            Gson gson = new Gson();

            statement.clearBindings();
            statement.bindLong(1, categoryGroupModel.get(idx).getId());
            statement.bindString(2, categoryGroupModel.get(idx).getGroup_value());
            statement.bindString(3, categoryGroupModel.get(idx).getAvailable());

            statement.execute();
        }
        db.setTransactionSuccessful();
        db.endTransaction();


    }

    //insert values newly once table is created #TABLE_CATEGORYTOQUOTE
    public void insertCategoryToQuote(ArrayList<CategoryToQuoteModel> categoryToQuoteModel, Context context) {
        String sql = "INSERT INTO " + TABLE_CATEGORYTOQUOTE
                + " VALUES (?,?,?);";
        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        for (int idx = 0; idx < categoryToQuoteModel.size(); idx++) {
            Gson gson = new Gson();

            statement.clearBindings();
            statement.bindLong(1, categoryToQuoteModel.get(idx).getId());
            statement.bindString(2, categoryToQuoteModel.get(idx).getCategory_id());
            statement.bindString(3, categoryToQuoteModel.get(idx).getQuote_id());

            statement.execute();
        }
        db.setTransactionSuccessful();
        db.endTransaction();

    }

    //insert values newly once table is created #TABLE_QUOTE
    public void insertQuote(ArrayList<QuoteModel> quote_model, Context context) {
        String sql = "INSERT INTO " + TABLE_QUOTE
                + " VALUES (?,?,?,?,?);";
        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        for (int idx = 0; idx < quote_model.size(); idx++) {
            Gson gson = new Gson();

            statement.clearBindings();
            statement.bindLong(1, quote_model.get(idx).getId());
            statement.bindString(2, quote_model.get(idx).getText());

            try {
                if (quote_model.get(idx).getBig_image() != null) {
                    statement.bindString(3, quote_model.get(idx).getBig_image());
                } else {
                    statement.bindString(3, "");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

            try {
                if (quote_model.get(idx).getSmall_image() != null) {
                    statement.bindString(4, quote_model.get(idx).getSmall_image());
                } else {
                    statement.bindString(4, "");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            statement.bindString(5, quote_model.get(idx).getAvailable());
            statement.execute();
        }
        db.setTransactionSuccessful();
        db.endTransaction();


    }

    //insert values newly once table is created #TABLE_QUOTE
    public void insertTop100(ArrayList<Top100Model> top100_model, Context context) {

        String sql = "INSERT INTO " + TABLE_TOP100
                + " VALUES (?,?,?,?,?);";
        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        for (int idx = 0; idx < top100_model.size(); idx++) {
            Gson gson = new Gson();

            statement.clearBindings();
            statement.bindLong(1, top100_model.get(idx).getId());
            statement.bindString(2, top100_model.get(idx).getText());

            try {
                if (top100_model.get(idx).getBig_image() != null) {
                    statement.bindString(3, top100_model.get(idx).getBig_image());
                } else {
                    statement.bindString(3, "");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

            try {
                if (top100_model.get(idx).getSmall_image() != null) {
                    statement.bindString(4, top100_model.get(idx).getSmall_image());
                } else {
                    statement.bindString(4, "");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            statement.bindString(5, top100_model.get(idx).getAvailable());
            statement.execute();
        }
        db.setTransactionSuccessful();
        db.endTransaction();



    }

    /////////////////////////////////////////////////////////////////////////////////////////

        //delete all records of the table
    public void deleteAllCategory(Context context) {

        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CATEGORY);
        db.close();
    }

    //delete all records of the table
    public void deleteAllCategoryGroup(Context context) {

        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CATEGORY_GROUP);
        db.close();
    }

    //delete all records of the table
    public void deleteAllCategoryToQuote(Context context) {

        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CATEGORYTOQUOTE);
        db.close();
    }

    //delete all records of the table
    public void deleteAllQuote(Context context) {

        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_QUOTE);
        db.close();
    }

    //delete all records of the table
    public void deleteAllTop100(Context context) {

        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TOP100);
        db.close();
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    //get all records from category table
    public ArrayList<CategoryModel> getCategory(Context context) {

        ArrayList<CategoryModel> dum = new ArrayList<CategoryModel>();

        String selectQuery =  "SELECT * FROM " + dbHelper.TABLE_CATEGORY + " where " + dbHelper.c_available + "= 0 order by " + dbHelper.c_name;

        Log.e("Notification", selectQuery);

        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                CategoryModel td = new CategoryModel();
                td.setId(c.getInt(c.getColumnIndex(dbHelper.c_id)));
                td.setName(c.getString(c.getColumnIndex(dbHelper.c_name)));
                td.setGroup_value(c.getString(c.getColumnIndex(dbHelper.c_group_value)));
                td.setBig_image(c.getString(c.getColumnIndex(dbHelper.c_big_image)));
                td.setSmall_image(c.getString(c.getColumnIndex(dbHelper.c_small_image)));
                td.setAvailable(c.getString(c.getColumnIndex(dbHelper.c_available)));

                dum.add(td);
            } while (c.moveToNext());
        }

        return dum;

    }

    //get all records from category table
    public ArrayList<CategoryGroupModel> getCategoryGroup(Context context) {

        ArrayList<CategoryGroupModel> dum = new ArrayList<CategoryGroupModel>();

        String selectQuery =  "SELECT * FROM " + dbHelper.TABLE_CATEGORY_GROUP + " where " + dbHelper.cg_available + "= 0";

        Log.e("Notification", selectQuery);

        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                CategoryGroupModel td = new CategoryGroupModel();
                td.setId(c.getInt(c.getColumnIndex(dbHelper.cg_id)));
                td.setGroup_value(c.getString(c.getColumnIndex(dbHelper.cg_group_value)));
                td.setAvailable(c.getString(c.getColumnIndex(dbHelper.cg_available)));

                dum.add(td);
            } while (c.moveToNext());
        }

        return dum;
    }

    public ArrayList<CategoryToQuoteModel> getCategoryToQuote(Context context) {

        ArrayList<CategoryToQuoteModel> dum = new ArrayList<CategoryToQuoteModel>();

        String selectQuery =  "SELECT * FROM " + dbHelper.TABLE_CATEGORYTOQUOTE ;

        Log.e("Notification", selectQuery);

        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                CategoryToQuoteModel td = new CategoryToQuoteModel();
                td.setId(c.getInt(c.getColumnIndex(dbHelper.c_id)));
                td.setCategory_id(c.getString(c.getColumnIndex(dbHelper.ctq_category_id)));
                td.setQuote_id(c.getString(c.getColumnIndex(dbHelper.ctq_quote_id)));

                dum.add(td);
            } while (c.moveToNext());
        }

        return dum;
    }


    //get all category related quote records from category and quote table
    public ArrayList<FCategoryQuoteModel> getAllCategory(Context context) {
        ArrayList<FCategoryQuoteModel> dum = new ArrayList<FCategoryQuoteModel>();

       // String selectQuery =  "SELECT * FROM " + dbHelper.TABLE_CATEGORY_GROUP + " where " + dbHelper.c_available + "= 0";
        String selectQuery =  "SELECT a.id,a.name,a.group_value,c.text,c.big_image,c.small_image FROM category a join categorytoquote b on a.id = b.category_id join quote c on b.quote_id = c.id";
        Log.e("Notification", selectQuery);

        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                FCategoryQuoteModel td = new FCategoryQuoteModel();
                td.setId(c.getInt(c.getColumnIndex(dbHelper.cg_id)));
                td.setGroup_value(c.getString(c.getColumnIndex(dbHelper.cg_group_value)));
                td.setName(c.getString(c.getColumnIndex(dbHelper.cg_available)));

                dum.add(td);
            } while (c.moveToNext());
        }

        return dum;
    }

    //get all topquote records from topquote table
    public ArrayList<Top100Model> getTopQuote(Context context) {
        ArrayList<Top100Model> aie_m_quotes = new ArrayList<Top100Model>();
        AIE_D_DCL aieDb = AIE_D_DCL.getInstance(context);
        SQLiteDatabase db = aieDb.getWritableDatabase();

        String sql = "SELECT * FROM top100 where available = 0";
        Cursor cursor = db.rawQuery(sql,null);

        Top100Model newCart = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    newCart = new Top100Model(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4));

                    aie_m_quotes.add(newCart);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return aie_m_quotes;
    }

}
