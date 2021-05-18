package com.example.myapplication;

public class ShoppingMallApiModelMannager {
    private static ShoppingMallApiModelMannager mannager;

    public static ShoppingMallApiModelMannager getInstance() {
        if (mannager == null) {
            mannager = createApiModel();
        }
        return mannager;
    }

    private static ShoppingMallApiModelMannager createApiModel() {
        return null;
    }
}
