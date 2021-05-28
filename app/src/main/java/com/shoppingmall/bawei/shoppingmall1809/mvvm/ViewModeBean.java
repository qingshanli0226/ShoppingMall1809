package com.shoppingmall.bawei.shoppingmall1809.mvvm;

public class ViewModeBean<T> {
    T data;
    private int status;//loading hideloading, error
    public ViewModeBean(T data, int status) {
        this.data = data;
        this.status = status;
    }

    public static <T> ViewModeBean<T> success(T data) {
        return new ViewModeBean<>(data, 1);
    }
    public static <T> ViewModeBean<T> loading(T data) {
        return new ViewModeBean<>(data, 2);
    }
    public static <T> ViewModeBean<T> fail(T data) {
        return new ViewModeBean<>(data, 3);
    }
    public void handle(IHanleCallback iHanleCallback) {
        if (status == 2) {
            iHanleCallback.onLoading();
        } else if (status == 1) {
            iHanleCallback.onSuccess(data);
        } else if (status == 3) {
            iHanleCallback.onError("error");
        }
    }

    public interface IHanleCallback<T>{
        void onLoading();
        void onSuccess(T data);
        void onError(String message);
    }
}
