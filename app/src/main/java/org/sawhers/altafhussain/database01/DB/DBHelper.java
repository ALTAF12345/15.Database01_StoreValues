package org.sawhers.altafhussain.database01.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ALTAFHUSSAIN on 12/29/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mydb.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_STUDENT = "tblstudent";
    //here the feild of database
    //when we final variable we need to write it in capital
    private static final String KEY_ID ="id";
    private static final String KEY_NAME ="name";
    private static final String KEY_COURSE ="course";
    private static final String KEY_TOTAL_FEE ="totalFee";
    private static final String KEY_FEE_PAID ="feepaid";

    //here we create DB and table with SQL Query and we assign this Query into a String
    //for the table creation in SQL QUERY we do this like
    /*
     CREATE TABLE tablename ( id Integer primary key Autoincrement,
                             name TEXT,
                             course TEXT,
                             totalfee Integer,
                             feepaid Integer
                             );

     */
    private static final String CREATE_TABLE_STUDENT ="CREATE TABLE "+ TABLE_STUDENT + " ( "

                                                +KEY_ID + " Integer Primary Key Autoincrement, "//apace integer ->the space is must
                                                 +KEY_NAME + " TEXT NOT NULL,"
                                                 +KEY_COURSE + " TEXT NOT NULL,"
                                                 +KEY_TOTAL_FEE + " Integer,"
                                                 +KEY_FEE_PAID + " Integer"
                                                  + ");";
            //; is for      Query terminator and other ; for this String terminator

    public DBHelper(Context context) {
       super(context, DB_NAME, null, DB_VERSION);
        //super(context, name, factory, version);
    //->"name" is the name of database,
    // ->"factory" mean when we reading data from datbase the record
    // give you in the form of cursor. so here if you want to make your own couser you need to creat it
    //and define in the place of factory
    // but if you want to used the default you need to assign your null
     //->version mean if you want to upgrade your DB you you need to increment but this is int mean 1,2,3,4 etc
//******Tell in last these word**********
        /*
        when we create the object of this class DBHelper they call constractor and the constrator
         creat database(mean oncrate call to create DB and OnUpgrade if you make changes) ,
         up to now just create database and create table , now we need to do do insertion in this
         database table .for this we create method for insertion in this database below the
         OnUpgrade method*/
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENT);//this is factory method this is automatically execute don't need to call and execut

        //if you have two or more table you need create the Qeury and then here you need to
        // write sqLiteDatabase.execSQL(yourTableName);
        //and also onUpgrade method
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    //table change or if you change the stracture of table then you need to write code here

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_STUDENT);
        onCreate(sqLiteDatabase);//recreate the database if you have change some thing in table etc

    }

    /*
    For insertion in this database we need to make a method here
    here we need to pass name ,course , total fee,fee paid*/

    public long saveStudent(String name , String course , int totalFee, int feePaid ){

        long result = -1;//this used to know the record add sucsussplly or not, -1 =not added and other is sucsuusplly added
        //for insertion in database we not need to write the complete Query we used the builtin ftn , and for writing to
        // database or table we need open the Writing DB and if we want to reading from DB we need to open reading mode of database

        //->norally we insert data into database we used this Query db.insert( TABLE_NAME , null, values );
        //the null is used when you want to send null data to your table . but this is rarly used in daily life DB.

        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values = new ContentValues();//this is accept key value pair .this is used to add all the
         values.put(KEY_NAME, name);
        values.put(KEY_COURSE, course);
        values.put(KEY_TOTAL_FEE, totalFee);
        values.put(KEY_FEE_PAID, feePaid);
        //here we insert the above data into db
        result=db.insert(TABLE_STUDENT, null, values);

        //Log.e("HAHAH","we insert values "+va)
        //db.insert(TABLE_STUDENT, null, values);
        //now we need to pass db.insert(TABLE_STUDENT, null, values); to result b/c we take decision on reult
        // now if db.insert(TABLE_STUDENT, null, values); give me value other then -1 it mean the record are add
        //if result = -1, mean record not added

        return result;

    }

}
