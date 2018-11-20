package com.hl.contract.bean;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ArrayAdapter;

import com.hl.core.lib.bean.TypeItem;
import com.hl.contract.R;
import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.model.DictInfo;
import com.hl.contract.table.model.Insurance;
import com.hl.contract.util.SurveyClaimUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public final class StaticCode {

    private static StaticCode instance;

    public static StaticCode getInstance() {
        if (instance == null) {
            instance = new StaticCode();
        }
        return instance;
    }

    private StaticCode() {
    }

    public ArrayAdapter<TypeItem> getAdapter(Context context, ArrayList<TypeItem> list) {
        if (list == null) {
            return null;
        }
        ArrayAdapter<TypeItem> adapter = new ArrayAdapter<TypeItem>(context, R.layout.survey_spinner_new_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }


    //车辆用途
    public ArrayList<TypeItem> getUsePropertyTypeList() {
        ArrayList<TypeItem> surveyTypeList = new ArrayList<>();
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.CODE_01, SurveyApplication.get().getResources().getString(R.string.survey_use_property_jiayong)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.CODE_02, SurveyApplication.get().getResources().getString(R.string.survey_use_property_passenger_car)));

        return surveyTypeList;
    }

    //与车主关系
    public ArrayList<TypeItem> getRelationTypeList() {
        ArrayList<TypeItem> surveyTypeList = new ArrayList<>();
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.CODE_00, SurveyApplication.get().getResources().getString(R.string.survey_text_choose)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.CODE_01, SurveyApplication.get().getResources().getString(R.string.survey_relation_is_owner)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.CODE_02, SurveyApplication.get().getResources().getString(R.string.survey_relation_is_not_owner)));

        return surveyTypeList;
    }

    //保险公司
    public ArrayList<TypeItem> getCommercialCompanyTypeList() {
        ArrayList<TypeItem> surveyTypeList = new ArrayList<>();
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_PICC, SurveyApplication.get().getResources().getString(R.string.survey_company_type_picc)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_PAIC, SurveyApplication.get().getResources().getString(R.string.survey_company_type_paic)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_CPIC, SurveyApplication.get().getResources().getString(R.string.survey_company_type_cpic)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_GPIC, SurveyApplication.get().getResources().getString(R.string.survey_company_type_gpic)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_CCIC, SurveyApplication.get().getResources().getString(R.string.survey_company_type_ccic)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_CICP, SurveyApplication.get().getResources().getString(R.string.survey_company_type_cicp)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_YGBX, SurveyApplication.get().getResources().getString(R.string.survey_company_type_ygbx)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_TPBX, SurveyApplication.get().getResources().getString(R.string.survey_company_type_tpbx)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_AXBX, SurveyApplication.get().getResources().getString(R.string.survey_company_type_axbx)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_ABIC, SurveyApplication.get().getResources().getString(R.string.survey_company_type_abic)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_AICS, SurveyApplication.get().getResources().getString(R.string.survey_company_type_aics)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_AZCN, SurveyApplication.get().getResources().getString(R.string.survey_company_type_azcn)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_BGIC, SurveyApplication.get().getResources().getString(R.string.survey_company_type_bgic)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_BOCI, SurveyApplication.get().getResources().getString(R.string.survey_company_type_boci)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_CAIC, SurveyApplication.get().getResources().getString(R.string.survey_company_type_caic)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_CHAC, SurveyApplication.get().getResources().getString(R.string.survey_company_type_chac)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_HAIC, SurveyApplication.get().getResources().getString(R.string.survey_company_type_haic)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_HTIC, SurveyApplication.get().getResources().getString(R.string.survey_company_type_htic)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_LIHI, SurveyApplication.get().getResources().getString(R.string.survey_company_type_lihi)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_SPIC, SurveyApplication.get().getResources().getString(R.string.survey_company_type_spic)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_TAIC, SurveyApplication.get().getResources().getString(R.string.survey_company_type_taic)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_TPIC, SurveyApplication.get().getResources().getString(R.string.survey_company_type_tpic)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_XDCX, SurveyApplication.get().getResources().getString(R.string.survey_company_type_xdcx)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_YAIC, SurveyApplication.get().getResources().getString(R.string.survey_company_type_yaic)));
        surveyTypeList.add(new TypeItem(SurveyClaimUtil.COMPANY_TYPE_CODE_ZAPA, SurveyApplication.get().getResources().getString(R.string.survey_company_type_zapa)));


        return surveyTypeList;
    }

    /***
     * 证件类型
     * @return
     */
    public ArrayList<TypeItem> getCertificateTypeList() {
        ArrayList<TypeItem> certificateTypeList = new ArrayList<>();
        certificateTypeList.add(new TypeItem(SurveyClaimUtil.CREDENTIALS_01, SurveyApplication.get().getResources().getString(R.string.survey_text_identity_card)));
//		certificateTypeList.add(new TypeItem(SurveyClaimUtil.CREDENTIALS_02,SurveyApplication.get().getResources().getString(R.string.survey_text_credentials_2)));
        certificateTypeList.add(new TypeItem(SurveyClaimUtil.CREDENTIALS_03, SurveyApplication.get().getResources().getString(R.string.survey_text_credentials_3)));
        certificateTypeList.add(new TypeItem(SurveyClaimUtil.CREDENTIALS_04, SurveyApplication.get().getResources().getString(R.string.survey_text_credentials_4)));
//		certificateTypeList.add(new TypeItem(SurveyClaimUtil.CREDENTIALS_05,SurveyApplication.get().getResources().getString(R.string.survey_text_credentials_5)));
//		certificateTypeList.add(new TypeItem(SurveyClaimUtil.CREDENTIALS_06,SurveyApplication.get().getResources().getString(R.string.survey_text_credentials_6)));
//		certificateTypeList.add(new TypeItem(SurveyClaimUtil.CREDENTIALS_07,SurveyApplication.get().getResources().getString(R.string.survey_text_credentials_7)));
//		certificateTypeList.add(new TypeItem(SurveyClaimUtil.CREDENTIALS_08,SurveyApplication.get().getResources().getString(R.string.survey_text_credentials_8)));
        certificateTypeList.add(new TypeItem(SurveyClaimUtil.CREDENTIALS_41, SurveyApplication.get().getResources().getString(R.string.survey_text_credentials_41)));
//		certificateTypeList.add(new TypeItem(SurveyClaimUtil.CREDENTIALS_42,SurveyApplication.get().getResources().getString(R.string.survey_text_credentials_42)));
//		certificateTypeList.add(new TypeItem(SurveyClaimUtil.CREDENTIALS_51,SurveyApplication.get().getResources().getString(R.string.survey_text_credentials_51)));
//		certificateTypeList.add(new TypeItem(SurveyClaimUtil.CREDENTIALS_52,SurveyApplication.get().getResources().getString(R.string.survey_text_credentials_52)));
//		certificateTypeList.add(new TypeItem(SurveyClaimUtil.CREDENTIALS_53,SurveyApplication.get().getResources().getString(R.string.survey_text_credentials_53)));
        certificateTypeList.add(new TypeItem(SurveyClaimUtil.CREDENTIALS_71, SurveyApplication.get().getResources().getString(R.string.survey_text_credentials_71)));
//		certificateTypeList.add(new TypeItem(SurveyClaimUtil.CREDENTIALS_72,SurveyApplication.get().getResources().getString(R.string.survey_text_credentials_72)));
//		certificateTypeList.add(new TypeItem(SurveyClaimUtil.CREDENTIALS_73,SurveyApplication.get().getResources().getString(R.string.survey_text_credentials_73)));
//		certificateTypeList.add(new TypeItem(SurveyClaimUtil.CREDENTIALS_99,SurveyApplication.get().getResources().getString(R.string.survey_text_credentials_99)));
        return certificateTypeList;
    }
    /***
     * 产品类型
     * @return
     */
    public ArrayList<TypeItem> getProductTypeList() {
        ArrayList<TypeItem> productTypeList = new ArrayList<>();
        productTypeList.add(new TypeItem(SurveyClaimUtil.PRODUCT_01, SurveyApplication.get().getResources().getString(R.string.survey_text_product_type_1)));
        productTypeList.add(new TypeItem(SurveyClaimUtil.PRODUCT_02, SurveyApplication.get().getResources().getString(R.string.survey_text_product_type_2)));

        return productTypeList;
    }
    /***
     * 服务年限
     * @return
     */
    public ArrayList<TypeItem> getValidityList() {
        ArrayList<TypeItem> serviceValidityTypeDictList = new ArrayList<>();
        serviceValidityTypeDictList.add(new TypeItem(SurveyClaimUtil.SERVICE_VALIDITTY_TWO, SurveyApplication.get().getResources().getString(R.string.survey_service_validity_two)));
        serviceValidityTypeDictList.add(new TypeItem(SurveyClaimUtil.SERVICE_VALIDITTY_THREE, SurveyApplication.get().getResources().getString(R.string.survey_service_validity_three)));
        serviceValidityTypeDictList.add(new TypeItem(SurveyClaimUtil.SERVICE_VALIDITTY_FOUR, SurveyApplication.get().getResources().getString(R.string.survey_service_validity_four)));
        serviceValidityTypeDictList.add(new TypeItem(SurveyClaimUtil.SERVICE_VALIDITTY_FIVE, SurveyApplication.get().getResources().getString(R.string.survey_service_validity_five)));
        serviceValidityTypeDictList.add(new TypeItem(SurveyClaimUtil.SERVICE_VALIDITTY_SIX, SurveyApplication.get().getResources().getString(R.string.survey_service_validity_six)));
        return serviceValidityTypeDictList;
    }
    /***
     * 服务年限
     * @return
     */
    public ArrayList<TypeItem> getValidityList2() {
        ArrayList<TypeItem> serviceValidityTypeDictList = new ArrayList<>();
       // serviceValidityTypeDictList.add(new TypeItem(SurveyClaimUtil.SERVICE_VALIDITTY_TWO, SurveyApplication.get().getResources().getString(R.string.survey_service_validity_two)));
        serviceValidityTypeDictList.add(new TypeItem(SurveyClaimUtil.SERVICE_VALIDITTY_THREE, SurveyApplication.get().getResources().getString(R.string.survey_service_validity_three2)));
        serviceValidityTypeDictList.add(new TypeItem(SurveyClaimUtil.SERVICE_VALIDITTY_FOUR, SurveyApplication.get().getResources().getString(R.string.survey_service_validity_four2)));
        serviceValidityTypeDictList.add(new TypeItem(SurveyClaimUtil.SERVICE_VALIDITTY_FIVE, SurveyApplication.get().getResources().getString(R.string.survey_service_validity_five2)));
        serviceValidityTypeDictList.add(new TypeItem(SurveyClaimUtil.SERVICE_VALIDITTY_SIX, SurveyApplication.get().getResources().getString(R.string.survey_service_validity_six2)));
        return serviceValidityTypeDictList;
    }
    /***
     * 号牌类型
     * @return
     */
    public ArrayList<TypeItem> getPlateTypeList() {
        ArrayList<TypeItem> plateTypeList = new ArrayList<>();
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_1, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type01)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_2, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type02)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_3, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type03)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_4, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type04)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_5, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type05)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_6, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type06)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_7, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type07)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_8, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type08)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_9, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type09)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_10, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type10)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_11, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type11)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_12, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type12)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_13, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type13)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_14, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type14)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_15, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type15)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_16, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type16)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_17, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type17)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_18, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type18)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_19, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type18)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_20, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type20)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_21, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type21)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_22, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type22)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_23, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type23)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_24, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type24)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_31, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type31)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_32, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type32)));
        plateTypeList.add(new TypeItem(SurveyClaimUtil.PLATE_TYPE_99, SurveyApplication.get().getResources().getString(R.string.survey_text_plate_type99)));
        return plateTypeList;
    }


    public String getValue(ArrayList<TypeItem> list, String id) {
        if (!TextUtils.isEmpty(id) && list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (id.equals(list.get(i).getID())) {
                    return list.get(i).getValue();
                }
            }
            return list.get(0).getValue();
        }

        return "-";
    }

    public static String getValueByCode(List<TypeItem> list, String code) {
        if (!TextUtils.isEmpty(code) && list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (code.equals(list.get(i).getCode())) {
                    return list.get(i).getValue();
                }
            }
        }

        return "-";
    }

    public ArrayList<TypeItem> getList(String key, ArrayList<TypeItem> list) {
        boolean allflag = true;
        ArrayList<TypeItem> arrayList = new ArrayList<TypeItem>();
        if (list != null && list.size() > 0 && (key != null && !"".equals(key))) {

            for (TypeItem item : list) {
                if (key.equals(item.getRemark())) {
                    arrayList.add(item);
                    allflag = false;
                }
            }
            if (allflag) {
                arrayList.addAll(list);
            }
        } else {
            arrayList.addAll(list);
        }
        return arrayList;
    }

    public ArrayList<TypeItem> removeEmpty(ArrayList<TypeItem> list) {
        ArrayList<TypeItem> arrayList = new ArrayList<TypeItem>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                TypeItem spinnerItem = list.get(i);
                if (spinnerItem.getID().equals("")) {
                    list.remove(i);
                }
            }
        }
        return list;
    }

    public String getValue(ArrayList<TypeItem> list, int id) {
        int i = 0;
        if (list == null) {
        }

        int size = list.size();
        for (; i < size; i++) {
            if (id == list.get(i).id) {
                return list.get(i).getValue();
            }
        }
        return "-";
    }

    public String getValueByIndex(ArrayList<TypeItem> list, int index) {
        int i = 0;
        if (list == null) {
        }

        int size = list.size();
        for (; i < size; i++) {
            if (i == index) {
                return list.get(i).getValue();
            }
        }
        return "-";
    }

    public String getCodeById(ArrayList<TypeItem> list, String id) {
        int i = 0;
        if (list == null) {
        }

        int size = list.size();
        for (; i < size; i++) {
            TypeItem item = list.get(i);
            if (id.equals(item.getID())) {
                return item.getRemark();
            }
        }
        return "0";
    }

    /**
     * @param list
     * @param id
     * @return
     */
    public int getIndex(ArrayList<TypeItem> list, String id) {
        int i = 0;
        if (!TextUtils.isEmpty(id) && list != null) {
            int size = list.size();
            for (; i < size; i++) {
                if (id.equals(list.get(i).getID())) {
                    return i;
                }
            }
        }
        return 0;
    }

    public int getIndexByValue(ArrayList<TypeItem> list, String value) {
        int i = 0;
        if (!TextUtils.isEmpty(value) && list != null) {
            int size = list.size();
            for (; i < size; i++) {
                if (value.equals(list.get(i).getValue())) {
                    return i;
                }
            }
        }
        return 0;
    }

    public int getIndexByUnsureValue(ArrayList<TypeItem> list, String value) {
        int i = 0;
        if (!TextUtils.isEmpty(value) && list != null) {
            int size = list.size();
            for (; i < size; i++) {
                if (list.get(i).getValue().contains(value)) {
                    return i;
                }
            }
        }
        return 0;
    }

    public int getIndex(ArrayList<TypeItem> list, int id) {
        if (list == null) {
        }

        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (id == list.get(i).id) {
                return i;
            }
        }
        return id;
    }


    public String[] getValues(ArrayList<TypeItem> list) {
        if (list == null) {
        }

        int size = list.size();
        String[] values = new String[size];
        for (int i = 0; i < size; i++) {
            values[i] = list.get(i).getValue();
        }
        return values;
    }

    public String getValues(ArrayList<TypeItem> list, String ids) {
        if (list == null) {
        }
        String values = "";
        if (ids != null && !"".equals(ids)) {
            String[] split = ids.split(",");
            int size = list.size();
            for (int j = 0; j < split.length; j++) {
                for (int i = 0; i < size; i++) {
                    if (list.get(i).getID().equals(split[j])) {
                        values = values + list.get(i).getValue() + ",";
                    }
                }
            }
        } else {
            return values;
        }
        return values.substring(0, values.lastIndexOf(","));
    }

    public String[] getIds(ArrayList<TypeItem> list) {
        if (list == null) {
        }

        int size = list.size();
        String[] ids = new String[size];
        for (int i = 0; i < size; i++) {
            ids[i] = list.get(i).getID();
        }
        return ids;
    }

    public String[] getCodes(ArrayList<TypeItem> list) {
        if (list == null) {
        }

        int size = list.size();
        String[] ids = new String[size];
        for (int i = 0; i < size; i++) {
            ids[i] = list.get(i).getRemark();
        }
        return ids;
    }

    boolean[] selected = null;

    public boolean[] getSelected(ArrayList<TypeItem> list,
                                 String[] ids) {
        if (list == null) {
        }

        int size = list.size();
        if (selected == null)
            selected = new boolean[size];

        for (int i = 0; i < size; i++) {
            String id = list.get(i).getID();
            selected[i] = false;
            if (ids != null && ids.length != 0) {
                for (int j = 0; j < ids.length; j++) {
                    if (id.equals(ids[j])) {
                        selected[i] = true;
                    }
                }
            }

        }
        return selected;
    }

    public boolean[] getSelected(int size) {
        if (selected == null)
            selected = new boolean[size];

        return selected;
    }

    public void setSelect(int index, boolean value) {
        if (selected != null) {
            selected[index] = value;
        }
    }


//	public  ArrayAdapter<TypeItem> getAdapter(Context context, ArrayList<TypeItem> list) {
//		if (list == null) {
//		}
//		ArrayAdapter<TypeItem> adapter = new ArrayAdapter<TypeItem>(context, R.layout.spinner_new_item, list);
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		return adapter;
//	}

    public static List<TypeItem> getTypeItemsByDictInfo(List<DictInfo> dictInfoList) {
        List<TypeItem> typeItemList = null;
        if (dictInfoList != null && dictInfoList.size() > 0) {
            typeItemList = new ArrayList<>();
            TypeItem typeItem = null;
            for (DictInfo dictInfo : dictInfoList) {
                typeItem = new TypeItem(dictInfo.getKey(), dictInfo.getSeedKey(), dictInfo.getTypeCode(), dictInfo.getTypeName());
                typeItemList.add(typeItem);
            }
        }
        return typeItemList;
    }

    public List<TypeItem> getTypeItemsByInsuranceInfo(List<Insurance> insuranceList) {
        List<TypeItem> typeItemList = null;
        if (insuranceList != null && insuranceList.size() > 0) {
            typeItemList = new ArrayList<>();
            TypeItem typeItem = null;
            for (Insurance insurance : insuranceList) {
                typeItem = new TypeItem(insurance.getItemCode(), insurance.getItemName());
                typeItemList.add(typeItem);
            }
        }
        return typeItemList;
    }

    private LinkedHashMap<String, String> bankProvinceMap = new LinkedHashMap<String, String>();
    private LinkedHashMap<String, String> bankCityMap = new LinkedHashMap<String, String>();
    private LinkedHashMap<String, String> moneyTypeMap = new LinkedHashMap<String, String>();
    private LinkedHashMap<String, String> bankPayTypeMap = new LinkedHashMap<String, String>();

    public LinkedHashMap<String, String> getBankProvinceMap() {
        initPaymentData();
        return bankProvinceMap;
    }

    public LinkedHashMap<String, String> getBankCityMap() {
        initPaymentData();
        return bankCityMap;
    }

    public LinkedHashMap<String, String> getMoneyTypeMap() {
        initPaymentData();
        return moneyTypeMap;
    }

    public LinkedHashMap<String, String> getBankPayTypeMap() {
        initPaymentData();
        return bankPayTypeMap;
    }

    public static String getTypeValueByCode(List<TypeItem> typeItemList, String code) {
        String name = "";
        for (TypeItem typeItem : typeItemList) {
            if (typeItem.getTypeCode().equals(code)) {
                name = typeItem.getValue();
                break;
            }
        }
        return name;
    }

    public static String getTypeValueByID(List<TypeItem> typeItemList, String ID) {
        String name = "";
        for (TypeItem typeItem : typeItemList) {
            if (typeItem.getID().equals(ID)) {
                name = typeItem.getValue();
                break;
            }
        }
        return name;
    }

    void initPaymentData() {
        // 支付信息	币种
        moneyTypeMap.put("1550001", "人民币");
        moneyTypeMap.put("1550002", "美元");
        moneyTypeMap.put("1550003", "日元");
        moneyTypeMap.put("1550004", "港元");
        moneyTypeMap.put("1550005", "台币");
        moneyTypeMap.put("1550006", "欧元");
        moneyTypeMap.put("1550007", "其他");

        // 支付信息	支付类型
//		bankPayTypeList.add(("", "请选择"));
        bankPayTypeMap.put("1520002", "网银转账");
        bankPayTypeMap.put("1520004", "待定");


        // 省直辖市名
        bankProvinceMap.put("4100001", "北京市");
        bankProvinceMap.put("4100002", "天津市");
        bankProvinceMap.put("4100003", "河北省");
        bankProvinceMap.put("4100004", "山西省");
        bankProvinceMap.put("4100005", "内蒙古自治区");
        bankProvinceMap.put("4100006", "辽宁省");
        bankProvinceMap.put("4100007", "吉林省");
        bankProvinceMap.put("4100008", "黑龙江省");
        bankProvinceMap.put("4100009", "上海市");
        bankProvinceMap.put("4100010", "江苏省");
        bankProvinceMap.put("4100011", "浙江省");
        bankProvinceMap.put("4100012", "安徽省");
        bankProvinceMap.put("4100013", "福建省");
        bankProvinceMap.put("4100014", "江西省");
        bankProvinceMap.put("4100015", "山东省");
        bankProvinceMap.put("4100016", "河南省");
        bankProvinceMap.put("4100017", "湖北省");
        bankProvinceMap.put("4100018", "湖南省");
        bankProvinceMap.put("4100019", "广东省");
        bankProvinceMap.put("4100020", "广西壮族自治区");
        bankProvinceMap.put("4100021", "海南省");
        bankProvinceMap.put("4100022", "重庆市");
        bankProvinceMap.put("4100023", "四川省");
        bankProvinceMap.put("4100024", "贵州省");
        bankProvinceMap.put("4100025", "云南省");
        bankProvinceMap.put("4100026", "西藏自治区");
        bankProvinceMap.put("4100027", "陕西省");
        bankProvinceMap.put("4100031", "甘肃省");
        bankProvinceMap.put("4100032", "青海省");
        bankProvinceMap.put("4100033", "宁夏回族自治区");
        bankProvinceMap.put("4100034", "新疆维吾尔自治区");


        bankCityMap.put("北京市", "4100001");
        bankCityMap.put("天津市", "4100002");
        bankCityMap.put("石家庄市", "4100003");//河北省
        bankCityMap.put("辛集市", "4100003");
        bankCityMap.put("藁城市", "4100003");
        bankCityMap.put("晋州市", "4100003");
        bankCityMap.put("新乐市", "4100003");
        bankCityMap.put("鹿泉市", "4100003");
        bankCityMap.put("唐山市", "4100003");
        bankCityMap.put("遵化市", "4100003");
        bankCityMap.put("迁安市", "4100003");
        bankCityMap.put("秦皇岛市", "4100003");
        bankCityMap.put("邯郸市", "4100003");
        bankCityMap.put("武安市", "4100003");
        bankCityMap.put("邢台市", "4100003");
        bankCityMap.put("南宫市", "4100003");
        bankCityMap.put("沙河市", "4100003");
        bankCityMap.put("保定市", "4100003");
        bankCityMap.put("涿州市", "4100003");
        bankCityMap.put("定州市", "4100003");
        bankCityMap.put("安国市", "4100003");
        bankCityMap.put("高碑店市", "4100003");
        bankCityMap.put("张家口市", "4100003");
        bankCityMap.put("承德市", "4100003");
        bankCityMap.put("沧州市", "4100003");
        bankCityMap.put("泊头市", "4100003");
        bankCityMap.put("任丘市", "4100003");
        bankCityMap.put("黄骅市", "4100003");
        bankCityMap.put("河间市", "4100003");
        bankCityMap.put("廊坊市", "4100003");
        bankCityMap.put("霸州市", "4100003");
        bankCityMap.put("三河市", "4100003");
        bankCityMap.put("衡水市", "4100003");
        bankCityMap.put("冀州市", "4100003");
        bankCityMap.put("深州市", "4100003");
        bankCityMap.put("太原市", "4100004");//山西省
        bankCityMap.put("古交市", "4100004");
        bankCityMap.put("大同市", "4100004");
        bankCityMap.put("阳泉市", "4100004");
        bankCityMap.put("长治市", "4100004");
        bankCityMap.put("潞城市", "4100004");
        bankCityMap.put("晋城市", "4100004");
        bankCityMap.put("高平市", "4100004");
        bankCityMap.put("朔州市", "4100004");
        bankCityMap.put("晋中市", "4100004");
        bankCityMap.put("介休市", "4100004");
        bankCityMap.put("运城市", "4100004");
        bankCityMap.put("永济市", "4100004");
        bankCityMap.put("河津市", "4100004");
        bankCityMap.put("忻州市", "4100004");
        bankCityMap.put("原平市", "4100004");
        bankCityMap.put("临汾市", "4100004");
        bankCityMap.put("侯马市", "4100004");
        bankCityMap.put("霍州市", "4100004");
        bankCityMap.put("吕梁市", "4100004");
        bankCityMap.put("孝义市", "4100004");
        bankCityMap.put("汾阳市", "4100004");
        bankCityMap.put("呼和浩特市", "4100005");//内蒙古自治区
        bankCityMap.put("乌海市", "4100005");
        bankCityMap.put("赤峰市", "4100005");
        bankCityMap.put("包头市", "4100005");
        bankCityMap.put("通辽市", "4100005");
        bankCityMap.put("霍林郭勒市", "4100005");
        bankCityMap.put("鄂尔多斯市", "4100005");
        bankCityMap.put("呼伦贝尔市", "4100005");
        bankCityMap.put("满洲里市", "4100005");
        bankCityMap.put("牙克石市", "4100005");
        bankCityMap.put("扎兰屯市", "4100005");
        bankCityMap.put("额尔古纳市", "4100005");
        bankCityMap.put("根河市", "4100005");
        bankCityMap.put("巴彦淖尔市", "4100005");
        bankCityMap.put("乌兰察布市", "4100005");
        bankCityMap.put("丰镇市", "4100005");
        bankCityMap.put("乌兰浩特市", "4100005");
        bankCityMap.put("阿尔山市", "4100005");
        bankCityMap.put("二连浩特市", "4100005");
        bankCityMap.put("锡林浩特市", "4100005");
        bankCityMap.put("沈阳市", "4100006");//辽宁省
        bankCityMap.put("新民市", "4100006");
        bankCityMap.put("大连市", "4100006");
        bankCityMap.put("瓦房店市", "4100006");
        bankCityMap.put("普兰店市", "4100006");
        bankCityMap.put("庄河市", "4100006");
        bankCityMap.put("鞍山市", "4100006");
        bankCityMap.put("海城市", "4100006");
        bankCityMap.put("抚顺市", "4100006");
        bankCityMap.put("本溪市", "4100006");
        bankCityMap.put("丹东市", "4100006");
        bankCityMap.put("东港市", "4100006");
        bankCityMap.put("凤城市", "4100006");
        bankCityMap.put("锦州市", "4100006");
        bankCityMap.put("凌海市", "4100006");
        bankCityMap.put("北镇市", "4100006");
        bankCityMap.put("营口市", "4100006");
        bankCityMap.put("盖州市", "4100006");
        bankCityMap.put("大石桥市", "4100006");
        bankCityMap.put("阜新市", "4100006");
        bankCityMap.put("辽阳市", "4100006");
        bankCityMap.put("灯塔市", "4100006");
        bankCityMap.put("盘锦市", "4100006");
        bankCityMap.put("铁岭市", "4100006");
        bankCityMap.put("调兵山市", "4100006");
        bankCityMap.put("开原市", "4100006");
        bankCityMap.put("朝阳市", "4100006");
        bankCityMap.put("北票市", "4100006");
        bankCityMap.put("凌源市", "4100006");
        bankCityMap.put("葫芦岛市", "4100006");
        bankCityMap.put("兴城市", "4100006");
        bankCityMap.put("长春市", "4100007");//吉林省
        bankCityMap.put("九台市", "4100007");
        bankCityMap.put("榆树市", "4100007");
        bankCityMap.put("德惠市", "4100007");
        bankCityMap.put("吉林市", "4100007");
        bankCityMap.put("蛟河市", "4100007");
        bankCityMap.put("桦甸市", "4100007");
        bankCityMap.put("舒兰市", "4100007");
        bankCityMap.put("磐石市", "4100007");
        bankCityMap.put("四平市", "4100007");
        bankCityMap.put("公主岭市", "4100007");
        bankCityMap.put("双辽市", "4100007");
        bankCityMap.put("辽源市", "4100007");
        bankCityMap.put("通化市", "4100007");
        bankCityMap.put("梅河口市", "4100007");
        bankCityMap.put("集安市", "4100007");
        bankCityMap.put("白山市", "4100007");
        bankCityMap.put("临江市", "4100007");
        bankCityMap.put("松原市", "4100007");
        bankCityMap.put("白城市", "4100007");
        bankCityMap.put("洮南市", "4100007");
        bankCityMap.put("大安市", "4100007");
        bankCityMap.put("延吉市", "4100007");
        bankCityMap.put("图们市", "4100007");
        bankCityMap.put("敦化市", "4100007");
        bankCityMap.put("珲春市", "4100007");
        bankCityMap.put("龙井市", "4100007");
        bankCityMap.put("和龙市", "4100007");
        bankCityMap.put("哈尔滨市", "4100008");//黑龙江省
        bankCityMap.put("双城市", "4100008");
        bankCityMap.put("尚志市", "4100008");
        bankCityMap.put("五常市", "4100008");
        bankCityMap.put("齐齐哈尔市", "4100008");
        bankCityMap.put("讷河市", "4100008");
        bankCityMap.put("鸡西市", "4100008");
        bankCityMap.put("虎林市", "4100008");
        bankCityMap.put("密山市", "4100008");
        bankCityMap.put("鹤岗市", "4100008");
        bankCityMap.put("双鸭山市", "4100008");
        bankCityMap.put("大庆市", "4100008");


        bankCityMap.put("伊春市", "4100008");
        bankCityMap.put("铁力市", "4100008");
        bankCityMap.put("佳木斯市", "4100008");
        bankCityMap.put("同江市", "4100008");
        bankCityMap.put("富锦市", "4100008");
        bankCityMap.put("七台河市", "4100008");
        bankCityMap.put("牡丹江市", "4100008");
        bankCityMap.put("绥芬河市", "4100008");
        bankCityMap.put("海林市", "4100008");
        bankCityMap.put("宁安市", "4100008");
        bankCityMap.put("穆棱市", "4100008");
        bankCityMap.put("黑河市", "4100008");
        bankCityMap.put("北安市", "4100008");
        bankCityMap.put("五大连池市", "4100008");
        bankCityMap.put("绥化市", "4100008");
        bankCityMap.put("安达市", "4100008");
        bankCityMap.put("肇东市", "4100008");
        bankCityMap.put("海伦市", "4100008");
        bankCityMap.put("上海市", "4100009");//上海市
        bankCityMap.put("南京市", "4100010");//江苏省
        bankCityMap.put("无锡市", "4100010");
        bankCityMap.put("江阴市", "4100010");
        bankCityMap.put("宜兴市", "4100010");
        bankCityMap.put("徐州市", "4100010");
        bankCityMap.put("新沂市", "4100010");
        bankCityMap.put("邳州市", "4100010");
        bankCityMap.put("常州市", "4100010");
        bankCityMap.put("溧阳市", "4100010");
        bankCityMap.put("金坛市", "4100010");
        bankCityMap.put("苏州市", "4100010");
        bankCityMap.put("常熟市", "4100010");
        bankCityMap.put("张家港市", "4100010");
        bankCityMap.put("昆山市", "4100010");
        bankCityMap.put("吴江市", "4100010");
        bankCityMap.put("太仓市", "4100010");
        bankCityMap.put("南通市", "4100010");
        bankCityMap.put("启东市", "4100010");
        bankCityMap.put("如皋市", "4100010");
        bankCityMap.put("海门市", "4100010");
        bankCityMap.put("连云港市", "4100010");
        bankCityMap.put("淮安市", "4100010");
        bankCityMap.put("盐城市", "4100010");
        bankCityMap.put("东台市", "4100010");
        bankCityMap.put("大丰市", "4100010");
        bankCityMap.put("扬州市", "4100010");
        bankCityMap.put("仪征市", "4100010");
        bankCityMap.put("高邮市", "4100010");
        bankCityMap.put("镇江市", "4100010");
        bankCityMap.put("丹阳市", "4100010");
        bankCityMap.put("扬中市", "4100010");
        bankCityMap.put("句容市", "4100010");
        bankCityMap.put("泰州市", "4100010");
        bankCityMap.put("兴化市", "4100010");
        bankCityMap.put("靖江市", "4100010");
        bankCityMap.put("泰兴市", "4100010");
        bankCityMap.put("姜堰市", "4100010");
        bankCityMap.put("宿迁市", "4100010");
        bankCityMap.put("杭州市", "4100011");//浙江省
        bankCityMap.put("建德市", "4100011");
        bankCityMap.put("富阳市", "4100011");
        bankCityMap.put("临安市", "4100011");
        bankCityMap.put("宁波市", "4100011");
        bankCityMap.put("余姚市", "4100011");
        bankCityMap.put("慈溪市", "4100011");
        bankCityMap.put("奉化市", "4100011");
        bankCityMap.put("温州市", "4100011");
        bankCityMap.put("瑞安市", "4100011");
        bankCityMap.put("乐清市", "4100011");
        bankCityMap.put("嘉兴市", "4100011");
        bankCityMap.put("海宁市", "4100011");
        bankCityMap.put("平湖市", "4100011");
        bankCityMap.put("桐乡市", "4100011");
        bankCityMap.put("湖州市", "4100011");
        bankCityMap.put("绍兴市", "4100011");
        bankCityMap.put("诸暨市", "4100011");
        bankCityMap.put("上虞市", "4100011");
        bankCityMap.put("嵊州市", "4100011");
        bankCityMap.put("金华市", "4100011");
        bankCityMap.put("兰溪市", "4100011");
        bankCityMap.put("义乌市", "4100011");
        bankCityMap.put("东阳市", "4100011");
        bankCityMap.put("永康市", "4100011");
        bankCityMap.put("衢州市", "4100011");
        bankCityMap.put("江山市", "4100011");
        bankCityMap.put("舟山市", "4100011");
        bankCityMap.put("台州市", "4100011");
        bankCityMap.put("温岭市", "4100011");
        bankCityMap.put("临海市", "4100011");
        bankCityMap.put("丽水市", "4100011");
        bankCityMap.put("龙泉市", "4100011");
        bankCityMap.put("合肥市", "4100012");//安徽省
        bankCityMap.put("巢湖市", "4100012");
        bankCityMap.put("芜湖市", "4100012");
        bankCityMap.put("蚌埠市", "4100012");
        bankCityMap.put("淮南市", "4100012");
        bankCityMap.put("马鞍山市", "4100012");
        bankCityMap.put("淮北市", "4100012");
        bankCityMap.put("铜陵市", "4100012");
        bankCityMap.put("安庆市", "4100012");
        bankCityMap.put("桐城市", "4100012");
        bankCityMap.put("黄山市", "4100012");
        bankCityMap.put("滁州市", "4100012");
        bankCityMap.put("天长市", "4100012");
        bankCityMap.put("明光市", "4100012");
        bankCityMap.put("阜阳市", "4100012");
        bankCityMap.put("界首市", "4100012");
        bankCityMap.put("宿州市", "4100012");
        bankCityMap.put("六安市", "4100012");
        bankCityMap.put("亳州市", "4100012");
        bankCityMap.put("池州市", "4100012");
        bankCityMap.put("宣城市", "4100012");
        bankCityMap.put("宁国市", "4100012");
        bankCityMap.put("福州市", "4100013");//福建省
        bankCityMap.put("福清市", "4100013");
        bankCityMap.put("长乐市", "4100013");
        bankCityMap.put("厦门市", "4100013");
        bankCityMap.put("莆田市", "4100013");
        bankCityMap.put("三明市", "4100013");
        bankCityMap.put("永安市", "4100013");
        bankCityMap.put("泉州市", "4100013");
        bankCityMap.put("石狮市", "4100013");
        bankCityMap.put("晋江市", "4100013");
        bankCityMap.put("南安市", "4100013");
        bankCityMap.put("漳州市", "4100013");
        bankCityMap.put("龙海市", "4100013");
        bankCityMap.put("南平市", "4100013");
        bankCityMap.put("邵武市", "4100013");
        bankCityMap.put("武夷山市", "4100013");
        bankCityMap.put("建瓯市", "4100013");
        bankCityMap.put("建阳市", "4100013");
        bankCityMap.put("龙岩市", "4100013");
        bankCityMap.put("漳平市", "4100013");
        bankCityMap.put("宁德市", "4100013");
        bankCityMap.put("福安市", "4100013");
        bankCityMap.put("福鼎市", "4100013");
        bankCityMap.put("南昌市", "4100014");//江西省
        bankCityMap.put("景德镇市", "4100014");
        bankCityMap.put("乐平市", "4100014");
        bankCityMap.put("萍乡市", "4100014");
        bankCityMap.put("九江市", "4100014");
        bankCityMap.put("瑞昌市", "4100014");
        bankCityMap.put("共青城市", "4100014");
        bankCityMap.put("新余市", "4100014");
        bankCityMap.put("鹰潭市", "4100014");
        bankCityMap.put("贵溪市", "4100014");
        bankCityMap.put("赣州市", "4100014");
        bankCityMap.put("瑞金市", "4100014");
        bankCityMap.put("南康市", "4100014");
        bankCityMap.put("吉安市", "4100014");
        bankCityMap.put("井冈山市", "4100014");
        bankCityMap.put("宜春市", "4100014");
        bankCityMap.put("丰城市", "4100014");
        bankCityMap.put("樟树市", "4100014");
        bankCityMap.put("高安市", "4100014");
        bankCityMap.put("抚州市", "4100014");
        bankCityMap.put("上饶市", "4100014");
        bankCityMap.put("德兴市", "4100014");
        bankCityMap.put("济南市", "4100015");//山东省
        bankCityMap.put("章丘市", "4100015");
        bankCityMap.put("青岛市", "4100015");
        bankCityMap.put("胶州市", "4100015");
        bankCityMap.put("即墨市", "4100015");
        bankCityMap.put("平度市", "4100015");
        bankCityMap.put("胶南市", "4100015");
        bankCityMap.put("莱西市", "4100015");
        bankCityMap.put("淄博市", "4100015");
        bankCityMap.put("枣庄市", "4100015");
        bankCityMap.put("滕州市", "4100015");
        bankCityMap.put("东营市", "4100015");
        bankCityMap.put("烟台市", "4100015");
        bankCityMap.put("龙口市", "4100015");
        bankCityMap.put("莱阳市", "4100015");
        bankCityMap.put("莱州市", "4100015");
        bankCityMap.put("蓬莱市", "4100015");
        bankCityMap.put("招远市", "4100015");
        bankCityMap.put("栖霞市", "4100015");
        bankCityMap.put("海阳市", "4100015");
        bankCityMap.put("潍坊市", "4100015");
        bankCityMap.put("青州市", "4100015");
        bankCityMap.put("诸城市", "4100015");
        bankCityMap.put("寿光市", "4100015");
        bankCityMap.put("安丘市", "4100015");
        bankCityMap.put("高密市", "4100015");
        bankCityMap.put("昌邑市", "4100015");
        bankCityMap.put("济宁市", "4100015");
        bankCityMap.put("曲阜市", "4100015");
        bankCityMap.put("兖州市", "4100015");
        bankCityMap.put("邹城市", "4100015");
        bankCityMap.put("泰安市", "4100015");
        bankCityMap.put("新泰市", "4100015");
        bankCityMap.put("肥城市", "4100015");
        bankCityMap.put("威海市", "4100015");
        bankCityMap.put("文登市", "4100015");
        bankCityMap.put("荣成市", "4100015");
        bankCityMap.put("乳山市", "4100015");
        bankCityMap.put("日照市", "4100015");
        bankCityMap.put("莱芜市", "4100015");
        bankCityMap.put("临沂市", "4100015");
        bankCityMap.put("德州市", "4100015");
        bankCityMap.put("乐陵市", "4100015");
        bankCityMap.put("禹城市", "4100015");
        bankCityMap.put("聊城市", "4100015");
        bankCityMap.put("临清市", "4100015");
        bankCityMap.put("滨州市", "4100015");
        bankCityMap.put("菏泽市", "4100015");
        bankCityMap.put("郑州市", "4100016");//河南省
        bankCityMap.put("巩义市", "4100016");
        bankCityMap.put("荥阳市", "4100016");
        bankCityMap.put("新密市", "4100016");
        bankCityMap.put("新郑市", "4100016");
        bankCityMap.put("登封市", "4100016");
        bankCityMap.put("开封市", "4100016");
        bankCityMap.put("洛阳市", "4100016");
        bankCityMap.put("偃师市", "4100016");
        bankCityMap.put("平顶山市", "4100016");
        bankCityMap.put("舞钢市", "4100016");
        bankCityMap.put("汝州市", "4100016");
        bankCityMap.put("安阳市", "4100016");
        bankCityMap.put("林州市", "4100016");
        bankCityMap.put("鹤壁市", "4100016");
        bankCityMap.put("新乡市", "4100016");
        bankCityMap.put("卫辉市", "4100016");
        bankCityMap.put("辉县市", "4100016");
        bankCityMap.put("焦作市", "4100016");
        bankCityMap.put("沁阳市", "4100016");
        bankCityMap.put("孟州市", "4100016");
        bankCityMap.put("濮阳市", "4100016");
        bankCityMap.put("许昌市", "4100016");
        bankCityMap.put("禹州市", "4100016");
        bankCityMap.put("长葛市", "4100016");
        bankCityMap.put("漯河市", "4100016");
        bankCityMap.put("三门峡市", "4100016");
        bankCityMap.put("义马市", "4100016");
        bankCityMap.put("灵宝市", "4100016");
        bankCityMap.put("南阳市", "4100016");
        bankCityMap.put("邓州市", "4100016");
        bankCityMap.put("商丘市", "4100016");
        bankCityMap.put("永城市", "4100016");
        bankCityMap.put("信阳市", "4100016");
        bankCityMap.put("周口市", "4100016");
        bankCityMap.put("项城市", "4100016");
        bankCityMap.put("驻马店市", "4100016");
        bankCityMap.put("济源市", "4100016");
        bankCityMap.put("武汉市", "4100017");//湖北省
        bankCityMap.put("黄石市", "4100017");
        bankCityMap.put("大冶市", "4100017");
        bankCityMap.put("十堰市", "4100017");
        bankCityMap.put("丹江口市", "4100017");
        bankCityMap.put("宜昌市", "4100017");
        bankCityMap.put("宜都市", "4100017");
        bankCityMap.put("当阳市", "4100017");
        bankCityMap.put("枝江市", "4100017");
        bankCityMap.put("襄阳市", "4100017");
        bankCityMap.put("老河口市", "4100017");
        bankCityMap.put("枣阳市", "4100017");
        bankCityMap.put("宜城市", "4100017");
        bankCityMap.put("鄂州市", "4100017");
        bankCityMap.put("荆门市", "4100017");
        bankCityMap.put("钟祥市", "4100017");
        bankCityMap.put("孝感市", "4100017");
        bankCityMap.put("应城市", "4100017");
        bankCityMap.put("安陆市", "4100017");
        bankCityMap.put("汉川市", "4100017");
        bankCityMap.put("荆州市", "4100017");
        bankCityMap.put("石首市", "4100017");
        bankCityMap.put("洪湖市", "4100017");
        bankCityMap.put("松滋市", "4100017");
        bankCityMap.put("黄冈市", "4100017");
        bankCityMap.put("麻城市", "4100017");
        bankCityMap.put("武穴市", "4100017");
        bankCityMap.put("咸宁市", "4100017");
        bankCityMap.put("赤壁市", "4100017");
        bankCityMap.put("随州市", "4100017");
        bankCityMap.put("广水市", "4100017");
        bankCityMap.put("恩施市", "4100017");
        bankCityMap.put("利川市", "4100017");
        bankCityMap.put("仙桃市", "4100017");
        bankCityMap.put("潜江市", "4100017");
        bankCityMap.put("天门市", "4100017");
        bankCityMap.put("长沙市", "4100018");//湖南省
        bankCityMap.put("浏阳市", "4100018");
        bankCityMap.put("株洲市", "4100018");
        bankCityMap.put("醴陵市", "4100018");
        bankCityMap.put("湘潭市", "4100018");
        bankCityMap.put("湘乡市", "4100018");
        bankCityMap.put("韶山市", "4100018");
        bankCityMap.put("衡阳市", "4100018");
        bankCityMap.put("耒阳市", "4100018");
        bankCityMap.put("常宁市", "4100018");
        bankCityMap.put("邵阳市", "4100018");
        bankCityMap.put("武冈市", "4100018");
        bankCityMap.put("岳阳市", "4100018");
        bankCityMap.put("汨罗市", "4100018");
        bankCityMap.put("临湘市", "4100018");
        bankCityMap.put("常德市", "4100018");
        bankCityMap.put("津市市", "4100018");
        bankCityMap.put("张家界市", "4100018");
        bankCityMap.put("益阳市", "4100018");
        bankCityMap.put("沅江市", "4100018");
        bankCityMap.put("郴州市", "4100018");
        bankCityMap.put("资兴市", "4100018");
        bankCityMap.put("永州市", "4100018");
        bankCityMap.put("怀化市", "4100018");
        bankCityMap.put("洪江市", "4100018");
        bankCityMap.put("娄底市", "4100018");
        bankCityMap.put("冷水江市", "4100018");
        bankCityMap.put("涟源市", "4100018");
        bankCityMap.put("吉首市", "4100018");
        bankCityMap.put("广州市", "4100019");//广州省
        bankCityMap.put("增城市", "4100019");
        bankCityMap.put("从化市", "4100019");
        bankCityMap.put("韶关市", "4100019");
        bankCityMap.put("乐昌市", "4100019");
        bankCityMap.put("南雄市", "4100019");
        bankCityMap.put("深圳市", "4100019");
        bankCityMap.put("珠海市", "4100019");
        bankCityMap.put("汕头市", "4100019");
        bankCityMap.put("佛山市", "4100019");
        bankCityMap.put("江门市", "4100019");
        bankCityMap.put("台山市", "4100019");
        bankCityMap.put("开平市", "4100019");
        bankCityMap.put("鹤山市", "4100019");
        bankCityMap.put("恩平市", "4100019");
        bankCityMap.put("湛江市", "4100019");
        bankCityMap.put("廉江市", "4100019");
        bankCityMap.put("雷州市", "4100019");
        bankCityMap.put("吴川市", "4100019");
        bankCityMap.put("茂名市", "4100019");
        bankCityMap.put("高州市", "4100019");
        bankCityMap.put("化州市", "4100019");
        bankCityMap.put("信宜市", "4100019");
        bankCityMap.put("肇庆市", "4100019");
        bankCityMap.put("高要市", "4100019");
        bankCityMap.put("四会市", "4100019");
        bankCityMap.put("惠州市", "4100019");
        bankCityMap.put("梅州市", "4100019");
        bankCityMap.put("兴宁市", "4100019");
        bankCityMap.put("汕尾市", "4100019");
        bankCityMap.put("陆丰市", "4100019");
        bankCityMap.put("河源市", "4100019");
        bankCityMap.put("阳江市", "4100019");
        bankCityMap.put("阳春市", "4100019");
        bankCityMap.put("清远市", "4100019");
        bankCityMap.put("英德市", "4100019");
        bankCityMap.put("连州市", "4100019");
        bankCityMap.put("东莞市", "4100019");
        bankCityMap.put("中山市", "4100019");
        bankCityMap.put("潮州市", "4100019");
        bankCityMap.put("揭阳市", "4100019");
        bankCityMap.put("普宁市", "4100019");
        bankCityMap.put("云浮市", "4100019");
        bankCityMap.put("罗定市", "4100019");
        bankCityMap.put("南宁市", "4100020");//广西壮族自治区
        bankCityMap.put("柳州市", "4100020");
        bankCityMap.put("桂林市", "4100020");
        bankCityMap.put("梧州市", "4100020");
        bankCityMap.put("岑溪市", "4100020");
        bankCityMap.put("北海市", "4100020");
        bankCityMap.put("防城港市", "4100020");
        bankCityMap.put("东兴市", "4100020");
        bankCityMap.put("钦州市", "4100020");
        bankCityMap.put("贵港市", "4100020");
        bankCityMap.put("桂平市", "4100020");
        bankCityMap.put("玉林市", "4100020");
        bankCityMap.put("北流市", "4100020");
        bankCityMap.put("百色市", "4100020");
        bankCityMap.put("贺州市", "4100020");
        bankCityMap.put("河池市", "4100020");
        bankCityMap.put("宜州市", "4100020");
        bankCityMap.put("来宾市", "4100020");
        bankCityMap.put("合山市", "4100020");
        bankCityMap.put("崇左市", "4100020");
        bankCityMap.put("凭祥市", "4100020");
        bankCityMap.put("海口市", "4100021");//海南省
        bankCityMap.put("三亚市", "4100021");
        bankCityMap.put("五指山市", "4100021");
        bankCityMap.put("琼海市", "4100021");
        bankCityMap.put("儋州市", "4100021");
        bankCityMap.put("文昌市", "4100021");
        bankCityMap.put("万宁市", "4100021");
        bankCityMap.put("东方市", "4100021");
        bankCityMap.put("重庆市", "4100022");//重庆市
        bankCityMap.put("成都市", "4100023");//四川省
        bankCityMap.put("都江堰市", "4100023");
        bankCityMap.put("彭州市", "4100023");
        bankCityMap.put("邛崃市", "4100023");
        bankCityMap.put("崇州市", "4100023");
        bankCityMap.put("自贡市", "4100023");
        bankCityMap.put("攀枝花市", "4100023");
        bankCityMap.put("泸州市", "4100023");
        bankCityMap.put("德阳市", "4100023");
        bankCityMap.put("广汉市", "4100023");
        bankCityMap.put("什邡市", "4100023");
        bankCityMap.put("绵竹市", "4100023");
        bankCityMap.put("绵阳市", "4100023");
        bankCityMap.put("江油市", "4100023");
        bankCityMap.put("广元市", "4100023");
        bankCityMap.put("遂宁市", "4100023");
        bankCityMap.put("内江市", "4100023");
        bankCityMap.put("乐山市", "4100023");
        bankCityMap.put("峨眉山市", "4100023");
        bankCityMap.put("南充市", "4100023");
        bankCityMap.put("阆中市", "4100023");
        bankCityMap.put("眉山市", "4100023");
        bankCityMap.put("宜宾市", "4100023");
        bankCityMap.put("广安市", "4100023");
        bankCityMap.put("华蓥市", "4100023");
        bankCityMap.put("达州市", "4100023");
        bankCityMap.put("万源市", "4100023");
        bankCityMap.put("雅安市", "4100023");
        bankCityMap.put("巴中市", "4100023");
        bankCityMap.put("资阳市", "4100023");
        bankCityMap.put("简阳市", "4100023");
        bankCityMap.put("西昌市", "4100023");
        bankCityMap.put("阿坝藏族羌族自治州", "410023");
        bankCityMap.put("甘孜藏族自治州", "410023");
        bankCityMap.put("贵阳市", "4100024");//贵州省
        bankCityMap.put("清镇市", "4100024");
        bankCityMap.put("六盘水市", "4100024");
        bankCityMap.put("遵义市", "4100024");
        bankCityMap.put("赤水市", "4100024");
        bankCityMap.put("仁怀市", "4100024");
        bankCityMap.put("安顺市", "4100024");
        bankCityMap.put("毕节市", "4100024");
        bankCityMap.put("铜仁市", "4100024");
        bankCityMap.put("兴义市", "4100024");
        bankCityMap.put("凯里市", "4100024");
        bankCityMap.put("都匀市", "4100024");
        bankCityMap.put("福泉市", "4100024");
        bankCityMap.put("昆明市", "4100025");//云南省
        bankCityMap.put("安宁市", "4100025");
        bankCityMap.put("曲靖市", "4100025");
        bankCityMap.put("宣威市", "4100025");
        bankCityMap.put("玉溪市", "4100025");
        bankCityMap.put("保山市", "4100025");
        bankCityMap.put("昭通市", "4100025");
        bankCityMap.put("丽江市", "4100025");
        bankCityMap.put("普洱市", "4100025");
        bankCityMap.put("临沧市", "4100025");
        bankCityMap.put("楚雄市", "4100025");
        bankCityMap.put("个旧市", "4100025");
        bankCityMap.put("开远市", "4100025");
        bankCityMap.put("蒙自市", "4100025");
        bankCityMap.put("文山市", "4100025");
        bankCityMap.put("景洪市", "4100025");
        bankCityMap.put("大理市", "4100025");
        bankCityMap.put("瑞丽市", "4100025");
        bankCityMap.put("芒市", "4100025");
        bankCityMap.put("拉萨市", "4100026");//西藏自治区
        bankCityMap.put("日喀则市", "4100026");
        bankCityMap.put("林芝地区", "4100026");
        bankCityMap.put("西安市", "4100027"); //陕西省
        bankCityMap.put("铜川市", "4100027");
        bankCityMap.put("宝鸡市", "4100027");
        bankCityMap.put("咸阳市", "4100027");
        bankCityMap.put("兴平市", "4100027");
        bankCityMap.put("渭南市", "4100027");
        bankCityMap.put("韩城市", "4100027");
        bankCityMap.put("华阴市", "4100027");
        bankCityMap.put("延安市", "4100027");
        bankCityMap.put("汉中市", "4100027");
        bankCityMap.put("榆林市", "4100027");
        bankCityMap.put("安康市", "4100027");
        bankCityMap.put("商洛市", "4100027");
        bankCityMap.put("兰州市", "4100031");//甘肃省
        bankCityMap.put("嘉峪关市", "4100031");
        bankCityMap.put("金昌市", "4100031");
        bankCityMap.put("白银市", "4100031");
        bankCityMap.put("天水市", "4100031");
        bankCityMap.put("武威市", "4100031");
        bankCityMap.put("张掖市", "4100031");
        bankCityMap.put("平凉市", "4100031");
        bankCityMap.put("酒泉市", "4100031");
        bankCityMap.put("玉门市", "4100031");
        bankCityMap.put("敦煌市", "4100031");
        bankCityMap.put("庆阳市", "4100031");
        bankCityMap.put("定西市", "4100031");
        bankCityMap.put("陇南市", "4100031");
        bankCityMap.put("临夏市", "4100031");
        bankCityMap.put("合作市", "4100031");
        bankCityMap.put("西宁市", "4100032");//青海省
        bankCityMap.put("格尔木市", "4100032");
        bankCityMap.put("德令哈市", "4100032");
        bankCityMap.put("银川市", "4100033");//宁夏回族自治区
        bankCityMap.put("灵武市", "4100033");
        bankCityMap.put("石嘴山市", "4100033");
        bankCityMap.put("吴忠市", "4100033");
        bankCityMap.put("青铜峡市", "4100033");
        bankCityMap.put("固原市", "4100033");
        bankCityMap.put("中卫市", "4100033");
        bankCityMap.put("乌鲁木齐市", "4100034");//新疆维吾尔自治区
        bankCityMap.put("克拉玛依市", "4100034");
        bankCityMap.put("吐鲁番市", "4100034");
        bankCityMap.put("哈密市", "4100034");
        bankCityMap.put("昌吉市", "4100034");
        bankCityMap.put("阜康市", "4100034");
        bankCityMap.put("博乐市", "4100034");
        bankCityMap.put("库尔勒市", "4100034");
        bankCityMap.put("阿克苏市", "4100034");
        bankCityMap.put("阿图什市", "4100034");
        bankCityMap.put("喀什市", "4100034");
        bankCityMap.put("和田市", "4100034");
        bankCityMap.put("伊宁市", "4100034");
        bankCityMap.put("奎屯市", "4100034");
        bankCityMap.put("塔城市", "4100034");
        bankCityMap.put("乌苏市", "4100034");
        bankCityMap.put("阿勒泰市", "4100034");
        bankCityMap.put("石河子市", "4100034");
        bankCityMap.put("阿拉尔市", "4100034");
        bankCityMap.put("图木舒克市", "4100034");
        bankCityMap.put("五家渠市", "4100034");

    }


}
