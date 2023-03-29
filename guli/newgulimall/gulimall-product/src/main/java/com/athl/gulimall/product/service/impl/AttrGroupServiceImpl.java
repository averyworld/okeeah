package com.athl.gulimall.product.service.impl;

import com.athl.common.constant.ProductConstant;
import com.athl.common.utils.PageUtils;
import com.athl.common.utils.Query;
import com.athl.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.athl.gulimall.product.dao.AttrDao;
import com.athl.gulimall.product.dao.AttrGroupDao;
import com.athl.gulimall.product.dao.CategoryDao;
import com.athl.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.athl.gulimall.product.entity.AttrEntity;
import com.athl.gulimall.product.entity.AttrGroupEntity;
import com.athl.gulimall.product.entity.vo.AttrGroupAndAttrVo;
import com.athl.gulimall.product.entity.vo.AttrGroupRelationVo;
import com.athl.gulimall.product.service.AttrGroupService;
import com.athl.gulimall.product.service.AttrService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Resource
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Resource
    private AttrDao attrDao;

    @Resource
    private CategoryDao categoryDao;

    @Resource
    private AttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 根据条件和catelogId分页查询分组信息
     *
     * @param params
     * @param catelogId
     * @return
     */
    @Override
    public PageUtils queryPageByCid(Map<String, Object> params, Long catelogId) {
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("attr_group_id", key).or().like("attr_group_name", key);
            });
        }
        //如果传过来的id是0，则查询所有属性
        //this.page两个参数，第一个参数是查询页码信息，其中Query.getPage方法传入一个map，会自动封装成IPage
        //第二个参数是查询条件，空的wapper就是查询全部
        if (catelogId == 0) {
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params), wrapper);
            return new PageUtils(page);
        } else {
            wrapper.eq("catelog_id", catelogId);
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params),
                    wrapper);
            return new PageUtils(page);
        }
    }

    /**
     * 根据attrGroupId 查询attr
     *
     * @param attrgroupId
     * @return
     */
    @Override
    public List<AttrEntity> getAttrByAttrGroupId(Long attrgroupId) {
        List<AttrAttrgroupRelationEntity> relationEntityList = attrAttrgroupRelationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>()
                .eq("attr_group_id", attrgroupId));
        List<Long> attrIdList = relationEntityList.stream().map((attrAttrgroupRelationEntity) -> {
            Long attrId = attrAttrgroupRelationEntity.getAttrId();
            return attrId;
        }).collect(Collectors.toList());
        List<AttrEntity> attrEntities = null;
        if (attrIdList != null && attrIdList.size() > 0) {
            attrEntities = attrDao.selectBatchIds(attrIdList);
        }
        return attrEntities;
    }

    /**
     * 根据attrId和attrGroupId删除关联
     *
     * @param attrGroupRelationVo
     */
    @Override
    public void deleteRelation(AttrGroupRelationVo[] attrGroupRelationVo) {
        List<AttrAttrgroupRelationEntity> entityList = Arrays.stream(attrGroupRelationVo).map((item) -> {
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(item, attrAttrgroupRelationEntity);
            return attrAttrgroupRelationEntity;
        }).collect(Collectors.toList());
        attrAttrgroupRelationDao.deleteBathRelations(entityList);
    }

    /**
     * 获取当前分组未关联的属性
     *
     * @param attrgroupId
     * @param params
     * @return
     */
    @Override
    public PageUtils getNoRelation(Long attrgroupId, Map<String, Object> params){
        //1、当前分组只能关联自己所属分类里面的所有属性
        AttrGroupEntity attrGroupEntity = baseMapper.selectById(attrgroupId);
        //1.1根据当前分组的信息获取当前分类的id
        Long catelogId = attrGroupEntity.getCatelogId();
        //2当前分组只能关联别的分组没有关联的属性
        //2.1 先找到当前分类（catelogId）下其他分组
        List<AttrGroupEntity> groupEntityList = baseMapper.selectList(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));
        List<Long> collect = groupEntityList.stream().map(item -> {
            return item.getAttrGroupId();
        }).collect(Collectors.toList());
        //2.2 再找到这些分组关联的属性
        QueryWrapper<AttrAttrgroupRelationEntity> wrapper = new QueryWrapper<>();
        if (collect != null && groupEntityList.size() > 0) {
            wrapper.in("attr_group_id", collect);
        }
        List<AttrAttrgroupRelationEntity> relationEntityList = attrAttrgroupRelationDao.selectList(wrapper);
        List<Long> attrIds = relationEntityList.stream().map(item -> {
            return item.getAttrId();
        }).collect(Collectors.toList());
        //2.3 从当前的分类中移除这些其他分组的属性
        QueryWrapper<AttrEntity> wrapper1 = new QueryWrapper<AttrEntity>().eq("catelog_id", catelogId).eq("attr_type", ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode());
        if (attrIds!=null &&attrIds.size()>0){
            wrapper1.notIn("attr_id",attrIds);
        }
        //模糊查询的时候
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)) {
            wrapper1.and((x)->{
                x.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage page = attrService.page(new Query<AttrEntity>().getPage(params), wrapper1);
        return new PageUtils(page);
    }

    @Override
    public List<AttrGroupAndAttrVo> getGroupAndAttr(Long catelogId) {
        List<AttrGroupEntity> groupEntityList = baseMapper.selectList(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));
        List<AttrGroupAndAttrVo> attrVoList = new ArrayList<>(100);
        for (int i = 0; i < groupEntityList.size(); i++) {
            AttrGroupEntity attrGroupEntity = groupEntityList.get(i);
            List<AttrAttrgroupRelationEntity> attrAttrgroupRelationEntityList = attrAttrgroupRelationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>()
                    .eq("attr_group_id", attrGroupEntity.getAttrGroupId()));
            List<Long> attrIds = attrAttrgroupRelationEntityList.stream().map(item -> {
                return item.getAttrId();
            }).collect(Collectors.toList());
            List<AttrEntity> attrEntities = null;
            if (attrIds != null && attrIds.size() > 0) {
                attrEntities = attrDao.selectBatchIds(attrIds);
            }
            AttrGroupAndAttrVo attrGroupAndAttrVo = new AttrGroupAndAttrVo();
            BeanUtils.copyProperties(attrGroupEntity, attrGroupAndAttrVo);
            attrGroupAndAttrVo.setAttrs(attrEntities);
            attrVoList.add(attrGroupAndAttrVo);
        }
        return attrVoList;
    }
}