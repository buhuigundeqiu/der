package com.atball.der.search;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DerSearchApplicationTests {

	public volatile boolean setThreadStop = false;


	@Autowired
	private RestHighLevelClient client;

	@Test
	public void contextLoads() {
		String s1 = new String("a") + new String("b");
		String s2 = "ab";//②

		s1.intern();//①
		System.out.println(s1 == s2);

		System.out.println(client);


		String s3 = new StringBuilder("c").append("d").toString();
		String s4 = "cd";
		System.out.println(s3 == s4);
	}

	@Test
	public void test() throws InterruptedException {
		new Thread(() -> {
			while (!setThreadStop) {

			}
			System.out.println("线程结束");
		}).start();
		Thread.sleep(1000);

		setThreadStop = true;
		System.out.println("已设置 setThreadStop = true");
	}


	ReentrantLock lock = new ReentrantLock();
	//创建两个回家条件：需要钱、需要票
	Condition moneyCondition = lock.newCondition();
	Condition ticketCondition = lock.newCondition();
	public boolean havaMoney = false;//是否有钱
	public boolean haveTicket = false;//是否有票

	@Test
	public void test1() throws InterruptedException {


		//线程farmer1
		new Thread(() -> {
			log.info("--------第一个农民想回家--------");
			while (!havaMoney) {
				try {
					lock.lock();
					System.out.println(havaMoney);
					log.info("---------第一个农民没有钱，回不去家了，等钱---------");
					moneyCondition.await();
					System.out.println(havaMoney);

					log.info("第一个农民回家了");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}

		}, "farmer1").start();

		//线程farmer2
		new Thread(() -> {
			log.info("--------第二个农民想回家--------");
			while (!haveTicket) {
				try {
					System.out.println(haveTicket);
					lock.lock();
					log.info("---------第二个农民没有票，回不去家了，等票---------");
					ticketCondition.await();
					System.out.println(haveTicket);
					log.info("第二个农民回家了");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}, "farmer2").start();

		//主线程12306睡一会
		Thread.sleep(1000);
		try {
			lock.lock();
			havaMoney = true;
			log.info("---------第一个农民有钱了，唤醒他可以回家了---------");
			moneyCondition.signal();//唤醒等钱的farm1线程执行回家。

			haveTicket = true;
			log.info("---------第二个农民有票了，唤醒他可以回家了---------");
			ticketCondition.signal();//唤醒等票的farm2线程执行回家。
		} catch (
				Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

}