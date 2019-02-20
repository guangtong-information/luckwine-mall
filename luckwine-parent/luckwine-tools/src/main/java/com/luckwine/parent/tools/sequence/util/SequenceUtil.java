package com.luckwine.parent.tools.sequence.util;


import com.luckwine.parent.tools.sequence.enums.SequenceCode;
import com.luckwine.parent.tools.sequence.exception.SequenceException;
import sun.management.VMManagement;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成流水序列号, 目前多种序列号共用一个方法,以后考虑拆分,减轻并发带来的压力
 */
public class SequenceUtil {
    private static Object lock = new Object();
    /**
     * 进程号
     */
    private static int pid = 0;
    /**
     * 当前自增数
     */
    private static int index = 0;

    /**
     * 自增数最大999
     */
    private static final int MAX_INDEX = 999;

    static {
        try {
            //获取当前进程id
            RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
            Field jvm = runtime.getClass().getDeclaredField("jvm");
            jvm.setAccessible(true);
            VMManagement mgmt = (VMManagement) jvm.get(runtime);
            Method pidMethod = mgmt.getClass().getDeclaredMethod("getProcessId");
            pidMethod.setAccessible(true);
            pid = (Integer) pidMethod.invoke(mgmt);
        } catch (Exception e) {
            pid = 0;
        }
    }

    public static void main(String[] args) {
        System.out.println("余额账户号生成：" + genSequence(SequenceCode.ACCT, "1"));
        System.out.println("账户流水号：" + genSequence(SequenceCode.EXPENSE, "1"));
        System.out.println("主订单号生成：" + genSequence(SequenceCode.MAINORDER, "1"));
        System.out.println("子订单号生成：" + genSequence(SequenceCode.SUBORDER, "1"));
        System.out.println("商品单号生成：" + genSequence(SequenceCode.GOODSORDER, "1"));
        System.out.println("物流单号生成：" + genSequence(SequenceCode.WORKORDER, "1"));
        System.out.println("资金流水号生成：" + genSequence(SequenceCode.CAPITAL, "1"));
        System.out.println("支付流水号生成：" + genSequence(SequenceCode.PAYMENT, "1"));
    }

    /**
     * 生成流水号(业务编码2位+环境位1位+当前时间15位+当前进程号和线程号的后4位+3位自增序列)
     *
     * @param code 业务编码 2位
     * @param env  环境编码 1位, 查看枚举com.luckwine.parent.entitybase.enums.Env
     * @return
     * @throws SequenceException
     */
    public static String genSequence(SequenceCode code, String env) throws SequenceException {
        StringBuffer sb = new StringBuffer();
        sb.append(code.getCode());
        if (env == null) {
            env = "0";
        }
        sb.append(fixedLength(env, 1));

        //获取当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
        String nowDateStr = sdf.format(date);
        sb.append(nowDateStr);

        //获取当前线程id
        long tid = Thread.currentThread().getId();
        String idStr = String.valueOf(pid) + String.valueOf(tid);
        sb.append(fixedLength(idStr, 4));

        //获取当前自增数
        String counter = getCount();
        sb.append(fixedLength(counter, 3));

        return sb.toString();
    }

    /**
     * 生产固定位数的字符串
     *
     * @param idStr
     * @param length
     * @return
     */
    private static String fixedLength(String idStr, int length) {
        if (idStr.length() > length) {
            return idStr.substring(idStr.length() - length, idStr.length());
        }

        String nextIdStr = "";

        for (int i = 0; i < length - idStr.length(); i++) {
            nextIdStr += "0";
        }

        nextIdStr += idStr;
        return nextIdStr;
    }


    /**
     * 获取当前自增数
     *
     * @return
     */
    private static String getCount() {
        synchronized (lock) {
            ++index;
            if (index > MAX_INDEX) {
                index = 0;
            }
            return String.valueOf(index);
        }
    }
}
