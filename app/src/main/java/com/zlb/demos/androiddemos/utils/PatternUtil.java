package com.zlb.demos.androiddemos.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhanglibin on 2014/8/17.
 */
public class PatternUtil {
    /**
     * <a href="//www.baidu.com/gaoji/preferences.html"  onmousedown="return
     * user_c({'fm':'set','tab':'setting','login':'0'})">搜索设置</a>
     * <A HREF="/2011-1-12.htm">2011-1-12.htm</A>
     */
    public static final String PATTERN_HEEFS =
            "<[Aa][^>]*(HREF|href)=(\"([^\"]*)\"|'([^']*)'|([^\\s>]*))[^>]*>(.*?)</[Aa]>";

    /**
     * /2011-1-2.htm
     */
    public static final String PATTERN_HTM = "/+[^\\s\"<>]*[.]htm";

    /**
     * <img src='http://img60.yayaimg.com/201004/1694520110105194554.jpg'
     */
    public static final String PATTERN_IMGS = "[hH]ttp://([^\"]*|[^\']*)(jpe?g|JPE?G|png|PNG)";

    public static final String PATTERN_VIDEOS =
            "(^[\\u4e00-\\u9fa5])*$|([hH]ttp://([^\"]*|[^\']*)(jpe?g|JPE?G|png|PNG))";

    //    public static final String PATTERN_CHINESE = "\\[?[\\w]*/?[\\w]*\\][\\w]*[^\\x00-\\xff\\s]+[\\w]*";
    //<br><br>[AVI/788MB]TRG-086 阿斯蒂芬<br><img src='http://img60.yayaim
    public static final String PATTERN_CHINESE =
            "\\[?[\\w]*/?[\\w]*\\][\\w\\-]*[[^\\x00-\\xff]*\\s*\\-]+[\\w]*[^\\x00-\\xff\\s]*[\\w]*";
    //    public static final String PATTERN_CHINESE = "\\[?[\\w]*/?[\\w]*\\][[^\\x00-\\xff]|[\\w]*]+|[\\w]?[^\\x00-\\xff]*";
    //    [[^\x00-\xff]|[\w]*]+[\\s]?[[^\x00-\xff]|[\w]*]*
    //"下载地址:<br>thunder://QUFodHRwOi8vczMzLmZyZWUtZHZkNTguY29tL2hkZHZkL6G+w7/I1bj80MJwdjk5MC5jb22hv1PP38z1yuzFrsjLxt66wLuq0NSwrsnxy8bP7crcLmF2aVpa<br><br>[AVI/788MB]TRG-086 安德森<br><img";
    public static final String PATTERN_THUNDER = "thunder:[^<]*";
    /**
     * <td style="text-align:left;">
     * <a href="show-98541a8de3b9b804990c9eb3a3fdbc9abc1bdf0b.html" target="_blank">
     * 一本道 110714_918 密室淫乱 一丿瀬ル力</a>
     * </td>
     * <td>1.9GB</td>
     */
    public static final String PATTERN_BTSHARE_URL = "^show-[\\w]+\\.html";


    /**** http://www.mzitu.com/ ***/
    public static final String PATTERN_MZ_HOME_LIST_URL = "'lazy' alt='[^']*'"
            + "|data-original='[^']*'"
            + "|<li><a href=\"[^\"]*/\\d+"
            ;

    /*** http://www.mzitu.com/74045 ****/

    public static final String PATTERN_MZ_HOME_DETAIL_URL = "img src=\"[^\"]*\"";
    public static final String PATTERN_MZ_HOME_DETAIL_TOTAL_PAGE = "<span>\\d+</span>";

    public static String combinePatterns(String... strings) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (i != strings.length - 1) {
                sb.append("(").append(strings[i]).append(")|");
            } else {
                sb.append("(").append(strings[i]).append(")");
            }
        }
        System.out.println("combine = " + sb.toString());
        return sb.toString();
    }

    /**
     * 将符合正则的所有数据保存到List中
     *
     * @param contents 原始数据
     * @param pattern 正则 <link>PatternUtil.PATTERN_HTM等
     */
    public static List<String> getPatternLists(String contents, String pattern) {
        List<String> list = new ArrayList<String>();
        Pattern pa = Pattern.compile(pattern, Pattern.DOTALL);
        Matcher ma = pa.matcher(contents);
        while (ma.find()) {
            list.add(ma.group());
        }
        return list;
    }
    /**

     <td style="text-align:left;">
     <a href="show-98541a8de3b9b804990c9eb3a3fdbc9abc1bdf0b.html" target="_blank">
     一本道 110714_918 密室淫乱 一丿瀬ル力</a>
     </td>
     <td>1.9GB</td>

     */

}
