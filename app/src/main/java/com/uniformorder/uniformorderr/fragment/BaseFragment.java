package com.uniformorder.uniformorderr.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.activities.BaseAppCompatActivity;
import com.uniformorder.uniformorderr.activities.LogUtils;


public abstract class BaseFragment extends Fragment {

    private static final String TAG = LogUtils.makeLogTag(BaseFragment.class);
    protected BaseAppCompatActivity mBaseAppCompatActivity;
    RelativeLayout mRelativeLayout;
    LottieAnimationView animationView;


    // private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBaseAppCompatActivity = (BaseAppCompatActivity) context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResource(), container, false);
        mRelativeLayout = view.findViewById(R.id.mRelativeLayout);
        animationView = view.findViewById(R.id.lt_loading_view);
        // unbinder = ButterKnife.bind(this, view);
        return view;
    }

    protected abstract int getLayoutResource();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // unbinder.unbind();
    }

    protected void hideKeyboard() {
        View view = mBaseAppCompatActivity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) mBaseAppCompatActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public void showToast(String message) {
        if (getActivity() != null && !TextUtils.isEmpty(message)) {
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }

    public void showHideProgressDialog(boolean isShow) {
        if (mRelativeLayout != null) {
            if (isShow) {
                mRelativeLayout.setVisibility(View.VISIBLE);
                animationView.setAnimation("notifyAllLoader.json");
                animationView.loop(true);
                animationView.playAnimation();

            } else {
                mRelativeLayout.setVisibility(View.GONE);
                animationView.pauseAnimation();
            }
        }
    }

    public void hideProgressDialog() {
        if (mRelativeLayout != null) {
            mRelativeLayout.setVisibility(View.GONE);
            mRelativeLayout.setVisibility(View.GONE);
//
//            if (mLottieAnimationView.isAnimating())
//                mLottieAnimationView.pauseAnimation();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void shareApp() {
        String message = "";
        LogUtils.LOGD(TAG, "shareApp() called: " + message);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

}
