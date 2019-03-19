package com.syj.olb.category.pojo;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Data
public class Category {
    private String cid;// 主键
    private String cname;// 分类名称
    private Category parent;// 父分类
    private String description;// 分类描述
    private List<Category> children;// 子分类
    private String orderBy;
    private String pid;
}
