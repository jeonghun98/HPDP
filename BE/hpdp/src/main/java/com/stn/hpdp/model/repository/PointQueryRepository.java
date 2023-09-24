package com.stn.hpdp.model.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stn.hpdp.controller.blockchain.response.WalletRes;
import com.stn.hpdp.controller.point.response.FundingHistoryRes;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static com.stn.hpdp.model.entity.QFunding.funding;
import static com.stn.hpdp.model.entity.QFundingHistory.fundingHistory;
import static com.stn.hpdp.model.entity.QMember.member;
import static com.stn.hpdp.model.entity.QWallet.wallet;
import static org.springframework.data.jpa.domain.Specification.where;

@Repository
public class PointQueryRepository {

    private final JPAQueryFactory queryFactory;

    public PointQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    public List<FundingHistoryRes> getFundingHistoryByPeriod(LocalDate startDateTime, LocalDate endDateTime,String name){
        return queryFactory
                .select(Projections.constructor(FundingHistoryRes.class,
                        fundingHistory.funding.id,
                        fundingHistory.funding.title,
                        fundingHistory.price,
                        fundingHistory.createdDate))
                .from(fundingHistory)
                .join(fundingHistory.funding,funding)
                .join(fundingHistory.member,member)
                .where(fundingHistory.member.name.eq(name),
                        fundingHistory.createdDate.between(startDateTime.atStartOfDay(), endDateTime.atStartOfDay()))
                .fetch();
    }
}
