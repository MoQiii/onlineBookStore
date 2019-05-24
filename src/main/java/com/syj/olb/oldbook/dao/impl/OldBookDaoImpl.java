package com.syj.olb.oldbook.dao.impl;

import com.syj.olb.book.pojo.*;
import com.syj.olb.oldbook.dao.OldBookDao;
import com.syj.olb.oldbook.pojo.OldBook;
import com.syj.olb.oldbook.pojo.OldBookMapper;
import com.syj.olb.oldbook.pojo.OldBookQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OldBookDaoImpl implements OldBookDao {

    @Autowired
    public OldBookMapper oldBookMapper;

    @Override
    public List<Book> oldBookList(String uid) {

        return oldBookMapper.oldBookList(uid);
    }

    /**
     * 删除图书
     *
     * @param bid
     */
    @Override
    public void delete(String bid) {
        oldBookMapper.delete(bid);
    }

    /**
     * 修改图书
     *
     * @param book
     */
    @Override
    public void edit(OldBook book) {

    }

    /**
     * 返回当前分类下图书个数
     *
     * @param cid
     * @return
     */
    @Override
    public int findBookCountByCategory(String cid) {
        return 0;
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
    public List<OldBook> load(List<String> bids) {
        List<OldBook> byBids = oldBookMapper.findByBids(bids);
        return byBids;
    }

    /**
     * 按分类查
     *
     * @param bookQuery
     * @return
     */
    @Override
    public PageBean<Book> findByCategory(OldBookQuery bookQuery) {
        int count = oldBookMapper.count(bookQuery);
        List<Book> byCriteria = oldBookMapper.findByCriteria(bookQuery);
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
    public PageBean<OldBook> findByBname(String bname, int pc) {
        /*
         * 1. 得到ps
         */
        int ps = PageConstants.BOOK_PAGE_SIZE;//每页记录数
        int tr = oldBookMapper.countByBname(bname);
        List<OldBook> beanList = oldBookMapper.findByBname(bname,(pc-1) * ps,ps);
        /*
         * 5. 创建PageBean，设置参数
         */
        PageBean<OldBook> pb = new PageBean<OldBook>();
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
    public PageBean<OldBook> findByAuthor(String author, int pc) {
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
    public PageBean<OldBook> findByPress(String press, int pc) {
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
    public PageBean<OldBook> findByCombination(OldBook criteria, int pc) {
        return null;
    }

    /**
     * 添加图书
     *
     * @param book
     */
    @Override
    public void add(OldBook book) {
        oldBookMapper.addOldBook(book);
    }

    /**
     * 按bid查询
     *
     * @param bid
     * @return
     */
    @Override
    public Book findByBid(String bid) {
        Book book = oldBookMapper.findByBid(bid);
        return book;
    }
}
