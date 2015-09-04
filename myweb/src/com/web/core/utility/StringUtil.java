package com.web.core.utility;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Edmund
 * Date: 15-3-17
 * Time: 上午10:20
 * To change this template use File | Settings | File Templates.
 */
public class StringUtil
{
    public static final String IMG_URL = "([^>=\\]\"'\\/]|^)((((https?|ftp):\\/\\/)|www\\.)([\\w\\-]+\\.)*[\\w\\-\\u4e00-\\u9fa5]+\\.([\\.a-zA-Z0-9]+|\\u4E2D\\u56FD|\\u7F51\\u7EDC|\\u516C\\u53F8)((\\?|\\/|:)+[\\w\\.\\/=\\?%\\-&~`@':+!]*)+\\.(jpg|gif|png|bmp))";
    public static final String WEBPAGE_URL = "([^>=\\]\"'\\/@]|^)((((https?|ftp|gopher|news|telnet|rtsp|mms|callto|bctp|ed2k):\\/\\/)|www\\.)([\\w\\-]+\\.)*[:\\.@\\-\\w\\u4e00-\\u9fa5]+\\.([\\.a-zA-Z0-9]+|\\u4E2D\\u56FD|\\u7F51\\u7EDC|\\u516C\\u53F8)((\\?|\\/|:)+[\\w\\.\\/=\\?%\\-&~`@':+!#]*)*)";

    public static String getAlphabet(String text)
    {
        return text.replaceAll("[^(A-Za-z)]", "");
    }

    public static String filterNumber(String number)
    {
        number = number.replaceAll("[^(0-9)]", "");
        return number;
    }

    public static String filterChinese(String chin)
    {
        chin = chin.replaceAll("[^(\\u4e00-\\u9fa5)]", "");
        return chin;
    }

    /**
     * ??String?е????????XML???
     * @param str String
     * @return String
     */
    public static String stringToXML(String str)
    {
        if (str == null)
        {
            return "";
        }

        String xml_Str = str;
        xml_Str = xml_Str.replaceAll("&amp;", "&");
        xml_Str = xml_Str.replaceAll("&lt;", "<");
        xml_Str = xml_Str.replaceAll("&gt;", ">");
        xml_Str = xml_Str.replaceAll("&quot;", "\"");
        xml_Str = xml_Str.replaceAll("&apos;", "'");
        return xml_Str;
    }

    /**
     * ??XML?е???????????
     * @param xmlstr String
     * @return String
     */
    public static String xmlToString(String xmlstr)
    {
        if (xmlstr == null)
        {
            return "";
        }

        String Str = xmlstr;
        Str = Str.replaceAll("&", "&amp;");
        Str = Str.replaceAll("<", "&lt;");
        Str = Str.replaceAll(">", "&gt;");
        Str = Str.replaceAll("\"", "&quot;");
        Str = Str.replaceAll("'", "&apos;");
        return Str;
    }

    /**
     * 取字符串的前length个字符，后缀为...
     *
     * @param src    被处理字符串
     * @param length 截取长度
     * @return String
     */
    public static String cutString(String src, int length)
    {
        return cutString(src, length, "...", "GBK");
    }

    /**
     * 取字符串的前toCount个字符
     * @param str         被处理字符串
     * @param toCount     截取长度
     * @param more        后缀字符串
     * @param charsetName 字符编码
     * @return String
     */
    public static String cutString(String str, int toCount, String more, String charsetName)
    {
        int reInt = 0;
        String reStr = "";

        if (str == null)
            return "";

        char[] tempChar = str.toCharArray();

        for (int kk = 0; (kk < tempChar.length && toCount > reInt); kk++)
        {
            String s1 = String.valueOf(tempChar[kk]);
            byte[] b;

            try
            {
                b = s1.getBytes(charsetName);
            }
            catch (UnsupportedEncodingException ex)
            {
                throw new RuntimeException(ex);
            }

            reInt += b.length;
            reStr += tempChar[kk];
        }

        if (toCount == reInt || (toCount == reInt - 1))
            reStr += more;

        return reStr;
    }

    public static String wipeScript(String html)
    {
        if (null == html)
            return "";

        //过滤<script></script>标记
        Pattern pattern01 = Pattern.compile("<script[\\s\\S]+</script *>", Pattern.CASE_INSENSITIVE);
        Matcher matcher01 = pattern01.matcher(html);
        html = matcher01.replaceAll(" ");

        //过滤<style></style>标记
        Pattern style_pattern01 = Pattern.compile("<style[\\s\\S]+</style *>", Pattern.CASE_INSENSITIVE);
        Matcher style_matcher01 = style_pattern01.matcher(html);
        html = style_matcher01.replaceAll(" ");

        //过滤href=javascript: (<A>) 属性
        Pattern pattern02 = Pattern.compile("href *= *[\\s\\S]*script *:[\\s\\S]* +", Pattern.CASE_INSENSITIVE);
        Matcher matcher02 = pattern02.matcher(html);
        html = matcher02.replaceAll("href=\"#\" ");

        //过滤其它控件的on...事件
        Pattern pattern03 = Pattern.compile(" on[\\w]*=", Pattern.CASE_INSENSITIVE);
        Matcher matcher03 = pattern03.matcher(html);
        html = matcher03.replaceAll(" _disibledevent=");

        //过滤iframe
        Pattern pattern04 = Pattern.compile("<iframe[\\s\\S]+</iframe *>", Pattern.CASE_INSENSITIVE);
        Matcher matcher04 = pattern04.matcher(html);
        html = matcher04.replaceAll(" ");

        //过滤frameset
        Pattern pattern05 = Pattern.compile("<frameset[\\s\\S]+</frameset *>", Pattern.CASE_INSENSITIVE);
        Matcher matcher05 = pattern05.matcher(html);
        html = matcher05.replaceAll(" ");
        return html;
    }

    /**
     * 自动感应url
     * @param s 待检测字符串
     * @return url被替换成bbcode形式
     */
    public static String parseUrl(String s)
    {
        if (StringUtils.isBlank(s))
            return s;

        StringBuffer sb;
        sb = regexReplace(s, IMG_URL, "$1[img]$2[/img]");
        sb = regexReplace(sb.toString(), WEBPAGE_URL, "$1[url]$2[/url]");
        sb = regexReplace(sb.toString(), "([^\\w>=\\]:\"'\\.\\/]|^)(([\\-\\.\\w]+@[\\.\\-\\w]+(\\.\\w+)+))", "$1[email]$2[/email]");
        return sb.toString();
    }

    public static String parseTable(String width, String s)
    {
        width = StringUtils.substring(width, -1).equals("%") ? (Integer.parseInt(StringUtils.substring(width, 0, -1)) <= 98 ? width : "98%") : (Integer.parseInt(width) < 420 ? width : "98%");
        StringBuffer sb = regexReplace(s, "/\\[td=(\\d{1,2}),(\\d{1,2})(,(\\d{1,3}%?))?\\]/is", "<td colspan=\"\\\\1\" rowspan=\"\\\\2\" width=\"\\\\4\">");
        sb = replace(sb.toString(), new String[]{"[tr]", "[td]", "[/td]", "[/tr]", "\\\\\""}, new String[]{"<tr>", "<td>", "</td>", "</tr>", "\""});

        s = "<table " + (width.equals("") ? "" : "width=\"" + width + "\" ") + "align=\"center\" class=\"t_table\">" + sb.toString() + "</table>";
        return s;
    }

    /**
     * 处理字符串中的bbcode,也叫ubb代码
     * @param s 待处理字符串
     * @return 处理后的字符串
     */
    public static String discuzcode(String s)
    {
        if (StringUtils.isBlank(s))
            return s;

        StringBuffer sb = new StringBuffer();

        Matcher mUrl_01 = Pattern.compile("\\[url\\]\\s*(www.|https?:\\/\\/|ftp:\\/\\/|gopher:\\/\\/|news:\\/\\/|telnet:\\/\\/|rtsp:\\/\\/|mms:\\/\\/|callto:\\/\\/|ed2k:\\/\\/){1}([^\\[\\\"']+?)\\s*\\[\\/url\\]", Pattern.CASE_INSENSITIVE).matcher(s);

        while (mUrl_01.find())
        {
            mUrl_01.appendReplacement(sb, cutUrl(mUrl_01.group(1) + mUrl_01.group(2)));
        }

        mUrl_01.appendTail(sb);

        sb = regexReplace(sb.toString(), "\\[url=www.([^\\[\\\"']+?)\\](.+?)\\[\\/url\\]", "<a href=\"http://www.$1\" target=\"_blank\">$2</a>");
        sb = regexReplace(sb.toString(), "\\[url=(https?|ftp|gopher|news|telnet|rtsp|mms|callto|ed2k){1}:\\/\\/([^\\[\\\"']+?)\\](.+?)\\[\\/url\\]", "<a href=\"$1://$2\" target=\"_blank\">$3</a>");
        sb = regexReplace(sb.toString(), "\\[email\\]\\s*([a-z0-9\\-_.+]+)@([a-z0-9\\-_]+[.][a-z0-9\\-_.]+)\\s*\\[\\/email\\]", "<a href=\"mailto:$1@$2\">$1@$2</a>");
        sb = regexReplace(sb.toString(), "\\[email=([a-z0-9\\-_.+]+)@([a-z0-9\\-_]+[.][a-z0-9\\-_.]+)\\](.+?)\\[\\/email\\]", "<a href=\"mailto:$1@$2\">$3</a>");

        Matcher mImg_01 = Pattern.compile("\\[img\\]\\s*([^\\[\\<\\r\\n]+?)\\s*\\[\\/img\\]", Pattern.CASE_INSENSITIVE).matcher(sb.toString());
        sb = new StringBuffer();

        while (mImg_01.find())
        {
            mImg_01.appendReplacement(sb, bbcodeurl(mImg_01.group(1), "<a href=\"/viewImage.html?src=%s\" target=\"_blank\"><img src=\"%s\" alt=\"\" onload=\"resizeImage(this);\"/></a>"));
        }

        mImg_01.appendTail(sb);

        sb = regexReplace(sb.toString(), "\\[color=([^\\[\\<]+?)\\]", "<font color=\"$1\">");
        sb = regexReplace(sb.toString(), "\\[size=(\\d+?)\\]", "<font size=\"$1\">");
        sb = regexReplace(sb.toString(), "\\[size=(\\d+(px|pt|in|cm|mm|pc|em|ex|%)+?)\\]", "<font style=\"font-size: $1\">");
        sb = regexReplace(sb.toString(), "\\[font=([^\\[\\<]+?)\\]", "<font face=\"$1\">");
        sb = regexReplace(sb.toString(), "\\[align=([^\\[\\<]+?)\\]", "<p align=\"$1\">");

        Matcher mTable_01 = Pattern.compile("\\s*\\[table(=(\\d{1,3}%?))?\\][\\n\\r]*(.+?)[\\n\\r]*\\[\\/table\\]\\s*", Pattern.CASE_INSENSITIVE).matcher(sb.toString());
        sb = new StringBuffer();

        while (mTable_01.find())
        {
            mTable_01.appendReplacement(sb, parseTable(mTable_01.group(2), mTable_01.group(3)));
        }

        mTable_01.appendTail(sb);

        mTable_01 = Pattern.compile("\\s*\\[table(=(\\d{1,3}%?))?\\][\\n\\r]*(.+?)[\\n\\r]*\\[\\/table\\]\\s*", Pattern.CASE_INSENSITIVE).matcher(sb.toString());
        sb = new StringBuffer();

        while (mTable_01.find())
        {
            mTable_01.appendReplacement(sb, parseTable(mTable_01.group(2), mTable_01.group(3)));
        }

        mTable_01.appendTail(sb);

        sb = regexReplace(sb.toString(), "\\s*\\[quote\\][\\n\\r]*(.+?)[\\n\\r]*\\[\\/quote\\]\\s*", "<br><br><div class=\\\"msgheader\\\">QUOTE:</div><div class=\\\"msgborder\\\">$1</div><br>");


        sb = replace(sb.toString(),
                new String[] {"[/color]", "[/size]", "[/font]", "[/align]", "[b]", "[/b]", "[i]", "[/i]", "[u]", "[/u]", "[list]", "[list=1]", "[list=a]", "[list=A]", "[*]", "[/list]", "[indent]", "[/indent]" },
                new String[] {"</font>", "</font>", "</font>", "</p>", "<b>", "</b>", "<i>", "</i>", "<u>", "</u>", "<ul>", "<ol type=1>", "<ol type=a>", "<ol type=A>", "<li>", "</ul></ol>", "<blockquote>", "</blockquote>" });
        return sb.toString();
    }

    public static String cutUrl(String url)
    {
        int length = 65;

        String urllink = "<a href=\"" + (url.toLowerCase().substring(0, 4).equalsIgnoreCase("www.") ? "http://" + url : url) + "\" target=\"_blank\">";

        if (url.length() > length)
        {
            url = url.substring(0, (int)(length * 0.5)) + "..." + StringUtils.substring(url, -(int)(length * 0.3));
        }

        urllink += url + "</a>";
        return urllink;
    }

    private static String bbcodeurl(String url, String tags)
    {
        if (!Pattern.compile("<.+?>").matcher(url).find())
        {
            String[] prefixes = new String[]{"http:/", "ftp://", "rtsp:/", "mms://"};
            url = !ArrayUtils.contains(prefixes, url.toLowerCase().substring(0, 6)) ? "http://" + url : url;
            return String.format(tags, url, url);
        }
        else
        {
            return "&nbsp;" + url;
        }
    }

    private static StringBuffer regexReplace(String s, String pattern, String replacement)
    {
        if (StringUtils.isBlank(s))
            return new StringBuffer();

        StringBuffer sb = new StringBuffer();
        Matcher m = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(s);

        while (m.find())
        {
            m.appendReplacement(sb, replacement);
        }

        m.appendTail(sb);
        return sb;
    }

    public static StringBuffer replace(String text, String[] repl, String[] with)
    {
        StringBuffer sb = new StringBuffer();
        int total = repl.length > with.length ? with.length : repl.length;

        for (int x = 0; x < total; x++)
        {
            text = StringUtils.replace(text, repl[x], with[x]);
        }

        sb.append(text);
        return sb;
    }

    /**
     * 获取twitter中@符号和空格之间的用户名
     * @param content twitter信息
     * @return null表示没有名字
     */
    public static String parseReceiverName(String content)
    {
        String ret = null;
        String s = StringUtils.trim(content);
        s = StringUtils.replace(s,"＠","@",1);
        s = StringUtils.replace(s,"　"," ",1);

        if (s.startsWith("@"))
        {
            ret = StringUtils.substringBetween(s, "@", " ");

            if (ret != null && ret.length() > 0)
            {
                return ret;
            }

            ret = StringUtils.substringBetween(s, "@", "　");

            if (ret != null && ret.length() > 0)
            {
                return ret;
            }

        }
        else if (s.startsWith("＠"))
        {
            ret = StringUtils.substringBetween(s, "＠", " ");

            if (ret != null && ret.length() > 0)
            {
                return ret;
            }

            ret = StringUtils.substringBetween(s, "＠", "   ");

            if (ret != null && ret.length() > 0)
            {
                return ret;
            }
        }

        return null;
    }

    /**
     * 获取twitter中!符号和空格之间的用户名
     * @param content twitter信息
     * @return null表示没有名字
     */
    public static String parseDirectMsgReceiverName(String content)
    {
        String ret = null;
        String s = StringUtils.trim(content);
        s = StringUtils.replace(s, "！", "!", 1);
        s = StringUtils.replace(s, "　", " ", 1);

        if (s.startsWith("!"))
        {
            ret = StringUtils.substringBetween(s, "!", " ");

            if (ret != null && ret.length() > 0)
            {
                return ret;
            }

            ret = StringUtils.substringBetween(s, "!", "　");

            if (ret != null && ret.length() > 0)
            {
                return ret;
            }
        }
        else if (s.startsWith("！"))
        {
            ret = StringUtils.substringBetween(s, "！", " ");

            if (ret != null && ret.length() > 0)
            {
                return ret;
            }

            ret = StringUtils.substringBetween(s, "！", "　");

            if (ret != null && ret.length() > 0)
            {
                return ret;
            }
        }

        return null;
    }

    /**
     * 获取twitter中#号和空格之间的话题名
     * @param content twitter信息
     * @return null表示没有名字
     */
    public static String parseTopicSubNumber(String content)
    {
        String s = StringUtils.trim(content);
        s = StringUtils.replace(s,"＃","#",1);
        s = StringUtils.replace(s,"　"," ",1);

        if (s.startsWith("#"))
        {
            return StringUtils.substringBetween(s,"#", " ");
        }
        else if (s.startsWith("＃"))
        {
            return StringUtils.substringBetween(s, "＃", " ");
        }

        return null;
    }

    public static String[] extractURL(String content)
    {
        List<String> urls = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        Matcher m = Pattern.compile(WEBPAGE_URL, Pattern.CASE_INSENSITIVE).matcher(content);

        while (m.find())
        {
            String url = m.group(2);
            urls.add(url);
            m.appendReplacement(sb, "");
        }

        m.appendTail(sb);
        m = Pattern.compile(IMG_URL, Pattern.CASE_INSENSITIVE).matcher(sb.toString());

        while (m.find())
        {
            String url = m.group(2);
            urls.add(url);
            m.appendReplacement(sb, "");
        }

        m.appendTail(sb);
        return urls.toArray(new String[]{});
    }

    public static String parseGroupSubNumber(String msg)
    {
        String s = StringUtils.trim(msg);
        s = StringUtils.replace(s, "￥", "$", 1);
        s = StringUtils.replace(s,"　"," ",1);

        if (s.startsWith("$"))
        {
            return StringUtils.substringBetween(s,"$", " ");
        }
        else if (s.startsWith("￥"))
        {
            return StringUtils.substringBetween(s, "￥", " ");
        }

        return null;
    }


    public static Date[] getStartAndEndDateOfCurWeek(int year,int week)
    {
        Date[] d = new Date[2];
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(year, 0, 1);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int endDayOfFirstWeek = (7 - dayOfWeek) + 1;

        int firstDayOfTargetWeek = (week - 2) * 7 + (endDayOfFirstWeek + 1);
        int endDayOfTargetWeek = firstDayOfTargetWeek + 6;

        if (endDayOfTargetWeek > cal.getMaximum(Calendar.DAY_OF_YEAR))
        {
            endDayOfTargetWeek = cal.getMaximum(Calendar.DAY_OF_YEAR);
        }

        cal.set(Calendar.DAY_OF_YEAR, firstDayOfTargetWeek);
        d[0] = cal.getTime();
        cal.set(Calendar.DAY_OF_YEAR, endDayOfTargetWeek);

        d[1] = cal.getTime();
        return d;
    }


    public static String outContent(String str)
    {
        if (str == null)
        {
            return "";
        }
        else
        {
            return StringUtil.discuzcode(StringUtils.replace(StringUtil.xmlToString(StringUtil.parseUrl(str)), "\n", "<br>"));
        }
    }


    public static boolean isNotBlankString(String str)
    {
        boolean isTuer = false;

        if (str != null && str.trim().length() != 0 && org.springframework.util.StringUtils.hasLength(str))
        {
            isTuer = true;
        }

        return isTuer;
    }

    /**
     * 返回格式化后的字符串
     * @param msc
     * @return
     */
    public static String getFormatString(String msc)
    {
        if (msc == null)
        {
            return null;
        }

        msc = StringUtil.wipeScript(msc);
        msc = KeyWordFilter.doFilter(msc);
        msc = StringUtil.parseUrl(msc);
        return  msc;
    }

    /**
     * 判断邮箱地址是否合法
     * @param email
     * @return
     */
    public static boolean getIsValidEmail(String email)
    {
        Pattern p = Pattern.compile("^([a-zA-Z0-9_\\-\\.])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 判断手机号码是否合法
     */
    public static boolean getIsValidMobile(String mobile)
    {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    public static String getFormattedDate(Date date)
    {
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.applyPattern("yyyy-MM-dd");
        return sf.format(date);
    }

    public static String getFormattedTime(Date date)
    {
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.applyPattern("yyyy-MM-dd HH:mm:ss");
        return sf.format(date);
    }

    public static void main(String args[])
    {
        Date[] d = StringUtil.getStartAndEndDateOfCurWeek(2009, 4);
        System.out.print("from:" + d[0].toString() + "\r\n");
        System.out.print("to:" + d[1].toString() + "\r\n");
    }

    /**
     * 获取UUID
     *
     * @return
     */
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
        return uuid.toString();
    }
    /**
     * 获取UUID 并进行格式化
     *
     * @return
     */
    public static String getUUIDByType(String type){
        return getUUID().replace("-",type);
    }
}
