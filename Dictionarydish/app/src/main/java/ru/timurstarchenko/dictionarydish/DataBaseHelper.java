package ru.timurstarchenko.dictionarydish;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DataBaseHelper extends SQLiteOpenHelper {
    //имя и версия БД
    private static final String DATABASE_NAME = "dictionary.dish.bd";
    private static final int DATABASE_VERSION = 3;
    // имя и столбцы таблицы
    public static final String DATABASE_TABLE_NAME  = "Dish";
    public static final String ID_COLUMN = "_id";
    public static final String DISH_NAME_COLUMN = "name";
    public static final String DISH_NAME_RECIPE_COLUMN  = "Recipe";


    public static final String SQL_DELETE_TABLE_PROVIDERS = "DROP TABLE IF EXISTS Dish";



    public DataBaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "
                + DATABASE_TABLE_NAME + " (" + ID_COLUMN
                + " integer primary key autoincrement, "
                + DISH_NAME_COLUMN + " text not null, "
                + DISH_NAME_RECIPE_COLUMN + " text not null);");


        ContentValues values1 = new ContentValues();
        values1.put(DataBaseHelper.DISH_NAME_COLUMN, "Лазанья");
        values1.put(DataBaseHelper.DISH_NAME_RECIPE_COLUMN, "Лазанья — популярное блюдо итальянской кухни?");
        db.insert("Dish",null,values1);

        ContentValues values2 = new ContentValues();
        values2.put(DataBaseHelper.DISH_NAME_COLUMN, "Плов");
        values2.put(DataBaseHelper.DISH_NAME_RECIPE_COLUMN, "Рецептов приготовления плова очень-очень много!");
        db.insert("Dish",null,values2);

        ContentValues values3 = new ContentValues();
        values3.put(DataBaseHelper.DISH_NAME_COLUMN, "Баранина с белыми грибами и помидорами");
        values3.put(DataBaseHelper.DISH_NAME_RECIPE_COLUMN, "Баранина с белыми грибами и помидорами");
        db.insert("Dish",null,values3);

        ContentValues values4 = new ContentValues();
        values4.put(DataBaseHelper.DISH_NAME_COLUMN, "Ростбиф");
        values4.put(DataBaseHelper.DISH_NAME_RECIPE_COLUMN, "Ростбиф");
        db.insert("Dish",null,values4);

        ContentValues values5 = new ContentValues();
        values5.put(DataBaseHelper.DISH_NAME_COLUMN, "Куринное филе запеченые с опятыми");
        values5.put(DataBaseHelper.DISH_NAME_RECIPE_COLUMN, "Куринное филе запеченые с опятыми");
        db.insert("Dish",null,values5);

        ContentValues values6 = new ContentValues();
        values6.put(DataBaseHelper.DISH_NAME_COLUMN, "Ленивые вареники");
        values6.put(DataBaseHelper.DISH_NAME_RECIPE_COLUMN, "Ленивые вареники");
        db.insert("Dish",null,values6);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Удаляем старую таблицу и создаём новую
        db.execSQL(SQL_DELETE_TABLE_PROVIDERS);
        // Создаём новую таблицу
        onCreate(db);
    }

}
