package mercari.pc.main;

import mercari.pc.output.Pc_Output;

/**
 * =====================================================================================================================
 * 【発送待ち】【評価待ち】商品出力の自動化処理
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_Output_Main {
	public static void main(String[] args) {
		Pc_Output output = new Pc_Output(args[0],args[1]);
		// 【発送待ち】【評価待ち】商品出力
		output.execute();
		// ブラウザドライバーを終了する
		output.driverQuit();
		System.out.println("【" + args[0] + "】アカウントの【発送待ち】【評価待ち】商品の抽出処理完了！！！");
	}

}
