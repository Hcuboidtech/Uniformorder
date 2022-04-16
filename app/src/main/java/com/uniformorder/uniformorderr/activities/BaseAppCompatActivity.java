package com.uniformorder.uniformorderr.activities;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.uniformorder.uniformorderr.R;


public abstract class BaseAppCompatActivity extends AppCompatActivity {

    protected BaseAppCompatActivity mBaseAppCompatActivity;

    public abstract String getActionTitle();

    public abstract boolean isHomeButtonEnable();

    public abstract int setHomeButtonIcon();

    LottieAnimationView mLottieAnimationView;
    RelativeLayout mRelativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        mLottieAnimationView = findViewById(R.id.lt_loading_view);
        mRelativeLayout = findViewById(R.id.mRelativeLayout);
        mBaseAppCompatActivity = this;

    }

    protected abstract int getLayoutResource();

    protected void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showHideProgressDialog(boolean isShow) {
        if (mRelativeLayout != null) {
            if (isShow) {
                mRelativeLayout.setVisibility(View.VISIBLE);
                mLottieAnimationView.playAnimation();
                mLottieAnimationView.setClickable(false);
            } else {
                mRelativeLayout.setVisibility(View.GONE);
                mLottieAnimationView.pauseAnimation();
            }
        }
    }


    public void hideProgressDialog() {
        if (mRelativeLayout != null) {
            mRelativeLayout.setVisibility(View.GONE);
            mRelativeLayout.setVisibility(View.GONE);

            if (mLottieAnimationView.isAnimating())
                mLottieAnimationView.pauseAnimation();
        }
    }


}
