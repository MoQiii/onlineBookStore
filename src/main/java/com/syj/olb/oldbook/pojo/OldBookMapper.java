package com.syj.olb.oldbook.pojo;

import com.syj.olb.book.pojo.Book;
import com.syj.olb.book.pojo.BookQuery;
import com.syj.olb.book.pojo.PageBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OldBookMapper {
 //   @Select("")
    public List<Book> findByCriteria(OldBookQuery bookQuery);
//    @Select("select count(*) from t_book where ")
    public int count(OldBookQuery bookQuery);
    @Select("SELECT * FROM t_book b, t_category c WHERE b.cid=c.cid AND b.bid=#{bid} and status='-1'")
    public Book findByBid(@Param("bid") String bid);

    public List<OldBook> findByBids(List<String> bids);
    @Select("select count(*) from t_oldbook  where bname like '%'#{bname}'%'")
    public int countByBname(@Param("bname") String bname);
    @Select("select * from t_oldbook  where bname like #{bname}  order by orderBy limit #{c},#{ps}")
    public List<OldBook> findByBname(@Param("bname") String bname, @Param("c") int c, @Param("ps") int ps);

    @Insert("insert into t_book(bid,bname,author,currPrice,price,publishtime,press,user_id,cid,image_b,image_w,status) values(#{oldBook.bid}," +
            "#{oldBook.bname},#{oldBook.author},#{oldBook.currPrice},#{oldBook.price},#{oldBook.publishtime},#{oldBook.press},#{oldBook.uid},#{oldBook.category.cid}" +
            ",#{oldBook.image_b},#{oldBook.image_w},#{oldBook.status})")
    public void addOldBook(@Param("oldBook") OldBook oldBook);

    @Select("select * from t_book where user_id=#{uid} and status='-1'")
    @Results(id = "t_oldbookResult", value = {
            @Result(property = "bid", column = "bid", id = true),
            @Result(property = "bname", column = "bname"),
            @Result(property = "author", column = "author"),
            @Result(property = "bname", column = "bname"),
            @Result(property = "price", column = "price"),
            @Result(property = "currPrice", column = "currPrice"),
            @Result(property = "press", column = "press"),
            @Result(property = "publishtime", column = "publishtime"),
            @Result(property = "edition", column = "edition"),
            @Result(property = "pageNum", column = "pageNum"),
            @Result(property = "wordNum", column = "wordNum"),
            @Result(property = "printtime", column = "printtime"),
            @Result(property = "booksize", column = "booksize"),
            @Result(property = "image_w", column = "image_w"),
            @Result(property = "image_b", column = "image_b"),
            @Result(property = "cid", column = "cid"),
            @Result(property = "user_id", column = "user_id"),
            @Result(property = "status", column = "status")
    })
    public List<Book> oldBookList(@Param("uid") String uid);

    @Delete("delete from t_oldbook where bid=#{bid}")
    public void  delete(@Param("bid") String bid);
}
