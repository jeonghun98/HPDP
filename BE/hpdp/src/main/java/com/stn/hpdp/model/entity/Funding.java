package com.stn.hpdp.model.entity;

import com.stn.hpdp.common.enums.FundingState;
import com.stn.hpdp.controller.funding.request.UpdateFundingReq;
import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Funding extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_id")
    private Long id;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private String thumbnailUrl;
    private String contentUrl;
    private String hashtag;
    private String title;
    private int targetAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String rewardName;
    private int rewardPrice;
    private String rewardDesc;
    private String rewardImg;

    private FundingState state;
    private int settlement;
    private String docsUrl;

    // update method
    public void update(UpdateFundingReq updateFundingReq){
        this.hashtag = updateFundingReq.getHashtag();
        this.title = updateFundingReq.getTitle();
        this.targetAmount = Integer.parseInt(updateFundingReq.getTargetAmount());
        this.startDate = LocalDateTime.parse(updateFundingReq.getStartDate());
        this.endDate = LocalDateTime.parse(updateFundingReq.getEndDate());
        this.rewardName = updateFundingReq.getRewardName();
        this.rewardPrice = Integer.parseInt(updateFundingReq.getRewardPrice());
        this.rewardDesc = updateFundingReq.getRewardDesc();
    }

}
