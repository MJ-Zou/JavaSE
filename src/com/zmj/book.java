/**
 * 数组工具类
 *
 * @author zmj(填写作者名)
 * @version v1.0(填写版本号)
 */
public class book {


    /**
     * 私有构造方法
     */
    private book() {//如果类中的方法都是静态的，要私有构造方法
    }

    /**
     * 显示数组全部元素
     *
     * @param arr 接收int型数组(填写形参名+作用)
     */
    public static void show(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.println(arr[i]);
    }

    /**
     * 返回数组的最大值
     *
     * @param arr 接收int型数组
     * @return 返回数组得到最大值(填写返回内容)
     */
    public static int max(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++)
            if (arr[i] > max)
                arr[i] = max;
        return max;
    }

    /**
     * 数组反转
     *
     * @param arr 接收int型数组
     */
    public static void reverse(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length / 2; i++) {
            temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}
