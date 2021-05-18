package mvp.presenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class BasePresenter<V> {

    protected List<Disposable> disposableList = new ArrayList<>();

    public void add(Disposable disposable) {
        if (disposable != null) {
            disposableList.add(disposable);
        }
    }

    protected V mView;

    public void attView(V view) {
        mView = view;
    }

    public void destroy() {
        if (mView != null) {
            mView = null;
        }
        if (disposableList != null) {
            for (int i = 0; i < disposableList.size(); i++) {
                disposableList.get(i).dispose();
            }
        }
    }
}
