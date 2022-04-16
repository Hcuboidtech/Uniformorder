package com.uniformorder.uniformorderr.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.uniformorder.uniformorderr.R;
import com.uniformorder.uniformorderr.fragment.Completed;
import com.uniformorder.uniformorderr.fragment.Paymentpending;
import com.uniformorder.uniformorderr.fragment.Pendingorder;

import java.util.ArrayList;
import java.util.List;

public class Orderllist extends BaseAppCompatActivity implements OnItemClicked {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ImageView img_back, filter;

    @Override
    public String getActionTitle() {
        return null;
    }

    @Override
    public boolean isHomeButtonEnable() {
        return false;
    }

    @Override
    public int setHomeButtonIcon() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_orderllist);
        img_back = findViewById(R.id.img_back);
        filter = findViewById(R.id.filter);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout1);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Filter->","Clicked");
                startActivity(new Intent(Orderllist.this, filteractivity.class));
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_orderllist;
    }

    private void setupViewPager(ViewPager viewPager) {
        Pendingorder pendingorder = Pendingorder.newInstance("", "");
        Paymentpending paymentpending = Paymentpending.newInstance("", "");
        Completed completed = Completed.newInstance("", "");

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(pendingorder, getString(R.string.pending));
        adapter.addFragment(paymentpending, getString(R.string.payment_pending));
        adapter.addFragment(completed, getString(R.string.completed));
      //  adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);

    }

    @Override
    public void onClick(String position) {
        Log.d("Interface ->",String.valueOf(position));
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}