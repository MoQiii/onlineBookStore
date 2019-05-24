package com.syj.olb.cart.web;


import com.syj.olb.book.pojo.Book;
import com.syj.olb.book.service.BookService;
import com.syj.olb.cart.domain.CartItem;
import com.syj.olb.cart.service.CartItemService;
import com.syj.olb.user.pojo.User;
import com.syj.olb.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/Cart")
public class CartItemController {
    @Resource(name="cartItemServiceImpl")
    private CartItemService cartItemService;

    @Resource(name="bookServiceImpl")
    private BookService bookService;

    @Resource(name="userServiceImpl")
    private UserService userService;
    /**
     * 加载多个CartItem
     * @param req
     * @param resp
     */
    @RequestMapping("/loadCartItems")
    public String loadCartItems(HttpServletRequest req, HttpServletResponse resp)
    {
        /*
         * 1. 获取cartItemIds参数
         */
        String cartItemIds = req.getParameter("cartItemIds");
        double total = Double.parseDouble(req.getParameter("total"));
        /*
         * 2. 通过service得到List<CartItem>
         */
        List<CartItem> cartItemList = cartItemService.loadCartItems(cartItemIds);
        List<String> bidList=new ArrayList<>();
        for(CartItem cartItem:cartItemList){
            bidList.add(cartItem.getBid());
        }
        List<Book> books = bookService.load(bidList);
       for(int i=0;i<books.size();i++){
           cartItemList.get(i).setBook(books.get(i));
       }
        /*
         * 3. 保存，然后转发到/cart/showitem.jsp
         */
        req.setAttribute("cartItemList", cartItemList);
        req.setAttribute("total", total);
        req.setAttribute("cartItemIds", cartItemIds);
        return "jsps/cart/showitem";
    }
    @RequestMapping("/updateQuantity")
    @ResponseBody
    public ResponseEntity<CartItem> updateQuantity(HttpServletRequest req, HttpServletResponse resp){
        Integer quantity = Integer.parseInt(req.getParameter("quantity"));
        String cartItemId = req.getParameter("cartItemId");
        CartItem cartItem = cartItemService.findById(cartItemId);

        cartItem.setQuantity(quantity);
     //   BigDecimal bid=new BigDecimal();
        BigDecimal currPrice = BigDecimal.valueOf(Double.valueOf(req.getParameter("price")));
        cartItem.setSubTotal(currPrice.multiply(BigDecimal.valueOf(quantity)));
        cartItem.setSubTotalStr(cartItem.getSubTotal().toString());
        cartItemService.updateQuantity(cartItem);
     //   CartItem cartItem = cartItemService.findById(cartItemId);
        // 给客户端返回一个json对象
        /*StringBuilder sb = new StringBuilder("{");
        sb.append("quantity").append(":").append(cartItem.getQuantity());
        sb.append(",");
        sb.append("subtotal").append(":").append(cartItem.getSubTotal().toString());
        sb.append("}");
        resp.setHeader("Content-Type","application/json;charset=UTF-8");*/
        ResponseEntity<CartItem> cartItemResponseEntity = new ResponseEntity<>(cartItem,HttpStatus.OK);
        return cartItemResponseEntity;
    }
    /**
     * 批量删除功能
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/batchDelete")
    public String batchDelete(HttpServletRequest req, HttpServletResponse resp){
        /*
         * 1. 获取cartItemIds参数
         * 2. 调用service方法完成工作
         * 3. 返回到list.jsp
         */
        String cartItemIds = req.getParameter("cartItemIds");
        List<String> ids =new ArrayList<>();
        if(cartItemIds.contains(",")){
            String[] split = cartItemIds.split(",");
           ids = Arrays.asList(split);
        }
        else{
            ids.add(cartItemIds);
        }

        cartItemService.batchDelete(ids);
        return myCart(req, resp);
    }
    /**
     * 添加购物车条目
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/add")
    public String addolbcart(HttpServletRequest req, HttpServletResponse resp,CartItem cartItem){
        User user = (User)req.getSession().getAttribute("sessionUser");
        if(!Objects.nonNull(user)){
            //未登录跳转到登录界面
            return "jsps/user/login";
        }
        //存书商品信、用户信息到购物车对象
        String bid = req.getParameter("bid");
        Book book = bookService.load(bid);
        BigDecimal subTotal= new BigDecimal(book.getCurrPrice()*cartItem.getQuantity());
        cartItem.setSubTotal(subTotal);
        cartItem.setBid(bid);
        cartItem.setQuantity(cartItem.getQuantity());
        cartItem.setUid(user.getUid());
        cartItem.setPrice(BigDecimal.valueOf(book.getCurrPrice()));
        /*
         * 2. 调用service完成添加
         */
        cartItemService.add(cartItem);
        /*
         * 3. 查询出当前用户的所有条目，转发到list.jsp显示
         */
        return myCart1(req, resp);
    }
    /**
     * 我的购物车
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/myCart")
    public String myCart(HttpServletRequest req, HttpServletResponse resp) {
        /*
         * 1. 得到uid
         */
        User user = (User)req.getSession().getAttribute("sessionUser");
        String uid = user.getUid();
        /*
         * 2. 通过service得到当前用户的所有购物车条目
         */
        List<CartItem> cartItemLIst = cartItemService.myCart(uid);
  //      List<String> uids=new ArrayList<>();
        List<String> bids=new ArrayList<>();
        for (CartItem cartItem:cartItemLIst){
            String bid = cartItem.getBid();
            bids.add(bid);
            /*String uid1 = cartItem.getUid();
            uids.add(uid1);*/
        }
        List<Book> books = bookService.load(bids);
        if(books!=null && books.size()>0){
            for(int i=0;i<cartItemLIst.size();i++){
                cartItemLIst.get(i).setBook(books.get(i));
            }
        }
        /*
         * 3. 保存起来，转发到/cart/list.jsp
         */
        req.setAttribute("cartItemList", cartItemLIst);
        return "jsps/cart/list";
    }
    public String myCart1(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User)req.getSession().getAttribute("sessionUser");
        String uid = user.getUid();
        int a=0;
        List<CartItem> cartItemLIst = cartItemService.myCart(uid);
        List<String> uids=new ArrayList<>();
        List<String> bids=new ArrayList<>();
        for (CartItem cartItem:cartItemLIst){
            String bid = cartItem.getBid();
            bids.add(bid);
            /*String uid1 = cartItem.getUid();
            uids.add(uid1);*/
        }
        List<Book> books = bookService.load(bids);
        if(books!=null && books.size()>0){
            for(int i=0;i<cartItemLIst.size();i++){
                cartItemLIst.get(i).setBook(books.get(i));
            }
        }
        req.setAttribute("cartItemList", cartItemLIst);
        return "jsps/cart/list";
    }
}
