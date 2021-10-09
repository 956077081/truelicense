package com.pht;

import de.schlichtherle.license.*;

import java.io.File;
import java.util.prefs.Preferences;

//证书校验
public class LicenseVerify {
    private static String subject;
    private static String publicAlias;
    private static String storePass;
    private static  String licensePath;
    private static String publicKeysStorePath;
    private  static  boolean isInstall=false;
    private static  LicenseManager licenseManager= null;

    public LicenseVerify(String subject, String publicAlias, String storePass, String licensePath, String publicKeysStorePath) {
        this.subject = subject;
        this.publicAlias = publicAlias;
        this.storePass = storePass;
        this.licensePath = licensePath;
        this.publicKeysStorePath = publicKeysStorePath;
    }
    public LicenseVerify(LicenseConfig licenseConfig){
        this.subject = licenseConfig.getSubject();
        this.publicAlias = licenseConfig.getPublicAlias();
        this.storePass = licenseConfig.getStorePass();
        this.licensePath = licenseConfig.getLicensePath();
        this.publicKeysStorePath = licenseConfig.getPublicKeysStorePath();
    }
    public static LicenseVerify  getInstance(LicenseConfig licenseConfig){
        subject = licenseConfig.getSubject();
        publicAlias = licenseConfig.getPublicAlias();
        storePass = licenseConfig.getStorePass();
        licensePath = licenseConfig.getLicensePath();
        publicKeysStorePath = licenseConfig.getPublicKeysStorePath();
        if(!isInstall) {
            installLicense();
        }
        return  new LicenseVerify(licenseConfig);
    }
    //  安装证书
    private static LicenseManager installLicense() {
        try {
            Preferences preferences = Preferences.userNodeForPackage(LicenseVerify.class);
            CipherParam cipherParam = new DefaultCipherParam(storePass);
            KeyStoreParam keyStoreParam = new CustKeyStoreParam(LicenseVerify.class,
                    publicKeysStorePath, publicAlias, storePass, null);
            LicenseParam licenseParam = new DefaultLicenseParam(subject, preferences, keyStoreParam, cipherParam);
            licenseManager = new LicenseExtraManager(licenseParam);
            licenseManager.uninstall();
            licenseManager.install(new File(licensePath));
            System.out.println("证书安装成功！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("证书安装失败！");
        }
        return licenseManager;
    }

    //    卸载证书
    public static void uninstallLicense() {
        try {
            if (isInstall){
                licenseManager.uninstall();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 校验
     *
     * @return
     */
    public boolean verify() {
        try {
            LicenseContent verify = licenseManager.verify();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
