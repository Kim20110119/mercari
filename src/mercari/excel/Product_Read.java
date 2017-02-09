package mercari.excel;

import mercari.bean.ProductBean;

public class Product_Read {
	public static void main(String[] args) {
		try {
			Product product = new Product();
			for(ProductBean	 bean : product.execute()){
				System.out.println(bean.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("処理が失敗しました");
		}
	}
}
