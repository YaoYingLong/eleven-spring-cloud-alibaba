package com.eleven.icode.servicetest;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;
import rx.Observable;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * @author by YingLong on 2021/10/18
 */
public class RibbonDemo {

    public static void main(String[] args) {
        List<Server> serverList = Arrays.asList(
                new Server("localhost", 8020),
                new Server("localhost", 8021));

        ILoadBalancer loadBalancer = LoadBalancerBuilder.newBuilder()
                .buildFixedServerListLoadBalancer(serverList);

        for (int i = 0; i < 5; i++) {
            String result = LoadBalancerCommand.<String>builder()
                    .withLoadBalancer(loadBalancer).build()
                    .submit(new ServerOperation<String>() {
                        @Override
                        public Observable<String> call(Server server) {
                            String addr = "http://" + server.getHostPort() + "/order/findOrderByUserId/1";
                            System.out.println("调用地址：" + addr);
                            URL url = null;
                            try {
                                url = new URL(addr);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setRequestMethod("GET");
                                conn.connect();
                                InputStream in = conn.getInputStream();
                                byte[] bytes = new byte[in.available()];
                                in.read(bytes);
                                return Observable.just(new String(bytes));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                    }).toBlocking().first();
            System.out.println("调用结果：" + result);
        }
    }

}
