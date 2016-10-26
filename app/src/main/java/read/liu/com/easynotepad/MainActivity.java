package read.liu.com.easynotepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import read.liu.com.easynotepad.db.DBUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private MainListAdapter adapter;
    private ListView listView;
    private List<String> list;
    private Button btn_edit;
    private DBUtil dbUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initData();
    }

    private void initData() {
        list.addAll(dbUtil.search());
        adapter.notifyDataSetChanged();
    }

    private void findView() {
        dbUtil = new DBUtil(this);
        list = new ArrayList<>();
        listView = (ListView)findViewById(R.id.lv_main);
        adapter = new MainListAdapter(this,list);
        listView.setAdapter(adapter);
        btn_edit = (Button)findViewById(R.id.btn_main);
        btn_edit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,WriteNoteActivity.class));
    }
}
