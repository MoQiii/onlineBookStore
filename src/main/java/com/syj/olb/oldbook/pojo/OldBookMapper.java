package com.syj.olb.oldbook.pojo;

import com.syj.olb.book.pojo.Book;
import com.syj.olb.book.pojo.BookQuery;
import com.syj.olb.book.pojo.PageBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OldBookMapper {
 //   @Select("")
    public List<OldBook> findByCriteria(OldBookQuery bookQuery);
//    @Select("select count(*) from t_book where ")
    public int count(OldBookQuery bookQuery);
    @Select("SELECT * FROM t_oldbook b, t_category c WHERE b.cid=c.cid AND b.bid=#{bid}")
    public OldBook findByBid(@Param("bid") String bid);

    public List<OldBook> findByBids(List<String> bids);
    @Select("select count(*) from t_oldbook  where bname like '%'#{bname}'%'")
    public int countByBname(@Param("bname") String bname);
    @Select("select * from t_oldbook  where bname like #{bname}  order by orderBy limit #{c},#{ps}")
    public List<OldBook> findByBname(@Param("bname") String bname, @Param("c") int c, @Param("ps") int ps);

    @Insert("insert into t_oldbook(bid,bname,author,price,original_price,publishtime,press,user_id,cid,image_b,image_w) values(#{oldBook.bid}," +
            "#{oldBook.bname},#{oldBook.author},#{oldBook.price},#{oldBook.originalPrice},#{oldBook.publishtime},#{oldBook.press},#{oldBook.uid},#{oldBook.category.cid}" +
            ",#{oldBook.image_b},#{oldBook.image_w})")
    public void addOldBook(@Param("oldBook") OldBook oldBook);

    @Select("select * from t_oldbook where user_id=#{uid}")
    public List<OldBook> oldBookList(@Param("uid") String uid);

    @Delete("delete from t_oldbook where bid=#{bid}")
    public void  delete(@Param("bid") String bid);
}
