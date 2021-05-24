package com.shoppingmall.detail.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GoodsTable {
    @Id(autoincrement = true)
    private Long id;
    private String goodPic;
    private String goodName;
    private String goodPrice;
    private int goodNum;
    @Generated(hash = 939752401)
    public GoodsTable(Long id, String goodPic, String goodName, String goodPrice,
            int goodNum) {
        this.id = id;
        this.goodPic = goodPic;
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.goodNum = goodNum;
    }
    @Generated(hash = 1540643195)
    public GoodsTable() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getGoodPic() {
        return this.goodPic;
    }
    public void setGoodPic(String goodPic) {
        this.goodPic = goodPic;
    }
    public String getGoodName() {
        return this.goodName;
    }
    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }
    public String getGoodPrice() {
        return this.goodPrice;
    }
    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice;
    }
    public int getGoodNum() {
        return this.goodNum;
    }
    public void setGoodNum(int goodNum) {
        this.goodNum = goodNum;
    }

}
