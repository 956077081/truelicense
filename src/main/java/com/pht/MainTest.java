package com.pht;

import org.junit.Test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class MainTest {
    private  static  LicenseVerify licenseVerify = LicenseVerify.getInstance(getLicenseConfig());
    /**
     * 生成证书
     */
    @Test
    public void test1(){
        LicenseCreator licenseCreator = new LicenseCreator(getLicenseContent());
        licenseCreator.generateLicense();
    }

    /**
     * 校验证书
     */
    @Test
    public void verify(){
        licenseVerify.verify();
    }
    /**
     * 构造生成证书参数
     * @return
     */
    private static LicenseGeneateParam getLicenseContent() {

        LicenseGeneateParam param = new LicenseGeneateParam();
        param.setSubject("certify");
        param.setStorePass("a123456");
        param.setPrivateKeysStorePath("D:\\javaproject\\truelicense\\src\\main\\resources\\privateKeys.keystore");
        param.setPrivateAlias("privatekey");
        param.setLicensePath("D:\\javaproject\\truelicense\\src\\main\\resources\\license.lic");
        LicenseExtraParam licenseExtraParam = new LicenseExtraParam();
        licenseExtraParam.setIp("127.0.0.1");
        licenseExtraParam.setBankCode("lzrcb");
        param.setLicenseExtraModel(licenseExtraParam);
        param.setKeyPass("a123456");
        param.setIssuedTime(new Date());
        Calendar calendar =Calendar.getInstance();
        calendar.set(2021,12,10);
        param.setExpiryTime(calendar.getTime()  );
        param.setDescription("当前产品到期测试");
        param.setConsumerType("user");
        param.setConsumerAmount(1);
        return param;
    }

    /**
     * 证书参数
     * @return
     */
    private  static  LicenseConfig getLicenseConfig(){
        LicenseConfig licenseConfig = new LicenseConfig();
        licenseConfig.setLicensePath("D:\\javaproject\\truelicense\\src\\main\\resources\\license.lic");
        licenseConfig.setSubject("certify");
        licenseConfig.setStorePass("a123456");//公钥密码
        licenseConfig.setPublicKeysStorePath("D:\\javaproject\\truelicense\\src\\main\\resources\\publicCerts.keystore");
        licenseConfig.setPublicAlias("publicCert");
        return  licenseConfig;
    }
}
