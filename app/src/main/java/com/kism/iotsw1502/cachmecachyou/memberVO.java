package com.kism.iotsw1502.cachmecachyou;

/**
 * Created by pc-15 on 2017-08-14.
 */
public class memberVO {
    private String nickname;
    private String MACaddr;
    private boolean IsCheck;

    public memberVO(String nickname, String MACaddr){
        this.nickname = nickname;
        this.MACaddr = MACaddr;
    }

    public memberVO(){

    }
    public void setCheck(boolean check) {
        IsCheck = check;
    }

    public void setMACaddr(String MACaddr) {
        this.MACaddr = MACaddr;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isCheck() {
        return IsCheck;
    }

    public String getMACaddr() {
        return MACaddr;
    }

    public String getNickname() {
        return nickname;
    }
}
