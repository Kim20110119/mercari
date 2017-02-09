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
	/** 商品画像パス */
	private String images_path;
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
	/** 発送までの日数 */
	private String day;
	/** 価格 */
	private String price;

	/**
	 * @return 商品画像パス
	 */
	public String getImages_path() {
		return images_path;
	}
	/**
	 * @param 商品画像パスをセットする
	 */
	public void setImages_path(String images_path) {
		this.images_path = images_path;
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
	 * @return 商品の説明
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param 商品の説明をセットする
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return カテゴリー（大）
	 */
	public String getCategory_0() {
		return category_0;
	}
	/**
	 * @param カテゴリー（大）をセットする
	 */
	public void setCategory_0(String category_0) {
		this.category_0 = category_0;
	}
	/**
	 * @return カテゴリー（大）
	 */
	public String getCategory_1() {
		return category_1;
	}
	/**
	 * @param カテゴリー（中）をセットする
	 */
	public void setCategory_1(String category_1) {
		this.category_1 = category_1;
	}
	/**
	 * @return カテゴリー（小）
	 */
	public String getCategory_2() {
		return category_2;
	}
	/**
	 * @param カテゴリー（小）をセットする
	 */
	public void setCategory_2(String category_2) {
		this.category_2 = category_2;
	}
	/**
	 * @return ブランド
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * @param ブランドをセットする
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * @return サイズ
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param サイズをセットする
	 */
	public void setSize(String size) {
		this.size = size;
	}
	/**
	 * @return 商品状態
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param 商品状態をセットする
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return 配送料の負担
	 */
	public String getDelivery_f() {
		return delivery_f;
	}
	/**
	 * @param 配送料の負担をセットする
	 */
	public void setDelivery_f(String delivery_f) {
		this.delivery_f = delivery_f;
	}
	/**
	 * @return 配送の方法
	 */
	public String getDelivery_m() {
		return delivery_m;
	}
	/**
	 * @param 配送の方法をセットする
	 */
	public void setDelivery_m(String delivery_m) {
		this.delivery_m = delivery_m;
	}
	/**
	 * @return 配送元の地域
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param 配送元の地域をセットする region
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return 発送までの日数
	 */
	public String getDay() {
		return day;
	}
	/**
	 * @param 発送までの日数をセットする
	 */
	public void setDay(String day) {
		this.day = day;
	}
	/**
	 * @return 価格
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param 価格をセットする
	 */
	public void setPrice(String price) {
		this.price = price;
	}
}
