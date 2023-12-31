package com.stn.hpdp.controller.alarm.response;

import com.stn.hpdp.model.entity.Company;
import com.stn.hpdp.model.entity.NewsAlarm;
import com.stn.hpdp.model.entity.PointAlarm;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AlarmRes {
    private long newsAlarmId;
    private String type;
    private int point;
    private Long fundingId;
    private String title;
    private String date;
    private long companyId;
    private String companyName;
    public static AlarmRes ofPoint(PointAlarm pointAlarm) {
        return AlarmRes.builder()
                .type(pointAlarm.getType().name())
                .point(pointAlarm.getPoint())
                .date(timeToString(pointAlarm.getCreatedDate()))
                .build();
    }

    public static AlarmRes ofNews(NewsAlarm alarm) {
        return AlarmRes.builder()
                .newsAlarmId(alarm.getId())
                .fundingId(alarm.getFunding().getId())
                .type(alarm.getType().name())
                .title(alarm.getTitle())
                .date(timeToString(alarm.getCreatedDate()))
                .companyId(alarm.getFunding().getCompany().getId())
                .companyName(alarm.getFunding().getCompany().getName())
                .build();
    }
    public static String timeToString(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss"));
    }
}