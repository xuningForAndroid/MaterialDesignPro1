package com.xn.materialdesignpro1.parallel_universe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TranslateFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int layoutId = bundle.getInt("layoutId", 0);
        int pageIndex = bundle.getInt("pageIndex",0);
        View view = inflater.inflate(layoutId, null);
        view.setTag(pageIndex);
        return view;
    }
}
