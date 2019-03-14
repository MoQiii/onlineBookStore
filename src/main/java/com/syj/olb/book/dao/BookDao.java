package com.syj.olb.book.dao;

import com.syj.olb.book.pojo.Book;
import com.syj.olb.book.pojo.BookQuery;
import com.syj.olb.book.pojo.PageBean;

import java.util.List;

public interface BookDao {
    /**
     * 删除图书
     * @param bid
     */
    public void delete(String bid);
    /**
     * 修改图书
     * @param book
     */
    public void edit(Book book);
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
    public List<Book> load(List<String> bids);
    /**
     * 按分类查
     * @param bookQuery
     * @return
     */
    public PageBean<Book> findByCategory(BookQuery bookQuery);

    /**
     * 按书名查
     * @param bname
     * @param pc
     * @return
     */
    public PageBean<Book> findByBname(String bname, int pc);
    /**
     * 按作者查
     * @param author
     * @param pc
     * @return
     */
    public PageBean<Book> findByAuthor(String author, int pc);
    /**
     * 按出版社查
     * @param press
     * @param pc
     * @return
     */
    public PageBean<Book> findByPress(String press, int pc);
    /**
     * 多条件组合查询
     * @param criteria
     * @param pc
     * @return
     */
    public PageBean<Book> findByCombination(Book criteria, int pc);
    /**
     * 添加图书
     * @param book
     */
    public void add(Book book);
    /**
     * 按bid查询
     * @param bid
     * @return
     */
    public Book findByBid(String bid);
}
