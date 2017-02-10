package common.constant;

/**
 * =====================================================================================================================
 * 【メルカリ】定数
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class MercariConstants {

	/**
	 * privateコンストラクタでインスタンス生成を抑止
	 */
	private MercariConstants() {
	}

	// ==================================================================================================================
	// 【URL】
	// ==================================================================================================================
	/** 【ログイン】URL（PC版メルカリ） */
	public static final String PC_LOGIN_URL = "https://www.mercari.com/jp/login/?login_callback=https%3A%2F%2Fwww.mercari.com%2Fjp%2F";
	/** 【出品】URL（PC版メルカリ） */
	public static final String PC_EXHIBIT_URL = "https://www.mercari.com/jp/sell/";
	/** 【マイページ】URL（PC版メルカリ） */
	public static final String PC_MYPAGE_URL = "https://www.mercari.com/jp/mypage/";
	/** 【出品した商品 - 出品中】URL（PC版メルカ）L */
	public static final String PC_LISTING_URL = "https://www.mercari.com/jp/mypage/listings/listing/";
	/** 【出品した商品 - 取引中】URL（PC版メルカ） */
	public static final String PC_IN_PROGRESS_URL = "https://www.mercari.com/jp/mypage/listings/in_progress/";
	/** 【出品した商品 - 売却済み】URL（PC版メルカ） */
	public static final String PC_COMPLETED_URL = "https://www.mercari.com/jp/mypage/listings/completed/";
	/** 【購入した商品 - 取引中】URL（PC版メルカ） */
	public static final String PC_PURCHASE_URL = "https://www.mercari.com/jp/mypage/purchase/";
	/** 【購入した商品 - 過去の取引】URL（PC版メルカ） */
	public static final String PC_PURCHASED_URL = "https://www.mercari.com/jp/mypage/purchased/";

	// ==================================================================================================================
	// 【パス】
	// ==================================================================================================================



	// ==================================================================================================================
	// 【文字列】
	// ==================================================================================================================
	/** 発送待ち */
	public final static String STR_WAIT_0 = "発送待ち";
	/** 受取評価待ち */
	public final static String STR_WAIT_1 = "受取評価待ち";
	/** 評価待ち */
	public final static String STR_WAIT_2 = "評価待ち";
	/** 【出品中の商品がありません】 */
	public static final String STR_LISTING = ".*出品中の商品がありません.*";
	/** 【取引中の商品がありません】 */
	public static final String STR_IN_PROGRESS = ".*取引中の商品がありません.*";

	// ==================================================================================================================
	// 【整数】
	// ==================================================================================================================
	/** 「0」 */
	public static final int INT_0 = 0;
	/** 「1」 */
	public static final int INT_1 = 1;
	/** 「2」 */
	public static final int INT_2 = 2;
	/** 「3」 */
	public static final int INT_3 = 3;
	/** 「4」 */
	public static final int INT_4 = 4;
	/** 「5」 */
	public static final int INT_5 = 5;
	/** 「6」 */
	public static final int INT_6 = 6;
	/** 「7」 */
	public static final int INT_7 = 7;
	/** 「8」 */
	public static final int INT_8 = 8;
	/** 「9」 */
	public static final int INT_9 = 9;
}
