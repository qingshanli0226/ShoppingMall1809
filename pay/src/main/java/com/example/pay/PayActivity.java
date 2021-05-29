package com.example.pay;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alipay.sdk.app.PayTask;

import java.util.Map;


public class PayActivity extends AppCompatActivity {
   private Handler handler=new Handler() {
       @Override
       public void handleMessage(@NonNull Message msg) {
           super.handleMessage(msg);
         switch (msg.what){
             case SDKK:
                 PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                 String resultInfo = payResult.getResult();  // 同步返回需要验证的信息
                 String resultStatus = payResult.getResultStatus();
                 // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                 if (TextUtils.equals(resultStatus, "9000")) {
                     showToast("支付成功");
                 } else {
                     // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                     if (TextUtils.equals(resultStatus, "8000")) {
                         showToast("支付结果确认中");
                     } else {
                         // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                         showToast("支付失败");
                     }
                 }
                 break;
       }
   };
    private void handleResult(final String payInfo) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    PayTask payTask = new PayTask(PayActivity.this);
                    // 调用支付接口，获取支付结果
                    Map<String, String> result = payTask.payV2(payInfo, true);
                    Message msg = new Message();
                    msg.what = SDKK;
                    msg.obj = result;
                    sendMessage(msg);
                }
            }).start();
    }};


    }




    }

}