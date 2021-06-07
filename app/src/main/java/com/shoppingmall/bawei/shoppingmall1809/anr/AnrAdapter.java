package com.shoppingmall.bawei.shoppingmall1809.anr;

import com.fiannce.bawei.framework.BaseRvAdapter;
import com.shoppingmall.bawei.shoppingmall1809.R;

public class AnrAdapter extends BaseRvAdapter<String> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_anr;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, String itemData) {
        long i = 0;

    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
