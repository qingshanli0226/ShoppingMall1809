package exception;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;

public class ShopCrashHandler implements Thread.UncaughtExceptionHandler{
//    private static ShopCrashHandler instance;
//    private Context context;
//    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    private static ShopCrashHandler instance;
    private Context context;
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    public static ShopCrashHandler getInstance() {
        if (instance==null){
            synchronized (ShopCrashHandler.class){
                if (instance==null){
                    instance=new ShopCrashHandler();
                }
            }
        }
        return instance;
    }
    public void init(Context context){
        this.context=context;
        defaultUncaughtExceptionHandler=Thread.getDefaultUncaughtExceptionHandler();//获取系统默认处理未捕获异常
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        LogUtils.e("zkh"+e.getMessage());
        new Thread(() -> {
            Looper.prepare();
            //这里现在是子线程  将子线程或主线程的异常全部转移到子线程统一处理
            Toast.makeText(context, "对不起程序错误，请重新启动服务", Toast.LENGTH_SHORT).show();
            Looper.loop();
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

    }
}
