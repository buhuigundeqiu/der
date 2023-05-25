//package com.atball.der.search.service.impl;
//
//import com.atball.der.search.service.AccountService;
//import lombok.extern.slf4j.Slf4j;
//import org.dromara.hmily.annotation.Hmily;
//import org.dromara.hmily.core.concurrent.threadlocal.HmilyTransactionContextLocal;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Slf4j
//public class AccountServiceImpl implements AccountService {
//
//
//    /**
//     * try方法执行逻辑：
//     * 1.try幂等校验
//     * 2.try悬挂处理
//     * 3.检查余额是否足够扣减
//     * 4.扣减金额
//     *
//     * @param from_accountNo 转出账户
//     * @param to_accountNo   转入账户
//     * @param amount         转账的金额
//     */
//    @Transactional
//    //只要标记@Hmily就是try方法，在注解中指定confirm、cancel两个方法的名字
//    @Hmily(confirmMethod = "commit", cancelMethod = "rollback")
//    @Override
//    public void transfer(String from_accountNo, String to_accountNo, Double amount) {
//
//        // 获取全局事务Id
//        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
//        log.info("bank1 try begin 开始执行...xid:{}", transId);
//        //幂等判断 判断local_transaction_log表中是否有try日志记录，如果有则不再执行
//
//
//
//    }
//}
