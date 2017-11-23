package net.saddam.onlinerestaurants.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import net.saddam.restaurantsbackend.common.ApiErrors;
import net.saddam.restaurantsbackend.common.JsonResponse;
import net.saddam.restaurantsbackend.dao.ProductDAO;
import net.saddam.restaurantsbackend.dao.UserDAO;
import net.saddam.restaurantsbackend.dto.Product;
import net.saddam.restaurantsbackend.dto.ProductResponse;
import net.saddam.restaurantsbackend.dto.Product_Data;
import net.saddam.restaurantsbackend.dto.UniqueProductResponse;
import net.saddam.restaurantsbackend.model.AllProduct;
import net.saddam.restaurantsbackend.model.AllProduct_Data;
import net.saddam.restaurantsbackend.model.RequestProduct;

/**
 * 
 * @author sk saddam hosan
 *
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
@EnableWebMvc
@RequestMapping("/product")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	public UserDAO userDAO;

	@Autowired
	public ProductDAO productDAO;

	/**
	 * Using shopID to get all product list
	 * 
	 * **
	 */

	/*
	 * @RequestMapping(value = "/list/shop/{shopId}/user/{userId}", method =
	 * RequestMethod.POST) public @ResponseBody AllProduct
	 * listOfProductByShopId(HttpServletRequest
	 * request,@PathVariable("shopId")String shopId,
	 * 
	 * @PathVariable("userId")String userId )
	 */
	@RequestMapping(value = "/list/byShopId", method = RequestMethod.POST)
	public @ResponseBody AllProduct listOfProductByShopId(@RequestBody ProductResponse productResponse) {

		{
			logger.info("Entered listOfProductByShopId()  - Get a Product list from shopId");
			AllProduct allProduct = new AllProduct();

			try {

				if (productResponse.getShop_ID() == null) {
					// ** no products exist, error message *//
					allProduct.setStatus_code(JsonResponse.CODE__EMPTY);
					allProduct.setStatus_message(JsonResponse.CODE__ERROR);
					logger.error(ApiErrors.ERROR__NO_USER_ID_EXIST);
					return allProduct;
				}

				List<Product> listOfSimpleEntities = productDAO.productsByShopId(productResponse.getShop_ID());
				allProduct.setProduct(listOfSimpleEntities);

				if (listOfSimpleEntities == null || listOfSimpleEntities.size() == 0) {
					// ** no products exist, error message *//
					allProduct.setStatus_code(JsonResponse.CODE__EMPTY);
					allProduct.setStatus_message(ApiErrors.ERROR__NO_PRODUCTS_EXIST);
					logger.error(ApiErrors.ERROR__NO_PRODUCTS_EXIST);
					return allProduct;
				}

				// ** set status OK *//*
				allProduct.setStatus_code(JsonResponse.CODE__OK);
				allProduct.setStatus_message("Successfully Authenticated");

			} catch (Exception e) {
				logger.error("listOfProductByShopId(): Error - " + e);
				allProduct.setStatus_code(JsonResponse.CODE__UNKNOWN_ERROR);
				allProduct.setStatus_message(JsonResponse.CODE__UNKNOWN_ERROR);
				return allProduct;
			}
			logger.info("Returning listOfProductByShopId()");

			return allProduct;
		}
	}

	@RequestMapping(value = "/list/shop/{shopId}", method = RequestMethod.GET)
	public @ResponseBody AllProduct_Data addAllProduct(HttpServletRequest request,
			@PathVariable("shopId") String shopId) {

		logger.info("Entered addAllProduct()  - Save all the product ");

		// Product_Data product = new Product_Data();
		AllProduct_Data allProduct = new AllProduct_Data();

		List<Product_Data> productList = new ArrayList();

		Product_Data product = new Product_Data();

		String productName = "Product_";
		int count = 0;

		try {
			if (shopId == null) {
				// ** no products exist, error message *//
				// product.setStatus_Code(JsonResponse.CODE__EMPTY);
				// product.setStatus_Message(JsonResponse.CODE__ERROR);
				logger.error(ApiErrors.ERROR__NO_USER_ID_EXIST);
				return allProduct;
			}

			List<Product_Data> listOfProduct = productDAO.addProductByShopId(shopId);

			/* When new shop id will come */
			if (listOfProduct == null || listOfProduct.size() == 0) {
				// ** no products exist, Add new product *//
				int i = 0;
				product.setProduct_Name("Apple");
				product.setShop_ID(shopId);
				product.setProduct_Price(200);
				product.setProduct_Image("image_1");
				product.setProduct_Category("Grocery");
				product.setProduct_Type("L_01");
				product.setAvailability(true);

				product.setProduct_ID(productName + i);

				productList.add(product);
				allProduct.setStatus_code(JsonResponse.CODE__OK);
				allProduct.setStatus_message("Successfully Authenticated");
				allProduct.setRequest_Type("Add-New_Product");
				// allProduct.setProductData(productList);
				productDAO.addProduct(product);
				return allProduct;
			}

			// Add new product of existing product

			product.setProduct_Name("Apple");
			product.setShop_ID(shopId);
			product.setProduct_Price(200);
			product.setProduct_Image("image_1");
			product.setProduct_Category("Grocery");
			product.setProduct_Type("L_01");
			product.setAvailability(true);

			for (Product_Data entity : listOfProduct) {
				// do something useful with entity;

				System.out.println("The value of database: " + entity.getProduct_ID());
				count++;

			}
			product.setProduct_ID(productName + count);

			productList.add(product);
			allProduct.setStatus_code(JsonResponse.CODE__OK);
			allProduct.setStatus_message("Successfully Authenticated");
			allProduct.setRequest_Type("Update_Product");
			// allProduct.setProductData(productList);

			productDAO.addProduct(product);

			// ** set status OK *//*
			// allProduct.setStatus_Code(JsonResponse.CODE__OK);
			// allProduct.setStatus_Message("Successfully Authenticated");

		} catch (Exception e) {
			logger.error("listOfProductByShopId(): Error - " + e);
			allProduct.setStatus_code(JsonResponse.CODE__UNKNOWN_ERROR);
			allProduct.setStatus_message(JsonResponse.CODE__UNKNOWN_ERROR);
			return allProduct;
		}
		logger.info("Returning listOfProductByShopId()");

		return allProduct;

	}

	/**
	 * Add product using shopid and product list
	 **/

	/*
	 * @RequestMapping(value = "/list/shop/{shopId}", method = RequestMethod.POST)
	 * public @ResponseBody AllProduct_Data addProductList(HttpServletRequest
	 * request,@PathVariable("shopId")String shopId ) {
	 */
	@RequestMapping(value = "/add/productsList", method = RequestMethod.POST)
	public @ResponseBody AllProduct_Data addProductsList(@RequestBody RequestProduct allProductData) {
		logger.info("Entered addProductList()  - Save all the product ");

		// String allP = allProductData.getRequest_Type();
		// String type1 = allProductData.getShop_ID();
		// Product_Data productD = allProductData.getProductData();
		// System.out.println(allP+" "+type1+""+productD.getProduct_ID());

		Product_Data productData = allProductData.getProductData();
		AllProduct_Data allProduct = new AllProduct_Data();

		String productName = "Product_";
		int count = -1;

		try {
			if (allProductData.getShop_ID() == null && productData.getProduct_Name() == null ) {
				// ** no products exist, error message *//
				allProduct.setStatus_code(JsonResponse.CODE__EMPTY);
				allProduct.setStatus_message(JsonResponse.CODE__ERROR);
				logger.error(ApiErrors.ERROR__NO_USER_ID_EXIST);
				// allProduct.setProductData(null);
				return allProduct;
			}

			List<Product_Data> listOfProduct = productDAO.addProductByShopId(allProductData.getShop_ID());

			// If list will be null then it will be execute
			if (listOfProduct == null || listOfProduct.size() == 0) {

				allProduct.setStatus_code(JsonResponse.CODE__EMPTY);
				allProduct.setStatus_message(JsonResponse.CODE__ERROR);
				allProduct.setRequest_Type("Product Is Not Exist ");
				// allProduct.setProductData(productData);
				// productDAO.addProduct(listOfProduct);
				return allProduct;
			}

			for (Product_Data entity : listOfProduct) {
				count++;
			}
			/* When new shop id will come */
			if (listOfProduct.get(count).getProduct_ID() == null || listOfProduct.size() == 0) {
				// ** no products exist, Add new product *//
				int i = 0;
				// product .setProduct_Name("Apple");
				productData.setShop_ID(allProductData.getShop_ID());

				productData.setProduct_ID(productName + i);
				allProduct.setStatus_code(JsonResponse.CODE__OK);
				allProduct.setStatus_message("Successfully Authenticated");
				allProduct.setRequest_Type("Add-New_Product");
				allProduct.setProductData(productData);
				productDAO.addProduct(productData);
				return allProduct;
			}

			// Add new product of existing product

			productData.setShop_ID(allProductData.getShop_ID());

			productData.setProduct_ID(productName + count);

			// productList.add(productD);
			allProduct.setStatus_code(JsonResponse.CODE__OK);
			allProduct.setStatus_message("Successfully Authenticated");
			allProduct.setRequest_Type("Add_New_Product");
			allProduct.setProductData(productData);

			productDAO.addProduct(productData);

			// ** set status OK *//*
			// allProduct.setStatus_Code(JsonResponse.CODE__OK);
			// allProduct.setStatus_Message("Successfully Authenticated");

		} catch (Exception e) {
			logger.error("listOfProductByShopId(): Error - " + e);
			allProduct.setStatus_code(JsonResponse.CODE__UNKNOWN_ERROR);
			allProduct.setStatus_message(JsonResponse.CODE__UNKNOWN_ERROR);
			return allProduct;
		}
		logger.info("Returning listOfProductByShopId()");

		return allProduct;

	}

	/**
	 * Update the product list using ShopId and productId
	 **/

	@RequestMapping(value = "/update/productsList", method = RequestMethod.POST)
	public @ResponseBody AllProduct_Data updateProductsList(@RequestBody RequestProduct allProductData) {
		logger.info("Entered updateProductList()  - Update all the product ");

		AllProduct_Data allProduct = new AllProduct_Data();
		Product_Data checkProduct = allProductData.getProductData();

		try {
			
			if(allProductData.getShop_ID() == null || checkProduct.getProduct_ID() == null ) {
				allProduct.setStatus_code(JsonResponse.CODE__EMPTY);
				allProduct.setStatus_message("CODE__ERROR");
				allProduct.setRequest_Type("ShopId Is Not Exist ");
				//allProduct.setProductData(updateData);
				return allProduct;
				
			}
			

			Product_Data updateData = productDAO.updateProduct(allProductData.getShop_ID(),
					allProductData.getProductData());
			

			if (updateData == null) {

				allProduct.setStatus_code(JsonResponse.CODE__EMPTY);
				allProduct.setStatus_message("CODE__ERROR");
				allProduct.setRequest_Type("Product Is Not Exist ");
				allProduct.setProductData(updateData);
				return allProduct;
			}

			allProduct.setStatus_code(JsonResponse.CODE__OK);
			allProduct.setStatus_message("Successfully Authenticated");
			allProduct.setRequest_Type("Update_Product_List");
			allProduct.setProductData(updateData);

			logger.info("Returning updateProductsList()");
			return allProduct;
		} catch (Exception e) {
			logger.error("listOfProductByShopId(): Error - " + e);
			allProduct.setStatus_code(JsonResponse.CODE__UNKNOWN_ERROR);
			allProduct.setStatus_message(JsonResponse.CODE__UNKNOWN_ERROR);
			return allProduct;
		}

	}

	/**
	 * This API for GET all the unique product list using name
	 **/

	@RequestMapping(value = "/unique/product/list", method = RequestMethod.GET)
	public @ResponseBody List<UniqueProductResponse> uniqueProductListByName(HttpServletRequest request)

	{
		logger.info("Entered uniqueProductListByName()  - Get all Unique Product list");
		// Product product = new Product();

		try {

			List<UniqueProductResponse> productList = productDAO.uniqueProductList();

			// System.out.println(productList);
			return productList;
		} catch (Exception e) {
			logger.error("listOfProductByShopId(): Error - " + e);
			logger.error(ApiErrors.ERROR__PRODUCT_DOES_NOT_EXIST);
			return null;
		}

	}

	/**
	 * SMS integration just for testion 
	 */

	@RequestMapping(value = "/list/byShopId/device", method = RequestMethod.GET)
	public @ResponseBody String forTestingoFDevice(HttpServletRequest request) {

		try {
			logger.info("Entered listOfProductByShopId()  - Get a Product list from shopId");

			// Construct data
			// String apiKey = "apikey=" + "l97FTELfBuM-wTRG1oJVPGFeIi09UDDPpCkneliehl";
			String user = "username=" + "sksddmhosan@gmail.com";
			String hash = "&hash=" + "02e651b2a16ed6a8a6a78879de06421dbcf740822a870da7c2bf5929ecf06f1a";

			// String apiKey = "apikey=" + "yourapiKey";
			String message = "&message=" + "my message";
			// String sender = "&sender=" + "saddam";
			String numbers = "&numbers=" + "7204414827";
			System.out.println("1");

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			System.out.println("2");
			// String data = apiKey + numbers + message + sender;
			String data = user + hash + numbers + message + "test=1";
			conn.setDoOutput(true);
			System.out.println("3");
			conn.setRequestMethod("POST");
			System.out.println("4");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			System.out.println("5");
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			System.out.println("6");
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS " + e);
			return "Error " + e;

		}

	}

}
