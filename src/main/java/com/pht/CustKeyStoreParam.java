package com.pht;

import de.schlichtherle.license.AbstractKeyStoreParam;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustKeyStoreParam extends AbstractKeyStoreParam {
    private String storePath;//秘钥存储地址
    private String alias;//别名
    private String storePwd;//秘钥仓库密码
    private String keyPwd;//秘钥密码

    protected CustKeyStoreParam(Class aClass, String path,String  alias,String storePwd,String keyPwd) {
        super(aClass, path);
        this.storePath =path;
        this.alias  = alias;
        this.storePwd = storePwd;
        this.keyPwd =keyPwd;
    }

    @Override
    public InputStream getStream() throws IOException {
        return  new FileInputStream(storePath);
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public String getStorePwd() {
        return storePwd;
    }

    @Override
    public String getKeyPwd() {
        return keyPwd;
    }
}
