package mercari.bean;

/**
 * =====================================================================================================================
 * 出品商品用Bean
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class ProductBean {
	//==================================================================================================================
	// 商品項目
	//==================================================================================================================
	/** 商品画像 */
	private String images;
	/** 商品名 */
	private String name;
	/** 商品の説明 */
	private String description;
	/** カテゴリー（大） */
	private String category_0;
	/** カテゴリー（中） */
	private String category_1;
	/** カテゴリー（小） */
	private String category_2;
	/** ブランド */
	private String brand;
	/** サイズ */
	private String size;
	/** 商品状態 */
	private String state;
	/** 配送料の負担 */
	private String delivery_f;
	/** 配送の方法 */
	private String delivery_m;
	/** 配送元の地域 */
	private String region;

	/**
	 * @return images
	 */
	public String getImages() {
		return images;
	}
	/**
	 * @param images セットする images
	 */
	public void setImages(String images) {
		this.images = images;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description セットする description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return category_0
	 */
	public String getCategory_0() {
		return category_0;
	}
	/**
	 * @param category_0 セットする category_0
	 */
	public void setCategory_0(String category_0) {
		this.category_0 = category_0;
	}
	/**
	 * @return category_1
	 */
	public String getCategory_1() {
		return category_1;
	}
	/**
	 * @param category_1 セットする category_1
	 */
	public void setCategory_1(String category_1) {
		this.category_1 = category_1;
	}
	/**
	 * @return category_2
	 */
	public String getCategory_2() {
		return category_2;
	}
	/**
	 * @param category_2 セットする category_2
	 */
	public void setCategory_2(String category_2) {
		this.category_2 = category_2;
	}
	/**
	 * @return brand
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * @param brand セットする brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * @return size
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param size セットする size
	 */
	public void setSize(String size) {
		this.size = size;
	}
	/**
	 * @return state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state セットする state
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return delivery_f
	 */
	public String getDelivery_f() {
		return delivery_f;
	}
	/**
	 * @param delivery_f セットする delivery_f
	 */
	public void setDelivery_f(String delivery_f) {
		this.delivery_f = delivery_f;
	}
	/**
	 * @return delivery_m
	 */
	public String getDelivery_m() {
		return delivery_m;
	}
	/**
	 * @param delivery_m セットする delivery_m
	 */
	public void setDelivery_m(String delivery_m) {
		this.delivery_m = delivery_m;
	}
	/**
	 * @return region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region セットする region
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return day
	 */
	public String getDay() {
		return day;
	}
	/**
	 * @param day セットする day
	 */
	public void setDay(String day) {
		this.day = day;
	}
	/**
	 * @return price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price セットする price
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/** 発送までの日数 */
	private String day;
	/** 価格 */
	private String price;

}
