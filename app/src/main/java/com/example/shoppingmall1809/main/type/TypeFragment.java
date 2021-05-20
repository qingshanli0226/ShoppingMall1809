package com.example.shoppingmall1809.main.type;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.shoppingmall1809.R;
import com.example.shoppingmall1809.adapter.FragmentAdapter;
import com.example.shoppingmall1809.main.type.category.CategoryFragment;
import com.example.shoppingmall1809.main.type.sort.SortFragment;

import java.util.ArrayList;


public class TypeFragment extends Fragment {


    private RadioGroup fragTypeRadiogroup;
    private ViewPager fragTypeVp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_type, container, false);
        fragTypeRadiogroup = (RadioGroup) inflate.findViewById(R.id.frag_type_radiogroup);
        fragTypeVp = (ViewPager) inflate.findViewById(R.id.frag_type_vp);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new CategoryFragment());
        fragments.add(new SortFragment());

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getActivity().getSupportFragmentManager(), fragments);
        fragTypeVp.setAdapter(fragmentAdapter);


        fragTypeRadiogroup.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.frag_type_radio_right:
                    fragTypeVp.setCurrentItem(1);
                    break;
                default:
                    fragTypeVp.setCurrentItem(0);
                    break;
            }
        });

        return inflate;
    }
}