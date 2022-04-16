package com.uniformorder.uniformorderr.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public abstract class BaseDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResource(), container, false);

        return view;
    }

    public abstract int getLayoutResource();

   /* public void showToast(String message) {
        ((BaseAppCompatActivity) getActivity()).showToast(message);
    }*/

}

