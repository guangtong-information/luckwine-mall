package com.luckwine.parent.tools.common;


import com.luckwine.parent.tools.date.DateStyle;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueUtil {
	private static DecimalFormat DecimalFormatter = new DecimalFormat("#.######");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(DateStyle.YYYY_MM_DD_HH_MM_SS.getValue());
	private static SimpleDateFormat dateFormat1 = new SimpleDateFormat(DateStyle.YYYY_MM_DD_HH_MM_SS_EN.getValue());
	private static Pattern datetimepattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([2][0-3])|([1][0-9]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
	private static Pattern datepattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");

	/**
	 * 对象转字符串
	 *
	 * @date: 2014年11月20日上午10:32:48
	 */
	public static String toStrNull(Object value) {
		if (null == value) {
			return null;
		}
		if (value instanceof Double || value instanceof Float) {
			return DecimalFormatter.format(value);
		}
		return value.toString().trim();
	}

	/**
	 * 返回字符串，为空则返回""
	 * Description:
	 *
	 * @date: 2014年11月20日上午10:32:48
	 */
	public static String toStr(Object value) {
		if (null == value) {
			return "";
		}
		if (value instanceof Double || value instanceof Float || value instanceof BigDecimal) {
			return DecimalFormatter.format(value);
		}
		return value.toString().trim();
	}

	/**
	 * 返回整数，为空则返回null
	 * Description:
	 *
	 * @date: 2014年11月20日上午10:32:48
	 */
	public static Integer toIntNull(Object value) {
		if (null == value) {
			return null;
		}
		Integer val = null;
		if (value instanceof String) {
			try {
				val = Integer.valueOf(value.toString());
			} catch (Exception e) {
			}
			if (null == val) {
				try {
					val = Float.valueOf(value.toString()).intValue();
				} catch (Exception er) {
					return null;
				}
			}
			return val;
		}
		if (value instanceof BigDecimal) {
			val = ((BigDecimal) value).intValue();
			return val;
		}
		if (value instanceof Number) {
			val = ((Number) value).intValue();
			return val;
		}

		return null;
	}

	/**
	 * 返回整数，为空则返回0
	 * Description:
	 *
	 * @date: 2014年11月20日上午10:32:48
	 */
	public static Integer toInt(Object value) {
		if (null == value) {
			return 0;
		}
		Integer val = 0;
		if (value instanceof String) {
			try {
				val = Integer.valueOf(value.toString());
			} catch (Exception e) {
			}
			if (null == val) {
				try {
					val = Float.valueOf(value.toString()).intValue();
				} catch (Exception er) {
					return 0;
				}
			}
			return val;
		}
		if (value instanceof BigDecimal) {
			val = ((BigDecimal) value).intValue();
			return val;
		}
		if (value instanceof Number) {
			val = ((Number) value).intValue();
			return val;
		}

		return 0;
	}

	public static Float toFloatNull(Object value) {
		if (null == value) {
			return null;
		}
		Float val = null;
		if (value instanceof String) {
			try {
				val = Float.valueOf(value.toString());
			} catch (Exception e) {
				return null;
			}
			return val;
		}
		if (value instanceof BigDecimal) {
			val = ((BigDecimal) value).floatValue();
			return val;
		}
		if (value instanceof Number) {
			val = ((Number) value).floatValue();
			return val;
		}

		return null;
	}

	public static Double toDoubleNull(Object value) {
		if (null == value) {
			return null;
		}
		Double val = null;
		if (value instanceof String) {
			try {
				val = Double.valueOf(value.toString());
			} catch (Exception e) {
			}
			return val;
		}
		if (value instanceof BigDecimal) {
			val = ((BigDecimal) value).doubleValue();
			return val;
		}
		if (value instanceof Number) {
			val = ((Number) value).doubleValue();
			return val;
		}

		return val;
	}

	public static Double toDouble(Object value) {
		if (null == value) {
			return 0d;
		}
		Double val = 0d;
		if (value instanceof String) {
			try {
				String converStr = value.toString().replaceAll(",", "");
				val = Double.valueOf(converStr);
			} catch (Exception e) {
			}
			return val;
		}
		if (value instanceof BigDecimal) {
			val = ((BigDecimal) value).doubleValue();
			return val;
		}
		if (value instanceof Number) {
			val = ((Number) value).doubleValue();
			return val;
		}

		return val;
	}

	public static Long toLongNull(Object value) {
		if (null == value) {
			return null;
		}
		Long val = null;
		if (value instanceof String) {
			try {
				val = Long.valueOf(value.toString());
			} catch (Exception e) {
			}
			if (null == val) {
				try {
					val = Double.valueOf(value.toString()).longValue();
				} catch (Exception er) {
				}
			}
			return val;
		}
		if (value instanceof BigDecimal) {
			val = ((BigDecimal) value).longValue();
			return val;
		}
		if (value instanceof Number) {
			val = ((Number) value).longValue();
			return val;
		}

		return val;
	}

	public static Long toLong(Object value) {
		if (null == value) {
			return 0L;
		}
		Long val = 0L;
		if (value instanceof String) {
			try {
				val = Long.valueOf(value.toString());
			} catch (Exception e) {
			}
			if (null == val) {
				try {
					val = Double.valueOf(value.toString()).longValue();
				} catch (Exception er) {
				}
			}
			return val;
		}
		if (value instanceof BigDecimal) {
			val = ((BigDecimal) value).longValue();
			return val;
		}
		if (value instanceof Number) {
			val = ((Number) value).longValue();
			return val;
		}

		return val;
	}

	public static Date toDateNull(Object value) {
		if (null == value) {
			return null;
		}

		if (value instanceof Date) {
			return (Date) value;
		}

		if ((value instanceof Integer) || (value instanceof Long)) {
			try {
				return new Date(Long.parseLong(value.toString()));
			} catch (Exception e) {
				return null;
			}
		}

		if ((value instanceof String) && (value.toString().trim().length() > 0)) {
			if (datepattern.matcher(value.toString()).matches()) {
				try {
					synchronized (dateFormat) {
						return dateFormat.parse(value + " 00:00:00");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					synchronized (dateFormat1) {
						return dateFormat1.parse(value + " 00:00:00");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			} else if (datetimepattern.matcher(value.toString()).matches()) {
				try {
					synchronized (dateFormat) {
						return dateFormat.parse(value.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					synchronized (dateFormat1) {
						return dateFormat1.parse(value.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			} else {
				boolean flag = true;
				for (char c : ((String) value).toCharArray()) {
					if (c >= '0' && c <= '9') {
						continue;
					} else {
						flag = false;
						break;
					}
				}
				if (flag) {
					try {
						return new Date(Long.parseLong(value.toString()));
					} catch (Exception e) {
						return null;
					}
				} else {
					return null;
				}
			}
		}
		return null;
	}

	/**
	 * 判断是否相等
	 */
	public static boolean equals(Object value1, Object value2) {
		if (null == value1 || null == value2) {
			return false;
		}
		return value1.equals(value2);
	}

	/**
	 * 判断是否相等
	 */
	public static boolean equalsIgnoreCase(Object value1, Object value2) {
		if (null == value1 || null == value2) {
			return false;
		}
		return value1.toString().equalsIgnoreCase(value2.toString());
	}

	// 浮点型判断
	public static boolean isDecimal(String str) {
		if (null == str || "".equals(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile(RegularUtil.DOUBLE);
		return pattern.matcher(str).matches();
	}

	// 整型判断
	public static boolean isInteger(String str) {
		if (null == str) {
			return false;
		}
		Pattern pattern = Pattern.compile(RegularUtil.INT);
		return pattern.matcher(str).matches();
	}

	public static String lpad(String str, int n, String padStr) {
		while (str.length() < n) {
			str = padStr + str;
		}
		return str;
	}

	public static String rpad(String str, int n, String padStr) {
		while (str.length() < n) {
			str = str + padStr;
		}
		return str;
	}

	/**
	 * 判断对象是否为空，字符串，集合，数组
	 * Description:
	 *
	 * @date: 2014年11月20日上午10:32:48
	 */
	public static boolean isEmpty(Object obj) {
		if (null == obj) {
			return true;
		}
		if (obj instanceof String) {
			return ((String) obj).trim().length() == 0;
		}
		if (obj instanceof Collection) {
			return ((Collection<?>) (obj)).size() == 0;
		}
		if (obj instanceof Object[]) {
			return ((Object[]) (obj)).length == 0;
		}
		if (obj instanceof Map) {
			return ((Map) (obj)).size() == 0;
		}
		if (obj instanceof Number) {
			return ((Number) (obj)).toString().length() == 0;
		}
		if (obj instanceof Enum) {
			return ((Enum) (obj)).toString().length() == 0;
		}

		return false;
	}

	/**
	 * 判断对象是否不为空，字符串，集合，数组
	 * Description:
	 *
	 * @date: 2014年11月20日上午10:32:48
	 */
	public static boolean isNotEmpty(Object value) {
		if (value == null) {
			return false;
		}
		if (value instanceof String) {
			if (((String) value).length() == 0) {
				return false;
			}
		}
		if (value instanceof Collection) {
			if (((Collection) value).isEmpty()) {
				return false;
			}
		}
		if (value instanceof Object[]) {
			if (((Object[]) value).length == 0) {
				return false;
			}
		}
		if (value instanceof Map) {
			if (((Map) value).size() == 0) {
				return false;
			}
		}

		if (value instanceof Number) {
			if (((Number) value).toString().length() == 0) {
				return false;
			}
		}
		if (value instanceof Enum) {
			if (((Enum) value).toString().length() == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @Description: 比较
	 * @date: 2014年11月20日上午10:32:48
	 */
	public static int compare(Object value1, Object value2) {
		if (value1 instanceof String) {
			return ValueUtil.toStr(value1).compareTo(ValueUtil.toStr(value2));
		}
		if (value1 instanceof Number) {
			return ValueUtil.toDouble(value1).compareTo(ValueUtil.toDouble(value2));
		}
		return 0;
	}

	/**
	 * @Description: 比较
	 * @date: 2014年11月20日上午10:32:48
	 */
	public static Boolean compareZero(Double number) {
		if (ValueUtil.isEmpty(number)) {
			return false;
		}
		BigDecimal amt = new BigDecimal(number);
		int i = amt.compareTo(BigDecimal.ZERO);

		if (i == 1) {
			return true;
		}
		return false;
	}

	/**
	 * @Description: 比较
	 * @date: 2014年11月20日上午10:32:48
	 */
	public static Boolean compareZero(Integer number) {
		if (ValueUtil.isEmpty(number)) {
			return false;
		}
		BigDecimal amt = new BigDecimal(number);
		int i = amt.compareTo(BigDecimal.ZERO);

		if (i == 1) {
			return true;
		}
		return false;
	}

	/**
	 * @Description: 比较
	 * @date: 2014年11月20日上午10:32:48
	 */
	public static Boolean compareZero(Long number) {
		if (ValueUtil.isEmpty(number)) {
			return false;
		}
		BigDecimal amt = new BigDecimal(number);
		int i = amt.compareTo(BigDecimal.ZERO);

		if (i == 1) {
			return true;
		}
		return false;
	}

	/**
	 * @Description: 比较
	 * @date: 2014年11月20日上午10:32:48
	 */
	public static Boolean compareZero(BigDecimal number) {
		if (ValueUtil.isEmpty(number)) {
			return false;
		}

		int i = number.compareTo(BigDecimal.ZERO);

		if (i == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 下划线转驼峰法
	 *
	 * @param line       源字符串
	 * @param smallCamel 大小驼峰,是否为小驼峰
	 * @return 转换后的字符串
	 */
	public static String underline2Camel(String line, boolean smallCamel) {
		if (line == null || "".equals(line)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		Pattern pattern = RegularUtil.EN_UNDERLINE_PATTERN;
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			String word = matcher.group();
			sb.append(smallCamel && matcher.start() == 0 ? Character.toLowerCase(word.charAt(0)) : Character.toUpperCase(word.charAt(0)));
			int index = word.lastIndexOf('_');
			if (index > 0) {
				sb.append(word.substring(1, index).toLowerCase());
			} else {
				sb.append(word.substring(1).toLowerCase());
			}
		}
		return sb.toString();
	}

	/**
	 * 驼峰法转下划线
	 *
	 * @param line 源字符串
	 * @return 转换后的字符串
	 */
	public static String camel2Underline(String line) {
		if (line == null || "".equals(line)) {
			return "";
		}
		line = String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
		StringBuffer sb = new StringBuffer();
		Pattern pattern = RegularUtil.EN_PATTERN;
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			String word = matcher.group();
			sb.append(word.toUpperCase());
			sb.append(matcher.end() == line.length() ? "" : "_");
		}
		return sb.toString();
	}

	//首字母转小写
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0))) {
			return s;
		} else {
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}


	//首字母转大写
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0))) {
			return s;
		} else {
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}

	public static void main(String[] args) throws Exception {
//		System.out.println(toStrNull(new Double(123121231233123.123123123123)));

/*
		String orderids="16022519591300159,16022519591300169,16022519591300179,16022910190800189,16022910190800199,160229101908001a9,160229101908001b9,160229101908001c9,160229101908001d9,160229101908001e9,160229101908001f9,16022910190800209,16022910190800219,16022910190800229,16022910190800239,16022910190800249,16022910190800259,16022910190800269,16022910190800279,16022910190800289,16022910190800299,160229101908002a9,160229101908002b9,160229101908002c9,160229101908002d9,160229101908002e9,160229101908002f9,16022910190800309,16022910190800319,16022910190800329,16022910190800339,16022910190800349,16022910190800359,16022910190800369,16022910190800379,16022910320400389,16022910320400399,16022917474600e19,16022917474600e29,16022917474600e39,16022917474600e49,16022917474600e59,16022917474600e69,16022917474600e79,16022917474600e89,16022917474600e99,16022917474600ea9,16022917474600eb9,16022917474600ec9,16022917474600ed9,16022917474600ee9,16022917474600ef9,16022917474600f09,16022917474600f19,16022917474600f29,16022917474600f39,16022917474600f49,16022917474600f59,16022917474600f69,16022917474600f79,16022917474600f89,16022917474600f99,16022917474600fa9,16022917474600fb9,16022917474600fc9,16022917474600fd9,16022917474600fe9,16022917474600ff9,16022917474601009,16022917474601019,16022917474601029,16022917474601039,16022917474601049,16022917474601059,16022917474601069,16022917474601079,16022917474601089,16022917474601099,160229174746010a9,160229174746010b9,160229174746010c9,160229174746010d9,160229174746010e9,160229174746010f9,16022917474601109,16022917474601119,16022917474601129,16022917474601139,16022917474601149,16022917474601159,16022917474601169,16022917474601179,16022917474601189,16022917474601199,160229174746011a9,160229174746011b9,160229174746011c9,160229174746011d9,160229174746011e9,160229174746011f9,16022917474601209,16022917474601219,16022917474601229,16022917474601239,16022917474601249,16022917474601259,16022917474601269,16022917474601279,16022917474601289,16022917474601299,160229174746012a9,160229174746012b9,160229174746012c9,160229174746012d9,160229174746012e9,160229174746012f9,16022917474601309,16022917474601319,16022917474601329,16022917474601339,16022917474601349,16022917474601359,16022917474601369,16022917474601379,16022917474601389,16022917474601399,160229174746013a9,160229174746013b9,160229174746013c9,160229174746013d9,160229174746013e9,160229174746013f9,16022917474601409,16022917474601419,16022917474601429,16022917474601439,16022917474601449,16022917474601459,16022917474601469,16022917474601479,16022917474601489,16022917474601499,160229174746014a9,160229174746014b9,160229174746014c9,160229174746014d9,160229174746014e9,160229174746014f9,16022917474601509,16022917474601519,16022917474601529,16022917474601539,16022917474601549,16022917474601559,16022917474601569,16022917474601579,16022917474601589,16022917474601599,160229174746015a9,160229174746015b9,160229174746015c9,160229174746015d9,160229174746015e9,160229174746015f9,16022917474601609,16022917474601619,16022917474601629,16022917474601639,16022917474601649,16022917474601659,16022917474601669,16022917474601679,16022917474601689,16022917474601699,160229174746016a9,160229174746016b9,160229174746016c9,160229174746016d9,160229174746016e9,160229174746016f9,16022917474601709,16022917474601719,16022917474601729,16022917474601739,16022917474601749,16022917474601759,16022917474601769,";
		String[] orderidArray=orderids.split(",");
		String[] orderidParams = {};
		System.out.println("orderidArray.length: "+orderidArray.length);
		List<String[]> tempList=new ArrayList<String[]>();
		if(orderidArray.length > 100){
//			int divisor=ValueUtil.toInt(Math.ceil(3.1));
			float divisor=(float)orderidArray.length/100;
			int divisorInt=ValueUtil.toInt(Math.ceil(divisor));
			int beforeIndex=0;

			for(int i=1;i<=divisorInt;i++){
				int index=i*100;
				if(i==1){
					orderidParams=ArrayUtils.subarray(orderidArray, beforeIndex, index);
				}else{
					beforeIndex=beforeIndex+1;
					orderidParams=ArrayUtils.subarray(orderidArray, beforeIndex, index);
				}
				orderidParams=ArrayUtils.subarray(orderidArray, beforeIndex, index);
				beforeIndex=index;
				tempList.add(orderidParams);
			}

//			System.out.println("tempList object: "+tempList);
			System.out.println("tempList size: "+tempList.size());

			System.out.println("tempList 1: "+tempList.get(0));
			System.out.println("tempList 2: "+tempList.get(1));
			System.out.println("tempList 3: "+tempList.get(2));
			System.out.println("tempList 4: "+tempList.get(3));
		}
*/
		/*
		Integer intHao = Integer.parseInt("000002");
	    intHao++;
	    DecimalFormat df = new DecimalFormat("000034");
	    System.out.println(df.format(intHao));

	    int i=4;
	    String str = String.format("%0"+i+"d", 1);
	    System.out.println(str); // 0001

	    double n= 0.0007;
	    DecimalFormat df1 = new DecimalFormat("0.0000");
	    System.out.println(df1.format(n));
	    */
		/*
		String[] a={"c","e","f","a","b","n","m"};
	    String[] b={"a","b"};
	    List<String> saveList=new ArrayList<String>();
	    for(String di:a){
	    	boolean flag=true;
			for(String mongo:b){
				if(ValueUtil.equals(di, mongo)){
					flag=false;
					break;
				}
			}
			if(flag)
				saveList.add(di);
		}
	    for(String s:saveList){
	    	System.out.println(s);
	    }
	    */

//		double num = 3.82;
//		System.out.println(Math.ceil(num));
//		double num1 = 3.0000000000001;
//		System.out.println(Math.ceil(num1));
//		double num2 = 3;
//		System.out.println(Math.ceil(num2));


//		double num=Math.floor(num);

		BigDecimal b1 = new BigDecimal(1);
		BigDecimal b2 = new BigDecimal(1);
		BigDecimal b3 = new BigDecimal(1);
		BigDecimal b4 = b1.multiply(b2).multiply(b3);
		BigDecimal b5 = new BigDecimal(6000);

//        System.out.println(b1.multiply(b2).multiply(b3).divide(b4, 2, BigDecimal.ROUND_HALF_UP).doubleValue());

		System.out.println(b4.divide(b5, 4, BigDecimal.ROUND_HALF_UP));
//		double calScale = (1 * 2 * 20) / 6000;
//		System.out.println(calScale);
//		BigDecimal calScaleBigDec = new BigDecimal(calScale);
//		calScale = calScaleBigDec.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//		System.out.println(calScale);

//		double i=2, j = 2.1, k = 2.49999999, m = 2.9;
//		System.out.println("舍掉小数取整:Math.floor(2)=" + (int) Math.floor(i));
//		System.out.println("舍掉小数取整:Math.floor(2.1)=" + (int) Math.floor(j));
//		System.out.println("舍掉小数取整:Math.floor(2.5)=" + (int) Math.floor(k));
//		System.out.println("舍掉小数取整:Math.floor(2.9)=" + (int)Math.floor(m));

		/* 这段被注释的代码不能正确的实现四舍五入取整*/
//		System.out.println("四舍五入取整:Math.rint(2)=" + Math.round(i));
//		System.out.println("四舍五入取整:Math.rint(2.1)=" + Math.round(j));
//		System.out.println("四舍五入取整:Math.rint(2.49)=" + Math.round(k));
//		System.out.println("四舍五入取整:Math.rint(2.9)=" + Math.round(m));
//		System.out.println(10 > 9);

		String line = "I_HAVE_AN_IPANG3_PIG";
		String camel = underline2Camel(line, true);
		System.out.println(camel);
		System.out.println(camel2Underline(camel));
	}

}
