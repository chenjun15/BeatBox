package com.cj.beatbox;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

public class SoundViewModel extends BaseObservable {
    private Sound mSound;
    private BeatBox mBeatBox;

    public SoundViewModel(BeatBox beatBox) {
        mBeatBox = beatBox;
    }

    @Bindable
    public String getTitle() {
        return mSound.getName();
    }

    public Sound getSound() {
        return mSound;
    }

    public void setSound(Sound sound) {
        mSound = sound;
        notifyChange();
    }

    /**
     * 书上原文："数据绑定只要想用那个属性,它就会调用这个静态方法"
     * 在初始化的时候，每一个button都想要 app:soundName 属性，所以会依次调用此方法。
     * 界面表现为一次弹出Toast，直至所有button对应的Toast弹完.
     * Button 加属性 app:soundName="@{viewModel.title}"
     *
     * @param button
     * @param assetFileName
     */
    @BindingAdapter("app:soundName")
    public static void bindAssetSound(Button button, String assetFileName) {
        Toast.makeText(button.getContext(), assetFileName, Toast.LENGTH_SHORT).show();
    }

    public void onButtonClicked() {
        mBeatBox.play(mSound);
    }
}
