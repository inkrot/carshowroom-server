package com.mera.inkrot.carshowroom.grpc.impl;

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;
import com.mera.inkrot.carshowroom.dto.CustomerDto;
import com.mera.inkrot.carshowroom.dto.OptionDto;
import com.mera.inkrot.carshowroom.dto.OrderDto;
import com.mera.inkrot.carshowroom.grpc.gen.GRpcOrder;
import com.mera.inkrot.carshowroom.grpc.gen.GRpcOrderSaveResponse;
import com.mera.inkrot.carshowroom.grpc.gen.GRpcOrderService;
import com.mera.inkrot.carshowroom.service.OrderService;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Set;
import java.util.stream.Collectors;

@GRpcService
public class GRpcOrderServiceImpl extends GRpcOrderService {

    private static final Logger logger = LoggerFactory.getLogger(GRpcOrderServiceImpl.class);

    @Autowired
    OrderService orderService;

    @Override
    public void save(RpcController controller, GRpcOrder request, RpcCallback<GRpcOrderSaveResponse> done) {
        logger.info("GRpc save: " + request.toString());
        Set<OptionDto> options = request.getOptionsList().stream()
                .map(requestOption -> new OptionDto(requestOption.getId()))
                .collect(Collectors.toSet());
        orderService.save(new OrderDto(
                new CustomerDto(request.getCustomer().getName()),
                request.getModelName(),
                request.getBrandName(),
                options));
    }
}