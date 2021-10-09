package com.pht;

import de.schlichtherle.license.*;
import de.schlichtherle.xml.GenericCertificate;

import javax.swing.filechooser.FileFilter;
import java.io.File;

//验证管理器
public class LicenseExtraManager extends LicenseManager {

    protected LicenseExtraManager() {
        super();
    }

    public LicenseExtraManager(LicenseParam licenseParam) {
        super(licenseParam);
    }

    @Override
    public synchronized LicenseParam getLicenseParam() {
        return super.getLicenseParam();
    }

    @Override
    public synchronized void setLicenseParam(LicenseParam licenseParam) {
        super.setLicenseParam(licenseParam);
    }

    @Override
    protected synchronized void store(LicenseContent licenseContent, LicenseNotary licenseNotary, File file) throws Exception {
        super.store(licenseContent, licenseNotary, file);
    }

    @Override
    protected synchronized byte[] create(LicenseContent licenseContent, LicenseNotary licenseNotary) throws Exception {
        this.initialize(licenseContent);
        this.validate(licenseContent);
        GenericCertificate var3 = licenseNotary.sign(licenseContent);
        return this.getPrivacyGuard().cert2key(var3);
    }
    @Override
    protected synchronized LicenseContent install(byte[] bytes, LicenseNotary licenseNotary) throws Exception {
        GenericCertificate var3 = this.getPrivacyGuard().key2cert(bytes);
        licenseNotary.verify(var3);
        LicenseContent var4 = (LicenseContent)var3.getContent();
        this.validate(var4);
        this.setLicenseKey(bytes);
        this.setCertificate(var3);
        return var4;
    }

    @Override
    protected synchronized LicenseContent verify(LicenseNotary licenseNotary) throws Exception {
        GenericCertificate var2 = this.getCertificate();
        if (null != var2) {
            return (LicenseContent)var2.getContent();
        } else {
            byte[] var3 = this.getLicenseKey();
            if (null == var3) {
                throw new NoLicenseInstalledException(this.getLicenseParam().getSubject());
            } else {
                var2 = this.getPrivacyGuard().key2cert(var3);
                licenseNotary.verify(var2);
                LicenseContent var4 = (LicenseContent)var2.getContent();
                this.validate(var4);
                this.setCertificate(var2);
                return var4;
            }
        }
    }

    @Override
    protected synchronized LicenseContent verify(byte[] bytes, LicenseNotary licenseNotary) throws Exception {
        GenericCertificate var3 = this.getPrivacyGuard().key2cert(bytes);
        licenseNotary.verify(var3);
        LicenseContent var4 = (LicenseContent)var3.getContent();
        this.validate(var4);
        return var4;
    }

    @Override
    public synchronized void uninstall() throws Exception {
        super.uninstall();
    }

    @Override
    protected synchronized void initialize(LicenseContent licenseContent) {
        super.initialize(licenseContent);
    }

    @Override
    protected synchronized void validate(LicenseContent licenseContent) throws LicenseContentException {
        super.validate(licenseContent);
        Object extra = licenseContent.getExtra();
        System.out.println(extra);

    }

}
