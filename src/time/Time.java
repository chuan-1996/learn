package time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class Time {
    public static void main(String[] args){
        LocalDateTime today = LocalDateTime.now();
        System.out.println(today);
        System.out.println(today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(LocalDateTime.now(ZoneId.of("Asia/Kolkata")));
        //获取当前年，并判断是否是闰年
        System.out.println("Year " + today.getYear() + " is Leap Year? " + today.toLocalDate().isLeapYear());

        //比较两个日期的先后
        System.out.println("Today is before 01/01/2015? " + today.isBefore(LocalDateTime.of(2015,1, 1, 11, 30)));

        //时间加减
        System.out.println("10 days after today will be " + today.plusDays(10));
        System.out.println("3 weeks after today will be " + today.plusWeeks(3));
        System.out.println("20 months after today will be " + today.plusMonths(20));
        System.out.println("10 days before today will be " + today.minusDays(10));
        System.out.println("3 weeks before today will be " + today.minusWeeks(3));
        System.out.println("20 months before today will be " + today.minusMonths(20));

        //查询日期的特定点
        System.out.println("First date of this month= " + today.with(TemporalAdjusters.firstDayOfMonth()));
        LocalDate lastDayOfYear = today.toLocalDate().with(TemporalAdjusters.lastDayOfYear());
        System.out.println("Last date of this year= " + lastDayOfYear);

        //两个日期间的间隔
        Period period = today.toLocalDate().until(lastDayOfYear);
        System.out.println("Period Format= " + period);

    }

}
