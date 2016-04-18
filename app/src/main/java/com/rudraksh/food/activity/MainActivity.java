package com.rudraksh.food.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.rudraksh.food.R;
import com.rudraksh.food.adapters.MainRecyclerAdapter;
import com.rudraksh.food.models.MainMenuModel;
import com.rudraksh.food.utils.Constant;
import com.rudraksh.food.utils.OnRecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MainActivity extends BaseActivity implements OnRecyclerViewItemClickListener {

    private RecyclerView mainActivityRecyclerView;
    private MainRecyclerAdapter mainRecyclerAdapter;
    private List<MainMenuModel> mainMenuModelList = new ArrayList<>();

    @Override
    protected void initView() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolBar);
        titleTextView = (TextView) toolbar.findViewById(R.id.row_toolbar_tv_title);
        setSupportActionBar(toolbar);
        setActionBarTitle(getString(R.string.rudraksh_food_service));

        mainActivityRecyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerView);
        mainRecyclerAdapter = new MainRecyclerAdapter(this,mainMenuModelList);
        mainRecyclerAdapter.setOnRecyclerViewItemClickListener(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mainActivityRecyclerView.setLayoutManager(mLayoutManager);
        mainActivityRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mainActivityRecyclerView.setAdapter(mainRecyclerAdapter);

        getCardViewData();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    private void getCardViewData() {
        MainMenuModel menuModel = new MainMenuModel("Food",R.drawable.ic_food);
        mainMenuModelList.add(menuModel);

        menuModel = new MainMenuModel("Photos",R.drawable.ic_photos);
        mainMenuModelList.add(menuModel);

        menuModel = new MainMenuModel("Service",R.drawable.ic_service);
        mainMenuModelList.add(menuModel);

        menuModel = new MainMenuModel("Offers",R.drawable.ic_offers);
        mainMenuModelList.add(menuModel);

        menuModel = new MainMenuModel("Contact Us",R.drawable.ic_phone);
        mainMenuModelList.add(menuModel);

        mainRecyclerAdapter.notifyDataSetChanged();
    }
    @Override
    public void onItemClick(int position, View view) {
        switch (view.getId()) {
            case R.id.row_main_recycler_cardView:
                final MainMenuModel mainMenuModel = (MainMenuModel) view.getTag();
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                final Bundle bundle = new Bundle();
                bundle.putString("Selected", mainMenuModel.getCardViewName());
                Log.e("TAG","Selected " + mainMenuModel.getCardViewName());
                startActivity(intent);

                break;
        }
    }
}
