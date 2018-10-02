package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;

import java.util.List;

/**
 * @author ZZH
 * @date 2018/4/22 0022 14:50
 **/
public interface ICategoryService {

    ServerResponse addCategory(String categoryName , Integer parentId);

    ServerResponse updateCategoryName(Integer categoryId , String categoryName);

    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
}
