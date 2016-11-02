package com.zlb.demos.androiddemos.testdemos;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;
import com.zlb.demos.androiddemos.utils.FileUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/10/27 下午4:46
 */

public class TestIOFile extends BaseActivity {

    @BindView(R.id.io_text)
    protected TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_io_file);
    }

    @OnClick(R.id.test_btn)
    public void onBtnClicked(View view) {
        StringBuilder builder = new StringBuilder();
        String path = Environment.getExternalStorageDirectory().getPath() + File.separator;
        builder.append(path).append("\n");
        String folderPath = path + "TestFolder";
        //builder.append("folderPath is " + folderPath).append("\n");
        //File fileFolder = new File(folderPath);
        File fileFolder = new File(Environment.getExternalStorageDirectory(),  File.separator + "TestFolder");
        builder.append("folderPath is " + fileFolder).append("\n");

        if(!fileFolder.exists()) {
            builder.append("folderPath is not exist, mkdirs = " + fileFolder.mkdirs() + "\n");
        }









        if(fileFolder.exists()) {
            String filePath = folderPath + File.separator + "test_file.txt";
            builder.append("new file " + filePath).append("\n");
            File file = new File(filePath);
            if(!file.exists()) {
                builder.append("file is not exit, FileUtil.writeFile() = " + FileUtil.writeFile(filePath, "test file .", true)).append("\n");
                //builder.append("file is not exit, file.createNewFile() = " + file.createNewFile()).append("\n");
            }
            if(file.exists()) {
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    BufferedOutputStream buffer = new BufferedOutputStream(out);
                    String temp = "abcd";
                    buffer.write(temp.getBytes());
                    buffer.flush();
                    buffer.close();
                    out.close();
                    builder.append("write file success");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    builder.append("write file failed 1");
                } catch (IOException e) {
                    e.printStackTrace();
                    builder.append("write file failed 2");
                }
            }

        }


        textView.setText(builder.toString());
    }
}
