package com.example.portfolio.fragmentstabsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.portfolio.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TabsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TabsFragment newInstance(String param1, String param2) {
        TabsFragment fragment = new TabsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabs, container, false);

        TextView titleFrag = (TextView) view.findViewById(R.id.titleFrag);
        TextView textFrag = (TextView) view.findViewById(R.id.textFrag);
        ImageView imageFrag = (ImageView) view.findViewById(R.id.imageFrag);

        assert getArguments() != null;
        String checker = getArguments().getString("title");

        switch (checker) {
            case "Fav Movie":
                titleFrag.setText(R.string.favMovieTitle);
                textFrag.setText(R.string.favMovieText);
                imageFrag.setImageResource(R.mipmap.the_prestige);
                break;

            case "Fav Pet":
                titleFrag.setText(R.string.favPetTitle);
                textFrag.setText(R.string.favPetText);
                imageFrag.setImageResource(R.mipmap.cute_cat);
                break;

            case "Fav Food":
                titleFrag.setText(R.string.favFoodTitle);
                textFrag.setText(R.string.favFoodText);
                imageFrag.setImageResource(R.mipmap.raspberry_cake);
                break;

            default:
                break;
        }

        return view;
    }
}