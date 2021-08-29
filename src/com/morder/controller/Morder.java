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
		Integer userId = (Integer)session.getAttribute("userId"); // �|���s��
		
		// �I��W�@�B�A�^���ʪ����e��
		if("backToCart".equals(action)) {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/shoppingcart.jsp");
			dispatcher.forward(request, response);
			return;
			
		}
		
		// �I��U�@�B�A�Y�O�I�ڤ覡����f�I�ګh�������I�ڧ���������
		// �Y���H�Υd�I�ګh�|������H�Υd��g��ơA�~�|���۸��I�ڧ�������
		if("payingProcess".equals(action)) {
					
			Map<String, String> errorMsgs = new HashMap<String, String>(); // ���~�T��
			
			// �q�ʤH��Ƹ��J����
			String userName = request.getParameter("userName"); // �|��
			if(userName == null || userName.trim().length() == 0) {
				errorMsgs.put("userName", "�t�θ��J�X���A�Э��㭶��");
			}
			
			String phone = request.getParameter("phone"); // �|���q��
			if(phone == null || phone.trim().length() == 0) {
				errorMsgs.put("phone", "�t�θ��J�X���A�Э��㭶��");
			}
			
			String address = request.getParameter("address"); // �|���a�}
			if(address == null || address.trim().length() == 0) {
				errorMsgs.put("address", "�t�θ��J�X���A�Э��㭶��");	
			}
			
			// �t�e�覡�������
			Integer deliveryType = null; // �t�e�覡
			try {
				deliveryType = new Integer(request.getParameter("deliveryType").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("deliveryType", "�ФĿ�t�e�覡");
			}

			// �I�ڤ覡�������
			Integer paying = null; // �I�ڤ覡
			try {
				paying = new Integer(request.getParameter("paying").trim()); 
			} catch (NumberFormatException e) {
				errorMsgs.put("paying", "�ФĿ�I�ڤ覡");
			}
			
			// �P�q�ʤH��ư��Ŀ�
			String sameAsBuyer= request.getParameter("sameAsBuyer");
			if(sameAsBuyer != null) {
				if(1 == new Integer(sameAsBuyer))
					request.setAttribute("sameAsBuyer", 1);
			}
			
			// ���f�H��ƪť�
			String receiver = request.getParameter("receiver"); // ���f�H
			if(receiver == null || receiver.trim().length() == 0) {
				errorMsgs.put("receiver", "���f�H�W�٤��o���ť�");
			}

			// ���f�H�s���q�ܬ��ť�
			String phoneReg = "^[0-9]{10}$";
			String receiverPhone = request.getParameter("receiverPhone"); // ���f�H�q��
			if(receiverPhone == null || receiverPhone.trim().length() == 0) {
				errorMsgs.put("receiverPhone", "���f�H�q�ܤ��o���ť�");
			} else if (!receiverPhone.trim().matches(phoneReg)) {
				errorMsgs.put("receiverPhone", "�q�ܸ��X�u�ର�Ʀr");
			}
			
			// �W�Ӧa�}�B���f�H�a�}���ť�
			String addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,}$"; 
			String receiverAddress = request.getParameter("receiverAddress"); // ���f�H�a�}
			String storeAddress = request.getParameter("storeAddress"); // �W�Ӧa�}	
			String storeName = request.getParameter("storeName"); // �W�ө��W
			//�I��v�t
			if(deliveryType == 14001) {
				if(receiverAddress == null || receiverAddress.trim().length() == 0) {
					errorMsgs.put("receiverAddress", "�a�}���o���ť�");
				} else  if(!receiverAddress.trim().matches(addressReg)) {
					errorMsgs.put("receiverAddress", "�a�}�榡��J���~");
				}
			}
			//�I��W��
			if(deliveryType == 14002) {
				Integer storeId = null;
				try {
					storeId = new Integer(request.getParameter("storeId").trim()); 
				} catch (NumberFormatException e) {
					errorMsgs.put("storeId", "�W�ӽs�����o���ť�");
				}
				
				if(!storeName.trim().matches(addressReg)) {
					errorMsgs.put("storeName", "�W�ӦW�٤��o���ť�");
				}	
				
				if(storeAddress == null || storeAddress.trim().length() == 0) {
					errorMsgs.put("storeAddress", "�W�Ӧa�}���o���ť�");
				} else  if(!storeAddress.trim().matches(addressReg)) {
					errorMsgs.put("storeAddress", "�a�}�榡��J���~");
				}
			}
		
			String couponValue = request.getParameter("couponValue"); // �u�f��
			Map<CartListVO, ProductVO> shoppingCartMap = 
					(Map<CartListVO, ProductVO>)session.getAttribute("shoppingCartMap"); // �ʪ�������������~�W�٪�Map����
			Map<String, Integer> totalCartList = 
					(Map<String, Integer>)session.getAttribute("totalCartList"); // ���o�ʪ������`�ƶq���`���B
			
			// �t�e�覡:�v�t --> �f��I�ڤΫH�Υd�I��
			if(14001 == deliveryType) {
				
				// �I�ڤ覡���f��I��
				if(0 == paying) {
					
					CouponService couponService = new CouponService(); 
					CouponVO couponVO = couponService.getCouponBySn(couponValue);
					Integer discount = checkCoupon(couponValue); // �u�f��馩
					// ���u�f��馩�^��-1��ܵL�ŦX���u�f��A�^�ǿ��~�T��
					if(discount == -1) {
						errorMsgs.put("couponValue", "�d�L���u�f��");
					}
					
					Integer deliveryFee = getDeliveryFee(deliveryType); // �B�O
					Integer totalAmount = totalCartList.get("totalAmount"); // �ӫ~���`���B

					// �s�W�q��
					MorderVO morder = new MorderVO(); // �q��
					morder.setUserId(userId);
					
					// �u�f������������
					if(couponVO != null) {
						// �����ŦX�u�f�魭�����~�i�H���u�f�馩
						if(totalAmount >= new Integer(couponVO.getCouponInfo())) {
							// �u�f�餣���ŭȤ~set
							morder.setCouponId(couponVO.getCouponId());
							morder.setTotalPrice(totalAmount - discount + deliveryFee);
						} else {
							// ���ŦX�h�e�X���~�T��
							errorMsgs.put("couponValue", "�ϥ��u�f�鶷�ŦX" + couponVO.getCouponName());
						}
					} else {
						// �Y���ŭȫh�����p���`���B
						morder.setTotalPrice(totalAmount - discount + deliveryFee);
					}
					
					morder.setOrderPayment(paying);
					morder.setOrderDeliveyTypeId(deliveryType);
					morder.setReceiver(receiver);
					morder.setReceiverPhone(receiverPhone);
					morder.setReceiverAddress(receiverAddress);
					
					// �N�ʪ������ӫ~��i�q�����
					Set<CartListVO> cartlistSet = shoppingCartMap.keySet();
					List<Order_listVO> list = new ArrayList<Order_listVO>();
					
					ProductSpecService specService = new ProductSpecService();
					for(CartListVO cartListVO : cartlistSet) {
						// �U���ƶq
						Integer itemQuantity = cartListVO.getItemQuantity();
						Order_listVO order_listVO = new Order_listVO();
						order_listVO.setProductSpecId(cartListVO.getProductSpecId());
						order_listVO.setPurchaseQuantity(itemQuantity);
						// ���ʪ������e���ӧO������*�ƥط�@�U�����p�p
						Integer price = specService.cartListGetPrice(cartListVO);
						order_listVO.setOrderCost(price * itemQuantity);
						// �N�q�ʪ������쪺�ݩ��ܦ��q����Өö�J�@��list�a�JaddMorderWithList(MorderVO morder, List<Order_listVO> list)
						list.add(order_listVO);
					}
					// ���~���
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
						// �s�W���\��A�����w�s�q
						ConsumeStock consumeStock = new ConsumeStock();
						for(Order_listVO orderList : list) {
							Integer stockAfterShopping =consumeStock.consumeStock(orderList);
							if(stockAfterShopping < 0) {
								errorMsgs.put("addError", "�w�s�q�����A�гs���t��");
								return;
							} else {
								specService.updateStock(orderList.getProductSpecId(), stockAfterShopping);
							}
						}
						
						// �s�W���\��A�M���ʪ���
						CartListService cartListService = new CartListService();
						cartListService.deleteCartList(userId);
						
						//�U�ӭ����ݭn���ݩ�
						List<Order_listVO> newList = orderListService.getOrderListByOrder(newOrderSN);
						MorderVO newMorder = morderService.getMorder(newOrderSN);
						String purchasingDate = DateTransform.TimeStampTranslatedToString(newMorder.getPurchaseDate());
						CouponVO coupon = couponService.getCoupon(newMorder.getCouponId());
						
						// �s�W���\��Nsession���F��M���b
						session.removeAttribute("shoppingCartMap");
						session.removeAttribute("totalCartList");
						// request forward���U�ӭ����һݭn������
						request.setAttribute("newMorder", newMorder);
						request.setAttribute("newList", newList);
						request.setAttribute("purchasingDate", purchasingDate);
						request.setAttribute("coupon", coupon);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/shopping-complete.jsp");
						dispatcher.forward(request, response);
						
					} catch(Exception e) {
						errorMsgs.put("addError", "�s�W�q�楢�� : " + e.getMessage());
					}
					
				} 
				// �I�ڤ覡���H�Υd�I�ڡA������H�Υd��ƭ���
				if(1 == paying) {
					
					CouponService couponService = new CouponService(); 
					CouponVO couponVO = couponService.getCouponBySn(couponValue);
					Integer discount = checkCoupon(couponValue); // �u�f��馩
					// ���u�f��馩�^��-1��ܵL�ŦX���u�f��A�^�ǿ��~�T��
					if(discount == -1) {
						errorMsgs.put("couponValue", "�d�L���u�f��A�Э��s��J");
					}
					
					Integer deliveryFee = getDeliveryFee(deliveryType); // �B�O
					Integer totalAmount = totalCartList.get("totalAmount"); // �ӫ~���`���B

					// �s�W�q��
					MorderVO morder = new MorderVO(); // �q��
					morder.setUserId(userId);
					
					// �u�f������������
					if(couponVO != null) {
						// �����ŦX�u�f�魭�����~�i�H���u�f�馩
						if(totalAmount >= new Integer(couponVO.getCouponInfo())) {
							// �u�f�餣���ŭȤ~set
							morder.setCouponId(couponVO.getCouponId());
							morder.setTotalPrice(totalAmount - discount + deliveryFee);
						} else {
							// ���ŦX�h�e�X���~�T��
							errorMsgs.put("couponValue", "�ϥ��u�f�鶷�ŦX" + couponVO.getCouponName());
						}
					} else {
						// �Y���ŭȫh�����p���`���B
						morder.setTotalPrice(totalAmount - discount + deliveryFee);
					}
					
					morder.setOrderPayment(paying);
					morder.setOrderDeliveyTypeId(deliveryType);
					morder.setReceiver(receiver);
					morder.setReceiverPhone(receiverPhone);
					morder.setReceiverAddress(receiverAddress);
					
					// �N�ʪ������ӫ~��i�q�����
					Set<CartListVO> cartlistSet = shoppingCartMap.keySet();
					List<Order_listVO> list = new ArrayList<Order_listVO>();
					
					ProductSpecService specService = new ProductSpecService();
					for(CartListVO cartListVO : cartlistSet) {
						// �U���ƶq
						Integer itemQuantity = cartListVO.getItemQuantity();
						Order_listVO order_listVO = new Order_listVO();
						order_listVO.setProductSpecId(cartListVO.getProductSpecId());
						order_listVO.setPurchaseQuantity(itemQuantity);
						// ���ʪ������e���ӧO������*�ƥط�@�U�����p�p
						Integer price = specService.cartListGetPrice(cartListVO);
						order_listVO.setOrderCost(price * itemQuantity);
						// �N�q�ʪ������쪺�ݩ��ܦ��q����Өö�J�@��list�a�JaddMorderWithList(MorderVO morder, List<Order_listVO> list)
						list.add(order_listVO);
					}
					// ���~���
					if(!errorMsgs.isEmpty()) {
						request.setAttribute("errorMsgs", errorMsgs);
						request.setAttribute("morder", morder);
						request.setAttribute("couponValue", couponValue);
						request.setAttribute("list", list);
						RequestDispatcher failure = request.getRequestDispatcher("/shopping/paying-delivery.jsp");
						failure.forward(request, response);
						return;
					}
					
					// ����H�Υd����
					session.setAttribute("morder", morder);
					session.setAttribute("couponValue", couponValue);
					session.setAttribute("list", list);
					RequestDispatcher creditCard = request.getRequestDispatcher("/shopping/shopping-creditcard.jsp");
					creditCard.forward(request, response);
				}
				
			}  // �t�e�覡:�W�Ө��f --> �f��I�ڤΫH�Υd�I��(���\��R��)
//			else if(14002 == deliveryType) {
//				
//			}
		}
		
		// �H�Υd��g��ƭ���
		if("aftercreditcard".equals(action)) {
			Map<String, String> errorMsgs = new HashMap<String, String>(); // ���~�T��
			String orderCard = request.getParameter("orderCard"); // �H�Υd�d��
			String orderCardMonth = request.getParameter("orderCardMonth"); // �H�Υd���
			String orderCardYear= request.getParameter("orderCardYear"); // �H�Υd�~��
			String cvvNumber= request.getParameter("cvvNumber");
			String cardRegex = "^[\\d]{4}-[\\d]{4}-[\\d]{4}-[\\d]{4}$";
			String cvvRegex = "^[\\d]{3}$";
			
			if(orderCard == null || orderCard.trim().length() == 0) {
				errorMsgs.put("orderCard", "�H�Υd���X���o���ť�");
			} else if(!orderCard.matches(cardRegex)) {
				errorMsgs.put("orderCard", "�H�Υd���X�榡���~�B���o�W�L6�X");
			}
			if(cvvNumber == null || cvvNumber.trim().length() == 0) {
				errorMsgs.put("cvvNumber", "�w���X���o���ť�");
			} else if(!cvvNumber.matches(cvvRegex)) {
				errorMsgs.put("cvvNumber", "�w���X����3�X�A�B�����Ʀr");
			}
			
			// �b�쥻morder���󤤥[�J�H�Υd��ƨ÷s�W�q��M�q�����
			MorderVO morder = (MorderVO)session.getAttribute("morder");
			List<Order_listVO> list = (List<Order_listVO>)session.getAttribute("list");
			morder.setOrderCard(orderCard);
			morder.setOrderCardMonth(orderCardMonth);
			morder.setOrderCardYear(orderCardYear);
			
			// ���~���
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
				
				// �s�W���\��A�����w�s�q
				ConsumeStock consumeStock = new ConsumeStock();
				for(Order_listVO orderList : list) {
					Integer stockAfterShopping =consumeStock.consumeStock(orderList);
					if(stockAfterShopping < 0) {
						errorMsgs.put("addError", "�w�s�q�����A�гs���t��");
						return;
					} else {
						specService.updateStock(orderList.getProductSpecId(), stockAfterShopping);
					}
				}
				
				// �s�W���\��A�M���ʪ���
				CartListService cartListService = new CartListService();
				cartListService.deleteCartList(userId);
				
				//�U�ӭ����ݭn���ݩ�
				List<Order_listVO> newList = orderListService.getOrderListByOrder(newOrderSN);
				MorderVO newMorder = morderService.getMorder(newOrderSN);
				String purchasingDate = DateTransform.TimeStampTranslatedToString(newMorder.getPurchaseDate());
				CouponService couponService = new CouponService();
				CouponVO coupon = couponService.getCoupon(newMorder.getCouponId());
				
				// �s�W���\��Nsession���F��M���b
				session.removeAttribute("shoppingCartMap");
				session.removeAttribute("totalCartList");
				// request forward���U�ӭ����һݭn������
				request.setAttribute("newMorder", newMorder);
				request.setAttribute("newList", newList);
				request.setAttribute("purchasingDate", purchasingDate);
				request.setAttribute("coupon", coupon);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/shopping-complete.jsp");
				dispatcher.forward(request, response);
				
			} catch(Exception e) {
				errorMsgs.put("addError", "�s�W�q�楢�� : " + e.getMessage());
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	// ����u�f��Ǹ��O�_�ŦX�æ^�ǧ馩
	public Integer checkCoupon(String couponValue) {
		Integer discount = new Integer(0);
		Map<String, String> errorMsgs = new HashMap<String, String>(); // ���~�T��
		// �u�f�餣���ŭ�
		if(couponValue.trim().length() != 0) {

			CouponService couponService = new CouponService();
			List<CouponVO> allCoupon = couponService.getAll();
			// ��X�ŦX�{�b�ɶ����u�f��A��ilist��
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<CouponVO> currentCoupon 
				=allCoupon.stream()
		                  .filter(c -> c.getCouponStarting().before(now))
		                  .filter(c -> c.getCouponEnding().after(now))
		                  .collect(Collectors.toList());
			
			// ����u�f��Ǹ��i����A�Y���ŦX�馩�X�h���X�馩�A�Y�L�ŦX�h�N�馩�]��-1
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
	
	// ���v�t�ζW�Ө��f�P�_�B�O
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
