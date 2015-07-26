package com.example.goldas.projectfh;

        import android.app.Fragment;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;


public class rightFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_right_fragment, container, false);
        return  view;
    }
}

