package com.rudraksh.food.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.rudraksh.food.R;
import com.rudraksh.food.activity.SecondActivity;

public abstract class BaseFragment extends Fragment {

    protected abstract void initView(View view);

    protected abstract void initToolbar();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        hideIcons();
        initToolbar();
    }

    private void hideIcons() {
        setHasOptionsMenu(false);
        if (SecondActivity.getInstance() != null) {
            SecondActivity.getInstance().getShareImageView().setVisibility(View.GONE);
            SecondActivity.getInstance().getShareImageView().setOnClickListener(null);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        hideIcons();
        initToolbar();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            hideIcons();
            initToolbar();
        }
    }

    protected void addFragment(Fragment currentFragment, Fragment fragment, boolean isAddToBackStack) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.hide(currentFragment);
        ft.add(R.id.activity_main_container, fragment, fragment.getClass().getSimpleName());
        if (isAddToBackStack)
            ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();
    }

    protected void replaceFragment(Fragment fragment, boolean isAddToBackStack) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.activity_main_container, fragment, fragment.getClass().getSimpleName());
        if (isAddToBackStack)
            ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();
    }
}
