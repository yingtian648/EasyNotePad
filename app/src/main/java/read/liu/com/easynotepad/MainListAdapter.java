package read.liu.com.easynotepad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import read.liu.com.easynotepad.db.DBUtil;

/**
 * Created by Administrator on 2016/10/24.
 */

public class MainListAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private EditText editText;
    private Button currentet;
    private String currentNote;
    private DBUtil dbUtil;
    public MainListAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        editText = new EditText(context);
        editText.setBackgroundResource(R.drawable.btn_background);
        dbUtil = new DBUtil(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_main_list,null);
        }
        currentNote = list.get(position);
        currentet = (Button) convertView.findViewById(R.id.et_item);
        Button btnEdit = (Button) convertView.findViewById(R.id.btn_edit_item);
        Button btnDelete = (Button) convertView.findViewById(R.id.btn_delete_item);
        currentet.setText(currentNote);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出一个对话框来编辑
                editDialogshow(position);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除当前的项
                dbUtil.deleteNote(currentNote);
                list.remove(currentNote);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    private void editDialogshow(final int position){
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("请输入")
                .setView(editText)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String note = editText.getText().toString();
                        dbUtil.updata(currentNote,note);
                        currentet.setText(note);
                        notifyDataSetChanged();
                    }
                })
                .setNegativeButton("取消",null)
                .show();
        dialog.show();
    }
}
