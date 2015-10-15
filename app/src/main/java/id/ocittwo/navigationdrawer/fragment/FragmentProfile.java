package id.ocittwo.navigationdrawer.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ocittwo.navigationdrawer.R;

/**
 * Created by ar-android on 15/10/2015.
 */
public class FragmentProfile extends Fragment{
    public FragmentProfile() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}
