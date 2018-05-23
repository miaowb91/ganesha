package com.epicc.ganesha.asset.service;

import com.epicc.ganesha.common.vo.Result;

/**
 * Description: 资产管理服务层接口
 * Author: lishangmin
 * Created: 2018-05-22 14:32
 */
public interface AssetService {
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
    Result issue(String name, String unit, String unitCode, Long amount, String type,String description,Long expires);

    /**
     * 增发资产接口
     *
     * @param assetCode 资产代码
     * @param amount    增发数量
     */
    Result addIssue(String assetCode, Long amount);

    /**
     * 转移资产（转账）
     *
     * @param assetCode 资产代码
     * @param account   转移账户
     */
    Result send(String assetCode, String account);

    /**
     * 查询资产详情
     * @param assetCode 资产代码
     */
    Result assetDetail(String assetCode);

    /**
     * 查询账户资产详情
     *
     * @param account 账户
     */
    Result detail(String account);

    /**
     * 查询账户资产交易记录
     *
     * @param account  账户
     * @param page     页码
     * @param length   长度
     */
    Result transactionRecord(String account, Integer page, Integer length);

}