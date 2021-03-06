package mercari.bean;

/**
 * =====================================================================================================================
 * 【出品した商品 - 取引中】商品用Bean
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class OutputBean {
	//==================================================================================================================
	// 商品項目
	//==================================================================================================================
	/** 商品ID */
	private String id;
	/** 商品名 */
	private String name;
	/** 販売価格 */
	private String price;
	/** 販売手数料 */
	private String commission;
	/** 販売利益 */
	private String profit;
	/** お届け先 */
	private String delivery;
	/** アカウント */
	private String account;
	/** メールアドレス */
	private String mail;
	/**
	 * @return 商品ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param 商品IDをセットする
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return 商品名
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param 商品名をセットする
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return 販売価格
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param 販売価格をセットする
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return 販売手数料
	 */
	public String getCommission() {
		return commission;
	}
	/**
	 * @param 販売手数料をセットする
	 */
	public void setCommission(String commission) {
		this.commission = commission;
	}
	/**
	 * @return 販売利益
	 */
	public String getProfit() {
		return profit;
	}
	/**
	 * @param 販売利益をセットする
	 */
	public void setProfit(String profit) {
		this.profit = profit;
	}
	/**
	 * @return お届け先
	 */
	public String getDelivery() {
		return delivery;
	}
	/**
	 * @param お届け先をセットする
	 */
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	/**
	 * @return アカウント
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @param アカウントをセットする
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * @return メールアドレス
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param メールアドレスをセットする
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
