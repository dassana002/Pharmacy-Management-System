package lk.ijse.pharmacymanagementsystem.model;

import lk.ijse.pharmacymanagementsystem.dbConnection.DBConnection;
import lk.ijse.pharmacymanagementsystem.dto.order.OrderDTO;
import lk.ijse.pharmacymanagementsystem.dto.order.OrderDetailDTO;
import lk.ijse.pharmacymanagementsystem.utility.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderModel {

    private final OrderDetailModel orderDetailModel = new OrderDetailModel();
    private final BatchModel batchModel = new BatchModel();

    public boolean placeOrder(OrderDTO orderDTO, OrderDetailDTO orderDetailDTO) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();

        try {
            conn.setAutoCommit(false);

            String query = "INSERT INTO orders (\n" +
                    "    order_id,\n" +
                    "    date,\n" +
                    "    status,\n" +
                    "    customer_NIC,\n" +
                    "    employee_id,\n" +
                    ") VALUES (?, ?, ?, ?, ?)";
            boolean isOrder = CrudUtil.execute(
                    query,
                    orderDTO.getOrder_id(),
                    orderDTO.getDate(),
                    orderDTO.getStatus().toString(),
                    orderDTO.getCustomer_NIC(),
                    orderDTO.getEmployee_id()
                    );
            if (!isOrder) {
                conn.rollback();
                return false;
            }

            boolean isSaveOrderDetail = orderDetailModel.save(orderDetailDTO);
            if (!isSaveOrderDetail) {
                conn.rollback();
                return false;
            }

            boolean isDecrementBatch = batchModel.decrementBatch(orderDetailDTO.getBatch_id(), orderDetailDTO.getQty());
            if (!isDecrementBatch) {
                conn.rollback();
                return false;
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            conn.rollback();
            throw new RuntimeException(e);

        } finally {
            conn.setAutoCommit(true);
        }
    }
}
