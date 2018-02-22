package kikilinux.code.snippet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateFormatter {

    public static void main(String[] args) {
        System.out.println(isFormatCorrect("EEE, MMM d, ''yy", "Wed, Jul 4, '01"));
    }

    /**
     * 日期和时间模式                  结果 <br/>
     "yyyy.MM.dd G 'at' HH:mm:ss z"  2001.07.04 AD at 12:08:56 PDT <br/>
     "EEE, MMM d, ''yy"              Wed, Jul 4, '01<br/>
     "h:mm a"                        12:08 PM<br/>
     "hh 'o''clock' a, zzzz"         12 o'clock PM, Pacific Daylight Time<br/>
     "K:mm a, z"                     0:08 PM, PDT<br/>
     "yyyyy.MMMMM.dd GGG hh:mm aaa"  02001.July.04 AD 12:08 PM<br/>
     "EEE, d MMM yyyy HH:mm:ss Z"    Wed, 4 Jul 2001 12:08:56 -0700<br/>
     "yyMMddHHmmssZ"                 010704120856-0700<br/>
     "yyyy-MM-dd'T'HH:mm:ss.SSSZ"    2001-07-04T12:08:56.235-0700<br/>
     * 字母	日期或时间元素			表示	示例<br/>
     G	Era 标志符					Text	AD<br/>
     y	年							Year	1996; 96<br/>
     M	年中的月份					Month	July; Jul; 07<br/>
     w	年中的周数					Number	27<br/>
     W	月份中的周数				Number	2<br/>
     D	年中的天数					Number	189<br/>
     d	月份中的天数				Number	10<br/>
     F	月份中的星期				Number	2<br/>
     E	星期中的天数				Text	Tuesday; Tue<br/>
     a	Am/pm 标记	Text			PM<br/>
     H	一天中的小时数（0-23）		Number	0<br/>
     k	一天中的小时数（1-24）		Number	24<br/>
     K	am/pm 中的小时数（0-11）	Number	0<br/>
     h	am/pm 中的小时数（1-12）	Number	12<br/>
     m	小时中的分钟数				Number	30<br/>
     s	分钟中的秒数				Number	55<br/>
     S	毫秒数						Number	978<br/>
     z	时区	General time zone	Pacific Standard Time; PST; GMT-08:00<br/>
     Z	时区	RFC 822 time zone	-0800<br/>
     * */
    public static boolean isFormatCorrect(String format, String dateStr) {
        //注意，如果不指定美国格式，很多奇怪的格式都不能通过校验
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        //lenient，宽容的。表示严格校验。
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateStr);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
