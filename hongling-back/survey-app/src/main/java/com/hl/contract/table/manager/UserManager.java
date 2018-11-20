package com.hl.contract.table.manager;

import android.content.Context;
import android.os.Handler;

import com.hl.core.lib.util.UtilManager;
import com.hl.core.lib.util.common.UUIDUtil;
import com.hl.contract.business.login.service.dto.UserInfoDTO;
import com.hl.contract.table.dao.DaoSession;
import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.SurveyGreenDaoHelper;
import com.hl.contract.table.dao.UserInfoDao;
import com.hl.contract.table.inter.UserListener;
import com.hl.contract.table.model.UserInfo;
import com.hl.contract.util.SurveyClaimUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Describe: 用户
 * @Package: com.hl.contract.table.manager
 * @Author: liyu
 * @Date: 2018/3/21 17:27
 * @Copyright: hl
 */
public class UserManager {

	public static UserManager instance;
	private Set<UserListener> mUserListeners;
	private Context mContext;
	private UserInfo mUser;
	private String mUsername;
	private String mPassword;
	private String mLastLoginUsername;
	private String sessionID;
	private String errorEnum;
	
	public static final String PREFERENCE_USERNAME = "username";
	public static final String PREFERENCE_PASSWORD = "password";

//	private DisposeDataListener mListener; // 回调
	protected final int NETWORK_ERROR = -1; // 网络错误
	protected final int JSON_ERROR = -2; // Json错误
	protected final int OTHER_ERROR = -3; // 其他未知错误
	private String EMPTY_MSG = ""; // 空消息

	/**
	 * 是否是自动登录
	 */
	public boolean mIsAutoLogin = true;

	private boolean mLoginInProgress;

//	private final Handler mHandler;
    private UserInfoDao userInfoDao;

	public static UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager(SurveyApplication.get());
		}

		return instance;
	}
	
	private UserManager(Context context) {
//		mHandler = new Handler();
//		mLastLoginUsername = mUsername;
////		errorEnum = ErrorEnum.LOGIN;
		mContext = context;
//		mUserListeners = new HashSet<UserListener>();
        DaoSession daoSession = SurveyGreenDaoHelper.getInstance().getDaoSession(context);
        userInfoDao = daoSession.getUserInfoDao();
	}


	public UserInfo getUserInfo(String userId){

		UserInfo userInfo = null;
		if (userId!=null){
			List<UserInfo> list = userInfoDao.queryBuilder().where(UserInfoDao.Properties.UserId.eq(userId)).list();
			if(list != null && list.size() > 0){
				userInfo = list.get(0);
			}
		}
		return userInfo;
	}

	public void saveUserInfo(UserInfoDTO userInfoDTO, UserInfo userInfo){
		if(userInfo != null&&userInfoDTO!=null){
			UserInfo localInfo = UserManager.getInstance().getUserInfo(userInfo.getUserId());
			if(localInfo != null){
				localInfo.setUserId(userInfoDTO.getUserId());
				localInfo.setUserName(userInfoDTO.getName());
				localInfo.setPassWord(userInfo.getPassWord());
				localInfo.setHandlerName(userInfo.getHandlerName());
				localInfo.setCompanyCode(userInfo.getCompanyCode());
				localInfo.setCompanyName(userInfo.getCompanyName());
				localInfo.setBranchCompanyCode(userInfo.getBranchCompanyCode());
				localInfo.setBranchCompanyName(userInfo.getBranchCompanyName());
				localInfo.setAddr(userInfoDTO.getAddr());
				localInfo.setTelephone(userInfo.getTelephone());
				localInfo.setSurveyCeritCode(userInfo.getSurveyCeritCode());
				localInfo.setDeviceNo(userInfo.getDeviceNo());
				localInfo.setMachineModel(userInfo.getMachineModel());
				localInfo.setSignInId(userInfo.getSignInId());


				localInfo.setAccount(userInfoDTO.getAccount());
				localInfo.setName(userInfoDTO.getName());
				localInfo.setStoreCode(userInfoDTO.getStoreCode());
				localInfo.setStoreName(userInfoDTO.getStoreName());
				localInfo.setRoleCode(userInfoDTO.getRoleCode());
				localInfo.setRoleName(userInfoDTO.getRoleName());
				localInfo.setRoleContent(userInfoDTO.getRoleContent());

				userInfoDao.update(localInfo);
			}else{
				userInfo.setId(UUIDUtil.getUUID());
				userInfo.setUserId(userInfoDTO.getUserId());
				userInfo.setUserName(userInfoDTO.getName());
				userInfo.setAddr(userInfoDTO.getAddr());


				userInfo.setAccount(userInfoDTO.getAccount());
				userInfo.setName(userInfoDTO.getName());
				userInfo.setStoreCode(userInfoDTO.getStoreCode());
				userInfo.setStoreName(userInfoDTO.getStoreName());
				userInfo.setRoleCode(userInfoDTO.getRoleCode());
				userInfo.setRoleName(userInfoDTO.getRoleName());
				userInfo.setRoleContent(userInfoDTO.getRoleContent());

				userInfoDao.insert(userInfo);
			}

			//保存缓存
			UtilManager.SP.survey().put(SurveyClaimUtil.USER_ID, userInfoDTO.getUserId());
			UtilManager.SP.survey().put(SurveyClaimUtil.LOGIN_NAME, userInfoDTO.getAccount());
			UtilManager.SP.survey().put(SurveyClaimUtil.LOGIN_PWD, userInfo.getPassWord());
			UtilManager.SP.survey().put(SurveyClaimUtil.LOGIN_SAVE_PWD, true);
			UtilManager.SP.survey().put(SurveyClaimUtil.COM_CODE, userInfo.getCompanyCode());
			UtilManager.SP.survey().put(SurveyClaimUtil.COM_CODE_NAME, userInfo.getCompanyName());
			UtilManager.SP.survey().put(SurveyClaimUtil.USER_CARD_NO, userInfo.getSurveyCeritCode());
		}

	}



    public UserInfo getmUser() {
        return mUser;
    }

    public void setmUser(UserInfo mUser) {
        this.mUser = mUser;
    }

    private void onUserLoginFailed(int errorCode, String errorDesc) {
		mLoginInProgress = false;

		for (UserListener listener : mUserListeners) {
			listener.onUserLoginFailed(errorCode, errorDesc);
		}
	}


	public boolean isLogin() {
		if (mUser == null)
			return false;
		return true;
	}

	public boolean isLoginInProgress() {
		return mLoginInProgress;
	}

	public void registerUserListener(UserListener l) {
		if (!mUserListeners.contains(l)) {
			mUserListeners.add(l);
		}
	}

	public void unregisterUserListener(UserListener l) {
		mUserListeners.remove(l);
	}

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }




}
