package com.epicc.ganesha.asset.service.impl;

import com.epicc.ganesha.asset.service.AssetService;
import com.epicc.ganesha.common.vo.Result;
import org.springframework.stereotype.Service;

/**
 * Description: 资产管理服务实现（传统方式）
 * Author: lishangmin
 * Created: 2018-05-22 14:51
 */
@Service
public class AssetServiceImpl implements AssetService{

    /**
     * 发行资产接口
     *
     * @param name        资产名称
     * @param unit        资产单位
     * @param unitCode    资产单位（Code）
     * @param amount      资产总额（整数）
     * @param type        资产类型
     * @param description 资产描述
     * @param expires     资产过期时间（单位：秒）
     */
    @Override
    public Result issue(String name, String unit, String unitCode, Long amount, String type, String description, Long expires) {
        return null;
    }

    /**
     * 增发资产接口
     *
     * @param assetCode 资产代码
     * @param amount    增发数量
     */
    @Override
    public Result addIssue(String assetCode, Long amount) {
        return null;
    }

    /**
     * 转移资产（转账）
     *
     * @param assetCode 资产代码
     * @param account   转移账户
     */
    @Override
    public Result send(String assetCode, String account) {
        return null;
    }

    /**
     * 查询资产详情
     *
     * @param assetCode 资产代码
     */
    @Override
    public Result assetDetail(String assetCode) {
        return null;
    }

    /**
     * 查询账户资产详情
     *
     * @param account 账户
     */
    @Override
    public Result detail(String account) {
        return null;
    }

    /**
     * 查询账户资产交易记录
     *
     * @param account 账户
     * @param page    页码
     * @param length  长度
     */
    @Override
    public Result transactionRecord(String account, Integer page, Integer length) {
        return null;
    }
}
