package mercari.pc.main;

import mercari.bean.AccountBean;
import mercari.excel.Product;
import mercari.pc.comment.Pc_Comment;

/**
 * =====================================================================================================================
 * 【コメント】商品詳細画面表示の自動化処理
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Comment_Main {
	public static void main(String[] args) {
		Product product = new Product();
		// アカウント情報を取得する
		AccountBean account = product.getAccount(args[0]);
		// 【コメント】商品詳細画面表示処理
		Pc_Comment comment = new Pc_Comment(account.getMail(),account.getPassword());
		comment.execute();
		System.out.println("【" + account.getMail() + "】アカウントの【コメント】商品詳細画面表示処理完了！！！");
	}

}
