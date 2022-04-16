package com.uniformorder.uniformorderr.adapter;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.uniformorder.uniformorderr.R;


public abstract class BaseBottomSheetDialogFragment extends BaseDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(getContext(), getTheme());
    }

    @Override
        public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;

        getDialog().setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                BottomSheetDialog d = (BottomSheetDialog) dialog;
                FrameLayout bottomSheet = (FrameLayout) d.findViewById(R.id.design_bottom_sheet);
                if (bottomSheet == null)
                    return;
//                bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
                bottomSheet.setBackground(null);

              /*  BottomSheetDialog bottomSheetDialog = (BottomSheetDialog)dialog;
                FrameLayout frameLayout = bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
                if(bottomSheetDialog!=null){
                    return;
                }
                frameLayout.setBackground(null);*/
            }
        });
    }

}