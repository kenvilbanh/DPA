package com.fa.DPA.constant;

public class Constant {
    public static final String DEFAULT_NUM_PAGE = "0";
    public static final int DEFAULT_PAGE_SIZE = 10;

    public static final String ACTIVE = "active";
    public static final String DEACTIVE = "deactive";

    public static final String PENDING = "pending";
    public static final long ID_PENDING = 3;
    public static final String ACCOMPLISH = "accomplished";
    public static final long ID_ACCOMPLISH = 1;
    public static final String CANCEL = "canceled";
    public static final long ID_CANCEL = 2;
    public static final String PROCESS = "processing";
    public static final long ID_PROCESS = 4;

    public static final String URL_RESET_PASSWORD = "http://localhost:8080/forgot-password/change-password";

    public static final int LIMIT_ORDER_PROCESS = 5;
    public static final String LIMIT_ORDER_PROCESS_MESSAGE = "Can't receive any order due to reach your limit of processing";
}
