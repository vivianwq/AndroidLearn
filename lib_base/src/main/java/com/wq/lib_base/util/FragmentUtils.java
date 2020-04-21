package com.wq.lib_base.util;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentUtils {

    public static void addFragment(AppCompatActivity appCompatActivity,
                                   Fragment fragment,
                                   @IdRes int containerId) {
        FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerId, fragment);
        transaction.commit();
    }
}
