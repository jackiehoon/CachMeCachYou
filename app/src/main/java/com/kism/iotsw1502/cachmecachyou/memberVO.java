package com.kism.iotsw1502.cachmecachyou;

/**
 * Created by pc-15 on 2017-08-14.
 */
public class memberVO {
    private String id;
    private String MACaddr;
    private String nickname;
    private String bt_name;
    private int IsCheck;
    private String catcher;

    public memberVO(String bt_name, String catcher, String id, int isCheck, String MACaddr, String nickname) {
        this.bt_name = bt_name;
        this.catcher = catcher;
        this.id = id;
        this.IsCheck = isCheck;
        this.MACaddr = MACaddr;
        this.nickname = nickname;
    }

    public memberVO(String nickname, String MACaddr){
        this.nickname = nickname;
        this.MACaddr = MACaddr;
    }

    public memberVO(String nickname, int IsCheck){
        this.nickname = nickname;
        this.IsCheck = IsCheck;
    }
    public memberVO(String nickname, String id, String catcher){
        this.nickname = nickname;
        this.id = id;
        this.catcher = catcher;
    }

    public memberVO(){

    }

    public String getBt_name() {
        return bt_name;
    }

    public void setBt_name(String bt_name) {
        this.bt_name = bt_name;
    }

    public String getCatcher() {
        return catcher;
    }

    public void setCatcher(String catcher) {
        this.catcher = catcher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setCheck(int check) {
        IsCheck = check;
    }

    public void setMACaddr(String MACaddr) {
        this.MACaddr = MACaddr;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int isCheck() {
        return IsCheck;
    }

    public String getMACaddr() {
        return MACaddr;
    }

    public String getNickname() {
        return nickname;
    }
}
