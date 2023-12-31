package com.stn.hpdp.model.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stn.hpdp.controller.company.response.FindCompanyRes;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.stn.hpdp.model.entity.QCompany.company;
import static com.stn.hpdp.model.entity.QInterest.interest;

@Repository
public class CompanyQueryRepository {

    private final JPAQueryFactory queryFactory;

    public CompanyQueryRepository(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<FindCompanyRes> findCompanyByKeyword(String keyword){
        return queryFactory
                .select(Projections.constructor(FindCompanyRes.class,
                        company.id.as("companyId"),
                        company.profile.as("profile"),
                        company.name.as("name"),
                        company.hashtag.as("hashtag")
                ))
                .from(company)
                .where(containKeyword(keyword))
                .orderBy(company.createdDate.asc())
                .fetch();
    }

    public List<FindCompanyRes> findCompanyByKeywordAndInterest(String keyword, Long memberId){
        return queryFactory
                .select(Projections.constructor(FindCompanyRes.class,
                        company.id.as("companyId"),
                        company.profile,
                        company.name,
                        company.hashtag,
                        interest.member.id.eq(memberId).as("isInterested")))
                .from(company)
                .leftJoin(interest)
                .on(company.id.eq(interest.company.id).and(interest.member.id.eq(memberId)))
                .where(containKeyword(keyword))
                .orderBy(company.createdDate.asc())
                .fetch();
    }

    private BooleanExpression containKeyword(String keyoword){
        if(keyoword == null || keyoword.isEmpty()){
            return null;
        }else{
            return company.name.containsIgnoreCase(keyoword).or(company.hashtag.containsIgnoreCase(keyoword));
        }
    }
}