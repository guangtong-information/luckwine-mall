package com.luckwine.parent.tools.date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 简单农历对象 
 * Title: 
 * Description: 
 * Company: Copyright @ 2016 优宜趣供应链管理有限公司 版权所有
 * @author: 陈志光
 * @date: 2014年12月28日上午9:28:30
 * @version 1.0 初稿
 */
public class DateLunar {

	/** 最小时间1900-1-31*/
	private final static long minTimeInMillis = -2206425952001L;
	/** 最大时间2099-12-31 */
	private final static long maxTimeInMillis = 4102416000000L;
	/**
	 * 农历年数据表(1900-2099年)<br>
	 * <br>
	 * 每个农历年用16进制来表示，解析时转为2进制<br>
	 * 前12位分别表示12个农历月份的大小月，1是大月，0是小月<br>
	 * 最后4位表示闰月，转为十进制后即为闰月值，例如0110，则为闰6月
	 */
	private final static int[] lunarInfo = { 0x4bd8, 0x4ae0, 0xa570, 0x54d5, 0xd260, 0xd950, 0x5554, 0x56af, 0x9ad0, 0x55d2, 0x4ae0, 0xa5b6, 0xa4d0, 0xd250, 0xd295, 0xb54f, 0xd6a0, 0xada2, 0x95b0,
			0x4977, 0x497f, 0xa4b0, 0xb4b5, 0x6a50, 0x6d40, 0xab54, 0x2b6f, 0x9570, 0x52f2, 0x4970, 0x6566, 0xd4a0, 0xea50, 0x6a95, 0x5adf, 0x2b60, 0x86e3, 0x92ef, 0xc8d7, 0xc95f, 0xd4a0, 0xd8a6,
			0xb55f, 0x56a0, 0xa5b4, 0x25df, 0x92d0, 0xd2b2, 0xa950, 0xb557, 0x6ca0, 0xb550, 0x5355, 0x4daf, 0xa5b0, 0x4573, 0x52bf, 0xa9a8, 0xe950, 0x6aa0, 0xaea6, 0xab50, 0x4b60, 0xaae4, 0xa570,
			0x5260, 0xf263, 0xd950, 0x5b57, 0x56a0, 0x96d0, 0x4dd5, 0x4ad0, 0xa4d0, 0xd4d4, 0xd250, 0xd558, 0xb540, 0xb6a0, 0x95a6, 0x95bf, 0x49b0, 0xa974, 0xa4b0, 0xb27a, 0x6a50, 0x6d40, 0xaf46,
			0xab60, 0x9570, 0x4af5, 0x4970, 0x64b0, 0x74a3, 0xea50, 0x6b58, 0x5ac0, 0xab60, 0x96d5, 0x92e0, 0xc960, 0xd954, 0xd4a0, 0xda50, 0x7552, 0x56a0, 0xabb7, 0x25d0, 0x92d0, 0xcab5, 0xa950,
			0xb4a0, 0xbaa4, 0xad50, 0x55d9, 0x4ba0, 0xa5b0, 0x5176, 0x52bf, 0xa930, 0x7954, 0x6aa0, 0xad50, 0x5b52, 0x4b60, 0xa6e6, 0xa4e0, 0xd260, 0xea65, 0xd530, 0x5aa0, 0x76a3, 0x96d0, 0x4afb,
			0x4ad0, 0xa4d0, 0xd0b6, 0xd25f, 0xd520, 0xdd45, 0xb5a0, 0x56d0, 0x55b2, 0x49b0, 0xa577, 0xa4b0, 0xaa50, 0xb255, 0x6d2f, 0xada0, 0x4b63, 0x937f, 0x49f8, 0x4970, 0x64b0, 0x68a6, 0xea5f,
			0x6b20, 0xa6c4, 0xaaef, 0x92e0, 0xd2e3, 0xc960, 0xd557, 0xd4a0, 0xda50, 0x5d55, 0x56a0, 0xa6d0, 0x55d4, 0x52d0, 0xa9b8, 0xa950, 0xb4a0, 0xb6a6, 0xad50, 0x55a0, 0xaba4, 0xa5b0, 0x52b0,
			0xb273, 0x6930, 0x7337, 0x6aa0, 0xad50, 0x4b55, 0x4b6f, 0xa570, 0x54e4, 0xd260, 0xe968, 0xd520, 0xdaa0, 0x6aa6, 0x56df, 0x4ae0, 0xa9d4, 0xa4d0, 0xd150, 0xf252, 0xd520 };
	/** 十二生肖 */
	private final static String[] Animals = { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };
	/** 农历中文字符串一 */
	private final static String[] lunarString1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
	/** 农历中文字符串二 */
	private final static String[] lunarString2 = { "初", "十", "廿", "卅", "正", "腊", "冬", "闰" };
	/** 农历年 */
	private int lunarYear;
	/** 农历月 */
	private int lunarMonth;
	/** 农历日 */
	private int lunarDay;
	/** 是否是闰月 */
	private boolean isLeap;
	/** 是否是闰年 */
	private boolean isLeapYear;
	/** 某农历月的最大天数 */
	private int maxDayInMonth = 29;

	/**
	 * 通过 TimeInMillis 构建农历信息
	 * @param TimeInMillis
	 */
	public DateLunar(long TimeInMillis) {
		this.init(TimeInMillis);
	}

	/**
	 * 通过 Date 对象构建农历信息
	 * @param date 指定日期对象
	 */
	public DateLunar(Date date) {
		if (date == null) {
			date = new Date();
		}
		this.init(date.getTime());
	}

	/**
	 * 农历初始化
	 * @param timeInMillis 时间毫秒数
	 */
	private void init(long timeInMillis) {
		if (timeInMillis > minTimeInMillis && timeInMillis < maxTimeInMillis) {
			// 以农历为1900年正月一日的1900-1-31作为起始日期
			Calendar baseDate = new GregorianCalendar(1900, 0, 31);
			// 距离起始日期间隔的总天数
			long offset = (timeInMillis - baseDate.getTimeInMillis()) / 86400000;
			// 默认农历年为1900年，且由此开始推算农历年份
			this.lunarYear = 1900;
			int daysInLunarYear = DateLunar.getLunarYearDays(this.lunarYear);
			// 递减每个农历年的总天数，确定农历年份
			while (this.lunarYear < 2100 && offset >= daysInLunarYear) {
				offset -= daysInLunarYear;
				daysInLunarYear = DateLunar.getLunarYearDays(++this.lunarYear);
			}
			// 获取该农历年的闰月月份
			int leapMonth = DateLunar.getLunarLeapMonth(this.lunarYear);
			// 没有闰月则不是闰年
			this.isLeapYear = leapMonth > 0;

			// 默认农历月为正月，且由此开始推荐农历月
			int lunarMonth = 1;
			// 是否递减农历月
			boolean isDecrease = true;
			boolean isLeap = false;
			int daysInLunarMonth = 0;
			// 递减每个农历月的总天数，确定农历月份
			while (lunarMonth < 13 && offset > 0) {
				if (isLeap && !isDecrease) {
					// 该农历年闰月的总天数
					daysInLunarMonth = DateLunar.getLunarLeapDays(this.lunarYear);
					isDecrease = true;
				} else {
					// 该农历年正常农历月份的天数
					daysInLunarMonth = DateLunar.getLunarMonthDays(this.lunarYear, lunarMonth);
				}
				if (offset < daysInLunarMonth) {
					break;
				}
				offset -= daysInLunarMonth;

				// 如果农历月是闰月，则不递增农历月份
				if (leapMonth == lunarMonth && isLeap == false) {
					isDecrease = false;
					isLeap = true;
				} else {
					lunarMonth++;
				}
			}
			// 如果daysInLunarMonth为0则说明默认农历月即为返回的农历月
			this.maxDayInMonth = daysInLunarMonth != 0 ? daysInLunarMonth : DateLunar.getLunarMonthDays(this.lunarYear, lunarMonth);
			this.lunarMonth = lunarMonth;
			this.isLeap = (lunarMonth == leapMonth && isLeap);
			this.lunarDay = (int) offset + 1;
		}
	}

	/**
	 * 获取某农历年的总天数
	 * @param lunarYear 农历年份
	 * @return 该农历年的总天数
	 */
	private static int getLunarYearDays(int lunarYear) {
		// 按小月计算,农历年最少有12 * 29 = 348天
		int daysInLunarYear = 348;

		// 遍历前12位
		for (int i = 0x8000; i > 0x8; i >>= 1) {
			// 每个大月累加一天
			daysInLunarYear += ((DateLunar.lunarInfo[lunarYear - 1900] & i) != 0) ? 1 : 0;
		}
		// 加上闰月天数
		daysInLunarYear += DateLunar.getLunarLeapDays(lunarYear);

		return daysInLunarYear;
	}

	/**
	 * 获取某农历年闰月的总天数
	 * @param lunarYear 农历年份
	 * @return 该农历年闰月的总天数，没有闰月返回0
	 */
	private static int getLunarLeapDays(int lunarYear) {
		// 下一年最后4bit为1111,返回30(大月)
		// 下一年最后4bit不为1111,返回29(小月)
		// 若该年没有闰月,返回0
		return DateLunar.getLunarLeapMonth(lunarYear) > 0 ? ((DateLunar.lunarInfo[lunarYear - 1899] & 0xf) == 0xf ? 30 : 29) : 0;
	}

	/**
	 * 获取某农历年闰月月份
	 * @param lunarYear 农历年份
	 * @return 该农历年闰月的月份，没有闰月返回0
	 */
	private static int getLunarLeapMonth(int lunarYear) {
		// 匹配后4位
		int leapMonth = DateLunar.lunarInfo[lunarYear - 1900] & 0xf;
		// 若最后4位全为1或全为0,表示没闰
		leapMonth = (leapMonth == 0xf ? 0 : leapMonth);
		return leapMonth;
	}

	/**
	 * 获取某农历年某农历月份的总天数
	 * @param lunarYear 农历年份
	 * @param lunarMonth 农历月份
	 * @return 该农历年该农历月的总天数
	 */
	private static int getLunarMonthDays(int lunarYear, int lunarMonth) {
		// 匹配前12位代表的相应农历月份的大小月，大月30天，小月29天
		int daysInLunarMonth = ((DateLunar.lunarInfo[lunarYear - 1900] & (0x10000 >> lunarMonth)) != 0) ? 30 : 29;
		return daysInLunarMonth;
	}

	/**
	 * 返回指定数字的农历年份表示字符串
	 * @param lunarYear 农历年份(数字,0为甲子)
	 * @return 农历年份字符串
	 */
	private static String getLunarYearString(int lunarYear) {
		String lunarYearString = "";
		String year = String.valueOf(lunarYear);
		for (int i = 0; i < year.length(); i++) {
			char yearChar = year.charAt(i);
			int index = Integer.parseInt(String.valueOf(yearChar));
			lunarYearString += lunarString1[index];
		}
		return lunarYearString;
	}

	/**
	 * 返回指定数字的农历月份表示字符串
	 * @param lunarMonth 农历月份(数字)
	 * @return 农历月份字符串 (例:正)
	 */
	private static String getLunarMonthString(int lunarMonth) {
		String lunarMonthString = "";
		if (lunarMonth == 1) {
			lunarMonthString = DateLunar.lunarString2[4];
		} else {
			if (lunarMonth > 9) {
				lunarMonthString += DateLunar.lunarString2[1];
			}
			if (lunarMonth % 10 > 0) {
				lunarMonthString += DateLunar.lunarString1[lunarMonth % 10];
			}
		}
		return lunarMonthString;
	}

	/**
	 * 返回指定数字的农历日表示字符串
	 * @param lunarDay 农历日(数字)
	 * @return 农历日字符串 (例: 廿一)
	 */
	private static String getLunarDayString(int lunarDay) {
		if (lunarDay < 1 || lunarDay > 30) {
			return "";
		}
		int i1 = lunarDay / 10;
		int i2 = lunarDay % 10;
		String c1 = DateLunar.lunarString2[i1];
		String c2 = DateLunar.lunarString1[i2];
		if (lunarDay < 11) {
			c1 = DateLunar.lunarString2[0];
		}
		if (i2 == 0) {
			c2 = DateLunar.lunarString2[1];
		}
		return c1 + c2;
	}

	/**
	 * 取农历年生肖
	 * @return 农历年生肖(例:龙)
	 */
	public String getAnimalString() {
		if (lunarYear == 0) {
			return null;
		}
		return DateLunar.Animals[(this.lunarYear - 4) % 12];
	}

	/**
	 * 返回农历日期字符串
	 * @return 农历日期字符串
	 */
	public String getDayString() {
		if (lunarDay == 0) {
			return null;
		}
		return DateLunar.getLunarDayString(this.lunarDay);
	}

	/**
	 * 返回农历日期字符串
	 * @return 农历日期字符串
	 */
	public String getMonthString() {
		if (lunarMonth == 0) {
			return null;
		}
		return (this.isLeap() ? "闰" : "") + DateLunar.getLunarMonthString(this.lunarMonth);
	}

	/**
	 * 返回农历日期字符串
	 * @return 农历日期字符串
	 */
	public String getYearString() {
		if (lunarYear == 0) {
			return null;
		}
		return DateLunar.getLunarYearString(this.lunarYear);
	}

	/**
	 * 返回农历表示字符串
	 * @return 农历字符串(例:甲子年正月初三)
	 */
	public String getDateString() {
		if (lunarYear == 0) {
			return null;
		}
		return this.getYearString() + "年" + this.getMonthString() + "月" + this.getDayString() + "日";
	}

	/**
	 * 农历年是否是闰月
	 * @return 农历年是否是闰月
	 */
	public boolean isLeap() {
		return isLeap;
	}

	/**
	 * 农历年是否是闰年
	 * @return 农历年是否是闰年
	 */
	public boolean isLeapYear() {
		return isLeapYear;
	}

	/**
	 * 当前农历月是否是大月
	 * @return 当前农历月是大月
	 */
	public boolean isBigMonth() {
		return this.getMaxDayInMonth() > 29;
	}

	/**
	 * 当前农历月有多少天
	 * @return 天数
	 */
	public int getMaxDayInMonth() {
		if (lunarYear == 0) {
			return 0;
		}
		return this.maxDayInMonth;
	}

	/**
	 * 农历日期
	 * @return 农历日期
	 */
	public int getDay() {
		return lunarDay;
	}

	/**
	 * 农历月份
	 * @return 农历月份
	 */
	public int getMonth() {
		return lunarMonth;
	}

	/**
	 * 农历年份
	 * @return 农历年份
	 */
	public int getYear() {
		return lunarYear;
	}
}