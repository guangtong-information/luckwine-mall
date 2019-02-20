package com.luckwine.goods.utils;

import com.luckwine.goods.dao.BrandMapper;
import com.luckwine.goods.dao.CategoryMapper;
import com.luckwine.goods.dao.PropsKeyMapper;
import com.luckwine.goods.dao.PropsValueMapper;
import com.luckwine.goods.model.base.Brand;
import com.luckwine.goods.model.base.Category;
import com.luckwine.goods.model.base.PropsKey;
import com.luckwine.goods.model.base.PropsValue;
import com.luckwine.goods.model.response.Props;
import com.luckwine.parent.entitybase.exception.ParamErrorException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@Slf4j
@CacheConfig
public class GoodsUtils {
    @Autowired
    private PropsKeyMapper propsKeyMapper;

    @Autowired
    private PropsValueMapper propsValueMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Cacheable(cacheNames = "GOODS_BRANDS", key = "#root.targetClass.simpleName+#root.methodName+#ids")
    public List<Brand> getBrands(Set<Long> ids) {
        Example exampleBrand = new Example(Brand.class);
        exampleBrand.createCriteria().andIn("id", ids);
        return brandMapper.selectByExample(exampleBrand);
    }


    public String getBrandName(Long id) {
        List<Brand> brands = getBrands(new HashSet<>(Arrays.asList(id)));
        return CollectionUtils.isEmpty(brands) ? null : brands.get(0).getBrandName();
    }


    public String getCategoryName(Long id) {
        List<Category> brands = getCategorys(new HashSet<>(Arrays.asList(id)));
        return CollectionUtils.isEmpty(brands) ? null : brands.get(0).getCategoryName();
    }


    @Cacheable(cacheNames = "GOODS_CATEGORYS", key = "#root.targetClass.simpleName+#root.methodName+#ids")
    public List<Category> getCategorys(Set<Long> ids) {
        Example exampleCategory = new Example(Category.class);
        exampleCategory.createCriteria().andIn("id", ids);
        return categoryMapper.selectByExample(exampleCategory);
    }


    @Cacheable(cacheNames = "GOODS_PROPS_KEYS", key = "#root.targetClass.simpleName+#root.methodName+#keyList")
    public List<PropsKey> getDbKeyList(Set<Long> keyList) {
        Example exampleKey = new Example(PropsKey.class);
        exampleKey.createCriteria().andIn("id", keyList);
        return propsKeyMapper.selectByExample(exampleKey);
    }


    @Cacheable(cacheNames = "GOODS_PROPS_VALUES", key = "#root.targetClass.simpleName+#root.methodName+#valueList")
    public List<PropsValue> getDbValueList(Set<Long> valueList) {
        Example exampleValue = new Example(PropsValue.class);
        exampleValue.createCriteria().andIn("id", valueList);
        return propsValueMapper.selectByExample(exampleValue);
    }


    /**
     * 根据字符串获取 属性的key,与value
     *
     * @return
     */
    public List<Props> getPropsList(String propsStr) {
        Set<Long> keyList = new HashSet<>();
        Set<Long> valueList = new HashSet<>();
        //按;取出每一项属性
        String[] propsItems = propsStr.split(";");
        List<Props> propes = new ArrayList<>();
        for (String item : propsItems) {
            boolean isMatch = Pattern.matches("^[1-9]\\d*:[1-9]\\d*", item);
            if (isMatch) {
                String[] str = item.split(":");
                Props props = new Props();
                props.setKey(str[0]);
                props.setValue(str[1]);
                keyList.add(Long.valueOf(str[0]));
                valueList.add(Long.valueOf(str[1]));
                propes.add(props);
            } else {
                throw new ParamErrorException(item + "不符合规则");
            }
        }


        List<PropsKey> dbKeyList = getDbKeyList(keyList);
        if (dbKeyList.size() != keyList.size()) {
            throw new ParamErrorException("存在错误key");
        }

        List<PropsValue> dbValueList = getDbValueList(valueList);
        if (dbValueList.size() != valueList.size()) {
            throw new ParamErrorException("存在错误value");
        }

        Map<Long, String> keyMap = dbKeyList.stream().collect(Collectors.toMap(PropsKey::getId, PropsKey::getKey));
        Map<Long, String> valueMap = dbValueList.stream().collect(Collectors.toMap(PropsValue::getId, PropsValue::getValue));
        for (Props props : propes) {
            props.setKeyStr(keyMap.get(Long.valueOf(props.getKey())));
            props.setValueStr(valueMap.get(Long.valueOf(props.getValue())));
        }


        log.info("props:{}", propes);
        return propes;
    }


    public String getPropsListStr(List<Props> list) {
        if (list.size() == 1) {
            return list.get(0).getItemStr();
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                stringBuffer.append(list.get(i).getItemStr());
                if (i != list.size() - 1) {
                    stringBuffer.append(";");
                }
            }
            return stringBuffer.toString();
        }
    }
}

