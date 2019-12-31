package my.fun.archerytool.ui.main;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import my.fun.archerytool.R;
import my.fun.archerytool.ui.main.vm.PageViewModel;
import my.fun.archerytool.ui.main.vm.SetupVM;

import static android.app.Activity.RESULT_OK;

/**
 * 处理分段编辑的界面
 */
public class SetupFragment extends Fragment implements View.OnClickListener {

    private SetupVM setupVM;
    private static Integer REQUEST_CODE = 10;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupVM = ViewModelProviders.of(this).get(SetupVM.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_setup, container, false);
        final TextView textView = root.findViewById(R.id.orgFilePathText);
        setupVM.getOrgFilePath().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        //监听事件
        Button b = (Button) root.findViewById(R.id.orgFilePathBtn);
        b.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.orgFilePathBtn:
                Log.i("UI", "OrgFileSelect clicked");
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
                        .setType("video/*");
                startActivityForResult(Intent.createChooser(intent, "Select a file"), REQUEST_CODE);
                break;
        }
    }

    //接收返回的媒体地址，然后下面一坨
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Uri selectedVideoUri = data.getData();
            String selectedVideoPath = getRealPathFromURI(selectedVideoUri);
            if(selectedVideoPath != null) {
                Log.i("CallBack", "File Path Selected：" + selectedVideoPath);
                setupVM.setOrgFilePath(selectedVideoPath);
            }else{
                Log.w("Callback", "File Path Selected null!");
            }
        }
    }

    public String getRealPathFromURI(Uri uri) {
        if (uri == null) {
            return null;
        }
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }


}