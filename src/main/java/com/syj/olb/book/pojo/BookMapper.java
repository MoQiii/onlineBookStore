package com.syj.olb.book.pojo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {
 //   @Select("")
    public List<Book> findByCriteria(BookQuery bookQuery);
//    @Select("select count(*) from t_book where ")
    public int count(BookQuery bookQuery);
    @Select("SELECT * FROM t_book b, t_category c WHERE b.cid=c.cid AND b.bid=#{bid}")
    public Book findByBid(@Param("bid") String bid);

    public List<Book> findByBids(List<String> bids);
    @Select("select count(*) from t_book  where bname like '%'#{bname}'%'")
    public int countByBname( @Param("bname") String bname);
    @Select("select * from t_book  where bname like #{bname}  order by orderBy limit #{c},#{ps}")
    public List<Book> findByBname( @Param("bname") String banme, @Param("c")int c, @Param("ps")int ps);
}
