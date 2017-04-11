package com.hurs.alejandrogs.hurs01z;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alejandrogs.hurs01z.R;

/**
 * Created by alejandrogs on 11/04/17.
 */

public class PhotoFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view  =inflater.inflate(R.layout.fragment_photos,container,false);

        return view;
    }

}
