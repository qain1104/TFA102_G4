package com.morder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cartList.model.CartListService;
import com.cartList.model.CartListVO;
import com.coupon.model.CouponService;
import com.coupon.model.CouponVO;
import com.morder.model.MorderService;
import com.morder.model.MorderVO;
import com.morder.util.ConsumeStock;
import com.morder.util.DateTransform;
import com.order_list.model.Order_listService;
import com.order_list.model.Order_listVO;
import com.product.model.ProductVO;
import com.productspec.model.ProductSpecJDBCDAO;
import com.productspec.model.ProductSpecService;
import com.productspec.model.ProductSpecVO;


//@WebServlet("/Morder")
public class Morder extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Morder() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		Integer userId = (Integer)session.getAttribute("userId"); // 會員編號
		
		// 點選上一步，回到購物車畫面
		if("backToCart".equals(action)) {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/shoppingcart.jsp");
			dispatcher.forward(request, response);
			return;
			
		}
		
		// 點選下一步，若是付款方式為到貨付款則直接跳付款完成的頁面
		// 若為信用卡付款則會先跳到信用卡填寫資料，才會接著跳付款完成頁面
		if("payingProcess".equals(action)) {
					
			Map<String, String> errorMsgs = new HashMap<String, String>(); // 錯誤訊息
			
			// 訂購人資料載入失敗
			String userName = request.getParameter("userName"); // 會員
			if(userName == null || userName.trim().length() == 0) {
				errorMsgs.put("userName", "系統載入出錯，請重整頁面");
			}
			
			String phone = request.getParameter("phone"); // 會員電話
			if(phone == null || phone.trim().length() == 0) {
				errorMsgs.put("phone", "系統載入出錯，請重整頁面");
			}
			
			String address = request.getParameter("address"); // 會員地址
			if(address == null || address.trim().length() == 0) {
				errorMsgs.put("address", "系統載入出錯，請重整頁面");	
			}
			
			// 配送方式取不到值
			Integer deliveryType = null; // 配送方式
			try {
				deliveryType = new Integer(request.getParameter("deliveryType").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("deliveryType", "請勾選配送方式");
			}

			// 付款方式取不到值
			Integer paying = null; // 付款方式
			try {
				paying = new Integer(request.getParameter("paying").trim()); 
			} catch (NumberFormatException e) {
				errorMsgs.put("paying", "請勾選付款方式");
			}
			
			// 同訂購人資料做勾選
			String sameAsBuyer= request.getParameter("sameAsBuyer");
			if(sameAsBuyer != null) {
				if(1 == new Integer(sameAsBuyer))
					request.setAttribute("sameAsBuyer", 1);
			}
			
			// 收貨人資料空白
			String receiver = request.getParameter("receiver"); // 收貨人
			if(receiver == null || receiver.trim().length() == 0) {
				errorMsgs.put("receiver", "收貨人名稱不得為空白");
			}

			// 收貨人連絡電話為空白
			String phoneReg = "^[0-9]{10}$";
			String receiverPhone = request.getParameter("receiverPhone"); // 收貨人電話
			if(receiverPhone == null || receiverPhone.trim().length() == 0) {
				errorMsgs.put("receiverPhone", "收貨人電話不得為空白");
			} else if (!receiverPhone.trim().matches(phoneReg)) {
				errorMsgs.put("receiverPhone", "電話號碼只能為數字");
			}
			
			// 超商地址且收貨人地址為空白
			String addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,}$"; 
			String receiverAddress = request.getParameter("receiverAddress"); // 收貨人地址
			String storeAddress = request.getParameter("storeAddress"); // 超商地址	
			String storeName = request.getParameter("storeName"); // 超商店名
			//點選宅配
			if(deliveryType == 14001) {
				if(receiverAddress == null || receiverAddress.trim().length() == 0) {
					errorMsgs.put("receiverAddress", "地址不得為空白");
				} else  if(!receiverAddress.trim().matches(addressReg)) {
					errorMsgs.put("receiverAddress", "地址格式輸入錯誤");
				}
			}
			//點選超商
			if(deliveryType == 14002) {
				Integer storeId = null;
				try {
					storeId = new Integer(request.getParameter("storeId").trim()); 
				} catch (NumberFormatException e) {
					errorMsgs.put("storeId", "超商編號不得為空白");
				}
				
				if(!storeName.trim().matches(addressReg)) {
					errorMsgs.put("storeName", "超商名稱不得為空白");
				}	
				
				if(storeAddress == null || storeAddress.trim().length() == 0) {
					errorMsgs.put("storeAddress", "超商地址不得為空白");
				} else  if(!storeAddress.trim().matches(addressReg)) {
					errorMsgs.put("storeAddress", "地址格式輸入錯誤");
				}
			}
		
			String couponValue = request.getParameter("couponValue"); // 優惠券
			Map<CartListVO, ProductVO> shoppingCartMap = 
					(Map<CartListVO, ProductVO>)session.getAttribute("shoppingCartMap"); // 購物車物件對應產品名稱的Map物件
			Map<String, Integer> totalCartList = 
					(Map<String, Integer>)session.getAttribute("totalCartList"); // 取得購物車的總數量及總金額
			
			// 配送方式:宅配 --> 貨到付款及信用卡付款
			if(14001 == deliveryType) {
				
				// 付款方式為貨到付款
				if(0 == paying) {
					
					CouponService couponService = new CouponService(); 
					CouponVO couponVO = couponService.getCouponBySn(couponValue);
					Integer discount = checkCoupon(couponValue); // 優惠券折扣
					// 當優惠券折扣回傳-1表示無符合的優惠券，回傳錯誤訊息
					if(discount == -1) {
						errorMsgs.put("couponValue", "查無此優惠券");
					}
					
					Integer deliveryFee = getDeliveryFee(deliveryType); // 運費
					Integer totalAmount = totalCartList.get("totalAmount"); // 商品的總金額

					// 新增訂單
					MorderVO morder = new MorderVO(); // 訂單
					morder.setUserId(userId);
					
					// 優惠券相關限制條件
					if(couponVO != null) {
						// 必須符合優惠券限制條件才可以做優惠折扣
						if(totalAmount >= new Integer(couponVO.getCouponInfo())) {
							// 優惠券不為空值才set
							morder.setCouponId(couponVO.getCouponId());
							morder.setTotalPrice(totalAmount - discount + deliveryFee);
						} else {
							// 不符合則送出錯誤訊息
							errorMsgs.put("couponValue", "使用優惠券須符合" + couponVO.getCouponName());
						}
					} else {
						// 若為空值則直接計算總金額
						morder.setTotalPrice(totalAmount - discount + deliveryFee);
					}
					
					morder.setOrderPayment(paying);
					morder.setOrderDeliveyTypeId(deliveryType);
					morder.setReceiver(receiver);
					morder.setReceiverPhone(receiverPhone);
					morder.setReceiverAddress(receiverAddress);
					
					// 將購物車的商品放進訂單明細
					Set<CartListVO> cartlistSet = shoppingCartMap.keySet();
					List<Order_listVO> list = new ArrayList<Order_listVO>();
					
					ProductSpecService specService = new ProductSpecService();
					for(CartListVO cartListVO : cartlistSet) {
						// 各項數量
						Integer itemQuantity = cartListVO.getItemQuantity();
						Order_listVO order_listVO = new Order_listVO();
						order_listVO.setProductSpecId(cartListVO.getProductSpecId());
						order_listVO.setPurchaseQuantity(itemQuantity);
						// 拿購物車內容找到個別的價錢*數目當作各項的小計
						Integer price = specService.cartListGetPrice(cartListVO);
						order_listVO.setOrderCost(price * itemQuantity);
						// 將從購物車拿到的屬性變成訂單明細並塞入一個list帶入addMorderWithList(MorderVO morder, List<Order_listVO> list)
						list.add(order_listVO);
					}
					// 錯誤轉交
					if(!errorMsgs.isEmpty()) {
						request.setAttribute("errorMsgs", errorMsgs);
						request.setAttribute("morder", morder);
						request.setAttribute("couponValue", couponValue);
						request.setAttribute("list", list);
						RequestDispatcher failure = request.getRequestDispatcher("/shopping/paying-delivery.jsp");
						failure.forward(request, response);
						return;
					}
					
					try {
						MorderService morderService = new MorderService();
						Order_listService orderListService = new Order_listService();
						Integer newOrderSN = morderService.addMorderWithList(morder, list);
						// 新增成功後，扣掉庫存量
						ConsumeStock consumeStock = new ConsumeStock();
						for(Order_listVO orderList : list) {
							Integer stockAfterShopping =consumeStock.consumeStock(orderList);
							if(stockAfterShopping < 0) {
								errorMsgs.put("addError", "庫存量不足，請連絡廠商");
								return;
							} else {
								specService.updateStock(orderList.getProductSpecId(), stockAfterShopping);
							}
						}
						
						// 新增成功後，清空購物車
						CartListService cartListService = new CartListService();
						cartListService.deleteCartList(userId);
						
						//下個頁面需要的屬性
						List<Order_listVO> newList = orderListService.getOrderListByOrder(newOrderSN);
						MorderVO newMorder = morderService.getMorder(newOrderSN);
						String purchasingDate = DateTransform.TimeStampTranslatedToString(newMorder.getPurchaseDate());
						CouponVO coupon = couponService.getCoupon(newMorder.getCouponId());
						
						// 新增成功後將session的東西清乾淨
						session.removeAttribute("shoppingCartMap");
						session.removeAttribute("totalCartList");
						// request forward給下個頁面所需要的物件
						request.setAttribute("newMorder", newMorder);
						request.setAttribute("newList", newList);
						request.setAttribute("purchasingDate", purchasingDate);
						request.setAttribute("coupon", coupon);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/shopping-complete.jsp");
						dispatcher.forward(request, response);
						
					} catch(Exception e) {
						errorMsgs.put("addError", "新增訂單失敗 : " + e.getMessage());
					}
					
				} 
				// 付款方式為信用卡付款，先跳轉信用卡資料頁面
				if(1 == paying) {
					
					CouponService couponService = new CouponService(); 
					CouponVO couponVO = couponService.getCouponBySn(couponValue);
					Integer discount = checkCoupon(couponValue); // 優惠券折扣
					// 當優惠券折扣回傳-1表示無符合的優惠券，回傳錯誤訊息
					if(discount == -1) {
						errorMsgs.put("couponValue", "查無此優惠券，請重新輸入");
					}
					
					Integer deliveryFee = getDeliveryFee(deliveryType); // 運費
					Integer totalAmount = totalCartList.get("totalAmount"); // 商品的總金額

					// 新增訂單
					MorderVO morder = new MorderVO(); // 訂單
					morder.setUserId(userId);
					
					// 優惠券相關限制條件
					if(couponVO != null) {
						// 必須符合優惠券限制條件才可以做優惠折扣
						if(totalAmount >= new Integer(couponVO.getCouponInfo())) {
							// 優惠券不為空值才set
							morder.setCouponId(couponVO.getCouponId());
							morder.setTotalPrice(totalAmount - discount + deliveryFee);
						} else {
							// 不符合則送出錯誤訊息
							errorMsgs.put("couponValue", "使用優惠券須符合" + couponVO.getCouponName());
						}
					} else {
						// 若為空值則直接計算總金額
						morder.setTotalPrice(totalAmount - discount + deliveryFee);
					}
					
					morder.setOrderPayment(paying);
					morder.setOrderDeliveyTypeId(deliveryType);
					morder.setReceiver(receiver);
					morder.setReceiverPhone(receiverPhone);
					morder.setReceiverAddress(receiverAddress);
					
					// 將購物車的商品放進訂單明細
					Set<CartListVO> cartlistSet = shoppingCartMap.keySet();
					List<Order_listVO> list = new ArrayList<Order_listVO>();
					
					ProductSpecService specService = new ProductSpecService();
					for(CartListVO cartListVO : cartlistSet) {
						// 各項數量
						Integer itemQuantity = cartListVO.getItemQuantity();
						Order_listVO order_listVO = new Order_listVO();
						order_listVO.setProductSpecId(cartListVO.getProductSpecId());
						order_listVO.setPurchaseQuantity(itemQuantity);
						// 拿購物車內容找到個別的價錢*數目當作各項的小計
						Integer price = specService.cartListGetPrice(cartListVO);
						order_listVO.setOrderCost(price * itemQuantity);
						// 將從購物車拿到的屬性變成訂單明細並塞入一個list帶入addMorderWithList(MorderVO morder, List<Order_listVO> list)
						list.add(order_listVO);
					}
					// 錯誤轉交
					if(!errorMsgs.isEmpty()) {
						request.setAttribute("errorMsgs", errorMsgs);
						request.setAttribute("morder", morder);
						request.setAttribute("couponValue", couponValue);
						request.setAttribute("list", list);
						RequestDispatcher failure = request.getRequestDispatcher("/shopping/paying-delivery.jsp");
						failure.forward(request, response);
						return;
					}
					
					// 跳轉信用卡頁面
					session.setAttribute("morder", morder);
					session.setAttribute("couponValue", couponValue);
					session.setAttribute("list", list);
					RequestDispatcher creditCard = request.getRequestDispatcher("/shopping/shopping-creditcard.jsp");
					creditCard.forward(request, response);
				}
				
			}  // 配送方式:超商取貨 --> 貨到付款及信用卡付款(此功能刪除)
//			else if(14002 == deliveryType) {
//				
//			}
		}
		
		// 信用卡填寫資料頁面
		if("aftercreditcard".equals(action)) {
			Map<String, String> errorMsgs = new HashMap<String, String>(); // 錯誤訊息
			String orderCard = request.getParameter("orderCard"); // 信用卡卡號
			String orderCardMonth = request.getParameter("orderCardMonth"); // 信用卡月份
			String orderCardYear= request.getParameter("orderCardYear"); // 信用卡年份
			String cvvNumber= request.getParameter("cvvNumber");
			String cardRegex = "^[\\d]{4}-[\\d]{4}-[\\d]{4}-[\\d]{4}$";
			String cvvRegex = "^[\\d]{3}$";
			
			if(orderCard == null || orderCard.trim().length() == 0) {
				errorMsgs.put("orderCard", "信用卡號碼不得為空白");
			} else if(!orderCard.matches(cardRegex)) {
				errorMsgs.put("orderCard", "信用卡號碼格式錯誤、不得超過6碼");
			}
			if(cvvNumber == null || cvvNumber.trim().length() == 0) {
				errorMsgs.put("cvvNumber", "安全碼不得為空白");
			} else if(!cvvNumber.matches(cvvRegex)) {
				errorMsgs.put("cvvNumber", "安全碼應為3碼，且須為數字");
			}
			
			// 在原本morder物件中加入信用卡資料並新增訂單和訂單明細
			MorderVO morder = (MorderVO)session.getAttribute("morder");
			List<Order_listVO> list = (List<Order_listVO>)session.getAttribute("list");
			morder.setOrderCard(orderCard);
			morder.setOrderCardMonth(orderCardMonth);
			morder.setOrderCardYear(orderCardYear);
			
			// 錯誤轉交
			if(!errorMsgs.isEmpty()) {
				request.setAttribute("errorMsgs", errorMsgs);
				request.setAttribute("orderCard", orderCard);
				request.setAttribute("orderCardMonth", orderCardMonth);
				request.setAttribute("orderCardYear", orderCardYear);
				request.setAttribute("cvvNumber", cvvNumber);
				RequestDispatcher failure = request.getRequestDispatcher("/shopping/shopping-creditcard.jsp");
				failure.forward(request, response);
				return;
			}
			
			try {
				ProductSpecService specService = new ProductSpecService();
				MorderService morderService = new MorderService();
				Order_listService orderListService = new Order_listService();
				Integer newOrderSN = morderService.addMorderWithList(morder, list);
				
				// 新增成功後，扣掉庫存量
				ConsumeStock consumeStock = new ConsumeStock();
				for(Order_listVO orderList : list) {
					Integer stockAfterShopping =consumeStock.consumeStock(orderList);
					if(stockAfterShopping < 0) {
						errorMsgs.put("addError", "庫存量不足，請連絡廠商");
						return;
					} else {
						specService.updateStock(orderList.getProductSpecId(), stockAfterShopping);
					}
				}
				
				// 新增成功後，清空購物車
				CartListService cartListService = new CartListService();
				cartListService.deleteCartList(userId);
				
				//下個頁面需要的屬性
				List<Order_listVO> newList = orderListService.getOrderListByOrder(newOrderSN);
				MorderVO newMorder = morderService.getMorder(newOrderSN);
				String purchasingDate = DateTransform.TimeStampTranslatedToString(newMorder.getPurchaseDate());
				CouponService couponService = new CouponService();
				CouponVO coupon = couponService.getCoupon(newMorder.getCouponId());
				
				// 新增成功後將session的東西清乾淨
				session.removeAttribute("shoppingCartMap");
				session.removeAttribute("totalCartList");
				// request forward給下個頁面所需要的物件
				request.setAttribute("newMorder", newMorder);
				request.setAttribute("newList", newList);
				request.setAttribute("purchasingDate", purchasingDate);
				request.setAttribute("coupon", coupon);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/shopping-complete.jsp");
				dispatcher.forward(request, response);
				
			} catch(Exception e) {
				errorMsgs.put("addError", "新增訂單失敗 : " + e.getMessage());
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	// 比對優惠券序號是否符合並回傳折扣
	public Integer checkCoupon(String couponValue) {
		Integer discount = new Integer(0);
		Map<String, String> errorMsgs = new HashMap<String, String>(); // 錯誤訊息
		// 優惠券不為空值
		if(couponValue.trim().length() != 0) {

			CouponService couponService = new CouponService();
			List<CouponVO> allCoupon = couponService.getAll();
			// 找出符合現在時間的優惠券，放進list中
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<CouponVO> currentCoupon 
				=allCoupon.stream()
		                  .filter(c -> c.getCouponStarting().before(now))
		                  .filter(c -> c.getCouponEnding().after(now))
		                  .collect(Collectors.toList());
			
			// 對於優惠券序號進行比對，若有符合折扣碼則取出折扣，若無符合則將折扣設為-1
			for(CouponVO couponVO : currentCoupon) {
				if(couponVO.getCouponSN().equals(couponValue)) {
					discount = couponVO.getCouponDiscount();
				} else {
					discount = new Integer(-1);
				}
			}
		} else {
			discount = new Integer(0);
		}
		return discount;
	}
	
	// 對於宅配及超商取貨判斷運費
	public Integer getDeliveryFee(Integer deliveryType) {
		Integer fee = null;
		if(14001 == deliveryType) {
			fee = new Integer(100);
		} else if(14002 == deliveryType) {
			fee = new Integer(80);
		}
		return fee;
	}
	
}
