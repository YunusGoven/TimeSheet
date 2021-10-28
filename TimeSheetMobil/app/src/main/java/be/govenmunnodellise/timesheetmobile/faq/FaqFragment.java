package be.govenmunnodellise.timesheetmobile.faq;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.govenmunnodellise.timesheetmobile.R;

public class FaqFragment extends Fragment {

    public FaqFragment() {
        // Required empty public constructor
    }

    public static FaqFragment newInstance() {
        FaqFragment fragment = new FaqFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.faq_fragment, container, false);
    }
}
