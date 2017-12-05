package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.sanghani.demo.R;
import com.example.sanghani.demo.SecondActivity;

/**
 * Created by peacock on 5/12/17.
 */

public class Home2Fragment extends Fragment {


    Button btn ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.home2_fragment, container, false);


        btn = rootview.findViewById(R.id.btn_home);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Peacock", Toast.LENGTH_SHORT).show();
                /*startActivity(new Intent(getActivity(), SecondActivity.class));
                getActivity().finish();*/
            }
        });


        return rootview;
    }
}