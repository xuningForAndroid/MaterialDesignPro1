package com.xn.materialdesignpro1.coordinatelayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xn.materialdesignpro1.R;

public class CoordinateFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String title = bundle.getString("title");

        View view = inflater.inflate(R.layout.coordinate_fragment, null);

        TextView textView=view.findViewById(R.id.tv_card);
        textView.setText(title);
        return view;
    }
}
