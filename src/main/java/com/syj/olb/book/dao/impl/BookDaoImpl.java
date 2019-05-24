package com.syj.olb.book.dao.impl;

import com.syj.olb.book.dao.BookDao;
import com.syj.olb.book.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    public BookMapper bookMapper;
    /**
     * 删除图书
     *
     * @param bid
     */
    @Override
    public void delete(String bid) {
        bookMapper.delete(bid);
    }

    /**
     * 修改图书
     *
     * @param book
     */
    @Override
    public void edit(Book book) {
        bookMapper.edit(book);
    }

    /**
     * 返回当前分类下图书个数
     *
     * @param cid
     * @return
     */
    @Override
    public int findBookCountByCategory(String cid) {
        return bookMapper.findBookCountByCategory(cid);
    }

    /**
     * 加载图书
     *
     * @param bid
     * @return
     */
    @Override
    public Book load(String bid) {
        return null;
    }

    /**
     * 加载图书
     *
     * @param bids
     * @return
     */
    @Override
    public List<Book> load(List<String> bids) {
        List<Book> byBids = bookMapper.findByBids(bids);
        return byBids;
    }

    /**
     * 按分类查
     *
     * @param bookQuery
     * @return
     */
    @Override
    public PageBean<Book> findByCategory(BookQuery bookQuery) {
        int count = bookMapper.count(bookQuery);
        List<Book> byCriteria = bookMapper.findByCriteria(bookQuery);
        PageBean<Book> pb = new PageBean<Book>();
        pb.setBeanList(byCriteria);
        return pb;
    }

    /**
     * 按书名查
     *
     * @param bname
     * @param pc
     * @return
     */
    @Override
    public PageBean<Book> findByBname(String bname, int pc) {
        /*
         * 1. 得到ps
         */
        int ps = PageConstants.BOOK_PAGE_SIZE;//每页记录数
        int tr = bookMapper.countByBname(bname);
        List<Book> beanList = bookMapper.findByBname(bname,(pc-1) * ps,ps);
        /*
         * 5. 创建PageBean，设置参数
         */
        PageBean<Book> pb = new PageBean<Book>();
        /*
         * 其中PageBean没有url，这个任务由Servlet完成
         */
        pb.setBeanList(beanList);
        pb.setPc(pc);
        pb.setPs(ps);
        pb.setTr(tr);
        return pb;
    }

    /**
     * 按作者查
     *
     * @param author
     * @param pc
     * @return
     */
    @Override
    public PageBean<Book> findByAuthor(String author, int pc) {
        return null;
    }

    /**
     * 按出版社查
     *
     * @param press
     * @param pc
     * @return
     */
    @Override
    public PageBean<Book> findByPress(String press, int pc) {
        return null;
    }

    /**
     * 多条件组合查询
     *
     * @param criteria
     * @param pc
     * @return
     */
    @Override
    public PageBean<Book> findByCombination(Book criteria, int pc) {
        List<Book> books = bookMapper.findByCombination(criteria, (pc - 1) * 8, 8);
        PageBean<Book> pb=new PageBean<>();
        pb.setBeanList(books);
        return pb;
    }

    /**
     * 添加图书
     *
     * @param book
     */
    @Override
    public void add(Book book) {

    }

    /**
     * 按bid查询
     *
     * @param bid
     * @return
     */
    @Override
    public Book findByBid(String bid) {
        return bookMapper.findByBid(bid);
    }
}
