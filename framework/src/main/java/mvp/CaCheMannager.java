package mvp;

import com.example.net.bean.HomeBean;

public class CaCheMannager {
    private static CaCheMannager mannager;

    public static CaCheMannager getInstance() {
        if (mannager==null){
            mannager=new CaCheMannager();
        }
        return mannager;
    }
    private HomeBean homeBean;

    public HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }
}
