package com.geeklin.pojo;

import java.util.HashSet;
import java.util.List;

/**
 * @author Lin
 * @date 2020/8/1 15:48
 */

/**
 * Page是分页的数据模型<br>
 * @param <T> 是具体分页的数据类型
 */
public class Page<T> {

    public static final Integer PAGE_SIZE = 4;

    //当前页码
    private Integer pageNo;
    //总页码
    private Integer pageTotal;

    //总记录数（总数据量）
    private Integer pageTotalCount;

    //每页显示的数据量
    private Integer pageSize = PAGE_SIZE;

    //当前页数据
    private List<T> items;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //添加分页条的请求地址
    private String url;

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", pageSize=" + pageSize +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {

        //设置当前页码
        if (pageNo <1){
            pageNo =1;
        }else if (pageNo> pageTotal){
            pageNo = pageTotal;
        }

        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Page() {
    }

    public Page(Integer pageTotal, Integer pageTotalCount, Integer pageSize, List<T> items) {
        this.pageTotal = pageTotal;
        this.pageTotalCount = pageTotalCount;
        this.pageSize = pageSize;
        this.items = items;
    }
}
