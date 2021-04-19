package calculator;

import java.util.HashSet;

// https://en.wikipedia.org/wiki/List_of_UTC_time_offsets
public enum TimeZones {
    Y("UTC-1200"),
    X("UTC-1100"),
    SST("UTC-1100"),
    W("UTC-1000"),
    HST("UTC-1000"),
    VT("UTC-0930"),
    V("UTC-0900"),
    U("UTC-0800"),
    PST("UTC-0800"),
    HNP("UTC-0800"),
    T("UTC-0700"),
    MST("UTC-0700"),
    HNR("UTC-0700"),
    PDT("UTC-0700"),
    S("UTC-0600"),
    CST("UTC-0600"),
    HNC("UTC-0600"),
    R("UTC-0500"),
    HNE("UTC-0500"),
    Q("UTC-0400"),
    AST("UTC-0400"),
    HNA("UTC-0400"),
    EDT("UTC-0400"),
    PT("UTC-0330"),
    NST("UTC-0330"),
    HNT("UTC-0330"),
    P("UTC-0300"),
    O("UTC-0200"),
    N("UTC-0100"),
    CVT("UTC-0100"),
    Z("UTC+0000"),
    GMT("UTC+0000"),
    WET("UTC+0000"),
    A("UTC+0100"),
    BMT("UTC+0100"),
    CET("UTC+0100"),
    HNEC("UTC+0100"),
    MET("UTC+0100"),
    WAT("UTC+0100"),
    WEST("UTC+0100"),
    B("UTC+0200"),
    CAT("UTC+0200"),
    CEST("UTC+0200"),
    EET("UTC+0200"),
    HAEC("UTC+0200"),
    SAST("UTC+0200"),
    WAST("UTC+0200"),
    C("UTC+0300"),
    EAT("UTC+0300"),
    EEST("UTC+0300"),
    MSK("UTC+0300"),
    CT("UTC+0330"),
    D("UTC+0400"),
    SAMT("UTC+0400"),
    DT("UTC+0430"),
    E("UTC+0500"),
    YEKT("UTC+0500"),
    ET("UTC+0530"),
    ETT("UTC+0545"),
    F("UTC+0600"),
    OMST("UTC+0600"),
    FT("UTC+0630"),
    G("UTC+0700"),
    KRAT("UTC+0700"),
    H("UTC+0800"),
    AWST("UTC+0800"),
    IRKT("UTC+0800"),
    HTT("UTC+0845"),
    I("UTC+0900"),
    YAKT("UTC+0900"),
    IT("UTC+0930"),
    ACST("UTC+0930"),
    K("UTC+0100"),
    AEST("UTC+0100"),
    VLAT("UTC+0100"),
    KT("UTC+1030"),
    L("UTC+0110"),
    SRET("UTC+0110"),
    M("UTC+0120"),
    PETT("UTC+0120"),
    MTT("UTC+1245"),
    MT("UTC+0130"),
    Mt("UTC+0140");

    public final String label;

    TimeZones(String label){
        this.label = label;
    }

    public static HashSet<String> getTimeZones() {
        HashSet<String> values = new HashSet<>();
        for (TimeZones zone : TimeZones.values()) {
            values.add(zone.name());
        }
        return values;
    }
}
