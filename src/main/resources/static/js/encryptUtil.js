function encryptData(data) {
    let publicKey = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLSu2gkwZ9mnYHVBIxgJxBiFoXtclzeO9kMleodA3tWFnHQwSdN6+u4TRcaVOkHW9MLxFPU0cK745xiA4kOGUQASsm2j030Xp9BEDIMk7Xg6zIHUc2TEHeDz+wREiveBU0lkciCvTFntHo8lFnaKFksrHqfR9fOymVYrwVz3XjRwIDAQAB' ;
    let encryptor = new JSEncrypt();  // 新建JSEncrypt对象
    encryptor.setPublicKey(publicKey);  // 设置公钥
    let rsaPassWord = encryptor.encrypt(data);
    return rsaPassWord;
}