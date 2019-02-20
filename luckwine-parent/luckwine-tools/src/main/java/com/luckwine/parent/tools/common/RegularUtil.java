package com.luckwine.parent.tools.common;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Title: 正则表达式处理类
 * Description: 手动生成
 *
 * @version 1.0 初稿
 * @author: 麦豪俊
 * @date: 2016-2-29 19:29:39
 */
public class RegularUtil {
	public static final String EN_UNDERLINE = "([A-Za-z\\d]+)(_)?";

	public static final String EN = "[A-Z]([a-z\\d]+)?";

	//	数值
	public static final String NUMBER = "^(-?(?:\\d+|\\d{1,3}(?:,\\d{3})+)(?:\\.\\d+)?)|((-?\\d+.?\\d*)[Ee]{1}(-?\\d+))$";

	public static final String DIGITS = "^\\d+$";

	public static final String EMAIL = "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?$";

	public static final String URL = "^(https?|ftp):\\/\\/(((([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:)*@)?(((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]))|((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?)(:\\d*)?)(\\/((([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)+(\\/(([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)*)*)?)?(\\?((([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)|[\\uE000-\\uF8FF]|\\/|\\?)*)?(\\#((([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)|\\/|\\?)*)?$";

	public static final String DATE = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([2][0-3])|([1][0-9]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";

	public static final String DOUBLE = "[0-9]*(\\.?)[0-9]*";

	public static final String INT = "\"[0-9]+\"*";

	public static final String FILE_TYPE = "(jpg|gif|png|bmp|jpeg|txt|rar|zip|doc|docx|xlsx|xls|pdf|xql)";

	public static final String PHONE = "^((\\+?86)?((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))-?\\d{4}-?\\d{4})|((0\\d{3}-?)?\\d{8})|((0\\d{2}-?)?\\d{8})|((0\\d{3}-?)?\\d{7})|(400\\d{7})$";

	public static final String SERIAL_CODE = "\\\\n\\\\s*\\\\r";

	public static final String HTML = "<(\\S*?)[^>]*>.*?|<.*? />";

	public static final Pattern EN_PATTERN = Pattern.compile(EN);
	public static final Pattern EN_UNDERLINE_PATTERN = Pattern.compile(EN_UNDERLINE);
	public static final Pattern SERIAL_PATTERN = Pattern.compile(SERIAL_CODE);
	public static final Pattern FILE_PATTERN = Pattern.compile(FILE_TYPE);
	public static final Pattern PHONE_PATTERN = Pattern.compile(PHONE);
	public static final Pattern HTML_PATTERN = Pattern.compile(HTML);

	public static void main(String args[]) {
		//System.out.println("0.004".matches(NUMBER));
		System.out.println(isChinaPhoneLegal("14060318116"));
//		System.out.println("12a3".matches(DIGITS));
//		System.out.println("http://www.baidu......cocccccm".matches(URL));
//		System.out.println("baf@sdf.niq@qq.com".matches(EMAIL));


//		String json = "${user},";
//	    Map<String,String> tokens = new HashMap<String,String>(); 
//	    tokens.put("user", "Garfield"); 
//		System.out.println(regexReplace(json,tokens));
//		System.out.println(regexReplace(json,"user","like this ball"));

	}

	/**
	 * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
	 * 此方法中前三位格式有：
	 * 13+任意数
	 * 15+除4的任意数
	 * 18+除1和4的任意数
	 * 17+除9的任意数
	 * 147
	 */
	public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
//        String regExp = "^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9])|(147))\\d{8}$";  
		String regExp = "^(1[3-9][0-9])\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	public static String regexReplace(String template, Map<String, String> tokens) {
//	    //生成匹配模式的正则表达式 
//	    System.out.println(StringUtils.join(tokens.keySet(), "|"));
		String patternString = "\\$\\{(" + StringUtils.join(tokens.keySet(), "|") + ")\\}";

		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(template);

		//两个方法：appendReplacement, appendTail
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, tokens.get(matcher.group(1)));
		}
		matcher.appendTail(sb);

//	    System.out.println(sb.toString()); 
		return sb.toString();
	}


	public static String regexReplace(String template, String key, String value) {
//	    //生成匹配模式的正则表达式 
		String patternString = "\\$\\{(" + key + ")\\}";

		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(template);

		//两个方法：appendReplacement, appendTail
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, value);
		}
		matcher.appendTail(sb);

//	    System.out.println(sb.toString()); 
		return StringUtils.substring(sb.toString(), 0, sb.length() - 1);
	}

}
