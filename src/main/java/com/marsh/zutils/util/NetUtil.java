package com.marsh.zutils.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;


/**
 * 网络工具类
 *
 * @author lucky
 * @version 1.0
 */
public class NetUtil {

    private static final Logger log = LoggerFactory.getLogger(NetUtil.class);

    private final static String LOCALHOST_IPV4 = "127.0.0.1";
    private final static String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

    private NetUtil() {

    }

    /**
     * get  remote real ip
     *
     * @param request servlet com.anchong.charging.api.item.request
     * @return ip str like 127.0.0.1
     */
    public static String realIp(HttpServletRequest request) {

        String ipAddress = request.getHeader("X-Forwarded-For");
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    ipAddress = inetAddress.getHostAddress();
                } catch (UnknownHostException e) {
                    log.error("unknown host exception:{}", e.getMessage());
                }
            }
        }

        if (!StringUtils.isEmpty(ipAddress)
            && ipAddress.length() > 15
            && ipAddress.indexOf(",") > 0) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }

        return ipAddress;
    }

    /**
     * get local machine's ip
     *
     * @param filterIp6: include ip6?
     * @return
     */
    public static List<String> getHostAddresses(Boolean filterIp6) {
        List<String> result = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> address = networkInterface.getInetAddresses();
                while (address.hasMoreElements()) {
                    InetAddress add = address.nextElement();
                    if (filterIp6 == null || filterIp6.booleanValue() == true) {
                        if (add.getHostAddress().contains(":")) {
                            continue;
                        }
                    }
                    result.add(add.getHostAddress());
                }
            }
        } catch (SocketException e) {
            log.error(e.getMessage());
        }
        return result;
    }


    /**
     * long number to ip, only support ipv4
     *
     * @param number long
     * @return ip str like 127.0.0.1
     */
    public static String numberToIp(long number) {
        StringBuilder ip = new StringBuilder();
        for (int i = 3; i >= 0; i--) {
            ip.append((number & 0xff));
            if (i != 0) {
                ip.append(".");
            }
            number = number >> 8;
        }
        String[] ipArr = ip.toString().split("\\.");
        List<String> list = Arrays.asList(ipArr);
        Collections.reverse(list);
        return list.stream().reduce((a, b) -> a = a + "." + b).orElse(null);
    }

    /**
     * ip convert to long number only support ipv4
     *
     * @param ip str like 127.0.0.1
     * @return number
     */
    public static long ipToNumber(String ip) {
        String[] ipArray = ip.split("\\.");
        List<Long> ipNums = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            ipNums.add(Long.parseLong(ipArray[i].trim()));
        }
        return ipNums.get(0) * 256L * 256L * 256L
               + ipNums.get(1) * 256L * 256L + ipNums.get(2) * 256L
               + ipNums.get(3);

    }


}

