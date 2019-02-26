package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static String DATABASE="dbL";
    private Context context;

    public Database(Context context) {
        super(context, DATABASE, null, 1);
        this.context=context;
        Toast.makeText(context, "DataBase Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tblList (id integer primary key autoincrement,data varchar(50),date varchar(50))");
        Toast.makeText(context, "Table Created Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public long insertData(DataList dataList){
       SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("data",dataList.getData());
        contentValues.put("date",dataList.getDate());

        long result=sqLiteDatabase.insert("tblList",null,contentValues);

        return result;

    }
    public ArrayList<DataList> getAllTask(){

        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String[] columns={"id","data","date"};
        Cursor cursor=sqLiteDatabase.query("tblList",columns,null,null,null,null,null);
        int idIndex;
        int dataIndex;
        int dateIndex;

        int id;
        String data;
        String date;

        ArrayList<DataList> dataLists=new ArrayList<>();

        while (cursor.moveToNext()){
            idIndex=cursor.getColumnIndex("id");
            dataIndex=cursor.getColumnIndex("data");
            dateIndex=cursor.getColumnIndex("date");

            id=Integer.parseInt(cursor.getString(idIndex));
            data=cursor.getString(dataIndex);
            date=cursor.getString(dateIndex);

            DataList dataList=new DataList();
            dataList.setId(id);
            dataList.setData(data);
            dataList.setDate(date);

            dataLists.add(dataList);
        }

return dataLists;
    }



    public long delete(int id){

        SQLiteDatabase db=this.getWritableDatabase();
        long del;
        del = db.delete("tblList","id=?",new String[]{String.valueOf(id)});
        return del;

    }


}
