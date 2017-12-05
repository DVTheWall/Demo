package fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.sanghani.demo.SecondActivity;
import com.example.sanghani.demo.interfaces.ChangeCurrentFragment;


public class BaseFragment extends Fragment {


    private SecondActivity activity;


    private ChangeCurrentFragment changeCurrentFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (SecondActivity) getActivity();



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        changeCurrentFragment = (ChangeCurrentFragment) context;//make object
    }

    public ChangeCurrentFragment onChangeCurrentFragment() {
        return changeCurrentFragment;
    }


    public Activity getBaseActivity() {

        return activity;

    }

    /*public SharedPreferenceUtility getBasePreferenceUtility() {

        return activity.getPreferenceUtility();

    }

    public ActionBar getBaseSupportActionBar() {

        return activity.getSupportActionBar();

    }



    public Toolbar getBaseToolBar() {

        return activity.getToolbar();

    }

    public TextView setToolBarText() {

        return activity.getToolbarTextView();

    }*/
}
