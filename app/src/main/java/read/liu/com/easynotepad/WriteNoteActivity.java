package read.liu.com.easynotepad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import read.liu.com.easynotepad.db.DBUtil;

public class WriteNoteActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editText;
    private Button button,btnVideo;
    private ImageView imageView;
    private String title;
    private DBUtil dbUtil;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);
        dbUtil = new DBUtil(this);
        initView();
    }

    private void initView() {
        textView = (TextView)findViewById(R.id.tv_time_write);//时间显示
        btnVideo = (Button) findViewById(R.id.button_makevideo);//录音

        editText = (EditText) findViewById(R.id.editText_write);
        button = (Button)findViewById(R.id.button_write);
        imageView = (ImageView)findViewById(R.id.imageView_write);
        button.setOnClickListener(this);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_write:
                if(editText.getText().length()>0){
                    title = editText.getText().toString();
                    dbUtil.saveNote(title);
                }else{
                    Toast.makeText(this,"输入为空",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imageView_write:

                break;
        }
    }
}
