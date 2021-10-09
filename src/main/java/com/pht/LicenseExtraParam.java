package com.pht;

//除了licence时间校验外的其他校验参数
public class LicenseExtraParam  {

    private String ip;
    private String bankCode;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}
