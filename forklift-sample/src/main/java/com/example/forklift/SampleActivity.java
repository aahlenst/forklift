package com.example.forklift;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class SampleActivity extends Activity implements ActionBar.TabListener {

    private static enum TabType {
        CANTONS,
        MOUNTAINS
    }

    private ActionBar actionBar;

    private Fragment cantonListFragment;

    private Fragment mountainListFragment;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(createTab(R.string.tab_cantons, TabType.CANTONS));
        actionBar.addTab(createTab(R.string.tab_mountains, TabType.MOUNTAINS));
    }

    private ActionBar.Tab createTab(int tabTitle, TabType tabType) {
        ActionBar.Tab tab = actionBar.newTab();
        tab.setText(tabTitle);
        tab.setTabListener(this);
        tab.setTag(tabType);
        return tab;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (tab.getTag().equals(TabType.CANTONS)) {
            if (cantonListFragment == null) {
                cantonListFragment = new CantonListFragment();
                ft.add(R.id.content, cantonListFragment, TabType.CANTONS.name());
            } else {
                ft.attach(cantonListFragment);
            }
        } else {
            if (mountainListFragment == null) {
                mountainListFragment = new MountainListFragment();
                ft.add(R.id.content, mountainListFragment, TabType.MOUNTAINS.name());
            } else {
                ft.attach(mountainListFragment);
            }
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (tab.getTag().equals(TabType.CANTONS)) {
            ft.detach(cantonListFragment);
        } else {
            ft.detach(mountainListFragment);
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // Do nothing
    }
}