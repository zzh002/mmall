package com.mmall.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by geely
 */
@Getter
@Setter
public class CartVo {

    private List<CartProductVo> cartProductVoList;
    private BigDecimal cartTotalPrice;
    private Boolean allChecked;//是否已经都勾选
    private String imageHost;

}
