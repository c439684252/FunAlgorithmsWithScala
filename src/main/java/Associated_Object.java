//--------------------------------------------------------------------//
public class Associated_Object {
    private Integer InstanceKey;    //非静态的属性保存在Associated_Object类本身，正常调用即可。

    public Integer getInstanceKey() {
        return InstanceKey;
    }

    public void setInstanceKey(Integer instanceKey) {
        InstanceKey = instanceKey;
    }  //Associate_Object类本身没有静态的"publicKey"属性。

    // 因此需要委托Associated_Object$的实例从MODULE$那获取相应的属性。
    public static Integer getPublicKey() {
        return Associated_Object$.MODULE$.getPublicKey();
    }

    // 原理同 getPublicKey 方法。
    public static void setPublicKey(Integer salary) {
        Associated_Object$.MODULE$.setPublicKey(salary);
    }
}