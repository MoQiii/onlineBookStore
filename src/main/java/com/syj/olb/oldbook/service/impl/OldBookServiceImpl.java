package com.syj.olb.oldbook.service.impl;

import com.syj.olb.book.dao.BookDao;
import com.syj.olb.book.pojo.Book;
import com.syj.olb.book.pojo.BookQuery;
import com.syj.olb.book.pojo.PageBean;
import com.syj.olb.book.service.BookService;
import com.syj.olb.oldbook.dao.OldBookDao;
import com.syj.olb.oldbook.pojo.OldBook;
import com.syj.olb.oldbook.pojo.OldBookQuery;
import com.syj.olb.oldbook.service.OldBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OldBookServiceImpl implements OldBookService {

    @Resource(name="oldBookDaoImpl")
    public OldBookDao bookDao;
    /**
     * 删除图书
     *
     * @param bid
     */
    @Override
    public void delete(String bid) {

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
    public OldBook load(String bid) {
        OldBook book = bookDao.findByBid(bid);
        return book;
    }

    /**
     * 加载图书
     *
     * @param bids
     * @return
     */
    @Override
    public List<OldBook> load(List<String> bids) {
        List<OldBook> load = bookDao.load(bids);
        return load;
    }

    /**
     * 按分类查
     *
     * @param bookQuery
     * @return
     */
    @Override
    public PageBean<OldBook> findByCategory(OldBookQuery bookQuery) {
        PageBean<OldBook> pb = bookDao.findByCategory(bookQuery);
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
        PageBean<OldBook> byBname = bookDao.findByBname(bname, pc);
        return byBname;
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
        bookDao.add(book);
    }
}
