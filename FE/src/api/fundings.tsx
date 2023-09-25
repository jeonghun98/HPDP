import { AxiosResponse } from "axios";
import { customApi, customApiForm } from ".";

export async function getFundingTotalList(
  success: (
    res: AxiosResponse<any, any>
  ) =>
    | AxiosResponse<any, any>
    | PromiseLike<AxiosResponse<any, any>>
    | null
    | undefined
    | void,
  fail: (err: any) => PromiseLike<never> | null | undefined | void
) {
  await customApi("fundings").get("").then(success).catch(fail);
}

export async function getFundingProgress(
  companyid: number | null,
  done: number | null,
  success: (
    res: AxiosResponse<any, any>
  ) =>
    | AxiosResponse<any, any>
    | PromiseLike<AxiosResponse<any, any>>
    | null
    | undefined
    | void,
  fail: (err: any) => PromiseLike<never> | null | undefined | void
) {
  let apiUrl = "";
  apiUrl += `?companyId=${companyid}`;
  apiUrl += `&done=${done}`;
  await customApi("fundings").get(apiUrl).then(success).catch(fail);
}

export async function getFundingDetail(
  fundingid: number,
  success: (
    res: AxiosResponse<any, any>
  ) =>
    | AxiosResponse<any, any>
    | PromiseLike<AxiosResponse<any, any>>
    | null
    | undefined
    | void,
  fail: (err: any) => PromiseLike<never> | null | undefined | void
) {
  await customApi("fundings").get(`/${fundingid}`).then(success).catch(fail);
}