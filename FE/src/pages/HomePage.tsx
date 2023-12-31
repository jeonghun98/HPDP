import React, { useState, useEffect } from "react";
import { LogoTopbar } from "../components/common/TopBar";
import HomeBanner from "../components/home/HomeBanner";
import FundingCardList from "../components/home/FundingCardList";
import SiteInfo from "../components/home/SiteInfo";
import style from "../styles/css/HomePage.module.css";
import * as Interfaces from "../interface/apiDataInterface";
import { getRecommendDeadline, getRecommendAchievement } from "../api/fundings";
import LoadingSpinner from "../components/common/LoadingSpinner";
import { useSelector } from "react-redux";

const HomePage = () => {
  const [fundingDeadlineData, setFundingDeadlineData] = useState<
    Interfaces.OutFundingsInfoInterface[]
  >([]);
  const [fundingAchievementData, setFundingAchievementData] = useState<
    Interfaces.OutFundingsInfoInterface[]
  >([]);
  const [isLoading, setIsLoading] = useState(true);
  const userName = useSelector((state: any) => state.user.info.name);
  const name = userName ? userName : "후원자";
  useEffect(() => {
    const firstSetting = async () => {
      await getRecommendDeadline(
        (res) => {
          setFundingDeadlineData(res.data.data);
        },
        (err) => {
          console.log(err);
        }
      );
      await getRecommendAchievement(
        (res) => {
          setFundingAchievementData(res.data.data);
        },
        (err) => {
          console.log(err);
        }
      );
    };
    firstSetting();
    setIsLoading(false);
  }, []);

  const top5FundingAchievementData = fundingAchievementData
    .slice()
    .sort((a, b) => b.totalFunding - a.totalFunding)
    .slice(0, 5);

  return isLoading ? (
    <div>
      <LogoTopbar />
      <LoadingSpinner />
    </div>
  ) : (
    <div>
      <LogoTopbar />
      <HomeBanner />
      <SiteInfo />
      <div className={style.introduce}>
        <div className={style.name}>{name}님</div>
        <div>새로운 펀딩에 참여해 보세요</div>
      </div>

      {fundingDeadlineData ? (
        <div>
          <div className={style.title}>마감이 얼마 남지 않았어요!</div>
          <FundingCardList items={fundingDeadlineData} />
        </div>
      ) : null}
      {top5FundingAchievementData.length > 0 ? (
        <div style={{ marginTop: "2rem", marginBottom: "1rem" }}>
          <div className={style.title}>인기가 많아요!</div>
          <FundingCardList items={top5FundingAchievementData} />
        </div>
      ) : null}
    </div>
  );
};

export default HomePage;
