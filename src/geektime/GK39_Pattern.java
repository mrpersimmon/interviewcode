package geektime;

/**
 * 正则表达式匹配
 * 正则表达式中，最重要的就是通配符，通配符结合在一起，可以表达非常丰富的语义。
 * 我们假设正则表达式中只包含 * 和 ？这两种通配符，并且对这两个通配符的语义稍微做些改变。
 * 其中，* 匹配任意多个（大于等于 0 个）任意字符，？匹配零个或者一个任意字符。
 *
 * 求：判断一个给定的文本，能够与给定的正则表达式匹配？
 * -----
 * 求解：
 * 依次考察正则表达式中的每个字符，当是非通配符时，我们就直接跟文本的字符进行匹配。
 * 如果相同，则继续往下处理，如果不同，则回溯。
 *
 * 如果遇到特殊字符的时候，我们就有多种处理方式了（岔路口）
 * 如 * 有多种匹配方案，可以匹配任意个文本串中的字符。
 * 我们先随意选一种匹配方案，然后继续考察剩下的字符。
 * 如果中途发现无法继续匹配了，就回到岔路口，重新选择一种匹配方案，然后再继续匹配剩下的字符。
 *
 */
public class GK39_Pattern {
    private boolean matched = false;
    private char[] pattern; // 正则表达式
    private int plen; // 正则表达式长度

    public GK39_Pattern(char[] pattern, int plen) {
        this.pattern = pattern;
        this.plen = plen;
    }

    public boolean match(char[] text, int tlen) { // 文本串及长度
        matched = false;
        rmatch(0, 0, text, tlen);
        return matched;
    }

    private void rmatch(int ti, int pj, char[] text, int tlen) {
        if (matched) // base case 如果已经匹配了，就不要继续递归了
            return;
        if (pj == plen) { // base case 如果正则表达式到结尾了
           if (ti == tlen){ // 如果文本串也到结尾了
               matched = true;
           }
           return;
        }

        if (pattern[pj] == '*') { // * 匹配任意个字符
            for (int k = 0; k <= tlen-ti; k++) {
                rmatch(ti + k, pj + 1, text, tlen);
            }
        } else if (pattern[pj] == '?') { // ？匹配 0 个或 1 个字符
            rmatch(ti, pj + 1, text, tlen);
            rmatch(ti + 1, pj + 1, text, tlen);
        } else if (ti < tlen && pattern[pj] == text[ti]) { // 纯字符串匹配才行
            rmatch(ti + 1, pj + 1, text, tlen);
        }
    }
}
