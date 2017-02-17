package mercari.pc.main;

import mercari.excel.CreateFolder;

/**
 * =====================================================================================================================
 * バッチファイル作成の自動化処理
 * =====================================================================================================================
 *
 * @author kimC
 *
 */
public class Pc_CreateFolder_Main {
	public static void main() {
		// バッチファイル作成の自動化処理
		CreateFolder createFolder = new CreateFolder();
		createFolder.execute();
		System.out.println("バッチファイル作成自動処理完了！！！");
	}

}
