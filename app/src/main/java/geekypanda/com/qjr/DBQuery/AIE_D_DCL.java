package geekypanda.com.qjr.DBQuery;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AIE_D_DCL extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    private static AIE_D_DCL mInstance = null;
    // Database Name
    private static final String DATABASE_NAME = "QUOTEJR";

    // table names
    private static final String TABLE_AUTHOR = "AUTHOR";
    private static final String TABLE_AUTHOR_GROUP = "AUTHOR_GROUP";
    private static final String TABLE_AUTHOR_QUOTE = "AUTHOR_QUOTE";
    private static final String TABLE_CATEGORY = "CATEGORY";
    private static final String TABLE_CATEGORY_GROUP = "CATEGORY_GROUP";
    private static final String TABLE_CATEGORYTOQUOTE = "CATEGORYTOQUOTE";
    private static final String TABLE_QUOTE = "QUOTE";
    private static final String TABLE_TOP100 = "TOP100";




    // TABLE_AUTHOR Columns names
    private static final String a_id = "id";
    private static final String a_name = "name";
    private static final String a_group_value = "group_value";
    private static final String a_wiki = "wiki";
    private static final String a_occupation = "occupation";



    // TABLE_AUTHOR_GROUP Columns names
    private static final String ag_id = "id";
    private static final String ag_group_value = "group_value";
    private static final String ag_available = "available";


    // TABLE_AUTHOR_QUOTE
    private static final String aq_id = "id";
    private static final String aq_text = "text";
    private static final String aq_name = "name";
    private static final String aq_small_image = "small_image";
    private static final String aq_big_image = "big_image";

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
    private static final String q_small_image = "small_image";
    private static final String q_big_image = "big_image";
    private static final String q_available = "available";


    // TABLE_TOP100 Columns names
    private static final String top_id = "id";
    private static final String top_text = "text";
    private static final String top_small_image = "small_image";
    private static final String top_big_image = "big_image";
    private static final String top_available = "available";



    public AIE_D_DCL(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public AIE_D_DCL(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static AIE_D_DCL getInstance(Context ctx) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mInstance == null) {
            mInstance = new AIE_D_DCL(ctx.getApplicationContext());
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY + " ( "
                + c_id + " INTEGER KEY, " + c_name + " VARCHAR, " + c_group_value + " VARCHAR, " + c_big_image + " VARCHAR, " + c_small_image + " VARCHAR, "
                + c_available + " INTEGER, "
                + " UNIQUE(" + c_id + ") ON CONFLICT REPLACE );";

        String CREATE_AUTHOR_TABLE = "CREATE TABLE " + TABLE_AUTHOR + " ( "
                + a_id + " INTEGER KEY, " + a_name + " VARCHAR, " + a_group_value + " VARCHAR, " + a_wiki + " VARCHAR, "
                + a_occupation + " VARCHAR, " + " UNIQUE(" + a_name + " ) ON CONFLICT REPLACE );";

        String CREATE_AUTHOR_GROUP_TABLE = "CREATE TABLE " + TABLE_AUTHOR_GROUP + " ( "
                + ag_id + " INTEGER KEY, " + ag_group_value + " VARCHAR, " + ag_available + " VARCHAR, "
                + " UNIQUE(" + ag_id + ") ON CONFLICT REPLACE );";

        String CREATE_AUTHOR_QUOTE_TABLE = "CREATE TABLE " + TABLE_AUTHOR_QUOTE + " ( "
                + aq_id + " INTEGER KEY, " + aq_text + " VARCHAR, " + aq_name + " VARCHAR, " + aq_small_image + " VARCHAR, "
                + aq_big_image + " VARCHAR, " + " UNIQUE(" + aq_id + ") ON CONFLICT REPLACE );";



        String CREATE_CATEGORY_GROUP_TABLE = "CREATE TABLE " + TABLE_CATEGORY_GROUP + " ( "
                + cg_id + " INTEGER KEY, " + cg_group_value + " VARCHAR, " + cg_available + " VARCHAR, "
                + " UNIQUE(" + cg_id + ") ON CONFLICT REPLACE );";


        String CREATE_CATEGORYTOQUOTE_TABLE = "CREATE TABLE " + TABLE_CATEGORYTOQUOTE + " ( "
                + ctq_id + " INTEGER KEY, " + ctq_category_id + " VARCHAR, " + ctq_quote_id + " VARCHAR, "
                + " UNIQUE(" + ctq_id + ") ON CONFLICT REPLACE );";


        String CREATE_QUOTE_TABLE = "CREATE TABLE " + TABLE_QUOTE + " ( "
                + q_id + " INTEGER KEY, " + q_text + " VARCHAR, "  + q_big_image + " VARCHAR, "
                + q_small_image + " VARCHAR, " + q_available + " VARCHAR, " + " UNIQUE(" + q_id + ") ON CONFLICT REPLACE );";

        String CREATE_TOP100_TABLE = "CREATE TABLE " + TABLE_TOP100 + " ( "
                + top_id + " INTEGER KEY, " + top_text + " VARCHAR, "  + top_big_image + " VARCHAR, "
                + top_small_image + " VARCHAR, " + top_available + " VARCHAR, " + " UNIQUE(" + top_id + ") ON CONFLICT REPLACE );";


        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_AUTHOR_TABLE);
        db.execSQL(CREATE_AUTHOR_GROUP_TABLE);
        db.execSQL(CREATE_AUTHOR_QUOTE_TABLE);
        db.execSQL(CREATE_CATEGORY_GROUP_TABLE);
        db.execSQL(CREATE_CATEGORYTOQUOTE_TABLE);
        db.execSQL(CREATE_QUOTE_TABLE);
        db.execSQL(CREATE_TOP100_TABLE);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUTHOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUTHOR_GROUP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUTHOR_QUOTE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY_GROUP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORYTOQUOTE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUOTE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOP100);

        // Create tables again
        onCreate(db);
    }
}
