package me.iqiuqiu.common.model.qo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class PageQO {

    @Range(min = 1, max = Integer.MAX_VALUE)
    private int pageNum;

    @Range(min = 1, max = Integer.MAX_VALUE)
    private int pageSize;



    public PageQO(int pageNum, int pageSize) {
        super();
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageQO() {
        super();
        this.pageNum = 1;
        this.pageSize = 10;
    }

    public int getOffset() {
        return (this.pageNum - 1) * this.pageSize;
    }

}