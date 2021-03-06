package com.syj.olb.book.pojo;

import org.apache.ibatis.annotations.Delete;
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
    @Select("SELECT b.* FROM t_book b, t_category c WHERE b.cid=c.cid AND b.bid=#{bid}")
    public Book findByBid(@Param("bid") String bid);

    public List<Book> findByBids(List<String> bids);
    @Select("select count(*) from t_book  where bname like '%'#{bname}'%'")
    public int countByBname( @Param("bname") String bname);
    @Select("select * from t_book  where bname like #{bname}  order by orderBy limit #{c},#{ps}")
    public List<Book> findByBname( @Param("bname") String banme, @Param("c")int c, @Param("ps")int ps);
    @Select("select count(*) from t_book where cid=#{cid}")
    public int findBookCountByCategory(@Param("cid") String cid);
    @Select("update t_book set bname=#{book.bname},author=#{book.author},price=#{book.price},currPrice=#{book.currPrice},"+
            "discount=#{book.discount},press=#{book.press},publishtime=#{book.publishtime},edition=#{book.edition},pageNum=#{book.pageNum},wordNum=#{book.wordNum}," +
            "printtime=#{book.printtime},booksize=#{book.booksize},paper=#{book.paper},cid=#{book.cid} where bid=#{book.bid}")
    public void edit(@Param("book") Book book);
    @Delete("delete from t_book where bid=#{bid}")
    public void delete(@Param("bid") String bid);
    @Select("<script>" +
            "select * from  t_book where 1=1" +
            "<if test=\"criteria.bname!='' and criteria.bname!=null\">and bname like  concat(concat('%',#{criteria.bname}),'%') </if>"+
            "<if test=\"criteria.author!='' and criteria.author!=null\">and author like  concat(concat('%',#{criteria.author}),'%')</if>"+
            "<if test=\"criteria.press!='' and criteria.press!=null\">and press like  concat(concat('%',#{criteria.press}),'%')</if>"+
            " order by orderBy limit #{pc},#{ps}"+
            "</script>")
    public List<Book> findByCombination(@Param("criteria") Book criteria, @Param("pc") int pc,@Param("ps") int ps);
}
