package com.example.movie_backend.util;

import jakarta.servlet.http.HttpServletRequest;

public class ClientIpUtil {

    public static final String UNKNOWN = "unknown";

    ClientIpUtil() {
    }

    /**
     * Retrieves the actual client IP address by checking various HTTP headers
     * This method handles requests that may come through proxies or load balancers
     *
     * @param request The HttpServletRequest object containing request information
     * @return The client's IP address as a String
     */
    public static String getClientIp(HttpServletRequest request) {
        // Check X-Forwarded-For header first (standard header for identifying
        // originating IP)
        // Format: client, proxy1, proxy2, ...
        String ip = request.getHeader("X-Forwarded-For");

        // If X-Forwarded-For is not available, try Proxy-Client-IP
        // Used by some proxy servers
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        // Try WebLogic proxy header if previous attempts failed
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        // Check HTTP_CLIENT_IP header
        // Less common but sometimes used
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        // Try alternative forwarded header format
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        // If all proxy headers failed, get the direct remote address
        // This will be the actual connecting IP if no proxy is involved
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
