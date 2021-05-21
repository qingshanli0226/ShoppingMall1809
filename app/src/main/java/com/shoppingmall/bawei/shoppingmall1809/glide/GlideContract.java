package com.shoppingmall.bawei.shoppingmall1809.glide;

import com.fiannce.bawei.framework.BasePresenter;
import com.fiannce.bawei.framework.IBaseView;
import com.fiannce.bawei.net.mode.FocusBean;

public class GlideContract {

    public interface IGLideView extends IBaseView {
        void onGlide(FocusBean focusBean);
    }

    public static abstract class GlidePresenter extends BasePresenter<IGLideView> {
        public abstract void getFocusData();
    }
}
