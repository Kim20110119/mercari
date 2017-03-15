package mercari.bean;

/**
 * =====================================================================================================================
 * アカウント用Bean
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class AccountBean {
	//==================================================================================================================
	// アカウント項目
	//==================================================================================================================
	/** アカウント */
	private String mail;
	/** パスワード */
	private String password;
	/** 出品時間 */
	private Integer time;
	/** Wifi */
	private Boolean wifi;
	/** Wifiパスワード */
	private String wifiPassword;
	/** ユーザーエージェント */
	private String userAgent;
	/** ユーザー情報パス */
	private String userPath;
	/**
	 * @return アカウント
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param アカウントをセットする
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param パスワードをセットする
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return 出品時間
	 */
	public Integer getTime() {
		return time;
	}
	/**
	 * @param 出品時間をセットする
	 */
	public void setTime(Integer time) {
		this.time = time;
	}
	/**
	 * @return Wifi
	 */
	public Boolean getWifi() {
		return wifi;
	}
	/**
	 * @param Wifiをセットする
	 */
	public void setWifi(Boolean wifi) {
		this.wifi = wifi;
	}
	/**
	 * @return Wifiパスワード
	 */
	public String getWifiPassword() {
		return wifiPassword;
	}
	/**
	 * @param Wifiパスワードをセットする
	 */
	public void setWifiPassword(String wifiPassword) {
		this.wifiPassword = wifiPassword;
	}
	/**
	 * @return ユーザーエージェント
	 */
	public String getUserAgent() {
		return userAgent;
	}
	/**
	 * @param ユーザーエージェントをセットする
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	/**
	 * @return ユーザーデータパス
	 */
	public String getUserPath() {
		return userPath;
	}
	/**
	 * @param ユーザーデータパスをセットする
	 */
	public void setUserPath(String userPath) {
		this.userPath = userPath;
	}

}
