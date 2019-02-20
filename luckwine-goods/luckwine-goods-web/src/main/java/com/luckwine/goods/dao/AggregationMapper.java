package com.luckwine.goods.dao;

import com.luckwine.goods.model.base.vo.SkuDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AggregationMapper {

    @Select(" select p.id as spu_id, p.goods_name, p.category_id, c.category_name, p.brand_id ,b.brand_name, p.title, p.props, p.props_str, p.pic_path,p.detail,  p.`status` ,p.create_time as spu_create_time , k.id as sku_id, k.sku_name, k.props as sku_props, k.props_str as sku_props_str, k.quantity, k.sale_count, k.price, k.`status` as sku_status , k.create_time as sku_create_time from spu p LEFT JOIN sku k on p.id = k.spu_id  LEFT JOIN brand b on p.brand_id = b.id  LEFT JOIN  category c on c.id = p.category_id where p.id = #{spuId}")
    List<SkuDetail> findBySpuId(String spuId);

    @Select(" select p.id as spu_id, p.goods_name, p.category_id, c.category_name, p.brand_id ,b.brand_name, p.title, p.props, p.props_str, p.pic_path,p.detail,  p.`status` ,p.create_time as spu_create_time , k.id as sku_id, k.sku_name, k.props as sku_props, k.props_str as sku_props_str, k.quantity, k.sale_count, k.price, k.`status` as sku_status , k.create_time as sku_create_time from spu p LEFT JOIN sku k on p.id = k.spu_id  LEFT JOIN brand b on p.brand_id = b.id  LEFT JOIN  category c on c.id = p.category_id")
    List<SkuDetail> findAll();

    @Select({
            "<script>",
            "select p.id as spu_id, p.goods_name, p.category_id, c.category_name, p.brand_id ,b.brand_name, p.title, p.props, p.props_str, p.pic_path,p.detail,  p.`status` ,p.create_time as spu_create_time , k.id as sku_id, k.sku_name, k.props as sku_props, k.props_str as sku_props_str, k.quantity, k.sale_count, k.price, k.`status` as sku_status , k.create_time as sku_create_time from spu p LEFT JOIN sku k on p.id = k.spu_id  LEFT JOIN brand b on p.brand_id = b.id  LEFT JOIN  category c on c.id = p.category_id",
            "where k.id in(",
            "<foreach item='item'  collection='ids'  separator=','> ",
            "#{item}",
            "</foreach>)",
            "</script>"
    })
    List<SkuDetail> findBySkuIds(@Param("ids") List<Long> ids);
}
