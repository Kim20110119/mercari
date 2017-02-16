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
	
	
	
}
