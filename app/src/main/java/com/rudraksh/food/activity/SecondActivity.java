package com.rudraksh.food.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rudraksh.food.R;
import com.rudraksh.food.fragments.ContactFragment;
import com.rudraksh.food.fragments.FoodTypeFragment;
import com.rudraksh.food.fragments.TabFragment2;
import com.rudraksh.food.fragments.ServiceFragment;

public class SecondActivity extends BaseActivity implements TabLayout.OnTabSelectedListener{

    private String selectedCardName;
    private int tabPosition;

    private TabLayout tabLayout;
    private Fragment selectedFragment;
    private static SecondActivity instance;
    private Toolbar toolbar;
    private ImageView backImageView;
    private ImageView shareImageView;

    @Override
    protected void initView() {
        instance = this;
        toolbar = (Toolbar) findViewById(R.id.activity_main_toolBar);
        titleTextView = (TextView) toolbar.findViewById(R.id.row_toolbar_tv_title);
        setSupportActionBar(toolbar);
        setActionBarTitle();
        backImageView = (ImageView) findViewById(R.id.row_toolbar_iv_back);
        shareImageView = (ImageView) findViewById(R.id.row_toolbar_iv_share);
        tabLayout = (TabLayout) findViewById(R.id.activity_main_tab_layout);
        tabLayout.setOnTabSelectedListener(this);
        setTabLayout();

        tabLayout.getTabAt(0).select();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_second;
    }

    private void setTabLayout() {

        for (int i = 0; i < 5; i++) {
            final TabLayout.Tab tab = tabLayout.newTab();
            final View view = LayoutInflater.from(this).inflate(R.layout.layout_tab, null);

            final TextView tabTextView = (TextView) view.findViewById(R.id.tabText);
            final ImageView tabImageView = (ImageView) view.findViewById(R.id.tabImage);
            switch (i) {
                case 0:
                    tabImageView.setImageResource(R.drawable.ic_food);
                    tabTextView.setText(getString(R.string.food));
                    break;
                case 1:
                    tabImageView.setImageResource(R.drawable.ic_photos);
                    tabTextView.setText(getString(R.string.photos));
                    break;
                case 2:
                    tabImageView.setImageResource(R.drawable.ic_service);
                    tabTextView.setText(getString(R.string.sevice));
                    break;
                case 3:
                    tabImageView.setImageResource(R.drawable.ic_offers);
                    tabTextView.setText(getString(R.string.offers));
                    break;
                case 4:
                    tabImageView.setImageResource(R.drawable.ic_phone);
                    tabTextView.setText(getString(R.string.contact));
                    break;
            }

            tab.setCustomView(view);
            tabLayout.addTab(tab);
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        switch (tab.getPosition()) {
            case 0:
                selectedFragment = new FoodTypeFragment();
                break;
            case 1:
                selectedFragment = new TabFragment2();
                break;
            case 2:
                selectedFragment = new ServiceFragment();
                break;
            case 3:
                selectedFragment = new TabFragment2();
                break;
            case 4:
                selectedFragment = new ContactFragment();
                break;
        }

        if (selectedFragment != null) {

            displayFragment(selectedFragment);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void displayFragment(final Fragment fragment) {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.activity_main_container, fragment, fragment.getClass().getSimpleName());
        ft.commit();
    }

    public static SecondActivity getInstance() {
        return instance;
    }

    public ImageView getShareImageView() {
        return shareImageView;
    }
}
