package read.liu.com.easynotepad.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */

public class DBUtil {
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase database;
    private ContentValues values;
    public DBUtil(Context context) {
        myOpenHelper = new MyOpenHelper(context);
    }
    //添加
    public void saveNote(String note){
        database = myOpenHelper.getWritableDatabase();
        values = new ContentValues();
        values.put("title",note);
        database.insert("note_tb",null,values);
        database.close();
    }
    //删除
    public void deleteNote(String note){
        database = myOpenHelper.getWritableDatabase();
        database.delete("note_tb","title=?",new String[]{note});
        database.close();
    }
    //更新
    public void updata(String old,String tag){
        database = myOpenHelper.getWritableDatabase();
        values = new ContentValues();
        values.put("title",tag);
        database.update("note_tb",values,"title=?",new String[]{old});
        database.close();
    }
    //查询
    public List<String> search(){
        List<String> list = new ArrayList<>();
        database = myOpenHelper.getWritableDatabase();
        String selectSql = "select * from note_tb";
        Cursor cursor = database.rawQuery(selectSql, null);
        while (cursor.moveToNext()){
            list.add(cursor.getString(cursor.getColumnIndex("title")));
        }
        database.close();
        return list;
    }
}
