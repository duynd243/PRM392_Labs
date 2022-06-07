package com.prm392.groupproject;

import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;

public class Pet {
    private SeekBar seekBar;
    private RadioButton radioButton;
    private ImageView badge;

    public Pet(SeekBar seekBar, RadioButton radioButton, ImageView badge) {
        this.seekBar = seekBar;
        this.radioButton = radioButton;
        this.badge = badge;
    }

    public SeekBar getSeekBar() {
        return seekBar;
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }

    public ImageView getBadge() {
        return badge;
    }
}
