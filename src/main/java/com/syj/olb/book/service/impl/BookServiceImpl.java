package com.syj.olb.book.service.impl;

import com.syj.olb.book.dao.BookDao;
import com.syj.olb.book.pojo.Book;
import com.syj.olb.book.pojo.BookQuery;
import com.syj.olb.book.pojo.PageBean;
import com.syj.olb.book.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Resource(name="bookDaoImpl")
    public BookDao bookDao;
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
    public void edit(Book book) {

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
        Book book = bookDao.findByBid(bid);
        return book;
    }

    /**
     * 加载图书
     *
     * @param bids
     * @return
     */
    @Override
    public List<Book> load(List<String> bids) {
        List<Book> load = bookDao.load(bids);
        return load;
    }

    /**
     * 按分类查
     *
     * @param bookQuery
     * @return
     */
    @Override
    public PageBean<Book> findByCategory(BookQuery bookQuery) {
        PageBean<Book> pb = bookDao.findByCategory(bookQuery);
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
        PageBean<Book> byBname = bookDao.findByBname(bname, pc);
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
        return null;
    }

    /**
     * 添加图书
     *
     * @param book
     */
    @Override
    public void add(Book book) {

    }
}
