public final class Associated_Object$ {
    private Integer publicKey;    //在静态域中直接指定MODULE$保存Associated_Object的静态属性。

    static {
        MODULE$ = new Associated_Object$();
    }

    public static final Associated_Object$ MODULE$;

    public Integer getPublicKey() {
        return MODULE$.publicKey;
    }

    public void setPublicKey(Integer publicKey) {
        MODULE$.publicKey = publicKey;
    }    //在初始化中，对publicKey进行赋值。

    // Associated_Object的静态属性会随着初始化保存到MODULE$当中。
    Associated_Object$() {
        this.publicKey = 99;
    }
}

