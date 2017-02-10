package mercari.pc.main;

import mercari.pc.comment.Pc_Comment;

/**
 * =====================================================================================================================
 * コメントある商品詳細画面表示の自動化処理
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Comment_Main {
	public static void main(String[] args) {
		Pc_Comment comment = new Pc_Comment(args[0],args[1]);
		comment.execute();
		System.out.println("【" + args[0] + "】アカウントのコメントある商品の抽出処理完了！！！");
	}

}
