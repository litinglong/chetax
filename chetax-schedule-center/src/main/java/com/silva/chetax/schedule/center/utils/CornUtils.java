package com.silva.chetax.schedule.center.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.CronExpression;
import org.springframework.util.StringUtils;

//import  org.quartz.CronExpression;

public class CornUtils {
    /**
     * 解析corn表达式，生成指定日期的时间序列
     *
     * @param cronExpression cron表达式
     * @param cronDate cron解析日期
     * @param result crom解析时间序列
     * @return 解析成功失败
     */
    public  static  boolean  parser(String cronExpression, String cronDate, List<String> result)
    {
        if  (cronExpression ==  null  || cronExpression.length() <  1  || cronDate ==  null  || cronDate.length() <  1 )
        {
            return  false ;
        }
        else
        {
            CronExpression exp =  null ;
            // 初始化cron表达式解析器
            try
            {
                exp =  new  CronExpression(cronExpression);
            }
            catch  (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return  false ;
            }

            // 定义生成时间范围
            // 定义开始时间，前一天的23点59分59秒
            Calendar c = Calendar.getInstance();
            String sStart = cronDate +  " 00:00:00" ;
            SimpleDateFormat sdf =  new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
            Date dStart =  null ;
            try
            {
                dStart = sdf.parse(sStart);
            }
            catch  (ParseException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            c.setTime(dStart);
            c.add(Calendar.SECOND, - 1 );
            dStart = c.getTime();

            // 定义结束时间，当天的23点59分59秒
            c.add(Calendar.DATE,  1 );
            Date dEnd = c.getTime();

            // 生成时间序列
            Date dd = dStart;
            dd = exp.getNextValidTimeAfter(dd);
            while  ((dd.getTime() >= dStart.getTime()) && (dd.getTime() <= dEnd.getTime()))
            {
                result.add(sdf.format(dd));
                dd = exp.getNextValidTimeAfter(dd);
            }
            exp =  null ;
        }
        return  true ;
    }

    public  static  String translateToChinese(String cronExp)
    {
        if  (cronExp ==  null  || cronExp.length() <  1 )
        {
            return  "cron表达式为空" ;
        }
        CronExpression exp =  null ;
        // 初始化cron表达式解析器
        try
        {
            exp =  new  CronExpression(cronExp);
        }
        catch  (Exception e)
        {
            return  "corn表达式不正确" ;
        }
        String[] tmpCorns = cronExp.split( " " );
        StringBuffer sBuffer =  new  StringBuffer();
        if (tmpCorns.length ==  6 )
        {
            //解析月
            if (tmpCorns[ 4 ].equals( "*" ))
            {
                sBuffer.append( "每月" );
            }
            else if (tmpCorns[ 4 ].contains("/")){
                String[] str = StringUtils.split(tmpCorns[ 4 ],"/");
                String first = str[0];
                String last = str[1];
                sBuffer.append( "第" + first + "月开始每隔" + last + "月");
            }
            else
            {
                sBuffer.append(tmpCorns[ 4 ]).append( "月" );
            }
            //解析周
            if (!tmpCorns[ 5 ].equals( "*" ) && !tmpCorns[ 5 ].equals( "?" ))
            {
                if (tmpCorns[ 5 ].contains("/")){
                    String[] str = StringUtils.split(tmpCorns[ 5 ],"/");
                    String first = str[0];
                    String last = str[1];
                    switch  (first)
                    {
                        case  "1" :
                            sBuffer.append( "从星期天开始每隔" + last + "周");
                            break ;
                        case  "2" :
                            sBuffer.append( "从星期一开始每隔" + last + "周");
                            break ;
                        case  "3" :
                            sBuffer.append( "从星期二开始每隔" + last + "周");
                            break ;
                        case  "4" :
                            sBuffer.append( "从星期三开始每隔" + last + "周");
                            break ;
                        case  "5" :
                            sBuffer.append( "从星期四开始每隔" + last + "周");
                            break ;
                        case  "6" :
                            sBuffer.append( "从星期五开始每隔" + last + "周");
                            break ;
                        case  "7" :
                            sBuffer.append( "从星期六开始每隔" + last + "周");
                            break ;
                    }
                }else if (tmpCorns[ 5 ].contains("#")){
                    String[] str = StringUtils.split(tmpCorns[ 5 ],"#");
                    String first = str[0];
                    String last = str[1];
                    switch  (first)
                    {
                        case  "1" :
                            sBuffer.append( "第" + last + "个" + "星期天");
                            break ;
                        case  "2" :
                            sBuffer.append( "第" + last + "个" + "星期一");
                            break ;
                        case  "3" :
                            sBuffer.append( "第" + last + "个" + "星期二");
                            break ;
                        case  "4" :
                            sBuffer.append( "第" + last + "个" + "星期三");
                            break ;
                        case  "5" :
                            sBuffer.append( "第" + last + "个" + "星期四");
                            break ;
                        case  "6" :
                            sBuffer.append( "第" + last + "个" + "星期五");
                            break ;
                        case  "7" :
                            sBuffer.append( "第" + last + "个" + "星期六");
                            break ;
                    }
                }
                else if (tmpCorns[ 5 ].contains("L") && !tmpCorns[ 5 ].contains("W") && tmpCorns[ 5 ].toCharArray().length == 2){
                    switch  (tmpCorns[ 5 ])
                    {
                        case  "1L" :
                            sBuffer.append( "最后一个星期天" );
                            break ;
                        case  "2L" :
                            sBuffer.append( "最后一个星期一" );
                            break ;
                        case  "3L" :
                            sBuffer.append( "最后一个星期二" );
                            break ;
                        case  "4L" :
                            sBuffer.append( "最后一个星期三" );
                            break ;
                        case  "5L" :
                            sBuffer.append( "最后一个星期四" );
                            break ;
                        case  "6L" :
                            sBuffer.append( "最后一个星期五" );
                            break ;
                        case  "7L" :
                            sBuffer.append( "最后一个星期六" );
                            break ;
                    }
                }
                else {
                    String [] tmpArray =  tmpCorns[ 5 ].split(",");
                    for ( String  tmp:tmpArray)
                    {
                        switch  (tmp)
                        {
                            case  "SUN" :
                                sBuffer.append( "星期天" );
                                break ;
                            case  "MON" :
                                sBuffer.append( "星期一" );
                                break ;
                            case  "TUE" :
                                sBuffer.append( "星期二" );
                                break ;
                            case  "WED" :
                                sBuffer.append( "星期三" );
                                break ;
                            case  "THU" :
                                sBuffer.append( "星期四" );
                                break ;
                            case  "FRI" :
                                sBuffer.append( "星期五" );
                                break ;
                            case  "SAT" :
                                sBuffer.append( "星期六" );
                                break ;
                            case  "-" :
                                sBuffer.append( "至" );
                                break ;
                            default :
                                sBuffer.append(tmp);
                                break ;
                        }
                    }
                }
            }

            //解析日
            if (!tmpCorns[ 3 ].equals( "?" ))
            {
                if (tmpCorns[ 3 ].equals( "*" ))
                {
                    sBuffer.append( "每日" );
                }
                else if (tmpCorns[ 3 ].contains("/")){
                    String[] str = StringUtils.split(tmpCorns[ 3 ],"/");
                    String first = str[0];
                    String last = str[1];
                    sBuffer.append( "第" + first + "天开始每隔" + last + "天");
                }
                else if (tmpCorns[ 3 ].contains("L") && tmpCorns[ 3 ].toCharArray().length == 1){
                    sBuffer.append("最后一天");
                }
                else if (tmpCorns[ 3 ].contains("LW") && tmpCorns[ 3 ].toCharArray().length == 2){
                    sBuffer.append("最后一个工作日");
                }
                else if (tmpCorns[ 3 ].contains("L-")){
                    switch  (tmpCorns[ 3 ])
                    {
                        case  "L-1" :
                            sBuffer.append( "月底前1日" );
                            break ;
                        case  "L-2" :
                            sBuffer.append( "月底前2日" );
                            break ;
                        case  "L-3" :
                            sBuffer.append( "月底前3日" );
                            break ;
                        case  "L-4" :
                            sBuffer.append( "月底前4日" );
                            break ;
                        case  "L-5" :
                            sBuffer.append( "月底前5日" );
                            break ;
                        case  "L-6" :
                            sBuffer.append( "月底前6日" );
                            break ;
                        case  "L-7" :
                            sBuffer.append( "月底前7日" );
                            break ;
                        case  "L-8" :
                            sBuffer.append( "月底前8日" );
                            break ;
                        case  "L-9" :
                            sBuffer.append( "月底前9日" );
                            break ;
                        case  "L-10" :
                            sBuffer.append( "月底前10日" );
                            break ;
                        case  "L-11" :
                            sBuffer.append( "月底前11日" );
                            break ;
                        case  "L-12" :
                            sBuffer.append( "月底前12日" );
                            break ;
                        case  "L-13" :
                            sBuffer.append( "月底前13日" );
                            break ;
                        case  "L-14" :
                            sBuffer.append( "月底前14日" );
                            break ;
                        case  "L-15" :
                            sBuffer.append( "月底前15日" );
                            break ;
                        case  "L-16" :
                            sBuffer.append( "月底前16日" );
                            break ;
                        case  "L-17" :
                            sBuffer.append( "月底前17日" );
                            break ;
                        case  "L-18" :
                            sBuffer.append( "月底前18日" );
                            break ;
                        case  "L-19" :
                            sBuffer.append( "月底前19日" );
                            break ;
                        case  "L-20" :
                            sBuffer.append( "月底前20日" );
                            break ;
                        case  "L-21" :
                            sBuffer.append( "月底前21日" );
                            break ;
                        case  "L-22" :
                            sBuffer.append( "月底前22日" );
                            break ;
                        case  "L-23" :
                            sBuffer.append( "月底前23日" );
                            break ;
                        case  "L-24" :
                            sBuffer.append( "月底前24日" );
                            break ;
                        case  "L-25" :
                            sBuffer.append( "月底前25日" );
                            break ;
                        case  "L-26" :
                            sBuffer.append( "月底前26日" );
                            break ;
                        case  "L-27" :
                            sBuffer.append( "月底前27日" );
                            break ;
                        case  "L-28" :
                            sBuffer.append( "月底前28日" );
                            break ;
                        case  "L-29" :
                            sBuffer.append( "月底前29日" );
                            break ;
                        case  "L-30" :
                            sBuffer.append( "月底前30日" );
                            break ;
                        case  "L-31" :
                            sBuffer.append( "月底前31日" );
                            break ;
                    }
                }
                else if (tmpCorns[ 3 ].contains("W") && !tmpCorns[ 3 ].contains("L")){
                    switch  (tmpCorns[ 3 ])
                    {
                        case  "1W" :
                            sBuffer.append( "最近的工作日至本月1日" );
                            break ;
                        case  "2W" :
                            sBuffer.append( "最近的工作日至本月2日" );
                            break ;
                        case  "3W" :
                            sBuffer.append( "最近的工作日至本月3日" );
                            break ;
                        case  "4W" :
                            sBuffer.append( "最近的工作日至本月4日" );
                            break ;
                        case  "5W" :
                            sBuffer.append( "最近的工作日至本月5日" );
                            break ;
                        case  "6W" :
                            sBuffer.append( "最近的工作日至本月6日" );
                            break ;
                        case  "7W" :
                            sBuffer.append( "最近的工作日至本月7日" );
                            break ;
                        case  "8W" :
                            sBuffer.append( "最近的工作日至本月8日" );
                            break ;
                        case  "9W" :
                            sBuffer.append( "最近的工作日至本月9日" );
                            break ;
                        case  "10W" :
                            sBuffer.append( "最近的工作日至本月10日" );
                            break ;
                        case  "11W" :
                            sBuffer.append( "最近的工作日至本月11日" );
                            break ;
                        case  "12W" :
                            sBuffer.append( "最近的工作日至本月12日" );
                            break ;
                        case  "13W" :
                            sBuffer.append( "最近的工作日至本月13日" );
                            break ;
                        case  "14W" :
                            sBuffer.append( "最近的工作日至本月14日" );
                            break ;
                        case  "15W" :
                            sBuffer.append( "最近的工作日至本月15日" );
                            break ;
                        case  "16W" :
                            sBuffer.append( "最近的工作日至本月16日" );
                            break ;
                        case  "17W" :
                            sBuffer.append( "最近的工作日至本月17日" );
                            break ;
                        case  "18W" :
                            sBuffer.append( "最近的工作日至本月18日" );
                            break ;
                        case  "19W" :
                            sBuffer.append( "最近的工作日至本月19日" );
                            break ;
                        case  "20W" :
                            sBuffer.append( "最近的工作日至本月20日" );
                            break ;
                        case  "21W" :
                            sBuffer.append( "最近的工作日至本月21日" );
                            break ;
                        case  "22W" :
                            sBuffer.append( "最近的工作日至本月22日" );
                            break ;
                        case  "23W" :
                            sBuffer.append( "最近的工作日至本月23日" );
                            break ;
                        case  "24W" :
                            sBuffer.append( "最近的工作日至本月24日" );
                            break ;
                        case  "25W" :
                            sBuffer.append( "最近的工作日至本月25日" );
                            break ;
                        case  "26W" :
                            sBuffer.append( "最近的工作日至本月26日" );
                            break ;
                        case  "27W" :
                            sBuffer.append( "最近的工作日至本月27日" );
                            break ;
                        case  "28W" :
                            sBuffer.append( "最近的工作日至本月28日" );
                            break ;
                        case  "29W" :
                            sBuffer.append( "最近的工作日至本月29日" );
                            break ;
                        case  "30W" :
                            sBuffer.append( "最近的工作日至本月30日" );
                            break ;
                        case  "31W" :
                            sBuffer.append( "最近的工作日至本月31日" );
                            break ;
                    }
                }
                else
                {
                    sBuffer.append(tmpCorns[ 3 ]).append( "日" );
                }
            }

            //解析时
            if (tmpCorns[ 2 ].equals( "*" ))
            {
                sBuffer.append( "每时" );
            }
            else if (tmpCorns[ 2 ].contains("/")){
                String[] str = StringUtils.split(tmpCorns[ 2 ],"/");
                String first = str[0];
                String last = str[1];
                sBuffer.append( "第" + first + "时开始每隔" + last + "时");
            }
            else
            {
                sBuffer.append(tmpCorns[ 2 ]).append( "时" );
            }

            //解析分
            if (tmpCorns[ 1 ].equals( "*" ))
            {
                sBuffer.append( "每分" );
            }
            else if (tmpCorns[ 1 ].contains("/")){
                String[] str = StringUtils.split(tmpCorns[ 1 ],"/");
                String first = str[0];
                String last = str[1];
                sBuffer.append( "第" + first + "分开始每隔" + last + "分");
            }
            else
            {
                sBuffer.append(tmpCorns[ 1 ]).append( "分" );
            }

            //解析秒
            /*if (!tmpCorns[ 0 ].equals( "*" ))
            {
                sBuffer.append(tmpCorns[ 0 ]).append( "秒" );
            }
            else
            {
                sBuffer.append( "每秒" );
            }*/
        }

        return  sBuffer.toString();

    }

    //测试方法
    public  static  void  main(String[] args)
    {
        String CRON_EXPRESSION =  "01 * * 2 * ?" ;
        CRON_EXPRESSION =  "0,2,3,5 0,5 0,5,6 LW 9,10 ? 2001-2020" ;
        
        // 生成指定日期的CRON时间序列
        String CRON_DATE =  "2016-04-26" ;
        System.out.println(CRON_EXPRESSION);
        System.out.println(translateToChinese(CRON_EXPRESSION));

        List<String> lTime =  new  ArrayList<String>();
        if (!parser(CRON_EXPRESSION, CRON_DATE, lTime)){
            System.out.println( "无法生成Cron表达式：日期," +CRON_DATE+ ";不符合规则cron表达式：" +CRON_EXPRESSION);
        }
        for ( int  i= 0 ;i<lTime.size();i++){
            System.out.println(lTime.get(i));
        }

    }
}
