package com.syj.olb.oldbook.dao;

import com.syj.olb.book.pojo.Book;
import com.syj.olb.book.pojo.BookQuery;
import com.syj.olb.book.pojo.PageBean;
import com.syj.olb.oldbook.pojo.OldBook;
import com.syj.olb.oldbook.pojo.OldBookQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OldBookDao {
    public List<Book> oldBookList( String uid);
    /**
     * 删除图书
     * @param bid
     */
    public void delete(String bid);
    /**
     * 修改图书
     * @param book
     */
    public void edit(OldBook book);
    /**
     * 返回当前分类下图书个数
     * @param cid
     * @return
     */
    public int findBookCountByCategory(String cid);
    /**
     * 加载图书
     * @param bid
     * @return
     */
    public Book load(String bid);
    /**
     * 加载图书
     * @param bids
     * @return
     */
    public List<OldBook> load(List<String> bids);
    /**
     * 按分类查
     * @param bookQuery
     * @return
     */
    public PageBean<Book> findByCategory(OldBookQuery bookQuery);

    /**
     * 按书名查
     * @param bname
     * @param pc
     * @return
     */
    public PageBean<OldBook> findByBname(String bname, int pc);
    /**
     * 按作者查
     * @param author
     * @param pc
     * @return
     */
    public PageBean<OldBook> findByAuthor(String author, int pc);
    /**
     * 按出版社查
     * @param press
     * @param pc
     * @return
     */
    public PageBean<OldBook> findByPress(String press, int pc);
    /**
     * 多条件组合查询
     * @param criteria
     * @param pc
     * @return
     */
    public PageBean<OldBook> findByCombination(OldBook criteria, int pc);
    /**
     * 添加图书
     * @param book
     */
    public void add(OldBook book);
    /**
     * 按bid查询
     * @param bid
     * @return
     */
    public Book findByBid(String bid);
}
