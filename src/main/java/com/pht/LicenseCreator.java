package com.pht;

import de.schlichtherle.license.*;

import javax.security.auth.x500.X500Principal;
import java.io.File;
import java.util.prefs.Preferences;

public class LicenseCreator {
    private final static X500Principal DEFAULT_HOLDER_AND_ISSUER = new X500Principal("CN=localhost, OU=localhost, O=localhost, L=SH, ST=SH, C=CN");

    private LicenseGeneateParam licenseGeneateParam;

    public LicenseCreator(LicenseGeneateParam licenseGeneateParam){
        this.licenseGeneateParam =licenseGeneateParam;
    }

    public boolean generateLicense() {
        try {
            LicenseManager licenseManager = new LicenseExtraManager(initLicenseParam());
            LicenseContent licenseContent =initLicenseContent();
            licenseManager.store(licenseContent,new File(licenseGeneateParam.getLicensePath()));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 初始化证书参数
     *
     * @return
     */
    public LicenseParam initLicenseParam() {
        Preferences preferences = Preferences.userNodeForPackage(LicenseCreator.class);
//        证书加密的秘钥
        CipherParam cipherParam = new DefaultCipherParam(licenseGeneateParam.getStorePass());
        KeyStoreParam keyStoreParam = new CustKeyStoreParam(LicenseCreator.class, licenseGeneateParam.getPrivateKeysStorePath()
                , licenseGeneateParam.getPrivateAlias(), licenseGeneateParam.getStorePass(), licenseGeneateParam.getKeyPass());
        return new DefaultLicenseParam(licenseGeneateParam.getSubject(), preferences, keyStoreParam, cipherParam);

    }

    public LicenseContent initLicenseContent() {
        LicenseContent licenseContent = new LicenseContent();
//        证书文件的格式
        licenseContent.setHolder(DEFAULT_HOLDER_AND_ISSUER);
        licenseContent.setIssuer(DEFAULT_HOLDER_AND_ISSUER);
        licenseContent.setIssuer(DEFAULT_HOLDER_AND_ISSUER);

        licenseContent.setSubject(licenseGeneateParam.getSubject());
        licenseContent.setIssued(licenseGeneateParam.getIssuedTime() );
        licenseContent.setNotBefore(licenseGeneateParam.getIssuedTime());
        licenseContent.setNotAfter(licenseGeneateParam.getExpiryTime());
        licenseContent.setConsumerType(licenseGeneateParam.getConsumerType());
        licenseContent.setConsumerAmount(licenseGeneateParam.getConsumerAmount());
        licenseContent.setInfo(licenseGeneateParam.getDescription());
        //校验额外参数
        if (licenseContent.getExtra() != null) {
            System.out.println("含有额外校验！");
        }
        return licenseContent;
    }

}
