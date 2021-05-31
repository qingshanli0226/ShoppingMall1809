package com.example.pay.obligation;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.framework.BaseRvAdapter;
import com.example.net.bean.find.FindForPayBean;
import com.example.pay.R;


import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ObligationAdapter extends BaseRvAdapter<FindForPayBean.ResultBean> {




    public ObligationAdapter(List<FindForPayBean.ResultBean> resultBeans){
        setDataList(resultBeans);
    }




    @Override
    public int getLayoutId(int viewType) {
        return R.layout.ob;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, FindForPayBean.ResultBean itemData) {
        TextView time = holder.getView(R.id.time);
        LinearLayout ll = holder.getView(R.id.ll);
        TextView money = holder.getView(R.id.money);
        TextView ordernum = holder.getView(R.id.ordernum);
        String timeData = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date(Long.parseLong(itemData.getTime())));
        time.setText(timeData);

        //金钱转换
        // 把string类型的货币转换为double类型。
        Double numDouble = Double.parseDouble(itemData.getTotalPrice());
        // 想要转换成指定国家的货币格式
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        // 把转换后的货币String类型返回
        String numString = format.format(numDouble);

        money.setText(numString);
        ordernum.setText(itemData.getTradeNo());






    }

    @Override
    public int getRootViewType(int position) {
        return position;
    }
}
