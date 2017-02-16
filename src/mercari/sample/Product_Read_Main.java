package mercari.sample;

import mercari.bean.AccountBean;
import mercari.bean.ProductBean;
import mercari.excel.Product;

public class Product_Read_Main {
	public static void main(String[] args) {
		try {
			Product product = new Product();
			AccountBean bean = product.getAccount("sample");
			System.out.println(bean.getMail());
			System.out.println(bean.getPassword());
			System.out.println(bean.getTime());
//			for(ProductBean	 bean : product.execute("ttxzyo7i0qu47@yahoo.co.jp")){
//				System.out.println(bean.getName());
//			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("処理が失敗しました");
		}
	}
}
