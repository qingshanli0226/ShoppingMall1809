package mvp.view;

import android.view.View;

public interface IFragment extends IActivity{
    <T extends View>T findViewById(int setId);
}
