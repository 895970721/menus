package com.crf.menu.enums;

public enum ExpTime {
    OneDay(86400000L),
    TwoDay(172800000L),
    OneWeek(604800000L),
    OneMonth(2592000000L);
    private Long exp;

    ExpTime(Long exp) {
        this.exp = exp;
    }

    public Long getExp() {
        return exp;
    }


}
