package com.luckwine.goods.handle.local.category;


import com.luckwine.goods.dao.CategoryMapper;
import com.luckwine.goods.dao.SpuMapper;
import com.luckwine.goods.model.base.Category;
import com.luckwine.goods.model.base.Spu;
import com.luckwine.goods.model.request.category.CategoryDeleteRequest;
import com.luckwine.parent.entitybase.exception.ParamErrorException;
import com.luckwine.parent.entitybase.request.CommonRequest;
import com.luckwine.parent.template.SingleTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class CategoryDeleteService extends SingleTemplate<CategoryDeleteRequest, Boolean> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private SpuMapper spuMapper;

    @Override
    protected Boolean callInner(CommonRequest<CategoryDeleteRequest> request) throws Exception {
        Long categoryId = request.getRequest().getId();
        //判断是否有该分类
        Category category=categoryMapper.selectByPrimaryKey(categoryId);
        if(category!=null ){
            //判断该分类是否有spu
            Example example = new Example(Spu.class);
            example.createCriteria().andEqualTo("categoryId",category.getId());
            int row = spuMapper.selectCountByExample(example);
            if(row>0){
                throw new ParamErrorException("删除错误，该分类下有商品");
            }else {
                //判断是否有分类删除成功
                int i = categoryMapper.deleteByPrimaryKey(categoryId);
                if(i>0){
                    return true;
                }else {
                    return false;
                }
            }
        }else {
            return false;
        }
    }
}
