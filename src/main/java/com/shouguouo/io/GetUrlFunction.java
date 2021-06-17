package com.shouguouo.io;

import com.google.common.collect.Lists;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.shouguouo.io.FindPrefixURL.checkUrl;

/**
 * @author shouguouo~
 * @date 2020/6/8 - 16:12
 */
public class GetUrlFunction {

    public static final String SQL_TEMPLATE = "insert into tsys_subtrans_relurl (URL, URL_NAME, TRANS_CODE, SUB_TRANS_CODE, URL_PARAM, REMARK)\n" +
        "values ('%s', '%s#%s', '%s', '%s', null, '默认名');\n";
    public static void main(String[] args) {
        File file = new File("F:\\xbrl-web-6.0\\src\\biz\\xbrl\\views\\reportmanagement");
        Map<String, List<String>> resMap = new HashMap<>();
        try {
            recurFindFile(file, resMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int count = 0;
        System.out.println(resMap.size() + ": " + resMap.values().stream().mapToLong(List::size).sum());
        for (Map.Entry<String, List<String>> entry : resMap.entrySet()) {
            if (menu.contains(entry.getKey())) {
                System.out.println("------------------------------------" + entry.getKey() + "------------------------------------");
                entry.getValue().forEach(x -> System.out.println(String.format(SQL_TEMPLATE, x, entry.getKey(), entry.getKey(), entry.getKey(), entry.getKey())));
            }
        }
        System.out.println("not contain");
        for (Map.Entry<String, List<String>> entry : resMap.entrySet()) {
            if (!menu.contains(entry.getKey())) {
                System.out.println("------------------------------------" + entry.getKey() + "------------------------------------");
                entry.getValue().forEach(x -> System.out.println(String.format(SQL_TEMPLATE, x, entry.getKey(), entry.getKey(), entry.getKey(), entry.getKey())));
            }
        }
    }
    public static void recurFindFile(File file, Map<String, List<String>> resMap) throws IOException {
        if (file.exists()) {
            if (file.isFile()) {
                checkUrl(file, resMap);
            } else {
                File[] subFiles = file.listFiles();
                if (subFiles != null) {
                    for (File subFile : subFiles) {
                        recurFindFile(subFile, resMap);
                    }
                }

            }
        }
    }


    public static final List<String> menu = Lists.newArrayList("verifyAndSign","financeindex_datasetInfo","financeindex_indexcenter","xbrl_templateManager","basicset_xbrlCustomIndex","gmjj","xbrl_generateNet","xbrl_reportManager","basicset_xbrlPathSet","xbrl","zgcp","xbrl_privateSecurities","smjj","xbrl_privateEquity","xbrl_fundSpecialAccount","xbrl_instancebase","stockCertificateInfo","depositoryReceiptSecurityInfo","bondSecurityInfo","repoSecurityInfo","fundSecurityInfo","shareWarrentSecurityInfo","bondPreReleaseSecurityInfo","stockIndexFuturesInfo","commodityFutureInfo","treasuryFuturesInfo","foreignExchangeFuturesInfo","preciousMetalSecurityInfo","domesticOptionSecurity","financialProductSecurity","abroadOptionSecurity","interbankRefinanceSecurity","interbankBorrowingSecurity","structuredNoteSecurity","interbankLendingSecurity","bondDefaultInfo","preferredStockSecurity","unlisteDequitySecurity","collectInformation","dxCustomerInfo","dxCustomerSituation","financialData","datainterface","djSyscustom","datasourceMaintain","etlJob","etlJobLogs","dataImport","basicset","workbench_tradingCalendar","fundFilterSet","fundGroupInfo","dataPermissionSet","dictionaryMaintain","basicdata","productDataManagement","fundBasicInformation","fundBasicInfo","fundStandardInfo","fundGradeInfo","fundOpendaySet","fundDividendInfo","basicdata_fundRelatedinfo","organization","managersInformation","trusteeInformation","financeindex","financeindex_datasourceInfo","counterpartyInformation","issuerInformation","fundAmaldarInformation","basicdata_thirdPartyInstitution","securityInfo","accountBalanceInfo");

}
