package com.syj.olb.cart.web;

import com.alibaba.fastjson.JSON;
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
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public ResponseEntity<CartItem> updateQuantity(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String cartItemId = req.getParameter("cartItemId");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        cartItemService.updateQuantity(cartItemId, quantity);
        CartItem cartItem = cartItemService.findById(cartItemId);
        // 给客户端返回一个json对象
        StringBuilder sb = new StringBuilder("{");
        sb.append("quantity").append(":").append(cartItem.getQuantity());
        sb.append(",");
        sb.append("subtotal").append(":").append(cartItem.getSubtotal());
        sb.append("}");
        resp.setHeader("Content-Type","application/json;charset=UTF-8");
        ResponseEntity<CartItem> cartItemResponseEntity = new ResponseEntity<CartItem>(cartItem,HttpStatus.OK);
        return cartItemResponseEntity;
    }
    /**
     * 批量删除功能
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/batchDelete")
    public String batchDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
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
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/add")
    public String add(HttpServletRequest req, HttpServletResponse resp,CartItem cartItem)
            throws ServletException, IOException {
        /*
         * 1. 封装表单数据到CartItem(bid, quantity)
         */
   //     Map map = req.getParameterMap();
//        CartItem cartItem = CommonUtils.toBean(map, CartItem.class);
        Book book =new Book();
        book.setBid(req.getParameter("bid"));
        User user = (User)req.getSession().getAttribute("sessionUser");
        if(!Objects.nonNull(user)){
            return "jsps/user/login";
        }
        cartItem.setBook(book);
        cartItem.setUser(user);

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
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/myCart")
    public String myCart(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
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
    public String myCart1(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
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
