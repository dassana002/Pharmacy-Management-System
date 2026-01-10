package lk.ijse.pharmacymanagementsystem.model;

import lk.ijse.pharmacymanagementsystem.dto.order.OrderDetailDTO;
import lk.ijse.pharmacymanagementsystem.utility.CrudUtil;

import java.sql.SQLException;

public class OrderDetailModel {

    public boolean save(OrderDetailDTO orderDetailDTO) throws SQLException {
        String query = "INSERT INTO order_detail (\n" +
                "    orderDetail_id,\n" +
                "    order_id,\n" +
                "    batch_id,\n" +
                "    qty,\n" +
                "    price,\n" +
                "    subTotal\n" +
                ") VALUES (?, ?, ?, ?, ?, ?)\n";
        return CrudUtil.execute(
                query,
                orderDetailDTO.getOrderDetail_id(),
                orderDetailDTO.getOrder_id(),
                orderDetailDTO.getBatch_id(),
                orderDetailDTO.getQty(),
                orderDetailDTO.getPrice(),
                orderDetailDTO.getSubTotal());
    }
}
