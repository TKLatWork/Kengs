package my.fun.archerytool.ctrl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import my.fun.archerytool.R;
import my.fun.archerytool.databinding.ActivityMainBinding;
import my.fun.archerytool.viewModel.MainVM;

public class MainActivity extends AppCompatActivity {

    private MainVM mainVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainVM = new MainVM();
        binding.setMainVM(mainVM);
        binding.getMainVM().setMessage("Hi there");
        binding.setCtrl(this);
    }

    public void onToSetupBtn() {
        System.out.println("MainActivity.onGotoSetupBtn");
    }

}
