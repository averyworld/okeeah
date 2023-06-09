package com.athl.gulimall.product.controller;

import com.athl.common.utils.PageUtils;
import com.athl.common.utils.R;
import com.athl.gulimall.product.entity.ProductAttrValueEntity;
import com.athl.gulimall.product.entity.vo.AttrResVo;
import com.athl.gulimall.product.entity.vo.AttrVo;
import com.athl.gulimall.product.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 商品属性
 *
 * @author huanglin
 * @email 2465652971@qq.com
 * @date 2020-07-16 15:28:09
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    @PostMapping("/update/{spuId}")
    public R updateSpecification(@PathVariable("spuId") Long spuId,
                                 @RequestBody List<ProductAttrValueEntity> productAttrValueEntities) {
        attrService.updateSpecification(spuId, productAttrValueEntities);
        return R.ok();
    }

    /**
     * 获取分类规格参数
     */

    @GetMapping("/base/list/{catelogId}")
    //Map<String, Object> params 获取分页参数
    public R baseList(@RequestParam Map<String, Object> params, Long catelogId) {
        PageUtils page = attrService.queryBaseAttrPage(params, catelogId);
        return R.ok().put("page", page);
    }

    /**
     * 获取spu规格
     *
     * @return
     */
    @GetMapping("/base/listforspu/{spuId}")
    public R getSpuSpecification(@PathVariable("spuId") Long spuId) {
        List<ProductAttrValueEntity> spuSpecification = attrService.getSpuSpecification(spuId);
        return R.ok().put("data", spuSpecification);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/{attrType}/list/{catelogId}")
    public R list(@RequestParam Map<String, Object> params,
                  @PathVariable("catelogId") Long catelogId,
                  @PathVariable("attrType") String attrType) {
        PageUtils page = attrService.queryByCid(params, catelogId, attrType);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId) {
        AttrResVo attr = attrService.getAttrInfo(attrId);
        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrVo attr) {
        attrService.saveVo(attr);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrVo attr) {
        attrService.updateVo(attr);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrIds) {
        attrService.removeByIds(Arrays.asList(attrIds));
        return R.ok();
    }

}
