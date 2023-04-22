package pdd;

public class pdd01 {
    /**
     * 1. 菱形存在正面积
     * 2. 菱形每个顶点的横坐标 xi 为 0 <= xi <= X 的整数，纵坐标 yi 为 0 <= yi <= Y 的整数
     * 3. 菱形的对角线平行于坐标轴
     *
     * 求满足条件的所有菱形个数
     * @param args
     */
    public static void main(String[] args) {
//        System.out.println(getXNum(2) * getYNum(2));
        System.out.println(getYNum(9));
    }

    // 对称轴长度必须是 2 的倍数
    // 穷举，
    public static int getXNum(int x) {
        if (x < 2)
            return 0;
        return x / 2 + getXNum(x - 1);
    }

    // 定义：得到当前 y 坐标的合法数量
    public static int getYNum(int y) {
        if (y < 2) {
            return 0;
        }
        if (y % 2 == 0) {
            return y / 2 + getYNum(y - 1);
        }
        y = y / 2;
        return (1 + y) * y;
//        return y / 2 + getYNum(y - 1);
    }
}
