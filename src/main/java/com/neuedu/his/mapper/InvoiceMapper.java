package com.neuedu.his.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neuedu.his.bean.Invoice;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface InvoiceMapper extends BaseMapper {
	
	//得到记录数量     没有注释的考虑删除==1
	int getInvoiceCount(@Param("keywords") String keywords);
	
	//发票查询  发票补打使用的==2
    List<Map<String,Object>> getInvoiceList(@Param("start") Integer start, @Param("count") Integer count
            , @Param("keywords") String keywords, @Param("state") int state);
	
	
    //更新  发票补打/发票重打  使用==3
    int updateState(Map<String, Object> invoice);
    
    //发票查询  发票重打使用的==4
    List<Map<String,Object>> getInvoiceList02(@Param("start") Integer start,
                                              @Param("count") Integer count,
                                              @Param("keywords") String keywords,
                                              @Param("state1") int state1,
                                              @Param("state2") int state2);
    
    //添加发票
    int addInvoice(Map<String, Object> invoice);
    
    //取发票表的最大ID号  发票重打使用的
  	int getMaxInvId();
  	
  	//取发票表的最大的发票号
  	Invoice getInvoiceBean();
}
