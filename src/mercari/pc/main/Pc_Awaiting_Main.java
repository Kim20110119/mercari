package mercari.pc.main;

import mercari.pc.awaiting.Pc_Awaiting;

/**
 * =====================================================================================================================
 * 評価待ち商品詳細画面表示の自動化処理
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Awaiting_Main {
	public static void main(String[] args) {
		Pc_Awaiting awaiting = new Pc_Awaiting(args[0],args[1]);
		awaiting.execute();
		System.out.println("【" + args[0] + "】アカウントの評価待ち商品の抽出処理完了！！！");
	}

}
