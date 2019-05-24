package com.syj.olb.book.pojo;

import com.syj.olb.category.pojo.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Data
public class BookQuery {
    private String bid;//主键
    private String bname;//图名
    private String author;//作者
    private double price;//定价
    private double currPrice;//当前价
    private double discount;//折扣
    private String press;//出版社
    private String publishtime;//出版时间
    private int edition;//版次
    private int pageNum;//页数
    private int wordNum;//字数
    private String printtime;//刷新时间
    private int booksize;//开本
    private String paper;//纸质
    private Category category;//所属分类
    private String image_w;//大图路径
    private String image_b;//小图路径
    private String cid;
    private int pc;
    private int orderBy;
    private String status;
}
