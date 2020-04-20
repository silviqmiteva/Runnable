package com.example.asynctask;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ExampleFragment extends DialogFragment {
    private TextView value;
    private ProgressBar circleBar;
    public ExampleFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_example, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        value = view.findViewById(R.id.currentNumber);
        circleBar = view.findViewById(R.id.progressBar);
    }

    public void setCurrentNumber(int number){
        value.setText(String.valueOf(number));
    }

    public static ExampleFragment newInstance(){
        return new ExampleFragment();
    }


}
