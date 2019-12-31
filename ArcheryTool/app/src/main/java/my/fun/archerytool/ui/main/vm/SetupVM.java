package my.fun.archerytool.ui.main.vm;

import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import my.fun.archerytool.ui.main.model.SetupData;

/**
 * 当前的分段配置数据
 */
public class SetupVM extends ViewModel {

    private SetupData setupData;
    private MutableLiveData<SetupData> mLiveData = new MutableLiveData<>();

    private LiveData<String> orgFilePath = Transformations.map(mLiveData, new Function<SetupData, String>() {
        @Override
        public String apply(SetupData input) {
            return input.orgFilePath;
        }
    });

    public SetupVM(){
        this.setupData = new SetupData();
        mLiveData.setValue(setupData);
    }

    public void setOrgFilePath(String orgFilePath){
        setupData.orgFilePath = orgFilePath;
        mLiveData.setValue(setupData);
    }

    public LiveData<String> getOrgFilePath(){
        return orgFilePath;
    }
}